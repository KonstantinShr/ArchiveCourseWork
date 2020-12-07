package com.example.archive.screens.signUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.archive.R
import com.example.archive.screens.signUp.SignUpFragmentDirections
import com.example.archive.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSignUpBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_sign_up,
            container,
            false)

        binding.signUpBtn.setOnClickListener { view: View ->
            view.findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToMainFragment())
        }
        return binding.root
    }
}