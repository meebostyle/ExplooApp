package com.example.alfatesttask.ui.theme


import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val TotalBlack = Color(0xFF000000)
val PrimaryGreen = Color(0xFF509881)
val HoverGreen = Color(0xFF287B60)
val PrimaryDarkBlue = Color(0xFF214652)
val InvalidColor = Color(0xFFB80000)

//val Foreground = Color(0xFF313131)
//val Background = Color(0xFFF5F6F4)
val InputGrey = Color(0xFFEBEBEB)
val CardsColor = Color(0xFFFFFFFF)
val SecondaryText = Color(0xFFC6C6C6)

val DarkForeground = Color(0xFFE6E5E5)
val DarkBackground = Color(0xFF1A1A1A)
val DarkInputGrey = Color(0xFF4C4C4C)
val DarkCardsColor = Color(0xFF2F2F2F)

val Background = Color(0xFFfaf8f5)
val Foreground = Color(0xFF222222) ///#222222FF
val ForegroundMuted = Color(0xFF969696)
val Primary = Color(0xFF009869)
val PrimaryShadow = Color(0x63009869) //#00986947
val PrimaryForeground = Color(0xFFedfdf5)
val Border = Color(0xE5e5e5e5) // или с прозрачностью: Color(0xD9e5e5e5)
val Surface = Color(0xC7ffffff)
val SurfaceStrong = Color(0xE0ffffff)
val Grid = Color(0x0F222222)
val GlassBg = Color(0xA6ffffff)
val GlassBorder = Color(0x14000000)
val FeatureSchedule = Color(0xFFFE9A00)
val FeatureBoards = Color(0xFF009dcf)
val FeatureAssignments = Color(0xFFef4b67)
val FeatureNotifications = Color(0xFF1ec189)
val FeatureVideocalls = Color(0xFF7d7df9)

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


