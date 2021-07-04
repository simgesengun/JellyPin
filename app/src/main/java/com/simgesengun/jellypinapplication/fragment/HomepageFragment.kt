package com.simgesengun.jellypinapplication.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.simgesengun.jellypinapplication.LoginActivity
import com.simgesengun.jellypinapplication.R
import com.simgesengun.jellypinapplication.adapter.CampaignPagerAdapter
import com.simgesengun.jellypinapplication.adapter.GameAdapter
import com.simgesengun.jellypinapplication.databinding.FragmentHomepageBinding
import com.simgesengun.jellypinapplication.entity.*
import com.simgesengun.jellypinapplication.viewmodel.HomepageViewModel


class HomepageFragment : Fragment() {
    private lateinit var design : FragmentHomepageBinding
    private lateinit var gamesList : ArrayList<Game>
    private lateinit var campaignsList : ArrayList<Campaign>
    private lateinit var viewModel : HomepageViewModel
    private lateinit var gameAdapter : GameAdapter
    private lateinit var campaignPagerAdapter : CampaignPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_homepage, container, false)
        gamesList = createGamesList()
        campaignsList = createCampaignsList()

        (activity as AppCompatActivity).setSupportActionBar(design.toolbar)

        checkUser()
        design.homepageFragment = this

        // ViewPager Campaigns
        design.viewPagerCampaigns.setPageTransformer(DepthPageTransformer())

        campaignPagerAdapter = CampaignPagerAdapter(campaignsList)
        design.campaignPagerAdapter = campaignPagerAdapter

        val countDownTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                val currentItem = design.viewPagerCampaigns.currentItem
                design.viewPagerCampaigns.currentItem = (currentItem + 1) % campaignsList.size
                start()
            }
        }
        countDownTimer.start()

        // ViewPager Games
        viewModel.itemsList.observe(viewLifecycleOwner, { itemsList ->
            gameAdapter = GameAdapter(requireContext(), gamesList, itemsList, viewModel)
            design.viewPager.adapter = gameAdapter

            TabLayoutMediator(design.tabLayout, design.viewPager, true) { tab, position ->
                tab.text = gamesList[position].name
            }.attach()
        })

        viewModel.cartItemsList.observe(viewLifecycleOwner, { cartItemsList ->
            design.cartItemsListSize = cartItemsList.size
        })


        return design.root
    }


    private fun checkUser(){
        val mPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        val gson = Gson()
        val jsonUser : String? = mPrefs.getString("user", "")
        val user =  gson.fromJson(jsonUser, User::class.java)
        when(user.mail_address){
            "simgesngn@gmail.com" -> design.isAdmin = 1
            else -> design.isAdmin = 0
        }
    }

    fun fabAddOnClick(){
        AddItemFragment().show(childFragmentManager, AddItemFragment.TAG)
    }
    fun fabAllItemsClick(){
        AllItemsFragment().show(childFragmentManager, AllItemsFragment.TAG)
    }

    fun createGamesList() : ArrayList<Game> {
        return arrayListOf(
            Game(0, "Destiny 2", "https://www.linkpicture.com/q/game_destiny2.jpg", "game_destiny2"),
            Game(1, "Smite", "https://www.linkpicture.com/q/game_smite.jpg", "game_smite"),
            Game(2, "Black Desert", "https://www.linkpicture.com/q/game_blackdesert.jpg", "game_blackdesert")
        )
    }

    fun createCampaignsList() : ArrayList<Campaign> {
        return arrayListOf(
                Campaign(R.string.black_friday, "https://i.imgur.com/o73ajLO.jpg",R.string.black_friday_desc,1),
                Campaign(R.string.double_trouble, "https://i.imgur.com/8payfBP.jpg",R.string.double_trouble_desc,6)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp : HomepageViewModel by viewModels()
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
                Navigation.findNavController(design.toolbar).navigate(R.id.homeToCart)
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