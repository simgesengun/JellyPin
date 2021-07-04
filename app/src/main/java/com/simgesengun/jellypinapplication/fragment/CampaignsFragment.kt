package com.simgesengun.jellypinapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.simgesengun.jellypinapplication.LoginActivity
import com.simgesengun.jellypinapplication.R
import com.simgesengun.jellypinapplication.adapter.CampaignAdapter
import com.simgesengun.jellypinapplication.databinding.FragmentCampaignsBinding
import com.simgesengun.jellypinapplication.entity.Campaign
import com.simgesengun.jellypinapplication.viewmodel.CampaignsViewModel

class CampaignsFragment : Fragment() {
    private lateinit var design : FragmentCampaignsBinding
    private lateinit var viewModel : CampaignsViewModel
    private lateinit var campaignsList : ArrayList<Campaign>
    private lateinit var adapter : CampaignAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        design = DataBindingUtil.inflate(inflater,R.layout.fragment_campaigns, container, false)

        (activity as AppCompatActivity).setSupportActionBar(design.toolbar)

        campaignsList = createCampaignsList()

        design.title = getString(R.string.campaigns)
        design.adapter = CampaignAdapter(campaignsList)

        viewModel.cartItemsList.observe(viewLifecycleOwner, { cartItemsList ->
            design.cartItemsListSize = cartItemsList.size
        })

        return design.root
    }

    fun createCampaignsList() : ArrayList<Campaign> {
        return arrayListOf(
            Campaign(R.string.black_friday, "https://i.imgur.com/o73ajLO.jpg",R.string.black_friday_desc,1),
            Campaign(R.string.double_trouble, "https://i.imgur.com/8payfBP.jpg",R.string.double_trouble_desc,6)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp : CampaignsViewModel by viewModels()
        viewModel = temp

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_to_cart -> {
                Navigation.findNavController(design.toolbar).navigate(R.id.campaignsToCart)
                true
            }
            R.id.action_profile -> {
                val popup = PopupMenu(requireContext(), design.toolbar.findViewById(R.id.action_profile))
                popup.menuInflater.inflate(R.menu.toolbar_profile_menu, popup.menu)
                popup.show()

                popup.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.action_logout -> {
                            val intent = Intent(requireContext(), LoginActivity::class.java)
                            startActivity(intent)
                            requireActivity().finish()
                            true
                        }
                        else -> false
                    }
                }
                true
            }
            else -> true
        }
    }
}