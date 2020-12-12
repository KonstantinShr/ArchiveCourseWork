package com.example.archive.screens.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.archive.R
import com.example.archive.databinding.FragmentMainBinding
import com.example.archive.screens.signIn.SignInFragmentDirections

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var arguments: MainFragmentArgs

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        arguments = MainFragmentArgs.fromBundle(requireArguments())



        binding.clientReportBtn.setOnClickListener(onClickListener)
        binding.profileBtn.setOnClickListener(onClickListener)
        binding.requestDocBtn.setOnClickListener(onClickListener)
        binding.requestsBtn.setOnClickListener(onClickListener)
        binding.workReportBtn.setOnClickListener(onClickListener)
        binding.exitBtn.setOnClickListener(onClickListener)

        return binding.root
    }

    private val onClickListener = View.OnClickListener { view: View ->
        when (view.id){
            binding.clientReportBtn.id, binding.workReportBtn.id -> view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToReportFragment())

            binding.profileBtn.id -> view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToProfileFragment())

            binding.requestDocBtn.id -> view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToGetDocumentFragment())

            binding.requestsBtn.id -> view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToCheckingRequestsFragment())

            binding.exitBtn.id -> {
                Log.d("ARGUMENTS", arguments.username)
                view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToSignInFragment())
            }
        }
    }


}