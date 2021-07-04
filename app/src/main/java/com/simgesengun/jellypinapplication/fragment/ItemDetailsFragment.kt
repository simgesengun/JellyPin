package com.simgesengun.jellypinapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.simgesengun.jellypinapplication.R
import com.simgesengun.jellypinapplication.databinding.FragmentItemDetailsBinding
import com.simgesengun.jellypinapplication.entity.Item
import com.simgesengun.jellypinapplication.repo.ItemDaoRepository
import com.simgesengun.jellypinapplication.viewmodel.HomepageViewModel
import com.simgesengun.jellypinapplication.viewmodel.ItemDetailsViewModel

class ItemDetailsFragment : DialogFragment() {

    private lateinit var design : FragmentItemDetailsBinding
    private lateinit var viewModel : ItemDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        design = DataBindingUtil.inflate(inflater,R.layout.fragment_item_details, container, false)

        arguments?.let {
            val item = it.getSerializable(ARG_ITEM) as Item
            design.item = item
        }

        design.itemDetailsFragment = this


        return design.root
    }

    fun addToCart(item : Item){
        Toast.makeText(requireContext(), requireContext().resources.getString(R.string.added_to_cart,item.name), Toast.LENGTH_SHORT).show()
        viewModel.updateCartItem(item.id,1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp : ItemDetailsViewModel by viewModels()
        viewModel = temp
        setStyle(STYLE_NO_TITLE, R.style.DialogTheme_transparent)
    }

    companion object{
        const val TAG = "ItemDetailsFragment"
        private const val ARG_ITEM = "argTask"

        fun newInstance(item : Item) = ItemDetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARG_ITEM, item)
            }
        }
    }
}