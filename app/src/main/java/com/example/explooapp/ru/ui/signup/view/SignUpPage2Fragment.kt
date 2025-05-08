package com.example.explooapp.ru.ui.signup.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.explooapp.R
import com.example.explooapp.databinding.FragmentSignUpPage2Binding
import com.example.explooapp.ru.ui.base.BaseFragment
import com.example.explooapp.ru.ui.signup.viewModel.SignUpPage2ViewModel
import com.example.explooapp.ru.utils.getDefaultNavOptions
import kotlinx.coroutines.launch

class SignUpPage2Fragment : BaseFragment<FragmentSignUpPage2Binding>() {

    private val viewModel: SignUpPage2ViewModel by viewModels()
    private val navController by lazy { findNavController() }
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignUpPage2Binding {
        return FragmentSignUpPage2Binding.inflate(inflater, container, false)
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun configureView() {
        super.configureView()
        with(binding) {
            etPhoneSignUp.addTextChangedListener {
                etPhoneSignUp.setBackgroundResource(R.drawable.background_et_container)
            }
            etMailSignUp.addTextChangedListener {
                etMailSignUp.setBackgroundResource(R.drawable.background_et_container)
            }
            btnSignUp.setOnClickListener {
                viewModel.checkInput(etPhoneSignUp.text.toString(), etMailSignUp.text.toString())

                viewLifecycleOwner.lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.isCorrectInput.collect { isCorrect ->
                            updateFields(isCorrect)
                        }
                    }
                }
            }
            etMailSignUp.addTextChangedListener {
                etMailSignUp.setBackgroundResource(R.drawable.background_et_container)
            }
        }
        binding.btnToSignUp.apply {
            setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        v.scaleX = 0.9f
                        v.scaleY = 0.9f
                        true
                    }

                    MotionEvent.ACTION_UP -> {
                        animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(50)
                            .setInterpolator(OvershootInterpolator())
                            .withEndAction {
                                navController.navigate(
                                    R.id.navigation_log_in,
                                    null,
                                    getDefaultNavOptions()
                                )
                            }
                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun updateFields(isCorrect: Set<SignUpPage2ViewModel.CorrectInput>) {
        if (!isCorrect.contains(SignUpPage2ViewModel.CorrectInput.PHONE)) {
            binding.etPhoneSignUp.setBackgroundResource(R.drawable.background_et_container_invalide_input)
        }
        if (!isCorrect.contains(SignUpPage2ViewModel.CorrectInput.MAIL)) {
            binding.etMailSignUp.setBackgroundResource(R.drawable.background_et_container_invalide_input)
        }
        if (isCorrect.size == 2) {
            navController.navigate(R.id.navigation_sign_up_code, null, getDefaultNavOptions())
        }
    }


}