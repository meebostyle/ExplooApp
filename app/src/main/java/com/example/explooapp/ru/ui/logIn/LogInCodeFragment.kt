package com.example.explooapp.ru.ui.logIn

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.explooapp.R
import com.example.explooapp.databinding.FragmentLogInCodeBinding
import com.example.explooapp.ru.ui.base.BaseFragment

class LogInCodeFragment : BaseFragment<FragmentLogInCodeBinding>() {

    lateinit var navController: NavController

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLogInCodeBinding {
        return FragmentLogInCodeBinding.inflate(inflater, container, false)
    }

    override fun configureView() {
        super.configureView()
        navController = findNavController()
        val animBuilder = NavOptions.Builder()
            .setEnterAnim(R.anim.anim_fragment_enter)
            .setExitAnim(R.anim.fragment_exit)
            .setPopEnterAnim(R.anim.fragment_pop_enter)
            .setPopExitAnim(R.anim.fragment_pop_exit)

        binding.btnLogIn.setOnClickListener {
            navController.navigate(
                R.id.navigation_log_in_password,
                null,
                animBuilder.build()
            )
        }
    }
}