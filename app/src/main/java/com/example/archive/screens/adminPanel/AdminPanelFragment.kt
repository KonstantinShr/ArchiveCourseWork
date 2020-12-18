package com.example.archive.screens.adminPanel

import android.database.DatabaseUtils
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
import com.example.archive.databinding.FragmentAdminPanelBinding

class AdminPanelFragment : Fragment() {
    private lateinit var binding: FragmentAdminPanelBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_panel, container, false)

        val arguments = AdminPanelFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application

        val database = ArchiveDatabase.getInstance(application)

        val viewModelFactory = AdminPanelViewModelFactory(arguments.username, database, application)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(AdminPanelViewModel::class.java)

        binding.adminPanelViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToCheckingRequest.observe(viewLifecycleOwner, Observer { username: String? ->
            username?.let{
                this.findNavController().navigate(AdminPanelFragmentDirections.actionAdminPanelFragmentToCheckingRequestsFragment(username))
                viewModel.doneNavigateToCheckingRequest()
            }
        })

        viewModel.navigateToLoadNewDoc.observe(viewLifecycleOwner, Observer { username: String? ->
            username?.let{
                this.findNavController().navigate(AdminPanelFragmentDirections.actionAdminPanelFragmentToLoadNewDocumentFragment(username))
                viewModel.doneNavigateToLoadNewDoc()
            }
        })

        return binding.root
    }


}