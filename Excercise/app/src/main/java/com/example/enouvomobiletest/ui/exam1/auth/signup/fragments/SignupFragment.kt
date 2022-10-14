package com.example.enouvomobiletest.ui.exam1.auth.signup.fragments

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.window.layout.WindowMetricsCalculator
import com.example.enouvomobiletest.R
import com.example.enouvomobiletest.data.model.User
import com.example.enouvomobiletest.databinding.FragmentSignupBinding
import com.example.enouvomobiletest.extension.PASSWORD_REGEX
import com.example.enouvomobiletest.ui.exam1.auth.viewmodel.AuthViewModal

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var userViewModal: AuthViewModal
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
        )[AuthViewModal::class.java]

        initView()
        initListener()


        return binding.root
    }

    private fun initListener() {
        binding.tvBack.setOnClickListener {
            findNavController().navigate(R.id.signup_to_login)
        }

        binding.tvErrPw.setOnClickListener {
            Toast.makeText(
                context, """
                Password must contain : 
                - at least 1 lower character. 
                - at least 1 upper character.
                - at least 1 digit.
                - at least 1 one symbol.
                - at least 8 characters long.
            """.trimIndent(), Toast.LENGTH_SHORT
            ).show()
        }

        binding.btnLogin.setOnClickListener {
            handleSignupClick()
        }

        binding.edtEmail.addTextChangedListener {

            val email = binding.edtEmail.text.toString()
            val pw = binding.edtPw.text.toString()
            val name = binding.edtName.text.toString()

            handleValidate(name, email, pw)

        }

        binding.edtPw.addTextChangedListener {

            val email = binding.edtEmail.text.toString()
            val pw = binding.edtPw.text.toString()
            val name = binding.edtName.text.toString()

            handleValidate(name, email, pw)

        }

        binding.edtName.addTextChangedListener {

            val email = binding.edtEmail.text.toString()
            val pw = binding.edtPw.text.toString()
            val name = binding.edtName.text.toString()

            handleValidate(name, email, pw)

        }
    }

    private fun initView() {
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
                if (count == 0) {
                    userViewModal.insertUser(User(null, name, email, pw)) {

                        isSignup = true

                        findNavController().navigate(R.id.signup_to_login)

                        Toast.makeText(
                            context,
                            getString(R.string.signupSuccess),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    if (!isSignup) Toast.makeText(
                        context,
                        getString(R.string.emailAlreadyUse),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun handleValidate(name: String, email: String, pw: String): Boolean {

        var isError = false

        if (name.isEmpty() && isShowErr) {
            isError = true
            binding.tvErrName.text = getString(R.string.errorNameEmpty)
        } else {
            binding.tvErrName.text = ""
        }

        if (email.isEmpty() && isShowErr) {
            isError = true
            binding.tvErrEmail.text = getString(R.string.errorEmailEmpty)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() && isShowErr) {
            isError = true
            binding.tvErrEmail.text = getString(R.string.errorEmailNotValid)
        } else {
            binding.tvErrEmail.text = ""
        }

        if (pw.isEmpty() && isShowErr) {
            isError = true
            binding.tvErrPw.text = getString(R.string.errorPasswordEmpty)
        } else if (!PASSWORD_REGEX.matches(pw) && isShowErr) {
            isError = true
            binding.tvErrPw.text = getString(R.string.errorPasswordNotValid)
        } else {
            binding.tvErrPw.text = ""
        }

        return isError
    }

}