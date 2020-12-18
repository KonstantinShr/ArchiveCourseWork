package com.example.archive.screens.loadNewDocument

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.archive.R
import com.example.archive.database.ArchiveDatabase
import com.example.archive.databinding.FragmentLoadNewDocumentBinding


class LoadNewDocumentFragment : Fragment() {

    private lateinit var binding: FragmentLoadNewDocumentBinding
    private var themes: MutableList<String> = mutableListOf()
    private lateinit var viewModel: LoadNewDocumentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_load_new_document, container, false)

        val arguments = LoadNewDocumentFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application

        val database = ArchiveDatabase.getInstance(application)

        val viewModelFactory = LoadNewDocumentViewModelFactory(arguments.username, database, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(LoadNewDocumentViewModel::class.java)

        binding.loadNewDocumentViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToAdminPanel.observe(viewLifecycleOwner, Observer { username: String? ->
            username?.let{
                this.findNavController().navigate(LoadNewDocumentFragmentDirections.actionLoadNewDocumentFragmentToAdminPanelFragment(username))
            }
        })

        viewModel.themesString.observe(viewLifecycleOwner, Observer { themeStrings: List<String> ->
            updateDocumentTheme(themeStrings)
        })

        binding.loadNewDocumentBtn.setOnClickListener(onClickListener)

        return binding.root
    }

    private fun updateDocumentTheme(themeStrings: List<String>){
        for (i in themeStrings.indices){
            themes.add(themeStrings[i])
        }
        val adapter = ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item, themes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerLoadDoc.adapter = adapter
    }

    private val onClickListener = View.OnClickListener { view: View ->
        when (view.id){
            binding.loadNewDocumentBtn.id -> {
                viewModel.updateForm(binding.docNameEditText.text.toString(), Integer.parseInt(binding.copyCountEditText.text.toString()), binding.spinnerLoadDoc.selectedItem.toString())
            }
        }
    }
}