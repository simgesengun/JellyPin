package com.simgesengun.jellypinapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.simgesengun.jellypinapplication.R
import com.simgesengun.jellypinapplication.databinding.FragmentRegisterBinding
import com.simgesengun.jellypinapplication.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {

    private lateinit var design : FragmentRegisterBinding
    private lateinit var viewModel : RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_register, container, false)

        design.registerViewModel = viewModel

        viewModel.success.observe(viewLifecycleOwner, { success ->
            when(success){
                1 -> registerSuccess()
            }
        })
        viewModel.nameSurnameError.observe(viewLifecycleOwner, { error ->
            design.nameSurnameError = error
        })
        viewModel.emailError.observe(viewLifecycleOwner, { error ->
            design.emailError = error
        })
        viewModel.passwordError.observe(viewLifecycleOwner, { error ->
            design.passwordError = error
        })
        viewModel.phoneNumberError.observe(viewLifecycleOwner, { error ->
            design.phoneNumberError = error
        })
        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp : RegisterViewModel by viewModels()
        viewModel = temp
    }

    fun registerSuccess(){
        Toast.makeText(requireContext(),requireContext().getString(R.string.register_success),Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.toLogin)
    }


}