package com.example.enouvomobiletest.ui.exam1.home.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.enouvomobiletest.R
import com.example.enouvomobiletest.databinding.FragmentHomeBinding
import com.example.enouvomobiletest.ui.MainActivity
import com.example.enouvomobiletest.ui.exam1.ExamOneActivity
import com.google.android.material.navigation.NavigationBarView

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        parentFragmentManager.commit {
            setReorderingAllowed(true)
            add<NewFeedFragment>(R.id.fragment_container_home)
        }

        binding.bottomNav.setOnItemSelectedListener {

            Log.e("AAAA", "${it.itemId}")

            return@setOnItemSelectedListener true
        }
        return binding.root
    }

}