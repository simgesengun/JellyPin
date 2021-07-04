package com.simgesengun.jellypinapplication.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.simgesengun.jellypinapplication.LoginActivity
import com.simgesengun.jellypinapplication.R
import com.simgesengun.jellypinapplication.databinding.FragmentProfileBinding
import com.simgesengun.jellypinapplication.entity.User
import com.simgesengun.jellypinapplication.viewmodel.ProfileViewModel


class ProfileFragment : Fragment() {

    private lateinit var design : FragmentProfileBinding
    private lateinit var user :User
    private lateinit var viewModel : ProfileViewModel
    private var userPoints : Int = 0
    private var userLevel : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        (activity as AppCompatActivity).setSupportActionBar(design.toolbar)

        user = getUser()
        userPoints = getUserPoints()
        userLevel = getUserLevel()

        design.title = getString(R.string.profile)
        design.user = user
        design.userPoints = userPoints
        design.userLevel = userLevel
        design.profileFragment = this

        viewModel.cartItemsList.observe(viewLifecycleOwner, { cartItemsList ->
            design.cartItemsListSize = cartItemsList.size
        })

        return design.root
    }

    fun info(view : View, games : String, gifts : String, jelly : String){
        val message = view.context.resources.getString(R.string.info_message,games,gifts,jelly)
        val alertDialogBuilder = AlertDialog.Builder(view.context, R.style.AlertDialogTheme)
        alertDialogBuilder.setTitle("Info")
            .setMessage(message)
            .setIcon(R.drawable.ic_info)
            .show()
    }

    fun getUser() : User {
        val mPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        val gson = Gson()
        val jsonUser : String? = mPrefs.getString("user", "")
        return gson.fromJson(jsonUser, User::class.java)
    }

    fun getUserPoints() : Int {
        val mPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        return mPrefs.getInt("userPoints", 0)
    }

    fun getUserLevel() : Int {
        val mPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        return mPrefs.getInt("userLevel", 0)
    }

    fun logout(){
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp : ProfileViewModel by viewModels()
        viewModel = temp

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        menu.findItem(R.id.action_profile).isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_to_cart -> {
                Navigation.findNavController(design.toolbar).navigate(R.id.profileToCart)
                true
            }
            else -> true
        }
    }
}