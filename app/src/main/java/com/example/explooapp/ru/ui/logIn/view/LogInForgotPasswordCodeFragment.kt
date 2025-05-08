package com.example.explooapp.ru.ui.logIn.view

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.explooapp.R
import com.example.explooapp.databinding.FragmentLogInForgotPasswordCodeBinding
import com.example.explooapp.ru.ui.base.BaseFragment
import com.example.explooapp.ru.ui.logIn.viewmodel.LogInForgotPasswordCodeViewModel
import com.example.explooapp.ru.utils.getDefaultNavOptions
import kotlinx.coroutines.launch

class LogInForgotPasswordCodeFragment : BaseFragment<FragmentLogInForgotPasswordCodeBinding>() {

    private val viewModel: LogInForgotPasswordCodeViewModel by viewModels()
    private val navController by lazy { findNavController() }
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.pvCode.setItemBackground(resources.getDrawable(R.drawable.item_background_code_input))
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentLogInForgotPasswordCodeBinding {
        return FragmentLogInForgotPasswordCodeBinding.inflate(inflater, container, false)
    }

    override fun configureView() {
        super.configureView()


        with(binding) {
            pvCode.addTextChangedListener(textWatcher)

            btnLogIn.setOnClickListener {
                viewModel.checkCode(pvCode.text.toString())
                viewLifecycleOwner.lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.isCorrectCode.collect { isCorrect ->
                            updateField(isCorrect)
                        }
                    }
                }
            }
        }


    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun updateField(isCorrect: Boolean) {
        if (isCorrect) {
            navController.navigate(
                R.id.navigation_log_in_new_password,
                null,
                getDefaultNavOptions()
            )
        } else {
            binding.pvCode.setItemBackground(resources.getDrawable(R.drawable.background_et_container_invalide_input))
        }
    }


}