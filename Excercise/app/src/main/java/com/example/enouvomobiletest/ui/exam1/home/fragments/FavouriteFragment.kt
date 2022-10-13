package com.example.enouvomobiletest.ui.exam1.home.fragments

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.enouvomobiletest.R
import com.example.enouvomobiletest.databinding.FragmentFavouriteBinding
import com.example.enouvomobiletest.ui.exam1.home.viewmodel.PostViewModal

class FavouriteFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteBinding
    private var postViewModal: PostViewModal? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFavouriteBinding.inflate(inflater, container, false)

        initView()
        initListener()

        return binding.root
    }

    private fun initListener() {
    }

    private fun initView() {
        postViewModal = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(context?.applicationContext as Application)
        )[PostViewModal::class.java]

        postViewModal?.getPage(0, 10)?.observe(viewLifecycleOwner){
            it.forEach {
                Log.e("AAAA", it.toString())
            }
        }
    }

}