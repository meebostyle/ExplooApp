package com.example.explooapp.ru.ui.screens.auth.loginmail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LogInMailViewModel : ViewModel() {

    private val _isLoadingScreenVisible = MutableStateFlow(false)
    val isLoadingScreenVisible = _isLoadingScreenVisible.asStateFlow()
    var email by mutableStateOf("")
        private set



    fun onTextChange(value: String){
        email = value
    }

}