package com.example.explooapp.ru.ui.screens.auth.loginmail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LogInCodeViewModel : ViewModel() {

    private val _isLoadingScreenVisible = MutableStateFlow(false)
    val isLoadingScreenVisible = _isLoadingScreenVisible.asStateFlow()


}