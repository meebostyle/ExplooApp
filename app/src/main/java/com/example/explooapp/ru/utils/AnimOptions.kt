package com.example.explooapp.ru.utils



import androidx.navigation.NavOptions
import com.example.explooapp.R

fun getDefaultNavOptions(): NavOptions{
    return NavOptions.Builder()
        .setEnterAnim(R.anim.anim_fragment_enter)
        .setExitAnim(R.anim.fragment_exit)
        .setPopEnterAnim(R.anim.fragment_pop_enter)
        .setPopExitAnim(R.anim.fragment_pop_exit)
        .build()
}
