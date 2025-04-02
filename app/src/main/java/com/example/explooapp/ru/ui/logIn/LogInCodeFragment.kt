package com.example.explooapp.ru.ui.logIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.explooapp.R
import com.example.explooapp.databinding.FragmentLogInBinding
import com.example.explooapp.databinding.FragmentLogInCodeBinding

class LogInCodeFragment: Fragment() {
    var _binding: FragmentLogInCodeBinding? = null
    val binding get() = _binding!!
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInCodeBinding.inflate(inflater, container, false)
        val root:View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        val animBuilder = NavOptions.Builder()
            .setEnterAnim(R.anim.fragment_enter)
            .setExitAnim(R.anim.fragment_exit)
            .setPopEnterAnim(R.anim.fragment_pop_enter)
            .setPopExitAnim(R.anim.fragment_pop_exit)

        binding.btnLogIn.setOnClickListener {
            navController.navigate(R.id.navigation_log_in_password,
                null,
                animBuilder.build())
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        @JvmStatic
        fun newInstance() = LogInFragment()
    }

}