package com.example.explooapp.ru.ui.logIn.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LogInForgotPasswordCodeViewModel: ViewModel() {
    private val _isCorrectCode = MutableStateFlow(false)
    val isCorrectCode = _isCorrectCode.asStateFlow()

    fun checkCode (code: String){
        _isCorrectCode.value = code.length == 4
    }
}