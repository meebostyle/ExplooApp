package com.example.explooapp.ru.ui.screens.welcome.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alfatesttask.ui.theme.FeatureSchedule
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.alfatesttask.ui.theme.Primary
import com.example.alfatesttask.ui.theme.PrimaryShadow
import com.example.alfatesttask.ui.theme.advakenSans
import com.example.alfatesttask.ui.theme.handwriteFontFamily
import com.example.alfatesttask.ui.theme.onestFontFamily
import com.example.explooapp.ru.ui.UIKit.backgounds.MathBackground
import com.example.explooapp.ru.ui.UIKit.items.NextButton
import com.example.explooapp.ru.ui.UIKit.items.WavyLine


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPage() {
    MathBackground()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .border(1.dp, ForegroundMuted.copy(alpha = 0.1f), RoundedCornerShape(24.dp))
                    .background(PrimaryShadow.copy(alpha = 0.1f))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "Открытое тестирование",
                    color = Primary,
                    fontFamily = onestFontFamily,
                    fontWeight = FontWeight(600),
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Единое пространство для",
                fontFamily = advakenSans,
                fontWeight = FontWeight(400),
                fontSize = 20.sp

            )
            Text(
                text = "ОНЛАЙН-РЕПЕТИТОРОВ",
                fontFamily = handwriteFontFamily,
                fontWeight = FontWeight(600),
                fontSize = 28.sp

            )
            WavyLine(modifier = Modifier.fillMaxWidth(0.85f))
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = buildAnnotatedString {
                    append("Расписание, доска, задания, чат — ")

                    withStyle(style = SpanStyle(color = FeatureSchedule)) {
                        append("всё вместе.")
                    }

                    append("\nЧтобы вы были с ")

                    withStyle(style = SpanStyle(color = FeatureSchedule)) {
                        append("учениками")
                    }

                    append(", а не с ")

                    withStyle(style = SpanStyle(color = FeatureSchedule)) {
                        append("вкладками.")
                    }
                },
                fontFamily = handwriteFontFamily,
                fontSize = 16.sp,
                color = ForegroundMuted
            )
            Spacer(modifier = Modifier.height(12.dp))
            NextButton(
                modifier = Modifier
                    .wrapContentWidth()
                    .shadow(
                        elevation = 16.dp,
                        clip = false,
                        shape = ShapeDefaults.ExtraLarge,
                        spotColor = PrimaryShadow
                    ),
                text = "ПОПРОБОВАТЬ БЕСПЛАТНО"
            )
        }

    }
}




