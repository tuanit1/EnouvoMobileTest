package com.example.enouvomobiletest.ui.exam1.auth.fragments

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.set
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.window.layout.WindowMetricsCalculator
import com.example.enouvomobiletest.R
import com.example.enouvomobiletest.data.model.User
import com.example.enouvomobiletest.databinding.FragmentSignupBinding
import com.example.enouvomobiletest.ui.exam1.auth.viewmodel.UserViewModal

class SignupFragment : Fragment() {

    lateinit var binding: FragmentSignupBinding
    private lateinit var userViewModal: UserViewModal
    private val ERR_NAME_EMPTY = "Name is empty"
    private val ERR_EMAIL_EMPTY = "Email is empty!"
    private val ERR_EMAIL_NOT_VALID = "Email is not valid"
    private val ERR_PW_EMPTY = "Password is empty!"
    private val ERR_PW_NOT_VALID = "Password is not valid"
    private val PASSWORD_REGEX =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}\$"
    private var isShowErr: Boolean = false
    private var widthDp: Float = 0f
    private var heightDp: Float = 0f
    private var isSignup: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSignupBinding.inflate(inflater, container, false)

        userViewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(context?.applicationContext as Application)
        )[UserViewModal::class.java]

        setUI()
        getScreenSize()

        return binding.root
    }

    private fun setUI() {
        binding.tvBack.setOnClickListener {
            findNavController().navigate(R.id.signup_to_login)
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

        binding.btnLogin.setOnClickListener {
            handleSignupClick()
        }

        binding.edtEmail.addTextChangedListener { text ->

            val email = binding.edtEmail.text.toString()
            val pw = binding.edtPw.text.toString()
            val name = binding.edtName.text.toString()

            handleValidate(name,email, pw)

        }

        binding.edtPw.addTextChangedListener { text ->

            val email = binding.edtEmail.text.toString()
            val pw = binding.edtPw.text.toString()
            val name = binding.edtName.text.toString()

            handleValidate(name,email, pw)

        }

        binding.edtName.addTextChangedListener { text ->

            val email = binding.edtEmail.text.toString()
            val pw = binding.edtPw.text.toString()
            val name = binding.edtName.text.toString()

            handleValidate(name,email, pw)

        }

    }

    private fun getScreenSize() {
        val metrics = WindowMetricsCalculator.getOrCreate()
            .computeCurrentWindowMetrics(context as Activity)

        widthDp = metrics.bounds.width() / resources.displayMetrics.density
        heightDp = metrics.bounds.height() / resources.displayMetrics.density

        binding.edtName.textSize = heightDp * 0.018f
        binding.edtEmail.textSize = heightDp * 0.018f
        binding.edtPw.textSize = heightDp * 0.018f

    }

    private fun handleSignupClick() {

        isShowErr = true

        val email = binding.edtEmail.text.toString()
        val pw = binding.edtPw.text.toString()
        val name = binding.edtName.text.toString()

        if (!handleValidate(name, email, pw)) {

            userViewModal.checkUserExist(email).observe(viewLifecycleOwner) { count ->
                if(count == 0) {
                    userViewModal.insertUser(User(null, name, email, pw)){

                        isSignup = true

                        findNavController().navigate(R.id.signup_to_login)

                        Toast.makeText(context, "Sign up successful", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    if (!isSignup) Toast.makeText(context, "This email is already in use", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

    private fun handleValidate(name: String, email: String, pw: String): Boolean {

        var isError = false

        if (name.isEmpty() && isShowErr) {
            isError = true
            binding.tvErrName.text = ERR_NAME_EMPTY
        } else {
            binding.tvErrName.text = ""
        }

        if (email.isEmpty() && isShowErr) {
            isError = true
            binding.tvErrEmail.text = ERR_EMAIL_EMPTY
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() && isShowErr) {
            isError = true
            binding.tvErrEmail.text = ERR_EMAIL_NOT_VALID
        } else {
            binding.tvErrEmail.text = ""
        }

        if (pw.isEmpty() && isShowErr) {
            isError = true
            binding.tvErrPw.text = ERR_PW_EMPTY
        } else if (!PASSWORD_REGEX.toRegex().matches(pw) && isShowErr) {
            isError = true
            binding.tvErrPw.text = ERR_PW_NOT_VALID
        } else {
            binding.tvErrPw.text = ""
        }

        return isError
    }

}