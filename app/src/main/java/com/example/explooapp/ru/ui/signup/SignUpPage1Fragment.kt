package com.example.explooapp.ru.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.explooapp.R
import com.example.explooapp.databinding.FragmentSignUpPage1Binding


class SignUpPage1Fragment : Fragment() {

    private var _binding: FragmentSignUpPage1Binding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpPage1Binding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    private fun validate(): Boolean {
        var result: Boolean
        with(binding) {
            result = (etSurnameSignUp.text.toString().isNotEmpty() && etNameSignUp.text.toString()
                .isNotEmpty())
        }
        if (!result) {
            hintContainer()
        }
        return result
    }

    private fun hintContainer() {
        with(binding) {
            if (etSurnameSignUp.text.toString().isEmpty()) {
                etSurnameSignUp.setBackgroundResource(R.drawable.background_et_container_invalide_input)
            } else {
                etNameSignUp.setBackgroundResource(R.drawable.background_et_container_invalide_input)
            }

        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        val animBuilder = NavOptions.Builder()
            .setEnterAnim(R.anim.anim_fragment_enter)
            .setExitAnim(R.anim.fragment_exit)
            .setPopEnterAnim(R.anim.fragment_pop_enter)
            .setPopExitAnim(R.anim.fragment_pop_exit)
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
                                        animBuilder.build()
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
            btnSignUp.setOnClickListener {

                if (validate()) {
                    navController.navigate(
                        R.id.navigation_sign_up_page2,
                        null,
                        animBuilder.build()
                    )
                }

            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignUpPage1Fragment()
    }


}