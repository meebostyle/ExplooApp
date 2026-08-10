package com.example.explooapp.ru.ui.UIKit.items.buttons

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alfatesttask.ui.theme.Foreground
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.alfatesttask.ui.theme.Primary
import com.example.alfatesttask.ui.theme.onestFontFamily
import com.example.explooapp.R
import com.example.explooapp.ru.ui.UIKit.items.TextInputField
import java.nio.file.WatchEvent

@Preview(
    showBackground = true
)
@Composable
fun RoomButton(onClick: ( () -> Unit) = {}){
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val animateScale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1f
    )
    var isExplooRoomChoosen by remember { mutableStateOf(false ) }
    var linkTitle by remember { mutableStateOf("") }

    Column() {
        if (!isExplooRoomChoosen){
            TextInputField(
                value = linkTitle,
                onValueChange = {
                    linkTitle = it
                },
                placeholder = "https://telemost.360.yandex.ru/j/..."
            )
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(animateScale)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    )
                    {
                        isExplooRoomChoosen = true
                    },
                color = ForegroundMuted.copy(0.05f),
                border = BorderStroke(
                    width = 1.dp,
                    color = ForegroundMuted.copy(alpha = 0.2f)
                ),
                shape = RoundedCornerShape(8.dp)
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    contentAlignment = Alignment.Center,

                    ){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.size(12.dp),
                            painter = painterResource(R.drawable.ic_plus),
                            contentDescription = "",
                            tint = Foreground
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Моя комната Exploo",
                            fontFamily = onestFontFamily,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            color = Foreground
                        )
                    }
                }
            }
        } else {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(animateScale)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    )
                    {
                        onClick()
                    },
                color = Primary.copy(0.15f),
                shape = RoundedCornerShape(12.dp)
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    contentAlignment = Alignment.Center,

                    ){
                    Row(modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier.size(10.dp),
                                painter = painterResource(R.drawable.ic_plus),
                                contentDescription = "",
                                tint = Primary
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Комната Exploo",
                                fontFamily = onestFontFamily,
                                fontSize = 10.sp,
                                fontWeight = FontWeight(400),
                                color = Primary
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable(
                                    interactionSource = null,
                                    indication = null
                                ){
                                    isExplooRoomChoosen = false
                                }) {
                            Icon(
                                modifier = Modifier.size(10.dp),
                                painter = painterResource(R.drawable.ic_plus),
                                contentDescription = "",
                                tint = Primary.copy(0.8f)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Убрать",
                                fontFamily = onestFontFamily,
                                fontSize = 10.sp,
                                fontWeight = FontWeight(400),
                                color = Primary.copy(0.8f)
                            )
                        }
                    }
                }
            }
        }

    }

}
