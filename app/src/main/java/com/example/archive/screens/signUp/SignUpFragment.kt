package com.example.archive.screens.signUp

import android.os.Bundle
import android.util.Log
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
import com.example.archive.database.enteties.User
import com.example.archive.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: SignUpViewModel
    private var departmentNames: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        val application = requireNotNull(this.activity).application

        val database = ArchiveDatabase.getInstance(application)

        val viewModelFactory = SignUpViewModelFactory(database, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SignUpViewModel::class.java)

        binding.signUpViewModel = viewModel

        binding.lifecycleOwner = this



        binding.signUpBtn.setOnClickListener(onClickListener)
        binding.signUpBackBtn.setOnClickListener(onClickListener)

        viewModel.departmentsString.observe(viewLifecycleOwner, Observer { depNames: List<String> ->
            updateDepNames(depNames)
        })

        viewModel.navigateToMain.observe(viewLifecycleOwner, Observer { username: String? ->
            username?.let{
                this.findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToMainFragment(username))
                viewModel.doneNavigateToMain()
            }
        })
        return binding.root
    }

    private val onClickListener = View.OnClickListener { view: View ->
        when (view.id){
            binding.signUpBtn.id -> {
                val username = binding.registrationUsernameEditText.text.toString()
                val firstName = binding.registrationFirstNameEditText.text.toString()
                val secondName = binding.registrationSecondNameEditText.text.toString()
                val patronymic = binding.registrationPatronymicEditText.text.toString()
                val department = binding.spinner.selectedItem.toString()
                val isAdmin = binding.isAdminCheckBox.isChecked

                if ((username != "") and (firstName != "") and (secondName != "") and (patronymic != "")){
                    viewModel.registration(username, "$secondName $firstName $patronymic", department, isAdmin)
                }
            }
            binding.signUpBackBtn.id -> {
                view.findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
            }
        }
    }

    private fun updateDepNames(depNames: List<String>){
        for (i in depNames.indices){
            departmentNames.add(depNames[i])
        }
        val adapter = ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item, departmentNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinner.adapter = adapter
    }
}