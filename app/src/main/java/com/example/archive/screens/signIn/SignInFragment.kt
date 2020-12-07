package com.example.archive.screens.signIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.archive.R
import com.example.archive.screens.signIn.SignInFragmentDirections
import com.example.archive.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_sign_in,
                container,
                false)

        binding.signInButton.setOnClickListener(onClickListener)
        binding.signUpButton.setOnClickListener(onClickListener)

        return binding.root
    }


    private val onClickListener = View.OnClickListener { view: View ->
        when (view.id){
            binding.signInButton.id -> view.findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())

            binding.signUpButton.id -> view.findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
        }
    }

}