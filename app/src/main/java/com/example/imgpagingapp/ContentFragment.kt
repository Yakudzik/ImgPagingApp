package com.example.imgpagingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.imgpagingapp.databinding.FragmentContentBinding


class ContentFragment : Fragment() {
    lateinit var binding: FragmentContentBinding
    val model: DataViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentContentBinding.inflate(inflater, container, false)

        model.getImagesResponse()
        model.categoryNumber.observe(viewLifecycleOwner,{

        })

        return binding.root
    }


}