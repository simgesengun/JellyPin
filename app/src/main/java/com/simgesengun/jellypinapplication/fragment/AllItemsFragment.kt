package com.simgesengun.jellypinapplication.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.simgesengun.jellypinapplication.R
import com.simgesengun.jellypinapplication.adapter.AdminItemAdapter
import com.simgesengun.jellypinapplication.adapter.ItemAdapter
import com.simgesengun.jellypinapplication.databinding.FragmentAddItemBinding
import com.simgesengun.jellypinapplication.databinding.FragmentAllItemsBinding
import com.simgesengun.jellypinapplication.entity.Item
import com.simgesengun.jellypinapplication.viewmodel.AddItemViewModel
import com.simgesengun.jellypinapplication.viewmodel.AllItemsViewModel
import com.simgesengun.jellypinapplication.viewmodel.CampaignsViewModel
import com.simgesengun.jellypinapplication.viewmodel.HomepageViewModel

class AllItemsFragment  : DialogFragment() {

    private lateinit var design : FragmentAllItemsBinding
    private lateinit var viewModel : AllItemsViewModel
    private lateinit var adapter : AdminItemAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_all_items, container, false)

        viewModel.itemsList.observe(viewLifecycleOwner,{ itemsList ->
            adapter = AdminItemAdapter(itemsList,viewModel)
            design.adapter = adapter
        })

        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogTheme_transparent)
        val temp : AllItemsViewModel by viewModels()
        viewModel = temp

    }
    companion object{
        const val TAG = "AllItemsFragment"
    }
}