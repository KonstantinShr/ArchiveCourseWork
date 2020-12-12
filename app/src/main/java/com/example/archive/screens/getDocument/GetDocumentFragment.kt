package com.example.archive.screens.getDocument

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.archive.R
import com.example.archive.databinding.FragmentGetDocumentBinding

class GetDocumentFragment : Fragment() {

    private lateinit var binding: FragmentGetDocumentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_document, container, false)

        binding.getDocBackBtn.setOnClickListener(onClickListener)

        return binding.root
    }

    private val onClickListener = View.OnClickListener { view: View ->
        when (view.id){
            //binding.getDocBackBtn.id -> view.findNavController().navigate(GetDocumentFragmentDirections.actionGetDocumentFragmentToMainFragment())
        }
    }
}