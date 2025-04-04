package com.example.explooapp.ru.ui.logIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.explooapp.R
import com.example.explooapp.databinding.FragmentLogInForgotPasswordCodeBinding

class LogInForgotPasswordCodeFragment : Fragment() {

    private var _binding: FragmentLogInForgotPasswordCodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInForgotPasswordCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val animBuilder = NavOptions.Builder()
            .setEnterAnim(R.anim.anim_fragment_enter)
            .setExitAnim(R.anim.fragment_exit)
            .setPopEnterAnim(R.anim.fragment_pop_enter)
            .setPopExitAnim(R.anim.fragment_pop_exit)

        binding.btnLogIn.setOnClickListener {
            navController.navigate(R.id.navigation_log_in_new_password, null, animBuilder.build())
        }

    }


}