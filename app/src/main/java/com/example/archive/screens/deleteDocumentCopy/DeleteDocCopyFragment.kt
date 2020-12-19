package com.example.archive.screens.deleteDocumentCopy

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
import com.example.archive.databinding.FragmentDeleteDocCopyBinding
import com.example.archive.screens.loadNewDocument.LoadNewDocumentFragmentDirections

class DeleteDocCopyFragment : Fragment() {

    private lateinit var binding: FragmentDeleteDocCopyBinding
    private lateinit var viewModel: DeleteDocCopyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_delete_doc_copy, container, false)

        val arguments = DeleteDocCopyFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application

        val database = ArchiveDatabase.getInstance(application)

        val viewModelFactory = DeleteDocCopyViewModelFactory(arguments.username, database, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(DeleteDocCopyViewModel::class.java)

        binding.deleteDocCopyViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToAdminPanel.observe(viewLifecycleOwner, Observer { username: String? ->
            username?.let{
                this.findNavController().navigate(DeleteDocCopyFragmentDirections.actionDeleteDocCopyFragmentToAdminPanelFragment(username))
                viewModel.doneNavigateToAdminPanel()
            }
        })

        binding.deleteDocCopyBtn.setOnClickListener(onClickListener)

        return binding.root
    }

    private val onClickListener = View.OnClickListener { view: View ->
        when (view.id){
            binding.deleteDocCopyBtn.id -> {
                viewModel.updateForm(binding.deleteDocCopyEditText.text.toString())
            }
        }
    }
}