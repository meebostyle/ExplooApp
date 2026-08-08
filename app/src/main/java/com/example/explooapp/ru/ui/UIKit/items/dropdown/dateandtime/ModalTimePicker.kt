package com.example.explooapp.ru.ui.UIKit.items.dropdown.dateandtime

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDialog
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alfatesttask.ui.theme.DarkBackground
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.alfatesttask.ui.theme.onestFontFamily
import com.example.explooapp.R
import com.example.explooapp.ru.ui.UIKit.items.AnimatedSurfaceSheet


@Preview(
    showBackground = true
)
@Composable
fun TimePickerField() {
    val textStyle = TextStyle(
        fontFamily = onestFontFamily,
        fontSize = 13.sp,
        fontWeight = FontWeight(400),
        color = DarkBackground
    )

    var showModel by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }


    var fieldText by remember { mutableStateOf("Время") }

    AnimatedSurfaceSheet(
        clickable = {showModel = true},
        ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = fieldText, style = textStyle)
        }
    }
    if (showModel){
        TimeInputDialogWithDismiss(
            onDismiss = {showModel = false},
            onConfirm = {hour, minute ->
                fieldText = "${hour.toString()}:${minute.toString()}"}
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeInputDialogWithDismiss(
    onDismiss: () -> Unit,
    onConfirm: (hour: Int, minute: Int) -> Unit
) {
    val state = rememberTimePickerState(
        initialHour = 12,
        initialMinute = 30,
        is24Hour = true
    )

    TimePickerDialog(
        onDismissRequest = onDismiss,   // клик вне диалога → закрыть
        confirmButton = {
            TextButton(onClick = {
                onConfirm(state.hour, state.minute)
                onDismiss()
            }) {
                Text("ОК")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Отмена")
            }
        },
        title = {}
    ) {
        TimeInput(state = state)   // поле ввода вместо циферблата
    }
}