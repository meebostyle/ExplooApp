package com.example.explooapp.ru.ui.logIn.view

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.explooapp.R
import com.example.explooapp.databinding.FragmentLogInCodeBinding
import com.example.explooapp.ru.ui.base.BaseFragment
import com.example.explooapp.ru.ui.logIn.viewmodel.LogInCodeViewModel
import com.example.explooapp.ru.utils.getDefaultNavOptions
import kotlinx.coroutines.launch

class LogInCodeFragment : BaseFragment<FragmentLogInCodeBinding>() {

    private val navController: NavController by lazy { findNavController() }
    private val viewModel: LogInCodeViewModel by viewModels()
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        @SuppressLint("UseCompatLoadingForDrawables")
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.pvCode.setItemBackground(resources.getDrawable(R.drawable.item_background_code_input))
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLogInCodeBinding {
        return FragmentLogInCodeBinding.inflate(inflater, container, false)
    }

    override fun configureView() {
        super.configureView()
        with(binding) {

            pvCode.addTextChangedListener(textWatcher)


            btnLogIn.setOnClickListener {
                Log.i("TAG", pvCode.text.toString() + " " + viewModel.isCodeCorrect.value)
                viewModel.checkCode(pvCode.text.toString())
                viewLifecycleOwner.lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.isCodeCorrect.collect { isCorrect ->
                            updateField(isCorrect)
                        }
                    }
                }
//            navController.navigate(
//                R.id.navigation_log_in_password,
//                null,
//                getDefaultNavOptions()
//            )
            }

        }

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun updateField(isCorrect: Boolean) {
        if (isCorrect) {
            navController.navigate(
                R.id.navigation_log_in_password,
                null,
                getDefaultNavOptions()
            )
        } else {
            Log.i("TAG", "incorrect")
            binding.pvCode.setItemBackground(resources.getDrawable(R.drawable.background_et_container_invalide_input))
        }
    }
}