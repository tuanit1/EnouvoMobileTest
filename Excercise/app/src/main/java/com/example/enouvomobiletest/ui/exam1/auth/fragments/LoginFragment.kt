package com.example.enouvomobiletest.ui.exam1.auth.fragments

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
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
    private val ERR_EMAIL_EMPTY = "Email is empty!"
    private val ERR_EMAIL_NOT_VALID = "Email is not valid"
    private val ERR_PW_EMPTY = "Password is empty!"
    private val ERR_PW_NOT_VALID = "Password is not valid"
    private val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}\$"
    private var isShowErr: Boolean = false
    var widthDp: Float = 0f
    var heightDp: Float = 0f


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        userViewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(context?.applicationContext as Application)
        )[UserViewModal::class.java]

        userViewModal.allUsers.observe(viewLifecycleOwner) { list ->
            list.forEach {
                Log.e("AAAA", it.toString())
            }
            Log.e("AAAA", "" + list.size)
        }

        getScreenSize()
        setUI()

        return binding.root
    }

    private fun setUI() {
        binding.tvSignup.setOnClickListener {
            findNavController().navigate(R.id.login_to_signup)
        }

        binding.btnLogin.setOnClickListener {
            handleLoginClick()
        }

        binding.tvErrPw.setOnClickListener {
            Toast.makeText(context, """
                Password must contain : 
                - at least 1 lower character. 
                - at least 1 upper character.
                - at least 1 digit.
                - at least 1 one symbol.
                - at least 8 characters long.
            """.trimIndent(), Toast.LENGTH_SHORT).show()
        }

        binding.edtEmail.addTextChangedListener { text ->

            val email = binding.edtEmail.text.toString()
            val pw = binding.edtPw.text.toString()

//            Log.e("AAAA", """
//                email: $email
//                valid: ${Patterns.EMAIL_ADDRESS.matcher(email).matches()}
//            """.trimIndent())

            handleValidate(email, pw)

        }

        binding.edtPw.addTextChangedListener { text ->
            val email = binding.edtEmail.text.toString()
            val pw = binding.edtPw.text.toString()
//
//            Log.e("AAAA", """
//                pw: $pw
//                valid: ${PASSWORD_REGEX.toRegex().matches(pw)}
//            """.trimIndent())

            handleValidate(email, pw)
        }
    }

    private fun handleValidate(email: String, pw: String): Boolean {

        var isError = false

        if (email.isEmpty() && isShowErr) {
            isError = true
            binding.tvErrEmail.text = ERR_EMAIL_EMPTY
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches() && isShowErr){
            isError = true
            binding.tvErrEmail.text = ERR_EMAIL_NOT_VALID
        }else{
            binding.tvErrEmail.text = ""
        }

        if (pw.isEmpty() && isShowErr) {
            isError = true
            binding.tvErrPw.text = ERR_PW_EMPTY
        }else if(!PASSWORD_REGEX.toRegex().matches(pw) && isShowErr){
            isError = true
            binding.tvErrPw.text = ERR_PW_NOT_VALID
        }else{
            binding.tvErrPw.text = ""
        }

        return isError
    }

    private fun handleLoginClick() {

        isShowErr = true

        val email = binding.edtEmail.text.toString()
        val pw = binding.edtPw.text.toString()

        if (!handleValidate(email, pw)) {
            userViewModal.checkLogin(email, pw).observe(viewLifecycleOwner) { count ->
                if (count > 0) {
                    findNavController().navigate(R.id.openHome)
                } else {
                    Toast.makeText(context, "Incorrect email address or password", Toast.LENGTH_SHORT).show()
                }
            }

//                userViewModal.checkLogin2(email, pw){ result ->
//                    if(result){
//                        findNavController().navigate(R.id.openHome)
//                    }else{
//                        Toast.makeText(context, "Sai thong tin", Toast.LENGTH_SHORT).show()
//                    }
//                }
        }
    }

    private fun getScreenSize() {
        val metrics = WindowMetricsCalculator.getOrCreate()
            .computeCurrentWindowMetrics(context as Activity)

        widthDp = metrics.bounds.width() / resources.displayMetrics.density
        heightDp = metrics.bounds.height() / resources.displayMetrics.density

        binding.edtEmail.textSize = heightDp * 0.018f
        binding.edtPw.textSize = heightDp * 0.018f

    }

}