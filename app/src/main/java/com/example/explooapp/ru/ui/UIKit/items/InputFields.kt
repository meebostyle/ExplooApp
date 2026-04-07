package com.example.explooapp.ru.ui.UIKit.items

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.alfatesttask.ui.theme.Primary

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun TextInputField(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
) {

    val focused by remember { mutableStateOf(false) }
    val borderInAlpha = animateFloatAsState(
        targetValue = if (focused) 0.4f else 0.2f,
        animationSpec = tween(300)
    )
    val borderOutAlpha = animateFloatAsState(
        targetValue = if (focused) 0.6f else 0f,
        animationSpec = tween(300)
    )
    var textFieldValue by remember(value) {
        mutableStateOf(TextFieldValue(value, selection = TextRange(value.length)))
    }
    LocalSoftwareKeyboardController.current
    LocalFocusManager.current
    remember { FocusRequester() }

    ImeOptions(
        singleLine = singleLine,
        autoCorrect = true,
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
    )

    LaunchedEffect(value) {
        if (textFieldValue.text != value) {
            textFieldValue = TextFieldValue(value, selection = TextRange(value.length))
        }
    }



    Box(
        modifier = Modifier
            .border(2.dp, Primary.copy(borderOutAlpha.value), RoundedCornerShape(14.dp)),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .padding(2.dp),
            color = Color.Transparent,
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, ForegroundMuted.copy(borderInAlpha.value))
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
//                BasicTextField(
//                    value = "asd",
//                    onValueChange = {
//                        textFieldValue.text = it
//                    }
//                )
            }
        }
    }

}

