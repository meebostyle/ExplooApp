package com.example.alfatesttask.ui.theme


import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val TotalBlack = Color(0xFF000000)
val PrimaryGreen = Color(0xFF509881)
val HoverGreen = Color(0xFF287B60)
val PrimaryDarkBlue = Color(0xFF214652)
val InvalidColor = Color(0xFFB80000)
val Foreground = Color(0xFF313131)
val Background = Color(0xFFF5F6F4)
val InputGrey = Color(0xFFEBEBEB)
val CardsColor = Color(0xFFFFFFFF)
val SecondaryText = Color(0xFFC6C6C6)

val DarkForeground = Color(0xFFE6E5E5)
val DarkBackground = Color(0xFF1A1A1A)
val DarkInputGrey = Color(0xFF4C4C4C)
val DarkCardsColor = Color(0xFF2F2F2F)


@Immutable
data class ExtendedColors(
    val totalBlack: Color,
    val primaryGreen: Color,
    val hoverGreen: Color,
    val primaryDarkBlue: Color,
    val invalidColor: Color,
    val foreground: Color,
    val background: Color,
    val inputGrey: Color,
    val cardsColor: Color,
    val secondaryText: Color,
)


