package com.example.explooapp.ru.ui.logIn.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.explooapp.databinding.FragmentLogInNewPasswordBinding
import com.example.explooapp.ru.ui.base.BaseFragment

class LogInNewPasswordFragment : BaseFragment<FragmentLogInNewPasswordBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLogInNewPasswordBinding {
        return FragmentLogInNewPasswordBinding.inflate(inflater, container, false)
    }

    override fun configureView() {
        super.configureView()
    }

}