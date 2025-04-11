package com.example.explooapp.ru.ui.signup

import android.annotation.SuppressLint
import android.util.Patterns
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.core.widget.addTextChangedListener
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.explooapp.R
import com.example.explooapp.databinding.FragmentSignUpPage2Binding
import com.example.explooapp.ru.ui.base.BaseFragment

class SignUpPage2Fragment : BaseFragment<FragmentSignUpPage2Binding>() {

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignUpPage2Binding {
        return FragmentSignUpPage2Binding.inflate(inflater, container, false)
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun configureView() {
        super.configureView()
        val animBuilder = NavOptions.Builder()
            .setEnterAnim(R.anim.anim_fragment_enter)
            .setExitAnim(R.anim.fragment_exit)
            .setPopEnterAnim(R.anim.fragment_pop_enter)
            .setPopExitAnim(R.anim.fragment_pop_exit)

        val navController = findNavController()


        with(binding) {
            btnSignUp.setOnClickListener {
                if (isEmailValid(etMailSignUp.text.toString())) {
                    navController.navigate(R.id.navigation_sign_up_code, null, animBuilder.build())
                } else {
                    etMailSignUp.setBackgroundResource(R.drawable.background_et_container_invalide_input)
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
                                    animBuilder.build()
                                )
                            }
                        true
                    }

                    else -> false
                }
            }
        }
    }




}