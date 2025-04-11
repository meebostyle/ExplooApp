package com.example.explooapp.ru.ui.signup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.explooapp.R
import com.example.explooapp.databinding.FragmentSignUpCodeBinding
import com.example.explooapp.ru.ui.base.BaseFragment

class SignUpCodeFragment : BaseFragment<FragmentSignUpCodeBinding>() {


    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignUpCodeBinding {
        return FragmentSignUpCodeBinding.inflate(inflater, container, false)
    }

    override fun configureView() {
        super.configureView()
        val animBuilder = NavOptions.Builder()
            .setEnterAnim(R.anim.anim_fragment_enter)
            .setExitAnim(R.anim.fragment_exit)
            .setPopEnterAnim(R.anim.fragment_pop_enter)
            .setPopExitAnim(R.anim.fragment_pop_exit)

        val navController = findNavController()

        binding.btnSignUp.setOnClickListener {
            navController.navigate(R.id.navigation_sign_up_account_type, null, animBuilder.build())
        }

    }

}