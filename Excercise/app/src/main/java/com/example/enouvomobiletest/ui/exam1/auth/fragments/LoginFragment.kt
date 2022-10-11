package com.example.enouvomobiletest.ui.exam1.auth.fragments

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.window.layout.WindowMetricsCalculator
import com.example.enouvomobiletest.R
import com.example.enouvomobiletest.databinding.FragmentLoginBinding
import com.example.enouvomobiletest.ui.MainActivity
import com.example.enouvomobiletest.ui.exam1.auth.viewmodel.UserViewModal
import com.example.enouvomobiletest.ui.exam1.home.viewmodel.PostViewModal
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var userViewModal: UserViewModal
    var widthDp: Float = 0f
    var heightDp: Float = 0f


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        userViewModal = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(context?.applicationContext as Application)
        )[UserViewModal::class.java]

        userViewModal.allUsers.observe(viewLifecycleOwner){ list ->
            list.forEach {
                Log.e("AAAA", it.toString())
            }
            Log.e("AAAA", "" + list.size)
        }

        getScreenSize()
        setUI()

        return binding.root
    }

    private fun setUI(){
        binding.tvSignup.setOnClickListener{
            findNavController().navigate(R.id.login_to_signup)
        }

        binding.btnLogin.setOnClickListener{

            val email = binding.edtEmail.text.toString()
            val pw = binding.edtPw.text.toString()

            if(email.isEmpty() || pw.isEmpty()){
                Toast.makeText(context, "Vui long nhap day du", Toast.LENGTH_SHORT).show()
            }else{
                userViewModal.checkLogin(email, pw).observe(viewLifecycleOwner){ count ->
                    if(count > 0){
                        findNavController().navigate(R.id.openHome)
                    }else{
                        Toast.makeText(context, "Sai thong tin", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun getScreenSize(){
        val metrics = WindowMetricsCalculator.getOrCreate()
            .computeCurrentWindowMetrics(context as Activity)

        widthDp = metrics.bounds.width() / resources.displayMetrics.density
        heightDp = metrics.bounds.height() / resources.displayMetrics.density

        binding.edtEmail.textSize = heightDp * 0.018f
        binding.edtPw.textSize = heightDp * 0.018f

    }

}