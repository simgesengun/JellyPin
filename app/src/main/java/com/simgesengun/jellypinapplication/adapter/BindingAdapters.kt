package com.simgesengun.jellypinapplication.adapter

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.SharedPreferences
import android.graphics.*
import android.graphics.drawable.Drawable
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.palette.graphics.Palette
import androidx.preference.PreferenceManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.simgesengun.jellypinapplication.R
import com.simgesengun.jellypinapplication.entity.User


@BindingAdapter("android:image_url_resource")
fun imageUrlResource(imageView: ImageView, picture_url: String){
    val anim = ObjectAnimator.ofFloat(
        imageView, "alpha", 0.7F, 1.0F
    )
    anim.duration = 2000
    anim.startDelay = 20
    anim.repeatMode = ValueAnimator.REVERSE
    anim.repeatCount = Animation.INFINITE
    anim.start()


    Glide.with(imageView.context).load(picture_url).placeholder(R.drawable.skeleton).listener(object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            e?.logRootCauses("glide")
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            anim.cancel()
            imageView.alpha = 1.0F
            return false
        }

    }).into(imageView)



}
@BindingAdapter("android:set_visibility")
fun setVisibility(floatingActionButton: FloatingActionButton, isAdmin: Int){
    when(isAdmin){
        0 -> floatingActionButton.visibility = View.GONE
        1 -> floatingActionButton.visibility = View.VISIBLE
    }
}

@BindingAdapter("android:sale_visibility")
fun saleVisibility(view: View, is_on_sale: Int){
    when(is_on_sale){
        0 -> view.visibility = View.GONE
        1 -> view.visibility = View.VISIBLE
    }
}
@BindingAdapter("android:set_toolbar_cart")
fun setToolbarCart(toolbar: androidx.appcompat.widget.Toolbar, cartItemsListSize: Int) {
    val cartItem = toolbar.menu.findItem(R.id.action_add_to_cart)
    when(cartItemsListSize){
        0 -> cartItem.setIcon(R.drawable.ic_toolbar_cart_empty)
        else -> cartItem.setIcon(R.drawable.ic_toolbar_cart_full)
    }
}
@BindingAdapter("android:profile_title_name","android:profile_title_level",requireAll = true)
fun nameExpText(textView: TextView, nameSurname: String, userLevel : Int){
    val str = textView.context.resources.getString(R.string.name_exp, nameSurname, userLevel)
    textView.text = str
}

@BindingAdapter("android:set_price")
fun setPrice(textView: TextView, price: String) {
    val priceDouble = price.toDouble()
    val str = textView.context.resources.getString(R.string.price, String.format("%.2f", priceDouble))
    textView.text = str
}

@BindingAdapter("android:set_cart_header")
fun setCartHeader(textView: TextView, cartListSize: Int) {
    var str : String
    var drawable : Drawable?
    when(cartListSize){
        0 -> {
            str = textView.context.resources.getString(R.string.cart_empty)
            drawable = ResourcesCompat.getDrawable(
                textView.context.resources,
                R.drawable.ic_cart_crying,
                null
            )
        }
        1 -> {
            str = textView.context.resources.getString(R.string.cart_one_item)
            drawable = ResourcesCompat.getDrawable(
                textView.context.resources,
                R.drawable.ic_cart_normal,
                null
            )
        }
        else -> {
            str = textView.context.resources.getString(R.string.cart_full, cartListSize)
            drawable = ResourcesCompat.getDrawable(
                textView.context.resources,
                R.drawable.ic_cart_happy,
                null
            )
        }
    }
    textView.text = str
    textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
}

@BindingAdapter("android:set_error")
fun setError(textInputLayout: TextInputLayout, resourceId: Int?) {
    when(resourceId){
        null -> textInputLayout.error = null
        else -> textInputLayout.error = textInputLayout.context.resources.getString(resourceId)
    }
}

@BindingAdapter("android:set_string_resource")
fun setStringResource(textView: TextView, resourceId: Int) {
    val str = textView.context.resources.getString(resourceId)
    textView.text = str
}

@BindingAdapter("android:set_campaign_description")
fun setCampaignDescription(textView: TextView, minLevel: Int) {
    val resources = textView.context.resources
    val str = resources.getString(R.string.campaign_min_level,minLevel)
    val underMinStr = resources.getString(R.string.campaign_under_min_level,str)
    val mPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(textView.context)
    val userLevel = mPrefs.getInt("userLevel", 0)
    if(userLevel < minLevel){
        textView.setTextColor(textView.context.getColor(R.color.red))
        textView.text = underMinStr
    }else{
        textView.text = str
    }
}

@BindingAdapter("android:set_sale_price")
fun setSalePrice(textView: TextView, price: String) {
    val newPrice = ( price.toDouble() * 0.8 )
    val str = textView.context.resources.getString(R.string.price, String.format("%.2f", newPrice))
    textView.text = str
}

@BindingAdapter("android:set_progress")
fun setProgress(textView: TextView, progress: Int) {
    val str = textView.context.resources.getString(R.string.jelly_points, progress)
    textView.text = str
}

@BindingAdapter("android:color_picker")
fun colorPicker(imageView: ImageView, picture_name: String){
    val id = imageView.context.resources.getIdentifier(
        picture_name,
        "drawable",
        imageView.context.packageName
    )

    val icon : Bitmap = BitmapFactory.decodeResource(imageView.context.resources, id)
    Palette.from(icon).generate { palette ->

        if (palette != null){

            val dominant = palette.dominantSwatch
            val darkMuted = palette.darkMutedSwatch
            val darkVibrant = palette.darkVibrantSwatch

            when {
                darkMuted!=null -> {
                    val filterColor = Color.argb(
                        255, Color.red(darkMuted.rgb), Color.green(darkMuted.rgb), Color.blue(
                            darkMuted.rgb
                        )
                    )
                    imageView.setColorFilter(filterColor, PorterDuff.Mode.SRC_ATOP)
                }
                darkVibrant != null -> {
                    val filterColor = Color.argb(
                        255, Color.red(darkVibrant.rgb), Color.green(
                            darkVibrant.rgb
                        ), Color.blue(darkVibrant.rgb)
                    )
                    imageView.setColorFilter(filterColor, PorterDuff.Mode.SRC_ATOP)
                }
                dominant != null -> {
                    val filterColor = Color.argb(
                        255, Color.red(dominant.rgb), Color.green(dominant.rgb), Color.blue(
                            dominant.rgb
                        )
                    )
                    imageView.setColorFilter(filterColor, PorterDuff.Mode.SRC_ATOP)
                }
            }

        }
    }
}