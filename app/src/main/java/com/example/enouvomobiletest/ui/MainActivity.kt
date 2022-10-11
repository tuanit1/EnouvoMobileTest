package com.example.enouvomobiletest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.enouvomobiletest.R
import com.example.enouvomobiletest.databinding.ActivityMainBinding
import com.example.enouvomobiletest.ui.exam1.home.activity.ExamOneActivity
import com.example.enouvomobiletest.ui.exam1.home.viewmodel.PostViewModal
import com.example.enouvomobiletest.ui.exam1.login.viewmodel.UserViewModal

class MainActivity : AppCompatActivity() {

    lateinit var userViewModel: UserViewModal
    lateinit var postViewModal: PostViewModal
    lateinit var mView: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mView.root)

        userViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[UserViewModal::class.java]

        postViewModal = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[PostViewModal::class.java]

        postViewModal.setFavorite(1, 1, true)
        postViewModal.setFavorite(2, 1, false)
        postViewModal.setFavorite(3, 1, true)

        postViewModal.getFavouritePosts(1).observe(this) { postList ->
            postList.forEach {
                Log.e("AAAA", it.toString())
            }
            Log.e("AAAA", "" + postList.size)
        }

        mView.btnExam1.setOnClickListener {
            startActivity(Intent(this, ExamOneActivity::class.java))
        }

//        postViewModal.getCrossFav(1).observe(this){ list ->
//            list.forEach{
//                Log.e("AAAA", it.toString())
//            }
//        }

//        postViewModal.userWithPosts.observe(this) { list ->
//            list.forEach {
//                Log.e("AAAA", it.toString())
//            }
//            Log.e("AAAA", "" + list.size)
//        }

//        postViewModal.getPage(0, 10).observe(this){ list ->
//            list.forEach {
//                Log.e("AAAA", it.toString())
//            }
//            Log.e("AAAA", "" + list.size)
//        }

    }
}