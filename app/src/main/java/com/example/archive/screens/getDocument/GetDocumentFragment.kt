package com.example.archive.screens.getDocument

import android.os.Bundle
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
import com.example.archive.databinding.FragmentGetDocumentBinding
import com.example.archive.screens.profile.ProfileFragmentDirections
import com.example.archive.screens.signIn.SignInFragmentDirections

class GetDocumentFragment : Fragment() {

    private lateinit var binding: FragmentGetDocumentBinding
    private lateinit var viewModel: GetDocumentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_document, container, false)

        val arguments = GetDocumentFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application

        val database = ArchiveDatabase.getInstance(application)

        val viewModelFactory = GetDocumentViewModelFactory(arguments.username, database, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(GetDocumentViewModel::class.java)

        binding.getDocumentViewModel = viewModel
        binding.lifecycleOwner = this

        binding.doReqBtn.setOnClickListener(onClickListener)

        viewModel.navigateToMain.observe(viewLifecycleOwner, Observer { username: String? ->
            username?.let{
                this.findNavController().navigate(GetDocumentFragmentDirections.actionGetDocumentFragmentToMainFragment(username))
                viewModel.doneNavigateToMain()
            }
        })

        return binding.root
    }

    private val onClickListener = View.OnClickListener { view: View ->
        when (view.id){
            binding.doReqBtn.id -> {
                val docName = binding.docNameEditText.text.toString()

                viewModel.createRequest(docName)


            }
        }
    }
}