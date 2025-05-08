package com.example.explooapp.ru.ui.signup.viewModel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpPage2ViewModel: ViewModel() {
    private val _isCorrectInput = MutableStateFlow<MutableSet<CorrectInput>>(mutableSetOf())
    val isCorrectInput = _isCorrectInput.asStateFlow()

    fun checkInput (phone: String, mail: String){
        when (phone.length){
            18 -> _isCorrectInput.value.add(CorrectInput.PHONE)
            else -> _isCorrectInput.value.remove(CorrectInput.PHONE)
        }
        if (Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            _isCorrectInput.value.add(CorrectInput.MAIL)
        } else {
            _isCorrectInput.value.remove(CorrectInput.MAIL)
        }
    }

    enum class CorrectInput {PHONE, MAIL}
}