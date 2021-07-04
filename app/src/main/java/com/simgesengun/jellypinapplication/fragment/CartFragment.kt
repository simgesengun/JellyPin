package com.simgesengun.jellypinapplication.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.simgesengun.jellypinapplication.LoginActivity
import com.simgesengun.jellypinapplication.R
import com.simgesengun.jellypinapplication.adapter.CartItemAdapter
import com.simgesengun.jellypinapplication.databinding.FragmentCartBinding
import com.simgesengun.jellypinapplication.viewmodel.CartViewModel

class CartFragment : Fragment() {

    private lateinit var design : FragmentCartBinding
    private lateinit var viewModel : CartViewModel
    private lateinit var adapter : CartItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        design = DataBindingUtil.inflate(inflater,R.layout.fragment_cart, container, false)

        (activity as AppCompatActivity).setSupportActionBar(design.toolbar)

        design.cartFragment = this
        design.title = getString(R.string.cart)

        viewModel.cartItemsList.observe(viewLifecycleOwner){ cartItemsList ->
            adapter = CartItemAdapter(cartItemsList, childFragmentManager, viewModel)
            design.adapter = adapter
            design.cartItemsListSize = cartItemsList.size
        }

        viewModel.totalPrice.observe(viewLifecycleOwner){ totalPrice ->
            design.totalPrice = totalPrice.toString()
        }

        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp : CartViewModel by viewModels()
        viewModel = temp


        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        menu.findItem(R.id.action_add_to_cart).isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
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
    fun checkOut(){
        val points = getUserPoints()
        val newPoints = viewModel.removeAllCartItems()
        saveUserPoints(points + newPoints)
        Toast.makeText(requireContext(),getString(R.string.new_points,newPoints,points+newPoints),Toast.LENGTH_SHORT).show()
    }

    fun getUserPoints() : Int{
        val mPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        return mPrefs.getInt("userPoints", 0)
    }

    fun saveUserPoints(points : Int){
        val mPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val prefsEditor = mPrefs.edit()
        prefsEditor.putInt("userPoints",points).apply()
    }
}