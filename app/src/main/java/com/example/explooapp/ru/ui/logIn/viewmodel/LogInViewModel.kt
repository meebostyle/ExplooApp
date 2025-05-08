package com.example.explooapp.ru.ui.logIn.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LogInViewModel:ViewModel() {

    private val _isPhoneCorrect = MutableStateFlow(false)
    val isPhoneCorrect = _isPhoneCorrect.asStateFlow()

    fun checkPhone(phone: String){
        _isPhoneCorrect.value = (phone.length == 18)

    }
}