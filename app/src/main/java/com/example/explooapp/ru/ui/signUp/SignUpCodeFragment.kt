package com.example.explooapp.ru.ui.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.explooapp.R
import com.example.explooapp.databinding.FragmentSignUpCodeBinding

class SignUpCodeFragment:Fragment() {

    private var _binding: FragmentSignUpCodeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpCodeBinding.inflate(inflater,container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val animBuilder = NavOptions.Builder()
            .setEnterAnim(R.anim.fragment_enter)
            .setExitAnim(R.anim.fragment_exit)
            .setPopEnterAnim(R.anim.fragment_pop_enter)
            .setPopExitAnim(R.anim.fragment_pop_exit)

        val navController = findNavController()

        binding.btnSignUp.setOnClickListener {
            navController.navigate(R.id.navigation_sign_up_account_type, null, animBuilder.build())
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    companion object{
        @JvmStatic
        fun newInstance() = SignUpPage1Fragment()
    }
}