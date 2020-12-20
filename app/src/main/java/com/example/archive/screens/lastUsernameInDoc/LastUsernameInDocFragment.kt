package com.example.archive.screens.lastUsernameInDoc

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
import com.example.archive.databinding.FragmentLastUsernameInDocBinding
import com.example.archive.screens.getDocument.GetDocumentFragmentArgs
import com.example.archive.screens.getDocument.GetDocumentFragmentDirections
import com.example.archive.screens.getDocument.GetDocumentViewModel
import com.example.archive.screens.getDocument.GetDocumentViewModelFactory

class LastUsernameInDocFragment : Fragment() {

    private lateinit var binding: FragmentLastUsernameInDocBinding
    private lateinit var viewModel: LastUsernameInDocViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_last_username_in_doc, container, false)

        val arguments = LastUsernameInDocFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application

        val database = ArchiveDatabase.getInstance(application)

        val viewModelFactory = LastUsernameInDocViewModelFactory(arguments.username, database, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(LastUsernameInDocViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.whoWasLastBtn.setOnClickListener(onClickListener)

        viewModel.navigateToCheckingRequests.observe(viewLifecycleOwner, Observer { username: String? ->
            username?.let{
                this.findNavController().navigate(LastUsernameInDocFragmentDirections.actionLastUsernameInDocFragmentToCheckingRequestsFragment(username))
                viewModel.doneNavigateToCheckingRequests()
            }
        })

        return binding.root
    }

    private val onClickListener = View.OnClickListener { view: View ->
        when (view.id){
            binding.whoWasLastBtn.id -> {
                val docName = binding.docNameForLastUsernameEditText.text.toString()

                viewModel.updateForm(docName)


            }
        }
    }
}