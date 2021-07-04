package com.simgesengun.jellypinapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.simgesengun.jellypinapplication.R
import com.simgesengun.jellypinapplication.databinding.CampaignCardDesignBinding
import com.simgesengun.jellypinapplication.entity.Campaign
import com.simgesengun.jellypinapplication.entity.Item
import com.simgesengun.jellypinapplication.fragment.CampaignsFragmentDirections
import com.simgesengun.jellypinapplication.fragment.HomepageFragmentDirections
import com.simgesengun.jellypinapplication.fragment.ItemDetailsFragment
import com.simgesengun.jellypinapplication.viewmodel.CampaignsViewModel


class CampaignAdapter( var campaignsList: List<Campaign>) : RecyclerView.Adapter<CampaignAdapter.CampaignCardDesignHolder>() {

    inner class CampaignCardDesignHolder(cardDesignBinding: CampaignCardDesignBinding) : RecyclerView.ViewHolder(
            cardDesignBinding.root
    ){
        var design : CampaignCardDesignBinding
        init{
            this.design = cardDesignBinding
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampaignAdapter.CampaignCardDesignHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val design = CampaignCardDesignBinding.inflate(layoutInflater,parent,false)
        return CampaignCardDesignHolder(design)
    }

    override fun onBindViewHolder(holder: CampaignCardDesignHolder, position: Int) {
        val campaign = campaignsList[position]

        holder.design.campaign = campaign
        holder.design.imageViewCampaign.setOnClickListener {
            val nav = CampaignsFragmentDirections.campaignToCampaignDetails(campaign)
            Navigation.findNavController(it).navigate(nav)
        }
    }
    override fun getItemCount(): Int = campaignsList.size


}