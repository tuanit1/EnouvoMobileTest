package com.example.enouvomobiletest.ui.exam1.auth.login.fragments

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.window.layout.WindowMetricsCalculator
import com.example.enouvomobiletest.R
import com.example.enouvomobiletest.databinding.FragmentLoginBinding
import com.example.enouvomobiletest.extension.PASSWORD_REGEX
import com.example.enouvomobiletest.ui.exam1.auth.viewmodel.AuthViewModal
import com.example.enouvomobiletest.util.Constant

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var userViewModal: AuthViewModal

    private var isShowErr: Boolean = false
    private var widthDp: Float = 0f
    private var heightDp: Float = 0f


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        userViewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(context?.applicationContext as Application)
        )[AuthViewModal::class.java]

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

        binding.edtEmail.addTextChangedListener {
            handleValidate()
        }

        binding.edtPw.addTextChangedListener {
            handleValidate()
        }
    }

    private fun handleValidate(): Boolean {

        val email = binding.edtEmail.text.toString()
        val pw = binding.edtPw.text.toString()

        var isError = false

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

    private fun handleLoginClick() {

        isShowErr = true

        val email = binding.edtEmail.text.toString()
        val pw = binding.edtPw.text.toString()

        if (!handleValidate()) {
            userViewModal.checkLogin(email, pw).observe(viewLifecycleOwner) { list ->

                if (list.isNotEmpty()) {
                    list[0].user_id?.let {
                        Constant.mUserID = it
                    }
                    findNavController().navigate(R.id.openHome)
                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.credentialIncorrect),
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
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