package com.example.enouvomobiletest.ui.exam1.login.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.window.layout.WindowMetricsCalculator
import com.example.enouvomobiletest.R
import com.example.enouvomobiletest.databinding.FragmentLoginBinding
import com.example.enouvomobiletest.ui.MainActivity

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    var widthDp: Float = 0f
    var heightDp: Float = 0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.text.setOnClickListener{
            findNavController().navigate(R.id.login_to_signup)
        }

        getScreenSize()

        return binding.root
    }

    fun getScreenSize(){
        val metrics = WindowMetricsCalculator.getOrCreate()
            .computeCurrentWindowMetrics(context as Activity)

        widthDp = metrics.bounds.width() / resources.displayMetrics.density
        heightDp = metrics.bounds.height() / resources.displayMetrics.density

        Log.e("AAAA", "W:$widthDp, H:$heightDp")

        binding.edtEmail.textSize = heightDp * 0.018f
        binding.edtPw.textSize = heightDp * 0.018f

    }

}