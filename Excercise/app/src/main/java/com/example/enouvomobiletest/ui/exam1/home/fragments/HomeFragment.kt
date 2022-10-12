package com.example.enouvomobiletest.ui.exam1.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.enouvomobiletest.R
import com.example.enouvomobiletest.databinding.FragmentHomeBinding
import com.example.enouvomobiletest.extension.addFragment
import com.example.enouvomobiletest.extension.replaceFragment

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initView()
        initListener()

        return binding.root
    }

    private fun initView(){
        openNewFeedFragment()
    }

    private fun initListener(){
        binding.bottomNav.setOnItemSelectedListener {

            when(it.itemId){

                R.id.itemNavNewFeed -> run {
                    replaceFragment(
                        containerId = getContainerId(),
                        fragment = NewFeedFragment(),
                        addToBackStack = false
                    )
                }

                R.id.itemNavFavourite -> run {
                    replaceFragment(
                        containerId = getContainerId(),
                        fragment = FavouriteFragment(),
                        addToBackStack = false
                    )
                }

            }

            return@setOnItemSelectedListener true
        }
    }

    private fun getContainerId(): Int = R.id.fragment_container_home

    private fun openNewFeedFragment(){
        addFragment(
            containerId = getContainerId(),
            fragment = NewFeedFragment(),
            addToBackStack = false
        )
    }

}