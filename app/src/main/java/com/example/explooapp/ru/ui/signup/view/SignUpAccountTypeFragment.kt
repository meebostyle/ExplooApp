package com.example.explooapp.ru.ui.signup.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.explooapp.R
import com.example.explooapp.databinding.FragmentSignUpAccountTypeBinding
import com.example.explooapp.ru.ui.base.BaseFragment
import com.example.explooapp.ru.ui.signup.viewModel.SignUpAccountTypeViewModel
import kotlinx.coroutines.launch

class SignUpAccountTypeFragment : BaseFragment<FragmentSignUpAccountTypeBinding>() {


    private val viewModel: SignUpAccountTypeViewModel by viewModels()


    private val tvText =
        "Регистрируясь, я понимаю принимаю Условия пользования и Политику конфиденциальности."


    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignUpAccountTypeBinding {
        return FragmentSignUpAccountTypeBinding.inflate(inflater, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun configureView() {
        super.configureView()
        val spannable = SpannableString(tvText)

        val privacySpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Log.i("span", "Условия пользования")
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ds.linkColor // Сохраняем цвет ссылки
                ds.isUnderlineText = false
            }
        }

        val termsSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Log.i("span", "Политику конфиденциальности.")
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ds.linkColor
                ds.isUnderlineText = false
            }
        }

        spannable.setSpan(
            privacySpan,
            tvText.indexOf("Условия пользования"),
            tvText.indexOf("Условия пользования") + "Условия пользования".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannable.setSpan(
            termsSpan,
            tvText.indexOf("Политику конфиденциальности."),
            tvText.indexOf("Политику конфиденциальности.") + "Политику конфиденциальности.".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        with(binding) {
            tvPolicy.apply {
                text = spannable
                movementMethod = LinkMovementMethod.getInstance()
                highlightColor = Color.TRANSPARENT
                linksClickable = true

            }

            tvMailing.setOnClickListener {
                viewModel.toggleMailing()
            }

            btnChoiceTutor.setOnClickListener {
                viewModel.selectAccountType(SignUpAccountTypeViewModel.AccountType.TUTOR)
            }
            btnChoiceStudent.setOnClickListener {
                viewModel.selectAccountType(SignUpAccountTypeViewModel.AccountType.STUDENT)
            }

        }





        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.accountType.collect { type ->
                    updateButtonSelection(type)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isMailingEnabled.collect { isChecked ->
                    binding.cbMailing.isChecked = isChecked
                }
            }
        }

    }

    private fun updateButtonSelection(type: SignUpAccountTypeViewModel.AccountType?) {
        when (type) {
            SignUpAccountTypeViewModel.AccountType.TUTOR -> {
                with(binding) {
                    btnChoiceTutor.setBackgroundColor(
                        ContextCompat.getColor(
                            requireView().context,
                            R.color.primary_green
                        )
                    )
                    btnChoiceTutor.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.cards_color
                        )
                    )
                    btnChoiceStudent.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.input_grey
                        )
                    )
                    btnChoiceStudent.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.foreground
                        )
                    )
                }

            }

            SignUpAccountTypeViewModel.AccountType.STUDENT -> {
                with(binding) {
                    btnChoiceTutor.setBackgroundColor(
                        ContextCompat.getColor(
                            requireView().context,
                            R.color.input_grey
                        )
                    )
                    btnChoiceTutor.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.foreground
                        )
                    )
                    btnChoiceStudent.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.primary_green
                        )
                    )
                    btnChoiceStudent.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.cards_color
                        )
                    )
                }
            }

            null -> {}
        }
    }


}