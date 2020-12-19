package com.example.archive.screens.changeDepartmentNumber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.archive.R
import com.example.archive.database.ArchiveDatabase
import com.example.archive.databinding.FragmentChangeDepNumberBinding
import com.example.archive.screens.loadNewDocument.LoadNewDocumentFragmentDirections
import com.example.archive.screens.signUp.SignUpFragmentDirections


class ChangeDepNumberFragment : Fragment() {

    private lateinit var binding: FragmentChangeDepNumberBinding
    private lateinit var viewModel: ChangeDepartmentNumberViewModel
    private var departmentNames: MutableList<String> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_dep_number, container, false)

        val arguments = ChangeDepNumberFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application

        val database = ArchiveDatabase.getInstance(application)

        val viewModelFactory = ChangeDepartmentNumberViewModelFactory(arguments.username, database, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(ChangeDepartmentNumberViewModel::class.java)

        binding.changeDepartmentNumberViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToAdminPanel.observe(viewLifecycleOwner, Observer { username: String? ->
            username?.let{
                this.findNavController().navigate(ChangeDepNumberFragmentDirections.actionChangeDepNumberFragmentToAdminPanelFragment(username))
                viewModel.doneNavigateToAdminPanel()
            }
        })

        binding.changeNumberBtn.setOnClickListener(onClickListener)

        viewModel.departmentsString.observe(viewLifecycleOwner, Observer { depNames: List<String> ->
            updateDepNames(depNames)
        })

        return binding.root
    }

    private val onClickListener = View.OnClickListener { view: View ->
        when (view.id){
            binding.changeNumberBtn.id -> {
                val telephone = binding.newNumberEditText.text.toString()

                val department = binding.spinnerDepName.selectedItem.toString()

                viewModel.changeDepartmentTelephone(department, telephone)
            }
        }
    }

    private fun updateDepNames(depNames: List<String>){
        for (i in depNames.indices){
            departmentNames.add(depNames[i])
        }
        val adapter = ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item, departmentNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerDepName.adapter = adapter
    }
}