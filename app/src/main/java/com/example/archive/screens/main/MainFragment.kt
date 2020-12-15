package com.example.archive.screens.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.archive.R
import com.example.archive.database.ArchiveDatabase
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

        val application = requireNotNull(this.activity).application

        val database = ArchiveDatabase.getInstance(application)

        val viewModelFactory = MainViewModelFactory(arguments.username, database, application)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        binding.mainViewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.navigateToProfile.observe(viewLifecycleOwner, Observer { username: String? ->
            username?.let{
                this.findNavController().navigate(MainFragmentDirections.actionMainFragmentToProfileFragment(username))
                viewModel.doneNavigateToProfile()
            }
        })

        viewModel.navigateToCheckingReq.observe(viewLifecycleOwner, Observer { username: String? ->
            username?.let{
                this.findNavController().navigate(MainFragmentDirections.actionMainFragmentToCheckingRequestsFragment(username))
                viewModel.doneNavigateToCheckingReq()
            }
        })

        //binding.clientReportBtn.setOnClickListener(onClickListener)
        binding.requestDocBtn.setOnClickListener(onClickListener)
        //binding.workReportBtn.setOnClickListener(onClickListener)
        binding.exitBtn.setOnClickListener(onClickListener)

        return binding.root
    }

    private val onClickListener = View.OnClickListener { view: View ->
        when (view.id){
            //binding.clientReportBtn.id, binding.workReportBtn.id -> view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToReportFragment())

            binding.requestDocBtn.id -> view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToGetDocumentFragment(arguments.username))


            binding.exitBtn.id -> {
                view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToSignInFragment())
            }
        }
    }


}