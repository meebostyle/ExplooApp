package com.example.explooapp.ru.ui.logIn.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LogInCodeViewModel: ViewModel() {
    private val _isCodeCorrect = MutableStateFlow(false)
    val isCodeCorrect = _isCodeCorrect.asStateFlow()

    fun checkCode (code: String){
        _isCodeCorrect.value = code.length == 4
    }
}