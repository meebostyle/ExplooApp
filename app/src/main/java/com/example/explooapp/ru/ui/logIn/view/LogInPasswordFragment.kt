package com.example.explooapp.ru.ui.logIn.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.explooapp.R
import com.example.explooapp.databinding.FragmentLogInPasswordBinding
import com.example.explooapp.ru.ui.base.BaseFragment
import com.example.explooapp.ru.utils.getDefaultNavOptions

class LogInPasswordFragment : BaseFragment<FragmentLogInPasswordBinding>() {
    private val navController: NavController by lazy {findNavController()}

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLogInPasswordBinding {
        return FragmentLogInPasswordBinding.inflate(inflater, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun configureView() {
        super.configureView()


        binding.tvBtnForgotPassword.apply {
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
                            .setInterpolator(OvershootInterpolator())
                            .setDuration(50)
                            .withEndAction {
                                navController.navigate(
                                    R.id.navigation_log_in_forgot_password_code,
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


}