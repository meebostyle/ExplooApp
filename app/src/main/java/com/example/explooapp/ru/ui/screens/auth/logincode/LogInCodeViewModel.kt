package com.example.explooapp.ru.ui.screens.auth.logincode

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LogInCodeViewModel : ViewModel() {


    private var _isFocused = MutableStateFlow<Boolean>(false)
    val isFocused = _isFocused.asStateFlow()


    fun setFocus() {
        _isFocused.value = true
    }

    fun clearFocus() {
        _isFocused.value = false
    }


}

