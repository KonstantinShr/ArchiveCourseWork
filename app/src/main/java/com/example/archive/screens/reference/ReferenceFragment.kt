package com.example.archive.screens.reference

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
import com.example.archive.databinding.FragmentReferenceBinding
import com.example.archive.screens.profile.ProfileFragmentDirections

class ReferenceFragment : Fragment() {

    private lateinit var binding: FragmentReferenceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_reference, container, false)

        val arguments = ReferenceFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application

        val database = ArchiveDatabase.getInstance(application)

        val viewModelFactory = ReferenceViewModelFactory(arguments.username, arguments.depName, database, application)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(ReferenceViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToChooseDep.observe(viewLifecycleOwner, Observer { username: String? ->
            username?.let{
                this.findNavController().navigate(ReferenceFragmentDirections.actionReferenceFragmentToChoseDepForReferenceFragment(username))
                viewModel.doneNavigateToChooseDep()
            }
        })

        return binding.root
    }

}