package com.example.alfatesttask.ui.theme


//
//private val DarkColorScheme = darkColorScheme(
//    primary = PrimaryGreen,
//    background = Background
//
//)
//
//
//private val LightColorScheme = lightColorScheme(
//    primary = PrimaryGreen,
//    background = DarkBackground
//
//
//)
//
//// Светлая тема
//private val LightExtendedColors = ExtendedColors(
//    totalBlack = TotalBlack,
//    primaryGreen = PrimaryGreen,
//    hoverGreen = HoverGreen,
//    primaryDarkBlue = PrimaryDarkBlue,
//    invalidColor = InvalidColor,
//    foreground = Foreground,
//    background = Background,
//    inputGrey = InputGrey,
//    cardsColor = CardsColor,
//    secondaryText = SecondaryText,
//)
//
//// Тёмная тема
//private val DarkExtendedColors = ExtendedColors(
//    totalBlack = TotalBlack,
//    primaryGreen = PrimaryGreen,
//    hoverGreen = HoverGreen,
//    primaryDarkBlue = PrimaryDarkBlue,
//    invalidColor = InvalidColor,
//    foreground = DarkForeground,
//    background = DarkBackground,
//    inputGrey = DarkInputGrey,
//    cardsColor = DarkCardsColor,
//    secondaryText = SecondaryText,
//)
//
//val LocalExtendedColors = staticCompositionLocalOf { LightExtendedColors }
//
//val MaterialTheme.explooColors: ExtendedColors
//    @Composable
//    get() = LocalExtendedColors.current
//
//
//@Composable
//fun ExplooTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    dynamicColor: Boolean = true,
//    content: @Composable () -> Unit
//) {
//    val extendedColors = if (darkTheme) DarkExtendedColors else LightExtendedColors
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//
//    }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//    ){
//        CompositionLocalProvider(
//            LocalExtendedColors provides extendedColors,
//            content = content
//        )
//    }
//}