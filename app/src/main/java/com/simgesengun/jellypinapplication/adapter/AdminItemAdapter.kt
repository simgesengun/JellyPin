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
import com.simgesengun.jellypinapplication.databinding.AdminCardDesignBinding
import com.simgesengun.jellypinapplication.databinding.ItemCardDesignBinding
import com.simgesengun.jellypinapplication.entity.Item
import com.simgesengun.jellypinapplication.fragment.ItemDetailsFragment
import com.simgesengun.jellypinapplication.viewmodel.AllItemsViewModel
import com.simgesengun.jellypinapplication.viewmodel.HomepageViewModel


class AdminItemAdapter(
    var itemsList: List<Item>,
    var viewModel: AllItemsViewModel
) : RecyclerView.Adapter<AdminItemAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(cardDesignBinding: AdminCardDesignBinding) : RecyclerView.ViewHolder(
        cardDesignBinding.root
    ){
        var design : AdminCardDesignBinding
        init{
            this.design = cardDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminItemAdapter.CardDesignHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val design = AdminCardDesignBinding.inflate(layoutInflater, parent, false)
        return CardDesignHolder(design)
    }

    override fun onBindViewHolder(holder: AdminItemAdapter.CardDesignHolder, position: Int) {
        val item = itemsList[position]
        holder.design.item = item

        holder.design.isOnSale = item.is_on_sale
        holder.design.buttonSetSale.setOnClickListener {
            when(item.is_on_sale){
                0 -> viewModel.updateSaleItem(item.id,1)
                else -> viewModel.updateSaleItem(item.id,0)
            }
        }


    }

    override fun getItemCount(): Int = itemsList.size

}