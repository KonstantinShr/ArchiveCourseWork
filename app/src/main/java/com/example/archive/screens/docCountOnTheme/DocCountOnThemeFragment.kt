package com.example.archive.screens.docCountOnTheme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.archive.R
import com.example.archive.database.ArchiveDatabase
import com.example.archive.databinding.FragmentDocCountOnThemeBinding
import com.example.archive.screens.mostOftenDoc.MostOftenDocFragmentArgs
import com.example.archive.screens.mostOftenDoc.MostOftenDocFragmentDirections
import com.example.archive.screens.mostOftenDoc.MostOftenDocViewModel
import com.example.archive.screens.mostOftenDoc.MostOftenDocViewModelFactory

class DocCountOnThemeFragment : Fragment() {

    private lateinit var binding: FragmentDocCountOnThemeBinding
    private var themes: MutableList<String> = mutableListOf()
    private lateinit var viewModel: DocCountOnThemeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_doc_count_on_theme, container, false)

        val arguments = DocCountOnThemeFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application

        val database = ArchiveDatabase.getInstance(application)

        val viewModelFactory = DocCountOnThemeViewModelFactory(arguments.username, database, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(DocCountOnThemeViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToMain.observe(viewLifecycleOwner, Observer { username: String? ->
            username?.let{
                this.findNavController().navigate(DocCountOnThemeFragmentDirections.actionDocCountOnThemeFragmentToCheckingRequestsFragment(username))
                viewModel.doneNavigateToMain()
            }
        })

        viewModel.themesString.observe(viewLifecycleOwner, Observer { themeStrings: List<String> ->
            updateDocumentTheme(themeStrings)
        })

        binding.docCountBtn.setOnClickListener(onClickListener)

        return binding.root
    }

    private fun updateDocumentTheme(themeStrings: List<String>){
        for (i in themeStrings.indices){
            themes.add(themeStrings[i])
        }
        val adapter = ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item, themes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.themeSpinner.adapter = adapter
    }

    private val onClickListener = View.OnClickListener { view: View ->
        when (view.id){
            binding.docCountBtn.id -> {
                viewModel.updateForm(binding.themeSpinner.selectedItem.toString())
            }
        }
    }

}