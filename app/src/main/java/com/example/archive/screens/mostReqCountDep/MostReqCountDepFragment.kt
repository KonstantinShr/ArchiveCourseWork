package com.example.archive.screens.mostReqCountDep

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
import com.example.archive.databinding.FragmentMostReqCountDepBinding
import com.example.archive.screens.mostOftenDoc.MostOftenDocFragmentArgs
import com.example.archive.screens.mostOftenDoc.MostOftenDocFragmentDirections
import com.example.archive.screens.mostOftenDoc.MostOftenDocViewModel
import com.example.archive.screens.mostOftenDoc.MostOftenDocViewModelFactory

class MostReqCountDepFragment : Fragment() {

    private lateinit var binding: FragmentMostReqCountDepBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_most_req_count_dep, container, false)

        val arguments = MostReqCountDepFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application

        val database = ArchiveDatabase.getInstance(application)

        val viewModelFactory = MostReqCountDepViewModelFactory(arguments.username, database, application)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(MostReqCountDepViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToMain.observe(viewLifecycleOwner, Observer { username: String? ->
            username?.let{
                this.findNavController().navigate(MostReqCountDepFragmentDirections.actionMostReqCountDepFragmentToCheckingRequestsFragment(username))
                viewModel.doneNavigateToMain()
            }
        })

        return binding.root
    }
}