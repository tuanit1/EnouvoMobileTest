package com.example.enouvomobiletest.ui.exam1.home.fragments.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.enouvomobiletest.R
import com.example.enouvomobiletest.data.model.User
import com.example.enouvomobiletest.data.model.relation.PostWithUser
import com.example.enouvomobiletest.extension.getFormatDateString
import com.example.enouvomobiletest.ui.exam1.home.viewmodel.PostViewModal
import com.example.enouvomobiletest.util.Constant

class PostAdapter(
    private val mPosts: MutableList<PostWithUser>,
    private val mPostViewModal: PostViewModal?,
    private val mViewLifecycleOwner: LifecycleOwner
) : ListAdapter<PostWithUser, PostAdapter.ViewHolder>(DiffCallback()) {

    private lateinit var mContext: Context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView
        val tvEmail: TextView
        val tvContent: TextView
        val tvTime: TextView
        val btnFavourite: ImageView

        init {
            tvName = view.findViewById(R.id.tvName)
            tvEmail = view.findViewById(R.id.tvEmail)
            tvContent = view.findViewById(R.id.tvContent)
            tvTime = view.findViewById(R.id.tvTime)
            btnFavourite = view.findViewById(R.id.btnFavourite)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        mContext = parent.context

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: PostWithUser = mPosts[position]

        holder.run {

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

