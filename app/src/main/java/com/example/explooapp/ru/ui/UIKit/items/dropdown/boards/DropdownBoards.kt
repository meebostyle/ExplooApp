package com.example.explooapp.ru.ui.UIKit.items.dropdown.boards

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import com.example.alfatesttask.ui.theme.DarkBackground
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.alfatesttask.ui.theme.Primary
import com.example.alfatesttask.ui.theme.onestFontFamily
import com.example.explooapp.R
import com.example.explooapp.ru.ui.UIKit.items.icons.DefaultIcons
import com.example.explooapp.ru.ui.UIKit.items.icons.check
import com.example.explooapp.ru.ui.UIKit.items.icons.downArrow
import com.example.explooapp.ru.utils.providers.FixedPopupPositionProvider

@Preview
    (showBackground = true)
@Composable
fun DropdownBoards(items: List<String> = listOf("Без доски","Максим 9кл", "Маша 11кл профиль", "Андрей ОГЭ")) {
    var expanded by remember { mutableStateOf(false) }
    val parentOffset = remember { mutableStateOf(IntOffset.Zero) }
    val parentSize = remember { mutableStateOf(IntSize.Zero) }
    var chosenItem by remember {
        mutableStateOf<String>(
            items[0]
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
            Box {
                var chosenItemHeight by remember { mutableStateOf(0) }
                ChosenItem(modifier = Modifier
                    .onSizeChanged { size ->
                        chosenItemHeight = size.height // ← получаем точную высоту элемента
                    },
                    expanded = expanded,
                    onClick = {
                        expanded = !expanded
                    },
                    text = chosenItem,
                )
                if (expanded) {
                    Popup(
                        popupPositionProvider = FixedPopupPositionProvider(
                            contentOffset = IntOffset(
                                0,
                                parentSize.value.height + with(density) { 4.dp.roundToPx() }
                            ),
                            alignment = Alignment.TopCenter
                        ),
                        onDismissRequest = { expanded = false },
                        properties = PopupProperties(
                            focusable = true,
                            dismissOnBackPress = true,
                            dismissOnClickOutside = true
                        )
                    ) {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = true,
                            enter = fadeIn(animationSpec = tween(300)),
                            exit = fadeOut(animationSpec = tween(300))
                        ) {
                            DropList(
                                modifier = Modifier
                                    .zIndex(1f)  // чтобы было поверх других элементов
                                    .offset {
                                        // позиционируем сразу под якорем с отступом 4dp
                                        IntOffset(
                                            x = 0,
                                            y = chosenItemHeight + with(density) { 4.dp.roundToPx() }
                                        )
                                    },
                                alpha = alpha,
                                scale = scale,
                                offset = offset,
                                onClick = { text ->
                                    expanded = false
                                    chosenItem = text
                                },
                                chosenItem = chosenItem,
                                listItems = items,
                            )
                        }
                    }
                }

            }

                }
            }




        }




@Preview(showBackground = true)
@Composable
private fun ChosenItem(
    modifier: Modifier = Modifier,
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
        modifier = modifier
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
                Icon(
                    modifier = Modifier.size(12.dp),
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = "",
                    tint = if (text == "Без доски") ForegroundMuted else Primary
                )
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
    modifier: Modifier = Modifier,
    alpha: Float = 1f,
    scale: Float = 1f,
    offset: Offset = Offset(x = 0f, y = 0f),
    chosenItem: String = "",
    listItems: List<String>,
    onClick: (String) -> Unit,
    ) {
    Column(
        modifier = modifier
            .alpha(alpha)
            .scale(scale)
            .offset(x = offset.x.dp, y = offset.y.dp)  // применяем сдвиг
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
            color = Color.White,
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, ForegroundMuted.copy(0.2f))
        ) {
            Column {
                listItems.forEachIndexed { index, item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 12.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) { onClick(item) },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = item,
                                fontFamily = onestFontFamily,
                                fontSize = 12.sp,
                                fontWeight = FontWeight(400),
                                color = ForegroundMuted
                            )
                        }
                        if (chosenItem == item)
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