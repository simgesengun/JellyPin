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
import androidx.navigation.fragment.navArgs
import com.simgesengun.jellypinapplication.LoginActivity
import com.simgesengun.jellypinapplication.R
import com.simgesengun.jellypinapplication.adapter.ItemAdapter
import com.simgesengun.jellypinapplication.databinding.FragmentCampaignDetailsBinding
import com.simgesengun.jellypinapplication.viewmodel.CampaignDetailsViewModel
import com.simgesengun.jellypinapplication.viewmodel.HomepageViewModel

class CampaignDetailsFragment : Fragment() {

    private lateinit var design : FragmentCampaignDetailsBinding
    private lateinit var viewModel : CampaignDetailsViewModel
    private lateinit var adapter : ItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_campaign_details, container, false)

        (activity as AppCompatActivity).setSupportActionBar(design.toolbar)

        val bundle : CampaignDetailsFragmentArgs by navArgs()
        val campaign = bundle.campaign
        design.campaign = campaign

        viewModel.saleItemsList.observe(viewLifecycleOwner){ saleItemsList ->
            adapter = ItemAdapter(requireContext(), saleItemsList, HomepageViewModel())
            design.adapter = adapter
        }

        viewModel.cartItemsList.observe(viewLifecycleOwner, { cartItemsList ->
            design.cartItemsListSize = cartItemsList.size
        })

        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp : CampaignDetailsViewModel by viewModels()
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