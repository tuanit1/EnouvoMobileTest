package com.example.enouvomobiletest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.enouvomobiletest.databinding.ActivityMainBinding
import com.example.enouvomobiletest.ui.exam1.ExamOneActivity
import com.example.enouvomobiletest.ui.exam1.home.viewmodel.PostViewModal
import com.example.enouvomobiletest.ui.exam1.auth.viewmodel.UserViewModal

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

        mView.btnExam1.setOnClickListener {
            startActivity(Intent(this, ExamOneActivity::class.java))
        }

    }
}