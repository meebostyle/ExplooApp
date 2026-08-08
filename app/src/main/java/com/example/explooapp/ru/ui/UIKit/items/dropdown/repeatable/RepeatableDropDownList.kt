package com.example.explooapp.ru.ui.UIKit.items.dropdown.repeatable

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.alfatesttask.ui.theme.DarkBackground
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.alfatesttask.ui.theme.onestFontFamily
import com.example.explooapp.ru.ui.UIKit.items.dropdown.dateandtime.DatePickerField
import com.example.explooapp.ru.ui.UIKit.items.icons.DefaultIcons
import com.example.explooapp.ru.ui.UIKit.items.icons.check
import com.example.explooapp.ru.ui.UIKit.items.icons.downArrow
import com.example.explooapp.ru.ui.UIKit.items.pickers.DayOfWeekPicker


@OptIn(ExperimentalAnimationApi::class)
@Preview(
    showBackground = true,
//    backgroundColor = 0xFF222222
)
@Composable
fun RepeatableDropDownList() {
    var expanded by remember { mutableStateOf(false) }
    val parentOffset = remember { mutableStateOf(IntOffset.Zero) }
    val parentSize = remember { mutableStateOf(IntSize.Zero) }
    val listItems = remember { listOf("Разовое занятие", "Ежедневно", "Еженедельно", "По дням недели") }
    var chosenItem by remember {
        mutableStateOf<String>(
            listItems[0]
        )
    }
    val density = LocalDensity.current
    val alpha by animateFloatAsState(
        targetValue = if (expanded) 1f else 0f,
        animationSpec = tween(durationMillis = 300)
    )
    val scale by animateFloatAsState(
        targetValue = if (expanded) 1f else 0.98f,
        animationSpec = tween(durationMillis = 300)
    )
    val extraItems = listOf("Ежедневно", "Еженедельно", "По дням недели")

    var lastExtraItem by remember { mutableStateOf(extraItems[0]) }

    if (chosenItem != "Разовое занятие") {
        lastExtraItem = chosenItem
    }

    val isExtraSpace = chosenItem != "Разовое занятие"

    val extraAlpha by animateFloatAsState(
        targetValue = if (chosenItem != "Разовое занятие") 1f else 0f,
        animationSpec = tween(durationMillis = 300)
    )
    val offset by animateOffsetAsState(
        targetValue = if (expanded) Offset(x = 0f, y = 0f) else Offset(x = -10f, y = -10f),
        animationSpec = tween(durationMillis = 300)
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        parentOffset.value = IntOffset(
                            coordinates.positionInWindow().x.toInt(),
                            coordinates.positionInWindow().y.toInt()
                        )
                        parentSize.value = coordinates.size
                    }
            ) {
                ChosenItem(
                    expanded = expanded,
                    onClick = {
                        expanded = !expanded
                        Log.i("TAG", "${parentOffset.value.x} ${parentOffset.value.y}")
                    },
                    text = chosenItem,
                )
            }


// Анимация раскрытия/схлопывания (AnimatedVisibility)
            AnimatedVisibility(
                modifier = Modifier.alpha(extraAlpha),
                visible = isExtraSpace,
                enter = expandVertically(animationSpec = tween(400)),
                exit = shrinkVertically(animationSpec = tween(400))
            ) {
                // Внутри – анимированная смена контента (fade)
                AnimatedContent(
                    targetState = lastExtraItem,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(300)) with fadeOut(animationSpec = tween(300))
                    }
                ) { item ->
                    when (item) {
                        "Ежедневно", "Еженедельно" -> {
                            Column {
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = "Дата окончания (необязательно)",
                                    fontFamily = onestFontFamily,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(400),
                                    color = ForegroundMuted
                                )
                                DatePickerField()
                            }
                        }
                        "По дням недели" -> {
                            Column {
                                Spacer(modifier = Modifier.height(12.dp))
                                DayOfWeekPicker()
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Дата окончания (необязательно)",
                                    fontFamily = onestFontFamily,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(400),
                                    color = ForegroundMuted
                                )
                                DatePickerField()
                            }
                        }
                    }
                }
            }

        }
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { expanded = false }
            ) {
                Popup(
                    alignment = Alignment.TopCenter,
                    offset = IntOffset(
                        0,
                        parentSize.value.height + with(density) { 4.dp.roundToPx() }
                    ),
                    onDismissRequest = { expanded = false },
                    properties = PopupProperties(
                        focusable = true,
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true
                    )
                ) {
                    DropList(
                        alpha = alpha,
                        scale = scale,
                        offset = offset,
                        onClick = { text->
                            expanded = false
                            chosenItem = text
                            Log.i("chosenItem", "$chosenItem, $isExtraSpace")
                        },
                        chosenItem = chosenItem,
                        listItems = listItems,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChosenItem(
    expanded: Boolean = true,
    onClick: () -> Unit = {},
    text: String = "Математика",
    color: Color = ForegroundMuted
) {
    val rotate by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val animateScale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1f
    )


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
        color = ForegroundMuted.copy(0.05f),
        border = BorderStroke(
            width = 1.dp,
            color = ForegroundMuted.copy(alpha = 0.2f)
        ),
        shape = RoundedCornerShape(12.dp)

    ) {

        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 8.dp,
                        vertical = 12.dp
                    ),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = text,
                    fontFamily = onestFontFamily,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = color
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(12.dp)
                            .rotate(rotate),
                        imageVector = DefaultIcons.downArrow,
                        contentDescription = "",
                        tint = ForegroundMuted
                    )
                }
            }
        }

    }

}


@Composable
private fun DropList(
    alpha: Float = 1f,
    scale: Float = 1f,
    offset: Offset = Offset(x = 0f, y = 0f),
    chosenItem: String = "",
    listItems: List<String>,
    onClick: (
        String,
    ) -> Unit,

) {
    Column {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .alpha(alpha)
                .scale(scale)
                .offset(x = offset.x.dp, y = offset.y.dp),
            color = Color.White,
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(width = 1.dp, ForegroundMuted.copy(0.2f))
        ) {
            Column {
                LazyColumn {
                    items(listItems.size) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 12.dp,
                                        vertical = 12.dp
                                    )
                                    .clickable {
                                        onClick(
                                            listItems[it]
                                        )
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = listItems[it],
                                        fontFamily = onestFontFamily,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight(400),
                                        color = ForegroundMuted
                                    )
                                }
                                if (chosenItem == listItems[it])
                                    Icon(
                                        modifier = Modifier.size(12.dp),
                                        imageVector = DefaultIcons.check,
                                        contentDescription = "",
                                        tint = DarkBackground
                                    )

                            }
                    }
                }
            }
        }
    }
}