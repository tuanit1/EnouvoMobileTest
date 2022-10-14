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

    private fun initView() {
        openNewFeedFragment()
    }

    private fun initListener() {
        binding.bottomNav.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.itemNavNewFeed -> run {

                    val frm = NewFeedFragment().apply{
                        arguments = Bundle().apply { putBoolean(getString(R.string.isFav), false) }
                    }

                    replaceFragment(
                        containerId = getContainerId(),
                        fragment = frm,
                        addToBackStack = false,
                        tag = getString(R.string.tweet)
                    )
                }

                R.id.itemNavFavourite -> run {
                    val frm = NewFeedFragment().apply{
                        arguments = Bundle().apply { putBoolean(getString(R.string.isFav), true) }
                    }

                    replaceFragment(
                        containerId = getContainerId(),
                        fragment = frm,
                        addToBackStack = false,
                        tag = getString(R.string.favourite)
                    )
                }

            }

            return@setOnItemSelectedListener true
        }
    }

    private fun getContainerId(): Int = R.id.fragmentContainerHome

    private fun openNewFeedFragment() {

        val frm = NewFeedFragment().apply{
            arguments = Bundle().apply { putBoolean(getString(R.string.isFav), false) }
        }

        addFragment(
            containerId = getContainerId(),
            fragment = frm,
            addToBackStack = false,
            tag = getString(R.string.tweet)
        )
    }

}
