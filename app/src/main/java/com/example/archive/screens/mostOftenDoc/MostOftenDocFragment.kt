package com.example.archive.screens.mostOftenDoc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.archive.R
import com.example.archive.database.ArchiveDatabase
import com.example.archive.databinding.FragmentMostOftenDocBinding
import com.example.archive.screens.profile.ProfileFragmentDirections
import com.example.archive.screens.profile.ProfileViewModel
import com.example.archive.screens.profile.ProfileViewModelFactory


class MostOftenDocFragment : Fragment() {

    private lateinit var binding: FragmentMostOftenDocBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_most_often_doc, container, false)

        val arguments = MostOftenDocFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application

        val database = ArchiveDatabase.getInstance(application)

        val viewModelFactory = MostOftenDocViewModelFactory(arguments.username, database, application)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(MostOftenDocViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToMain.observe(viewLifecycleOwner, Observer { username: String? ->
            username?.let{
                this.findNavController().navigate(MostOftenDocFragmentDirections.actionMostOftenDocFragmentToCheckingRequestsFragment(username))
                viewModel.doneNavigateToMain()
            }
        })

        return binding.root
    }

}