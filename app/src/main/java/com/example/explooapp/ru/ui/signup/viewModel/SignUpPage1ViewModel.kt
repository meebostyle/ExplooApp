package com.example.explooapp.ru.ui.signup.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpPage1ViewModel: ViewModel() {
    private val _isCorrectInput = MutableStateFlow<MutableSet<CorrectInput>>(mutableSetOf())
    val isCorrectInput = _isCorrectInput.asStateFlow()


    fun checkInput (surname: String, name: String){
        when (surname.isEmpty()){
            true -> _isCorrectInput.value.remove(CorrectInput.SURNAME)
            else -> _isCorrectInput.value.add(CorrectInput.SURNAME)
        }
        when (name.isEmpty()){
            true -> _isCorrectInput.value.remove(CorrectInput.NAME)
            else -> _isCorrectInput.value.add(CorrectInput.NAME)
        }
    }
     enum class CorrectInput {NAME, SURNAME}
}