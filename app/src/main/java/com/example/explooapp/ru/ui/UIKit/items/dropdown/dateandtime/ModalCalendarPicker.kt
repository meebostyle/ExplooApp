package com.example.explooapp.ru.ui.UIKit.items.dropdown.dateandtime


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Preview(
    showBackground = true
)
@Composable
fun DatePickerField() {
    val textStyle = TextStyle(
        fontFamily = onestFontFamily,
        fontSize = 13.sp,
        fontWeight = FontWeight(400),
        color = DarkBackground
    )

    var showModel by remember { mutableStateOf(false) }
    var fieldText by remember { mutableStateOf("Выберите дату") }

    AnimatedSurfaceSheet(
        clickable = {
            showModel = true
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, top = 12.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(12.dp),
                painter = painterResource(R.drawable.ic_plus),
                contentDescription = "",
                tint = ForegroundMuted
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = fieldText, style = textStyle)
        }
    }
    if (showModel){
        DatePickerModal(
            onDismiss = {showModel = false},
            onDateSelected = {fieldText = if (it != null) convertMillisToDate(it) else "Выберите дату"}
        )
    }
}
fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}


@Composable
internal fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

