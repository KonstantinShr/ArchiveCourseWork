package com.example.archive.screens.checkingRequests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.archive.R
import com.example.archive.database.ArchiveDatabase
import com.example.archive.databinding.FragmentCheckingRequestsBinding
import com.example.archive.databinding.FragmentGetDocumentBinding
import com.example.archive.screens.profile.ProfileFragmentDirections

class CheckingRequestsFragment : Fragment() {
    private lateinit var binding: FragmentCheckingRequestsBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checking_requests, container, false)

        val arguments = CheckingRequestsFragmentArgs.fromBundle((requireArguments()))

        val application = requireNotNull(this.activity).application

        val database = ArchiveDatabase.getInstance(application)

        val viewModelFactory = CheckingRequestsViewModelFactory(arguments.username, database, application)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(CheckingRequestsViewModel::class.java)

        binding.checkingRequestsViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.adminPanelBtnVisibility.observe(viewLifecycleOwner, Observer { visibility: Boolean ->
                if (visibility){
                    binding.panelOfAdminBtn.visibility = View.VISIBLE
                }
                else{
                    binding.panelOfAdminBtn.visibility = View.INVISIBLE
                }
        })

        viewModel.navigateToMain.observe(viewLifecycleOwner, Observer { username: String? ->
            username?.let{
                this.findNavController().navigate(CheckingRequestsFragmentDirections.actionCheckingRequestsFragmentToMainFragment(username))
                viewModel.doneNavigateToMain()
            }
        })

        viewModel.navigateToAdminPanel.observe(viewLifecycleOwner, Observer{ username: String? ->
            username?.let{
                this.findNavController().navigate(CheckingRequestsFragmentDirections.actionCheckingRequestsFragmentToAdminPanelFragment(username))
                viewModel.doneNavigateToAdminPanel()
            }
        })

        viewModel.navigateToMostOftenDoc.observe(viewLifecycleOwner, Observer{ username: String? ->
            username?.let{
                this.findNavController().navigate(CheckingRequestsFragmentDirections.actionCheckingRequestsFragmentToMostOftenDocFragment(username))
                viewModel.doneNavigateToMostOftenDoc()
            }
        })

        viewModel.navigateToDocCountOnTheme.observe(viewLifecycleOwner, Observer{ username: String? ->
            username?.let{
                this.findNavController().navigate(CheckingRequestsFragmentDirections.actionCheckingRequestsFragmentToDocCountOnThemeFragment(username))
                viewModel.doneNavigateToDocCountOnTheme()
            }
        })

        viewModel.navigateToMostCopiedDoc.observe(viewLifecycleOwner, Observer{ username: String? ->
            username?.let{
                this.findNavController().navigate(CheckingRequestsFragmentDirections.actionCheckingRequestsFragmentToMostCopiedDocFragment(username))
                viewModel.doneNavigateToMostCopiedDoc()
            }
        })

        viewModel.navigateToMostReqCountDep.observe(viewLifecycleOwner, Observer{ username: String? ->
            username?.let{
                this.findNavController().navigate(CheckingRequestsFragmentDirections.actionCheckingRequestsFragmentToMostReqCountDepFragment(username))
                viewModel.doneNavigateToMostReqCountDep()
            }
        })

        viewModel.navigateToLastUsernameInDoc.observe(viewLifecycleOwner, Observer{ username: String? ->
            username?.let{
                this.findNavController().navigate(CheckingRequestsFragmentDirections.actionCheckingRequestsFragmentToLastUsernameInDocFragment(username))
                viewModel.doneNavigateToLastUsernameInDoc()
            }
        })

        viewModel.navigateToThemeOfDoc.observe(viewLifecycleOwner, Observer{ username: String? ->
            username?.let{
                this.findNavController().navigate(CheckingRequestsFragmentDirections.actionCheckingRequestsFragmentToThemeOfDocFragment(username))
                viewModel.doneNavigateToThemeOfDoc()
            }
        })

        return binding.root
    }
}