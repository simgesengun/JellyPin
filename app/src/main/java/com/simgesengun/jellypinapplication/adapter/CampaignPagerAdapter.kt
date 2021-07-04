package com.simgesengun.jellypinapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.simgesengun.jellypinapplication.R
import com.simgesengun.jellypinapplication.databinding.*
import com.simgesengun.jellypinapplication.entity.Campaign
import com.simgesengun.jellypinapplication.entity.Item
import com.simgesengun.jellypinapplication.fragment.HomepageFragmentDirections
import com.simgesengun.jellypinapplication.fragment.ItemDetailsFragment
import com.simgesengun.jellypinapplication.viewmodel.CampaignsViewModel


class CampaignPagerAdapter( var campaignsList: List<Campaign>) : RecyclerView.Adapter<CampaignPagerAdapter.CampaignCardDesignHolder>() {

    inner class CampaignCardDesignHolder(cardDesignBinding: CampaignPagerDesignBinding) : RecyclerView.ViewHolder(
            cardDesignBinding.root
    ){
        var design : CampaignPagerDesignBinding
        init{
            this.design = cardDesignBinding
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampaignPagerAdapter.CampaignCardDesignHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val design = CampaignPagerDesignBinding.inflate(layoutInflater,parent,false)
        return CampaignCardDesignHolder(design)
    }

    override fun onBindViewHolder(holder: CampaignCardDesignHolder, position: Int) {
        val campaign = campaignsList[position]

        holder.design.campaign = campaign
        holder.design.imageViewCampaign.setOnClickListener {
            val nav = HomepageFragmentDirections.homeToCampaignDetails(campaign)
            Navigation.findNavController(it).navigate(nav)
        }
    }
    /* override fun onBindViewHolder(holder: CampaignAdapter.CampaignCardDesignHolder, position: Int) {
         val campaign = campaingsList[position]

         holder.design.item = item
         holder.design.imageViewCart.setOnClickListener {
             viewModel.updateCartItem(item.id, 1)
             val message =  mContext.resources.getString(R.string.added_to_cart, item.name)
             viewModel.loadCartItems()
             Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

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
     }*/

    override fun getItemCount(): Int = campaignsList.size


}