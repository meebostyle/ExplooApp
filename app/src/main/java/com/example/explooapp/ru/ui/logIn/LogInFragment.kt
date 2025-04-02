package com.example.explooapp.ru.ui.logIn

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.explooapp.R
import com.example.explooapp.databinding.FragmentLogInBinding
import com.example.explooapp.ru.ui.signUp.SignUpPage1Fragment
import com.vicmikhailau.maskededittext.addMaskedTextChangedListener

class LogInFragment:Fragment() {

    private var _binding:FragmentLogInBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        val animBuilder = NavOptions.Builder()
            .setEnterAnim(R.anim.fragment_enter)
            .setExitAnim(R.anim.fragment_exit)
            .setPopEnterAnim(R.anim.fragment_pop_enter)
            .setPopExitAnim(R.anim.fragment_pop_exit)
        binding.tvBtnToSignUp.apply {
            setOnTouchListener { v, event ->
                when(event.action){
                    MotionEvent.ACTION_DOWN ->{
                        scaleX = 0.9f
                        scaleY = 0.9f
                        true
                    }

                    MotionEvent.ACTION_UP -> {
                        animate()
                            .scaleY(1f)
                            .scaleX(1f)
                            .setDuration(50)
                            .setInterpolator(OvershootInterpolator())
                            .withEndAction {
                                navController.navigate(R.id.navigation_sign_up_page1, null, animBuilder.build())
                            }

                        true
                    }
                    else -> false
                }
            }
        }
        val inputFilter = InputFilter { source, start, end, dest, dstart, dend ->
            if (dstart == 0 && source.isNotEmpty() && source[0] == '8') {
                return@InputFilter ""
            }
            null
        }
        binding.etPhoneLogIn.setOnFocusChangeListener { _, hasFocus ->

            if (hasFocus && binding.etPhoneLogIn.text.isNullOrEmpty()) {
                binding.etPhoneLogIn.setText("+7 (")
            }
        }
        binding.etPhoneLogIn.addMaskedTextChangedListener {
            binding.run { etPhoneLogIn.setBackgroundResource(R.drawable.background_et_container) }
        }

        binding.etPhoneLogIn.filters = arrayOf(inputFilter)
        binding.btnLogIn.setOnClickListener {

            if (validatePhoneNumber()){
                navController.navigate(R.id.navigation_log_in_code,
                    null,
                    animBuilder.build())

            } else{
                binding.etPhoneLogIn.setBackgroundResource(R.drawable.background_et_container_invalide_input)

            }



        }

    }

    private fun validatePhoneNumber(): Boolean {
        return (binding.etPhoneLogIn.unMaskedText?.length == 10)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        @JvmStatic
        fun newInstance() = SignUpPage1Fragment()
    }
}