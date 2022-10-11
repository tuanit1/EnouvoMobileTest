package com.example.enouvomobiletest.ui.exam1.auth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.enouvomobiletest.R
import com.example.enouvomobiletest.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {

    lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSignupBinding.inflate(inflater, container, false)

        setUI()

        return binding.root
    }

    private fun setUI(){
        binding.tvBack.setOnClickListener{
            findNavController().navigate(R.id.signup_to_login)
        }
    }

}