package com.simgesengun.jellypinapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.simgesengun.jellypinapplication.MainActivity
import com.simgesengun.jellypinapplication.R
import com.simgesengun.jellypinapplication.databinding.FragmentLoginBinding
import com.simgesengun.jellypinapplication.entity.User
import com.simgesengun.jellypinapplication.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var design : FragmentLoginBinding
    private lateinit var viewModel : LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false)

        viewModel.loginResponse.observe(viewLifecycleOwner, {loginResponse ->
            if(loginResponse != null){
                saveUser(loginResponse)
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }else{
                Toast.makeText(requireContext(), "Invalid email or password",Toast.LENGTH_SHORT).show()
            }
        })

        design.loginFragment = this

        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp : LoginViewModel by viewModels()
        viewModel = temp
    }

    fun buttonLogin(mail_address : String, password : String){
        viewModel.loginUser(mail_address, password)
    }

    fun buttonSignUp(view : View){
        Navigation.findNavController(view).navigate(R.id.toRegister)
    }

    fun saveUser(user : User){
        val mPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val prefsEditor = mPrefs.edit()
        val gson = Gson()
        val jsonUser = gson.toJson(user)
        prefsEditor.putString("user",jsonUser).apply()
        prefsEditor.putInt("userPoints",0).apply()
        prefsEditor.putInt("userLevel",5).apply()
    }
}