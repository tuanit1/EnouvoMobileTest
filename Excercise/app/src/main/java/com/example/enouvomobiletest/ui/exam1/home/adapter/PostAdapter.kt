package com.example.enouvomobiletest.ui.exam1.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.enouvomobiletest.R
import com.example.enouvomobiletest.data.model.relation.PostWithUser
import com.example.enouvomobiletest.databinding.ItemPostBinding
import com.example.enouvomobiletest.extension.getFormatDateString
import com.example.enouvomobiletest.ui.exam1.home.viewmodel.HomeViewModal
import com.example.enouvomobiletest.util.Constant

class PostAdapter(
    private val mPosts: MutableList<PostWithUser>,
    private val mPostViewModal: HomeViewModal?,
) : ListAdapter<PostWithUser, PostAdapter.ViewHolder>(DiffCallback()) {

    private lateinit var mContext: Context

    inner class ViewHolder(
        private val binding: ItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PostWithUser, position: Int) {
            binding.run {
                tvContent.text = item.post.content
                tvEmail.text = item.user.email
                tvName.text = item.user.name
                tvTime.text = mContext.getFormatDateString(item.post.timestamp)

                item.post.post_id?.let { postId ->
                    mPostViewModal?.isFavourite(Constant.mUserID, postId) { isFav ->
                        if (isFav) {
                            btnFavourite.setBackgroundResource(R.drawable.ic_heart_red)

                            btnFavourite.setOnClickListener {
                                mPostViewModal.removeFavorite(postId, Constant.mUserID)
                                notifyItemChanged(position)
                            }

                        } else {
                            btnFavourite.setBackgroundResource(R.drawable.ic_heart_stroke)

                            btnFavourite.setOnClickListener {
                                mPostViewModal.setFavorite(postId, Constant.mUserID)
                                notifyItemChanged(position)
                            }
                        }
                    }
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
        val item: PostWithUser = mPosts[position]
        holder.bind(item, position)
    }

    override fun getItemCount() = mPosts.size

}

class DiffCallback : DiffUtil.ItemCallback<PostWithUser>() {
    override fun areItemsTheSame(oldItem: PostWithUser, newItem: PostWithUser): Boolean {
        return oldItem.post.post_id == newItem.post.post_id
    }

    override fun areContentsTheSame(oldItem: PostWithUser, newItem: PostWithUser): Boolean {
        return oldItem == newItem
    }

}

