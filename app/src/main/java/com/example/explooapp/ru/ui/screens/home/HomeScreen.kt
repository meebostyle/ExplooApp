package com.example.explooapp.ru.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.alfatesttask.ui.theme.Primary
import com.example.alfatesttask.ui.theme.onestFontFamily
import com.example.explooapp.R
import com.example.explooapp.ru.ui.UIKit.widgets.navigation.NavigationBar

@Composable
fun HomeScreen(){
    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(scrollState)
        .padding(horizontal = 4.dp)) {
        NavigationBar()
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp),
                    tint = Primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier.requiredWidth(200.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontFamily = onestFontFamily,
                                fontSize = 24.sp,
                                fontWeight = FontWeight(600),
                                color = ForegroundMuted
                            )
                        ) {
                            append("Добрый вечер, ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontFamily = onestFontFamily,
                                fontSize = 24.sp,
                                fontWeight = FontWeight(600),
                                color = Primary
                            )
                        ) {
                            append("Имя")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontFamily = onestFontFamily,
                                fontSize = 24.sp,
                                fontWeight = FontWeight(600),
                                color = ForegroundMuted
                            )
                        ) {
                            append("!")
                        }
                    }
                )
            }
            Icon(
                painter = painterResource(R.drawable.ic_plus),
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = ForegroundMuted
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Чем займемся сегодня?",
            fontFamily = onestFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight(400),
            color = ForegroundMuted
        )
    }
}



@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}