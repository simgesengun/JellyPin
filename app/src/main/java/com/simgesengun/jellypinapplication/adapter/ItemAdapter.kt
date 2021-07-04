package com.simgesengun.jellypinapplication.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.simgesengun.jellypinapplication.R
import com.simgesengun.jellypinapplication.databinding.ItemCardDesignBinding
import com.simgesengun.jellypinapplication.entity.Item
import com.simgesengun.jellypinapplication.fragment.ItemDetailsFragment
import com.simgesengun.jellypinapplication.viewmodel.HomepageViewModel


class ItemAdapter(
    var mContext: Context,
    var itemsList: List<Item>,
    var viewModel: HomepageViewModel
) : RecyclerView.Adapter<ItemAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(cardDesignBinding: ItemCardDesignBinding) : RecyclerView.ViewHolder(
        cardDesignBinding.root
    ){
        var design : ItemCardDesignBinding
        init{
            this.design = cardDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.CardDesignHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val design = ItemCardDesignBinding.inflate(layoutInflater, parent, false)
        return CardDesignHolder(design)
    }

    override fun onBindViewHolder(holder: ItemAdapter.CardDesignHolder, position: Int) {
        val item = itemsList[position]
        holder.design.item = item

        holder.design.imageViewCart.setOnClickListener {
            viewModel.updateCartItem(item.id, 1)
            val message =  mContext.resources.getString(R.string.added_to_cart, item.name)
            viewModel.loadCartItems()
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

        }
        holder.design.imageViewMore.setOnClickListener {
            val popup = androidx.appcompat.widget.PopupMenu(mContext, it)
            popup.menuInflater.inflate(R.menu.item_card_menu,popup.menu)
            popup.show()

            popup.setOnMenuItemClickListener { menuItem ->
                when(menuItem.itemId){
                    R.id.action_info -> {
                        toItemDetails(item)
                        true
                    }
                    else -> false
                }
            }
        }
        holder.design.itemCardView.setOnClickListener {
            toItemDetails(item)
        }


    }

    fun toItemDetails(item : Item){
        ItemDetailsFragment.newInstance(item).show(
                (mContext as AppCompatActivity).supportFragmentManager,
                ItemDetailsFragment.TAG
        )
    }

    override fun getItemCount(): Int = itemsList.size

}