package com.example.archive.screens.themeOfDoc

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
import com.example.archive.databinding.FragmentThemeOfDocBinding
import com.example.archive.screens.mostOftenDoc.MostOftenDocFragmentArgs
import com.example.archive.screens.mostOftenDoc.MostOftenDocFragmentDirections
import com.example.archive.screens.mostOftenDoc.MostOftenDocViewModel
import com.example.archive.screens.mostOftenDoc.MostOftenDocViewModelFactory


class ThemeOfDocFragment : Fragment() {

    private lateinit var binding: FragmentThemeOfDocBinding
    private lateinit var viewModel: ThemeOfDocViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_theme_of_doc, container, false)

        val arguments = ThemeOfDocFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application

        val database = ArchiveDatabase.getInstance(application)

        val viewModelFactory = ThemeOfDocViewModelFactory(arguments.username, database, application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(ThemeOfDocViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToMain.observe(viewLifecycleOwner, Observer { username: String? ->
            username?.let{
                this.findNavController().navigate(ThemeOfDocFragmentDirections.actionThemeOfDocFragmentToCheckingRequestsFragment(username))
                viewModel.doneNavigateToMain()
            }
        })

        binding.findThemeBtn.setOnClickListener(onClickListener)

        return binding.root
    }

    private val onClickListener = View.OnClickListener { view: View ->
        when (view.id){
            binding.findThemeBtn.id -> {
                viewModel.updateForm(binding.nameOfDocEditText.text.toString())
            }
        }
    }

}