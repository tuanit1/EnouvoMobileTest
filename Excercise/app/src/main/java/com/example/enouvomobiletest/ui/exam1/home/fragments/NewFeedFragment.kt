package com.example.enouvomobiletest.ui.exam1.home.fragments

import android.app.Application
import android.icu.lang.UCharacter.VerticalOrientation
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.enouvomobiletest.R
import com.example.enouvomobiletest.data.model.Post
import com.example.enouvomobiletest.data.model.relation.PostWithUser
import com.example.enouvomobiletest.databinding.FragmentFavouriteBinding
import com.example.enouvomobiletest.databinding.FragmentNewFeedBinding
import com.example.enouvomobiletest.ui.exam1.home.fragments.adapter.PostAdapter
import com.example.enouvomobiletest.ui.exam1.home.viewmodel.PostViewModal
import com.example.enouvomobiletest.util.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class NewFeedFragment : Fragment() {

    private lateinit var binding: FragmentNewFeedBinding
    private lateinit var mPost: List<PostWithUser>
    private var mAdapter: PostAdapter? = null
    private var postViewModal: PostViewModal? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewFeedBinding.inflate(inflater, container, false)

        Log.e("AAAA", "user_id: ${Constant.mUserID}")

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

        loadPostList()
    }

    private fun loadPostList(){
        postViewModal?.getPage(0, 10)?.observe(viewLifecycleOwner){ it ->

            mPost = it.toMutableList()

            mAdapter = PostAdapter(
                mPosts = mPost,
                mPostViewModal = postViewModal,
                mViewLifecycleOwner = viewLifecycleOwner
            )

            binding.rvPost.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(context)
                itemAnimator = DefaultItemAnimator()
                setHasFixedSize(true)

            }

            mAdapter?.submitList(it)
        }

    }

}