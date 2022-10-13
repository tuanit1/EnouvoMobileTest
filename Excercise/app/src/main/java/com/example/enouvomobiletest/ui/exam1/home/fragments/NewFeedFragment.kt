package com.example.enouvomobiletest.ui.exam1.home.fragments

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enouvomobiletest.data.model.relation.PostWithUser
import com.example.enouvomobiletest.databinding.FragmentNewFeedBinding
import com.example.enouvomobiletest.ui.exam1.home.fragments.adapter.PostAdapter
import com.example.enouvomobiletest.ui.exam1.home.viewmodel.PostViewModal
import com.example.enouvomobiletest.util.Constant


class NewFeedFragment : Fragment() {

    private lateinit var binding: FragmentNewFeedBinding
    private lateinit var mPost: MutableList<PostWithUser>
    private lateinit var mAdapter: PostAdapter
    private var postViewModal: PostViewModal? = null
    private var isFavourite: Boolean? = null
    private var isLoading: Boolean = true
    private var mPage: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewFeedBinding.inflate(inflater, container, false)

        mPost = mutableListOf<PostWithUser>()

        initView()
        initListener()

        return binding.root
    }

    private fun initListener() {
        binding.rvPost.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1) && dy > 0) {

                    if(isLoading){
                        isLoading = false
                        fetchPostList()
                    }

                }
            }
        })
    }

    private fun initView() {
        isFavourite = arguments?.getBoolean("isFav")

        postViewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(context?.applicationContext as Application)
        )[PostViewModal::class.java]

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

        fetchPostList()
    }

    private fun fetchPostList() {
        if(isFavourite == false){
            postViewModal?.getPage(mPage, 10)?.observe(viewLifecycleOwner) { it ->

                mPost.addAll(it.toMutableList())

                mAdapter.submitList(mPost.toMutableList())

                isLoading = true

                mPage++
            }

        }else{
            postViewModal?.getFavouritePosts(Constant.mUserID)?.observe(viewLifecycleOwner) { it ->

                mPost.addAll(it.toMutableList())

                mAdapter.submitList(mPost.toMutableList())

                isLoading = false
            }
        }
    }

}
