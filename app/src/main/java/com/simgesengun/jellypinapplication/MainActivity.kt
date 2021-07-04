package com.simgesengun.jellypinapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.simgesengun.jellypinapplication.databinding.ActivityMainBinding
import com.simgesengun.jellypinapplication.entity.User
import com.simgesengun.jellypinapplication.fragment.AddItemFragment

class MainActivity : AppCompatActivity() {

    private lateinit var design : ActivityMainBinding
    private lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        design = DataBindingUtil.setContentView(this, R.layout.activity_main)
        design.lifecycleOwner = this
        design.mainActivity = this

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        NavigationUI.setupWithNavController(design.bottomNav, navHostFragment.navController)
    }




}