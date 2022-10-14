package com.example.enouvomobiletest.ui.exam1.home.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.enouvomobiletest.R
import com.example.enouvomobiletest.data.model.User
import com.example.enouvomobiletest.data.model.relation.PostWithFavoriteUsers
import com.example.enouvomobiletest.databinding.ItemPostBinding
import com.example.enouvomobiletest.extension.getFormatDateString
import com.example.enouvomobiletest.ui.exam1.home.viewmodel.HomeViewModal
import com.example.enouvomobiletest.util.Constant

class PostFavouriteAdapter(
    private var mPosts: MutableList<PostWithFavoriteUsers>,
    private val mPostViewModal: HomeViewModal?,
    private val onFavRemove: (Int) -> Unit
) : ListAdapter<PostWithFavoriteUsers, PostFavouriteAdapter.ViewHolder>(DiffCallbackFavourite()) {

    private lateinit var mContext: Context

    inner class ViewHolder(
        private val binding: ItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PostWithFavoriteUsers, position: Int) {
            binding.run {
                tvContent.text = item.post.content
                tvEmail.text = item.user.email
                tvName.text = item.user.name
                tvTime.text = mContext.getFormatDateString(item.post.timestamp)

                if(checkIfUserExist(item.favUser)){
                    btnFavourite.setBackgroundResource(R.drawable.ic_heart_red)

                    btnFavourite.setOnClickListener {
                        mPostViewModal?.removeFavorite(item.post.post_id!!, Constant.mUserID)

                        item.post.post_id?.let { it1 -> onFavRemove(it1) }
                    }
                }else{
                    btnFavourite.setBackgroundResource(R.drawable.ic_heart_solid)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        mContext = parent.context

        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: PostWithFavoriteUsers = mPosts[position]
        holder.bind(item, holder.adapterPosition)
    }

    override fun getItemCount() = mPosts.size

    private fun checkIfUserExist(list: List<User>): Boolean{
        list.forEach {
            if (it.user_id == Constant.mUserID) return true
        }

        return false
    }

}

class DiffCallbackFavourite : DiffUtil.ItemCallback<PostWithFavoriteUsers>() {

    override fun areItemsTheSame(
        oldItem: PostWithFavoriteUsers,
        newItem: PostWithFavoriteUsers
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: PostWithFavoriteUsers,
        newItem: PostWithFavoriteUsers
    ): Boolean {
        return oldItem == newItem
    }

}

