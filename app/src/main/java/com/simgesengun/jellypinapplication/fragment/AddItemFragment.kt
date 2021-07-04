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
import com.simgesengun.jellypinapplication.databinding.FragmentAddItemBinding
import com.simgesengun.jellypinapplication.entity.Item
import com.simgesengun.jellypinapplication.viewmodel.AddItemViewModel
import com.simgesengun.jellypinapplication.viewmodel.CampaignsViewModel
import com.simgesengun.jellypinapplication.viewmodel.HomepageViewModel

class AddItemFragment  : DialogFragment() {

    private lateinit var design : FragmentAddItemBinding
    private lateinit var viewModel : AddItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_add_item, container, false)

        design.addItemFragment = this

        return design.root
    }

    fun buttonAdd(name : String, price : String, description : String, pictureUrl : String){
        viewModel.addItem("200 Gems", "4.99","Smite - 200 Gems","https://i.imgur.com/g5MnWov.png")
        viewModel.addItem("400 Gems", "7.99","Smite - 400 Gems","https://i.imgur.com/KuQBcPe.png")
        viewModel.addItem("800 Gems", "14.99","Smite - 800 Gems","https://i.imgur.com/XfSxAL6.png")
        viewModel.addItem("2500 Gems", "34.99","Smite - 2500 Gems","https://i.imgur.com/YqbQiss.png")
        viewModel.addItem("3500 Gems", "49.99","Smite - 3500 Gems","https://i.imgur.com/kDrnOyk.png")
        viewModel.addItem("8000 Gems", "99.99","Smite - 8000 Gems","https://i.imgur.com/97oomIJ.png")
        viewModel.addItem("500 Silver", "24.99","Destiny 2 - 500 Silver","https://i.imgur.com/34xkXda.png")
        viewModel.addItem("1000 Silver", "49.99","Destiny 2 - 1000 Silver","https://i.imgur.com/waa5lKX.png")
        viewModel.addItem("3000 Silver", "99.99","Destiny 2 - 3000 Silver","https://i.imgur.com/ieD8xu4.png")
        viewModel.addItem("5000 Silver", "249.99","Destiny 2 - 5000 Silver","https://i.imgur.com/G0xZJb9.png")
        viewModel.addItem("100 Acoin", "11.80","Black Desert - 100 Acoin","https://i.imgur.com/IwwEFmo.png")
        viewModel.addItem("200 Acoin", "23.60","Black Desert - 200 Acoin","https://i.imgur.com/fxalvKv.png")
        viewModel.addItem("500 Acoin", "59.00","Black Desert - 500 Acoin","https://i.imgur.com/DYGLvjS.png")
        viewModel.addItem("2000 Acoin", "236.00","Black Desert - 2000 Acoin","https://i.imgur.com/ctDUBgV.png")
        viewModel.addItem("5000 Acoin", "590.00","Black Desert - 5000 Acoin","https://i.imgur.com/QTBpaL7.png")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogTheme_transparent)
        val temp : AddItemViewModel by viewModels()
        viewModel = temp

    }
    companion object{
        const val TAG = "AddItemFragment"
    }
}