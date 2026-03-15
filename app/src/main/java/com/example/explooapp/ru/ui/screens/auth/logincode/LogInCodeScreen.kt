package com.example.explooapp.ru.ui.screens.auth.logincode


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alfatesttask.ui.theme.Foreground
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.alfatesttask.ui.theme.Primary
import com.example.alfatesttask.ui.theme.PrimaryShadow
import com.example.alfatesttask.ui.theme.onestFontFamily
import com.example.explooapp.R
import com.example.explooapp.ru.ui.LocalNavigationManager
import com.example.explooapp.ru.ui.UIKit.items.RectangleNextButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun LogInCodeScreen(
    modifier: Modifier = Modifier,
    viewModel: LogInCodeViewModel = viewModel()
) {
    val focusManager = LocalFocusManager.current
    LocalNavigationManager.current
    Box(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                viewModel.clearFocus()
                focusManager.clearFocus()
            },
        contentAlignment = Alignment.TopCenter
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_exploo_logo_full),
                contentDescription = "logo"
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.33f))
            Text(
                text = "Добро пожаловать!",
                fontFamily = onestFontFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight(600)
            )
            Spacer(modifier = Modifier.height(4.dp))
            var email = "vlad20062003@yandex.ru"
            Text(
                text = "Код отправлен на $email",
                fontFamily = onestFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight(300),
                color = ForegroundMuted
            )
            Spacer(modifier = Modifier.height(12.dp))
            CodeInputField(viewModel = viewModel)
            Spacer(modifier = Modifier.height(12.dp))
            RectangleNextButton(
                fontFamily = onestFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight(300),
                text = "Продолжить",
                modifier = Modifier
                    .wrapContentWidth()
                    .shadow(
                        elevation = 16.dp,
                        clip = false,
                        shape = RoundedCornerShape(12.dp),
                        spotColor = PrimaryShadow
                    ),
                shape = RoundedCornerShape(12.dp)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CodeInputField(
    onCodeComplete: (String) -> Unit = {},
    viewModel: LogInCodeViewModel = viewModel(),
) {
    var code by rememberSaveable { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    var isFocused = viewModel.isFocused.collectAsState().value
    val focusManager = LocalFocusManager.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    viewModel.setFocus()
                    focusRequester.requestFocus()
                },
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(6) { index ->
                CodeBox(
                    digit = code.getOrElse(index) { ' ' },
                    isFocused = code.length == index && isFocused,
                    isActive = code.length > index,
                    viewModel = viewModel
                )
            }
        }

        BasicTextField(
            value = code,
            onValueChange = { newValue ->
                if (newValue.length <= 6 && newValue.all { it.isDigit() }) {
                    code = newValue
                    viewModel.setFocus() // Поддерживаем фокус при вводе
                    if (newValue.length == 6) {
                        onCodeComplete(newValue)
                        // Опционально: снять фокус при завершении ввода
                        viewModel.clearFocus()
                        focusManager.clearFocus()
                    }
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(
                fontSize = 1.sp, // Невидимый текст
                color = Color.Transparent
            ),
            cursorBrush = SolidColor(Color.Transparent),
            modifier = Modifier
                .size(1.dp) // Невидимый размер
                .focusable()
                .focusRequester(focusRequester),
        )
    }

}

@Composable
fun CodeBox(
    digit: Char,
    isFocused: Boolean,
    isActive: Boolean,
    viewModel: LogInCodeViewModel = viewModel()
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(
                color = Color.White,
            ),
        contentAlignment = Alignment.Center
    )
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(contentAlignment = Alignment.Center) {
                if (!isFocused && !isActive) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .size(3.dp)
                            .background(ForegroundMuted)
                    ) {}
                }

                Text(
                    text = if (digit != ' ') digit.toString() else "",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier
                    .width(40.dp)
                    .height(1.dp)
                    .background(
                        color = when {
                            isFocused -> Primary // Синий когда в фокусе
                            isActive -> ForegroundMuted   // Черный когда цифра введена
                            else -> Foreground             // Серый когда пусто
                        }
                    )
            )
        }
        val cursorAlpha = remember { Animatable(0f) }

        LaunchedEffect(isFocused) {
            if (isFocused) {
                while (true) {
                    launch {
                        cursorAlpha.animateTo(
                            targetValue = 1f,
                            animationSpec = tween(700)
                        )
                    }
                    delay(700)
                    launch {
                        cursorAlpha.animateTo(
                            targetValue = 0f,
                            animationSpec = tween(400)
                        )
                    }
                    delay(700)
                }
            } else {
                cursorAlpha.snapTo(0f)
            }
        }


        if (isFocused) {
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(24.dp)
                    .background(Primary.copy(alpha = cursorAlpha.value))
            )
        }
    }
}