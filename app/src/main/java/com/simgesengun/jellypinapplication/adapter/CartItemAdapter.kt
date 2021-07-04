package com.simgesengun.jellypinapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.simgesengun.jellypinapplication.databinding.CartCardDesignBinding
import com.simgesengun.jellypinapplication.entity.Item
import com.simgesengun.jellypinapplication.fragment.ItemDetailsFragment
import com.simgesengun.jellypinapplication.viewmodel.CartViewModel

class CartItemAdapter(var cartItemsList : List<Item>,
                      var fm : FragmentManager,
                      var viewModel : CartViewModel) : RecyclerView.Adapter<CartItemAdapter.CartCardDesignHolder>() {

    inner class CartCardDesignHolder(cardDesignBinding: CartCardDesignBinding) : RecyclerView.ViewHolder(cardDesignBinding.root){
        var design : CartCardDesignBinding
        init{
            this.design = cardDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemAdapter.CartCardDesignHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val design = CartCardDesignBinding.inflate(layoutInflater,parent,false)
        return CartCardDesignHolder(design)
    }

    override fun onBindViewHolder(holder: CartItemAdapter.CartCardDesignHolder, position: Int) {
        val item = cartItemsList[position]
        holder.design.item = item
        holder.design.itemCartCardView.setOnClickListener {
            ItemDetailsFragment.newInstance(item).show(fm, ItemDetailsFragment.TAG)
        }
        holder.design.frameLayout2.setOnClickListener {
            viewModel.updateCartItem(item.id,0)
        }

    }

    override fun getItemCount(): Int = cartItemsList.size

}