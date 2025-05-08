package com.example.explooapp.ru.ui.signup.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpCodeViewModel:ViewModel() {
    private val _isCodeCorrect = MutableStateFlow (false)
    val isCodeCorrect = _isCodeCorrect.asStateFlow()

    fun checkCode (code: String){
        _isCodeCorrect.value = code.length == 4
    }
}