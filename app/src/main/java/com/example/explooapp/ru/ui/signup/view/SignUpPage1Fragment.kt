package com.example.explooapp.ru.ui.signup.view

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
import com.example.explooapp.databinding.FragmentSignUpPage1Binding
import com.example.explooapp.ru.ui.base.BaseFragment
import com.example.explooapp.ru.ui.signup.viewModel.SignUpPage1ViewModel
import com.example.explooapp.ru.utils.getDefaultNavOptions
import kotlinx.coroutines.launch


class SignUpPage1Fragment : BaseFragment<FragmentSignUpPage1Binding>() {


    private val navController by lazy { findNavController() }
    private val viewModel: SignUpPage1ViewModel by viewModels()


    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignUpPage1Binding {
        return FragmentSignUpPage1Binding.inflate(inflater, container, false)
    }


    override fun configureView() {
        super.configureView()
        with(binding) {
            etSurnameSignUp.addTextChangedListener {
                etSurnameSignUp.setBackgroundResource(R.drawable.background_et_container)
            }
            etNameSignUp.addTextChangedListener {
                etNameSignUp.setBackgroundResource(R.drawable.background_et_container)
            }

            binding.tvBtnToLogIn.apply {
                setOnTouchListener { v, event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            scaleX = 0.9f
                            scaleY = 0.9f
                            v.performClick()
                            true
                        }

                        MotionEvent.ACTION_UP -> {

                            animate()
                                .scaleX(1f)
                                .scaleY(1f)
                                .setDuration(100)
                                .setInterpolator(OvershootInterpolator())
                                .withEndAction {

                                    navController.navigate(
                                        R.id.navigation_log_in,
                                        null,
                                        getDefaultNavOptions()
                                    )
                                }
                                .start()
                            true
                        }

                        MotionEvent.ACTION_CANCEL -> {

                            animate()
                                .scaleX(1f)
                                .scaleY(1f)
                                .setDuration(50)
                                .start()
                            true
                        }

                        else -> false
                    }
                }


            }

            etSurnameSignUp.addTextChangedListener {
                etSurnameSignUp.setBackgroundResource(R.drawable.background_et_container)
            }

            etNameSignUp.addTextChangedListener {
                etNameSignUp.setBackgroundResource(R.drawable.background_et_container)
            }

            btnSignUp.setOnClickListener {
                viewModel.checkInput(etSurnameSignUp.text.toString(), etNameSignUp.text.toString())
                viewLifecycleOwner.lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.isCorrectInput.collect { isCorrect ->
                            updateFields(isCorrect)
                        }
                    }
                }
            }

        }

    }

    private fun updateFields(isCorrect: Set<SignUpPage1ViewModel.CorrectInput>) {
        if (!isCorrect.contains(SignUpPage1ViewModel.CorrectInput.SURNAME)) {
            binding.etSurnameSignUp.setBackgroundResource(R.drawable.background_et_container_invalide_input)
        }
        if (!isCorrect.contains(SignUpPage1ViewModel.CorrectInput.NAME)) {
            binding.etNameSignUp.setBackgroundResource(R.drawable.background_et_container_invalide_input)
        }
        if (isCorrect.size == 2) {
            navController.navigate(R.id.navigation_sign_up_page2, null, getDefaultNavOptions())
        }
    }


}