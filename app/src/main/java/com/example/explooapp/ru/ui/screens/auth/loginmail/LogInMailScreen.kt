package com.example.explooapp.ru.ui.screens.auth.loginmail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.alfatesttask.ui.theme.PrimaryShadow
import com.example.alfatesttask.ui.theme.onestFontFamily
import com.example.explooapp.R
import com.example.explooapp.ru.ui.LocalNavigationManager
import com.example.explooapp.ru.ui.UIKit.items.TextInputField
import com.example.explooapp.ru.ui.UIKit.items.buttons.RectangleNextButton
import com.example.explooapp.ru.ui.navigation.NavigationManager


// ---------- STATEFUL ----------
@Composable
fun LogInMailScreen(
    modifier: Modifier = Modifier,
    navManager: NavigationManager = LocalNavigationManager.current,
    viewModel: LogInMailViewModel = viewModel(),
) {
    LogInMailContent(
        modifier = modifier,
        text = viewModel.email,
        onTextChange = { viewModel.onTextChange(it)},
        onContinueClick = { navManager.navigateToLogInCode() }
    )
}

// ---------- STATELESS ----------
@Composable
fun LogInMailContent(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    onContinueClick: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                text = "Введите email для входа или регистрации",
                fontFamily = onestFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight(300),
                color = ForegroundMuted
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextInputField(
                value = text,
                onValueChange = onTextChange,
                placeholder = "example@exploo.ru",
                secondBorder = false
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
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LogInMailContentPreview() {
    var text by remember { mutableStateOf("") }
    LogInMailContent(
        text = text,
        onTextChange = { text = it },
        onContinueClick = {}
    )
}