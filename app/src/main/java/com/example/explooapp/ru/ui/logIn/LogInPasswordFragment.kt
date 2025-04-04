package com.example.explooapp.ru.ui.logIn

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.explooapp.R
import com.example.explooapp.databinding.FragmentLogInPasswordBinding

class LogInPasswordFragment : Fragment() {

    private var _binding: FragmentLogInPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInPasswordBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animBuilder = NavOptions.Builder()
            .setEnterAnim(R.anim.anim_fragment_enter)
            .setExitAnim(R.anim.fragment_exit)
            .setPopEnterAnim(R.anim.fragment_pop_enter)
            .setPopExitAnim(R.anim.fragment_pop_exit)

        val navController = findNavController()


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


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}