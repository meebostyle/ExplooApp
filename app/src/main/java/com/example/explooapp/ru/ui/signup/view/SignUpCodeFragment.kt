package com.example.explooapp.ru.ui.signup.view

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.explooapp.R
import com.example.explooapp.databinding.FragmentSignUpCodeBinding
import com.example.explooapp.ru.ui.base.BaseFragment
import com.example.explooapp.ru.ui.signup.viewModel.SignUpCodeViewModel
import com.example.explooapp.ru.utils.getDefaultNavOptions
import kotlinx.coroutines.launch

class SignUpCodeFragment : BaseFragment<FragmentSignUpCodeBinding>() {

    private val viewModel: SignUpCodeViewModel by viewModels()
    private val navController: NavController by lazy { findNavController() }
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        override fun onTextChanged(
            s: CharSequence?,
            start: Int,
            before: Int,
            count: Int
        ) {
            binding.pvCode.setItemBackground(resources.getDrawable(R.drawable.background_et_container))
        }

        override fun afterTextChanged(s: Editable?) {}

    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignUpCodeBinding {
        return FragmentSignUpCodeBinding.inflate(inflater, container, false)
    }

    override fun configureView() {
        super.configureView()


        with(binding) {
            pvCode.addTextChangedListener(textWatcher)
            btnSignUp.setOnClickListener {
                checkCode(pvCode.text.toString())
            }
        }

    }

    private fun checkCode(code: String) {
        viewModel.checkCode(code)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isCodeCorrect.collect { isCorrect ->
                    updateField(isCorrect)
                }
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun updateField(isCorrect: Boolean) {
        when (isCorrect) {
            true -> navController.navigate(
                R.id.navigation_sign_up_account_type,
                null,
                getDefaultNavOptions()
            )

            false -> binding.pvCode.setItemBackground(resources.getDrawable(R.drawable.background_et_container_invalide_input))
        }
    }

}