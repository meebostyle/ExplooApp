package com.example.explooapp.ru.ui.logIn.view

import android.annotation.SuppressLint
import android.text.InputFilter
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.explooapp.R
import com.example.explooapp.databinding.FragmentLogInBinding
import com.example.explooapp.ru.ui.base.BaseFragment
import com.example.explooapp.ru.ui.logIn.viewmodel.LogInViewModel
import com.example.explooapp.ru.utils.getDefaultNavOptions
import com.vicmikhailau.maskededittext.addMaskedTextChangedListener
import kotlinx.coroutines.launch

class LogInFragment : BaseFragment<FragmentLogInBinding>() {


    private val navController: NavController by lazy { findNavController() }

    //    private val testRepository = TestRepository()
    private val viewModel: LogInViewModel by viewModels()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLogInBinding {
        return FragmentLogInBinding.inflate(inflater, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun configureView() {
        super.configureView()

        val inputFilter = InputFilter { source, start, end, dest, dstart, dend ->
            if (dstart == 0 && source.isNotEmpty() && source[0] == '8') {
                return@InputFilter ""
            }
            null
        }


        with(binding) {
            tvBtnToSignUp.apply {
                setOnTouchListener { v, event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
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
                                    navController.navigate(
                                        R.id.navigation_sign_up_page1,
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
            btnLogIn.setOnClickListener {
                Log.i("TAG", "${etPhoneLogIn.text}, ${etPhoneLogIn.text?.length}")
                viewModel.checkPhone(etPhoneLogIn.text.toString())

                viewLifecycleOwner.lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.isPhoneCorrect.collect { isCorrect ->
                            updateField(isCorrect)
                        }
                    }
                }
            }

            etPhoneLogIn.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus && etPhoneLogIn.text.isNullOrEmpty()) {
                    etPhoneLogIn.setText("+7 (")
                }
                etPhoneLogIn.setBackgroundResource(R.drawable.background_et_container)
            }

            etPhoneLogIn.addMaskedTextChangedListener {
                run { etPhoneLogIn.setBackgroundResource(R.drawable.background_et_container) }
            }

            etPhoneLogIn.filters = arrayOf(inputFilter)
        }


//        binding.btnLogIn.setOnClickListener {
//
//            testRepository.getTickets(0, 10, "101")
//                .enqueue(
//                    object : Callback<TestResponse> {
//                        override fun onResponse(
//                            p0: Call<TestResponse>,
//                            p1: Response<TestResponse>
//                        ) {
//                            Log.e("TAG", "Success ${p1.body()}")
//                        }
//
//                        override fun onFailure(p0: Call<TestResponse>, p1: Throwable) {
//                            Log.e("TAG", "Error $p1")
//                        }
//
//                    }
//                )
//        }
    }

    private fun updateField(isCorrect: Boolean) {
        when (isCorrect) {
            true -> {
                navController.navigate(R.id.navigation_log_in_code)
            }

            false -> {
                binding.etPhoneLogIn.setBackgroundResource(R.drawable.background_et_container_invalide_input)
            }
        }
    }
}