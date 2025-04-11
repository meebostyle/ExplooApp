package com.example.explooapp.ru.ui.signup

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
import com.example.explooapp.R
import com.example.explooapp.databinding.FragmentSignUpAccountTypeBinding
import com.example.explooapp.ru.ui.base.BaseFragment

class SignUpAccountTypeFragment : BaseFragment<FragmentSignUpAccountTypeBinding>() {



    private var isTutor = false
    private var isStudent = false


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
                //  (widget as? TextView)?.text = widget.text
                Log.i("span", "Условия пользования")
                // Ваш код обработки клика
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ds.linkColor // Сохраняем цвет ссылки
                ds.isUnderlineText = false
            }
        }

        val termsSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                //     (widget as? TextView)?.text = widget.text
                Log.i("span", "Политику конфиденциальности.")
                // Ваш код обработки клика
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

        binding.tvPolicy.apply {
            text = spannable
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
            linksClickable = true

        }



        with(binding) {
            tvPolicy.text = spannable
            tvPolicy.movementMethod = LinkMovementMethod.getInstance()
            btnChoiceTutor.setOnClickListener {
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
                isTutor = true
                if (isStudent) {
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
                    isStudent = false
                }
                btnChoiceStudent.isClickable = true
                btnChoiceTutor.isClickable = false
                Log.i("flags", "Репетитор:${isTutor}, Ученик: ${isStudent}")
            }
            btnChoiceStudent.setOnClickListener {
                btnChoiceStudent.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.primary_green
                    )
                )
                btnChoiceStudent.setTextColor(
                    ContextCompat.getColor(
                        requireView().context,
                        R.color.cards_color
                    )
                )
                isStudent = true
                if (isTutor) {
                    btnChoiceTutor.setBackgroundColor(
                        ContextCompat.getColor(
                            requireView().context,
                            R.color.input_grey
                        )
                    )
                    btnChoiceTutor.setTextColor(
                        ContextCompat.getColor(
                            requireView().context,
                            R.color.foreground
                        )
                    )
                    isTutor = false
                }
                btnChoiceTutor.isClickable = true
                btnChoiceStudent.isClickable = false
                Log.i("flags", "Репетитор:${isTutor}, Ученик: ${isStudent}")
            }

            tvMailing.setOnClickListener {
                cbMailing.isChecked = !cbMailing.isChecked
            }


        }

    }



}