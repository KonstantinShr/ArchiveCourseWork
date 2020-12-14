package com.example.archive.screens.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.archive.R
import com.example.archive.databinding.FragmentProfileBinding
import com.example.archive.screens.main.MainFragmentArgs

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var arguments: ProfileFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        arguments = ProfileFragmentArgs.fromBundle(requireArguments())

        binding.profileBackBtn.setOnClickListener(onClickListener)

        return binding.root
    }

    private val onClickListener = View.OnClickListener { view: View ->
        when (view.id){
            binding.profileBackBtn.id -> {
                Log.d("USERNAME FROM PROFILE", arguments.username.toString())
                view.findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToMainFragment(arguments.username))
            }
        }
    }
}