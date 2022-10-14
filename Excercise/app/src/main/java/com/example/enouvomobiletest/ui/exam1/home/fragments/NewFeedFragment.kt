package com.example.enouvomobiletest.ui.exam1.home.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enouvomobiletest.R
import com.example.enouvomobiletest.data.model.relation.PostWithFavoriteUsers
import com.example.enouvomobiletest.data.model.relation.PostWithUser
import com.example.enouvomobiletest.databinding.FragmentNewFeedBinding
import com.example.enouvomobiletest.ui.exam1.home.adapter.PostAdapter
import com.example.enouvomobiletest.ui.exam1.home.adapter.PostFavouriteAdapter
import com.example.enouvomobiletest.ui.exam1.home.viewmodel.HomeViewModal


class NewFeedFragment : Fragment() {

    private lateinit var binding: FragmentNewFeedBinding
    private lateinit var mPost: MutableList<PostWithUser>
    private lateinit var mPostFavourite: MutableList<PostWithFavoriteUsers>
    private lateinit var mAdapter: PostAdapter
    private lateinit var mAdapterFavourite: PostFavouriteAdapter
    private var postViewModal: HomeViewModal? = null
    private var isFavourite: Boolean? = null
    private var isLoading: Boolean = true
    private var mPage: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewFeedBinding.inflate(inflater, container, false)

        initView()
        initListener()

        return binding.root
    }

    private fun initListener() {
        binding.run {
            rvPost.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (!recyclerView.canScrollVertically(1) && dy > 0) {

                        if(isLoading && isFavourite == false){
                            isLoading = false
                            fetchPostList()
                        }

                    }
                }
            })

            btnBack.setOnClickListener {
                (parentFragment as HomeFragment).findNavController().navigate(R.id.home_to_login)
            }
        }
    }

    private fun initView() {
        isFavourite = arguments?.getBoolean(getString(R.string.isFav))

        postViewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(context?.applicationContext as Application)
        )[HomeViewModal::class.java]

        mPost = mutableListOf()
        mPostFavourite = mutableListOf()

        mAdapter = PostAdapter(
            mPosts = mPost,
            mPostViewModal = postViewModal
        )

        mAdapterFavourite = PostFavouriteAdapter(
            mPosts = mPostFavourite,
            mPostViewModal = postViewModal
        )

        binding.rvPost.apply {
            adapter = if (isFavourite == false) mAdapter else mAdapterFavourite
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
        }

        binding.apply {
            tvTitle.text = if (isFavourite == true) getString(R.string.favourite) else getString(R.string.tweet)
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
            postViewModal?.getPostWithFavoriteUsers{
                if(mPostFavourite.isEmpty()){
                    mPostFavourite.addAll(it.toMutableList())
                    mAdapterFavourite.notifyItemRangeInserted(0, mPostFavourite.size)
                }
            }
        }
    }

}
