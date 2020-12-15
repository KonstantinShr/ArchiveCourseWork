package com.example.archive.screens.checkingRequests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.archive.R
import com.example.archive.database.ArchiveDatabase
import com.example.archive.databinding.FragmentCheckingRequestsBinding
import com.example.archive.databinding.FragmentGetDocumentBinding

class CheckingRequestsFragment : Fragment() {
    private lateinit var binding: FragmentCheckingRequestsBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checking_requests, container, false)

        val arguments = CheckingRequestsFragmentArgs.fromBundle((requireArguments()))

        val application = requireNotNull(this.activity).application

        val database = ArchiveDatabase.getInstance(application)



        binding.checkingReqBackBtn.setOnClickListener(onClickListener)
        return binding.root
    }

    private val onClickListener = View.OnClickListener { view: View ->
        when (view.id){
            //binding.checkingReqBackBtn.id -> view.findNavController().navigate(CheckingRequestsFragmentDirections.actionCheckingRequestsFragmentToMainFragment())
        }
    }
}