package com.example.enouvomobiletest.ui.exam1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.enouvomobiletest.databinding.ActivityExamOneBinding

class ExamOneActivity : AppCompatActivity() {

    lateinit var binding: ActivityExamOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamOneBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
