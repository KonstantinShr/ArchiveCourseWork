package com.example.archive.screens.choseDepForReference

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
import com.example.archive.databinding.FragmentChoseDepForReferenceBinding
import com.example.archive.screens.changeDepartmentNumber.ChangeDepNumberFragmentArgs
import com.example.archive.screens.changeDepartmentNumber.ChangeDepNumberFragmentDirections
import com.example.archive.screens.changeDepartmentNumber.ChangeDepartmentNumberViewModel
import com.example.archive.screens.changeDepartmentNumber.ChangeDepartmentNumberViewModelFactory


class ChoseDepForReferenceFragment : Fragment() {
    private lateinit var binding: FragmentChoseDepForReferenceBinding
    private lateinit var viewModel: ChoseDepForReferenceViewModel
    private var departmentNames: MutableList<String> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chose_dep_for_reference, container, false)

        val arguments = ChoseDepForReferenceFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application

        val database = ArchiveDatabase.getInstance(application)

        val viewModelFactory = ChoseDepForReferenceViewModelFactory(arguments.username, database, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(ChoseDepForReferenceViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToMain.observe(viewLifecycleOwner, Observer { username: String? ->
            username?.let{
                this.findNavController().navigate(ChoseDepForReferenceFragmentDirections.actionChoseDepForReferenceFragmentToMainFragment(username))
                viewModel.doneNavigateToMain()
            }
        })

        binding.getRefBtn.setOnClickListener(onClickListener)

        viewModel.departmentsString.observe(viewLifecycleOwner, Observer { depNames: List<String> ->
            updateDepNames(depNames)
        })

        viewModel.navigateToReference.observe(viewLifecycleOwner, Observer { args: List<String?> ->
            this.findNavController().navigate(ChoseDepForReferenceFragmentDirections.actionChoseDepForReferenceFragmentToReferenceFragment(args[0], args[1]))
        })

        return binding.root
    }

    private val onClickListener = View.OnClickListener { view: View ->
        when (view.id){
            binding.getRefBtn.id -> {
                val department = binding.spinnerDepNameForRef.selectedItem.toString()

                viewModel.getReference(department)
            }
        }
    }

    private fun updateDepNames(depNames: List<String>){
        for (i in depNames.indices){
            departmentNames.add(depNames[i])
        }
        val adapter = ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item, departmentNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerDepNameForRef.adapter = adapter
    }
}