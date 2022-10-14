package com.example.enouvomobiletest.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.enouvomobiletest.databinding.ActivityMainBinding
import com.example.enouvomobiletest.ui.exam1.ExamOneActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mView: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mView.root)

        mView.btnExam1.setOnClickListener {
            startActivity(Intent(this, ExamOneActivity::class.java))
        }
    }
}
