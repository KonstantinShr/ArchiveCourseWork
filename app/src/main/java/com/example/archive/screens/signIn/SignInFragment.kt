package com.example.archive.screens.signIn

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.archive.R
import com.example.archive.database.ArchiveDatabase
import com.example.archive.database.enteties.User
import com.example.archive.screens.signIn.SignInFragmentDirections
import com.example.archive.databinding.FragmentSignInBinding
import kotlinx.coroutines.launch


class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: SignInViewModel

    //private lateinit var database: ArchiveDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_sign_in,
                container,
                false)

        val application = requireNotNull(this.activity).application

        val database = ArchiveDatabase.getInstance(application)

        val viewModelFactory = SignInViewModelFactory(database, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SignInViewModel::class.java)

        binding.signInViewModel = viewModel

        binding.lifecycleOwner = this

        binding.signInButton.setOnClickListener(onClickListener)
        binding.signUpButton.setOnClickListener(onClickListener)

        viewModel.navigateToMain.observe(viewLifecycleOwner, Observer {username: String? ->
            username?.let{
                this.findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment(username))
                viewModel.doneNavigateToMain()
            }
        })

        return binding.root
    }


    private val onClickListener = View.OnClickListener { view: View ->
        when (view.id){
            binding.signInButton.id -> {
                val username = binding.usernameEditText.text.toString()

                viewModel.updateUsername(username)


            }

            binding.signUpButton.id -> view.findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
        }
    }
}