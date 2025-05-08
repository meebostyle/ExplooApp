package com.example.explooapp.ru.ui.signup.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpAccountTypeViewModel: ViewModel() {
    private val _accountType = MutableStateFlow<AccountType?>(null)
    val accountType: StateFlow<AccountType?> = _accountType.asStateFlow()

    private val _isMailingEnabled = MutableStateFlow(false)
    val isMailingEnabled: StateFlow<Boolean> = _isMailingEnabled.asStateFlow()

    fun selectAccountType(type: AccountType) {
        _accountType.value = type
    }

    fun toggleMailing() {
        _isMailingEnabled.value = !_isMailingEnabled.value
    }

    enum class AccountType { TUTOR, STUDENT }

}