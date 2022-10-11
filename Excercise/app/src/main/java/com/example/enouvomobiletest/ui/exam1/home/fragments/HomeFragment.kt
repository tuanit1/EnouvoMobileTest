package com.example.enouvomobiletest.ui.exam1.home.fragments

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.enouvomobiletest.R
import com.example.enouvomobiletest.databinding.FragmentHomeBinding
import com.example.enouvomobiletest.databinding.FragmentSignupBinding
import com.example.enouvomobiletest.ui.exam1.auth.viewmodel.UserViewModal
import com.example.enouvomobiletest.ui.exam1.home.viewmodel.PostViewModal

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root
    }

}