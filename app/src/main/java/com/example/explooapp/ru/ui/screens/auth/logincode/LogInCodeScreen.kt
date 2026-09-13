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
import com.example.explooapp.ru.ui.UIKit.items.buttons.RectangleNextButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



// ---------- STATEFUL ----------
@Composable
fun LogInCodeScreen(
    email: String = "",
    modifier: Modifier = Modifier,
    viewModel: LogInCodeViewModel = viewModel(),
) {
    val focusManager = LocalFocusManager.current
    val isFocused by viewModel.isFocused.collectAsState()

    var code by rememberSaveable { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    LogInCodeContent(
        email = email,
        code = code,
        isFocused = isFocused,
        focusRequester = focusRequester,
        modifier = modifier,
        onScreenClick = {
            viewModel.clearFocus()
            focusManager.clearFocus()
        },
        onFieldClick = {
            viewModel.setFocus()
            focusRequester.requestFocus()
        },
        onCodeChange = { newValue ->
            if (newValue.length <= 6 && newValue.all { it.isDigit() }) {
                code = newValue
                viewModel.setFocus()
                if (newValue.length == 6) {
                    viewModel.clearFocus()
                    focusManager.clearFocus()
                }
            }
        },
        onContinueClick = {
            // viewModel.submit(code)
        }
    )
}


// ---------- STATELESS ----------
@Composable
fun LogInCodeContent(
    email: String,
    code: String,
    isFocused: Boolean,
    focusRequester: FocusRequester,
    onScreenClick: () -> Unit,
    onFieldClick: () -> Unit,
    onCodeChange: (String) -> Unit,
    onContinueClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onScreenClick() },
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
            Text(
                text = "Код отправлен на $email",
                fontFamily = onestFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight(300),
                color = ForegroundMuted
            )
            Spacer(modifier = Modifier.height(12.dp))

            CodeInputFieldContent(
                code = code,
                isFocused = isFocused,
                focusRequester = focusRequester,
                onCodeChange = onCodeChange,
                onFieldClick = onFieldClick,
            )

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
                shape = RoundedCornerShape(12.dp),
                onClick = onContinueClick
            )
        }
    }
}

// ---------- STATELESS (CODE FIELD)----------
@Composable
fun CodeInputFieldContent(
    code: String,
    isFocused: Boolean,
    focusRequester: FocusRequester,
    onCodeChange: (String) -> Unit,
    onFieldClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onFieldClick() },
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(6) { index ->
                CodeBox(
                    digit = code.getOrElse(index) { ' ' },
                    isFocused = code.length == index && isFocused,
                    isActive = code.length > index,
                )
            }
        }

        BasicTextField(
            value = code,
            onValueChange = onCodeChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(fontSize = 1.sp, color = Color.Transparent),
            cursorBrush = SolidColor(Color.Transparent),
            modifier = Modifier
                .size(1.dp)
                .focusable()
                .focusRequester(focusRequester),
        )
    }
}

// ---------- STATELESS  (CODE FIELD ITEMS)----------
@Composable
fun CodeBox(
    digit: Char,
    isFocused: Boolean,
    isActive: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(contentAlignment = Alignment.Center) {
                if (!isFocused && !isActive) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .size(3.dp)
                            .background(ForegroundMuted)
                    )
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
                            isFocused -> Primary
                            isActive -> ForegroundMuted
                            else -> Foreground
                        }
                    )
            )
        }

        val cursorAlpha = remember { Animatable(0f) }

        LaunchedEffect(isFocused) {
            if (isFocused) {
                while (true) {
                    launch {
                        cursorAlpha.animateTo(1f, animationSpec = tween(700))
                    }
                    delay(700)
                    launch {
                        cursorAlpha.animateTo(0f, animationSpec = tween(400))
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
private const val PREVIEW_EMAIL = "vlad20062003@yandex.ru"

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LogInCodeContentEmptyPreview() {
    LogInCodeContent(
        email = PREVIEW_EMAIL,
        code = "",
        isFocused = false,
        focusRequester = remember { FocusRequester() },
        onScreenClick = {},
        onFieldClick = {},
        onCodeChange = {},
        onContinueClick = {},
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LogInCodeContentFocusPreview() {
    LogInCodeContent(
        email = PREVIEW_EMAIL,
        code = "12",
        isFocused = true,
        focusRequester = remember { FocusRequester() },
        onScreenClick = {},
        onFieldClick = {},
        onCodeChange = {},
        onContinueClick = {},
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LogInCodeContentFilledPreview() {
    LogInCodeContent(
        email = PREVIEW_EMAIL,
        code = "123456",
        isFocused = false,
        focusRequester = remember { FocusRequester() },
        onScreenClick = {},
        onFieldClick = {},
        onCodeChange = {},
        onContinueClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun CodeBoxStatesPreview() {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        CodeBox(digit = ' ', isFocused = false, isActive = false) // пусто
        CodeBox(digit = ' ', isFocused = true,  isActive = false) // курсор
        CodeBox(digit = '7', isFocused = false, isActive = true)  // введена
    }
}