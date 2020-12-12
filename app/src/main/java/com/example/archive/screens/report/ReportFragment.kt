package com.example.archive.screens.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.archive.R
import com.example.archive.databinding.FragmentGetDocumentBinding
import com.example.archive.databinding.FragmentReportBinding

class ReportFragment : Fragment() {

    private lateinit var binding: FragmentReportBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_report, container, false)

        binding.reportBackBtn.setOnClickListener(onClickListener)
        return binding.root
    }

    private val onClickListener = View.OnClickListener { view: View ->
        when (view.id){
            //binding.reportBackBtn.id -> view.findNavController().navigate(ReportFragmentDirections.actionReportFragmentToMainFragment())
        }
    }
}