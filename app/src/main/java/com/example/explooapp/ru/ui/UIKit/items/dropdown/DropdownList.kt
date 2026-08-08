package com.example.explooapp.ru.ui.UIKit.items.dropdown

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.explooapp.ru.domain.schedule.LessonsList
import com.example.explooapp.ru.domain.schedule.toListItem
import com.example.explooapp.ru.ui.UIKit.items.icons.DefaultIcons
import com.example.explooapp.ru.ui.UIKit.items.icons.check
import com.example.explooapp.ru.ui.UIKit.items.icons.downArrow

@Preview(
    showBackground = true,
//    backgroundColor = 0xFF222222
)
@Composable
fun DropdownList(
    listItem: List<ItemModel> = LessonsList.entries.map { it.toListItem() },
    isLessonMenu: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }
    val parentOffset = remember { mutableStateOf(IntOffset.Zero) }
    val parentSize = remember { mutableStateOf(IntSize.Zero) }
    val listItem = remember { listItem }
    val chosenItem = remember {
        mutableStateOf<ItemModel>(
            ItemModel(
                text = listItem.first().text,
                icon = listItem.first().icon,
                color = listItem.first().color
            )
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
                    text = chosenItem.value.text,
                    icon = chosenItem.value.icon,
                    color = chosenItem.value.color
                )
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
                        onClick = { text, icon, color ->
                            expanded = false
                            chosenItem.value = ItemModel(
                                text = text,
                                icon = icon,
                                color = color
                            )

                        },
                        chosenItem = chosenItem.value.text,
                        listItems = listItem,
                        isLessonMenu = isLessonMenu
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
    icon: ImageVector? = null,
    color: Color = ForegroundMuted
) {
    val rotate by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable
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
                if (icon != null) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        imageVector = icon,
                        contentDescription = "",
                        tint = color
                    )
                }
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
    listItems: List<ItemModel> = LessonsList.entries.map { it.toListItem() },
    onClick: (
        String,
        ImageVector?,
        Color
    ) -> Unit,
    isLessonMenu: Boolean = true

) {
    val favoriteList = listOf<LessonsList>(LessonsList.Math).map { it.toListItem() }
    val listItem =
        listItems.filter { it !in favoriteList && it != LessonsList.NoChosen.toListItem() }
    Column {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .padding(horizontal = 12.dp)
                .alpha(alpha)
                .scale(scale)
                .offset(x = offset.x.dp, y = offset.y.dp),
            color = Color.White,
            shape = RoundedCornerShape(12.dp)
        ) {
            Column {
                LazyColumn {
                    if (isLessonMenu) {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 12.dp,
                                        vertical = 12.dp
                                    )
                                    .clickable {
                                        onClick(
                                            LessonsList.NoChosen.lesson,
                                            LessonsList.NoChosen.icon,
                                            LessonsList.NoChosen.color
                                        )
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Icon(
                                        modifier = Modifier.size(12.dp),
                                        imageVector = LessonsList.NoChosen.icon!!,
                                        contentDescription = "",
                                        tint = ForegroundMuted
                                    )


                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = LessonsList.NoChosen.lesson,
                                        fontFamily = onestFontFamily,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight(400),
                                        color = DarkBackground
                                    )
                                }
                                if (chosenItem == LessonsList.NoChosen.lesson)
                                    Icon(
                                        modifier = Modifier.size(12.dp),
                                        imageVector = DefaultIcons.check,
                                        contentDescription = "",
                                        tint = DarkBackground
                                    )
                            }

                            Text(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                text = "Мои предметы",
                                fontFamily = onestFontFamily,
                                fontSize = 10.sp,
                                fontWeight = FontWeight(400),
                                color = ForegroundMuted
                            )
                        }
                    }

                    if (isLessonMenu) {
                        items(favoriteList.size) {
                            with(favoriteList[it]) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            horizontal = 12.dp,
                                            vertical = 12.dp
                                        )
                                        .clickable {
                                            onClick(
                                                text,
                                                icon,
                                                color
                                            )
                                        },
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.Start,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        if (icon != null)
                                            Icon(
                                                modifier = Modifier.size(12.dp),
                                                imageVector = icon,
                                                contentDescription = "",
                                                tint = color
                                            )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = text,
                                            fontFamily = onestFontFamily,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight(400),
                                            color = color
                                        )
                                    }
                                    if (text == chosenItem)
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
                    item {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(ForegroundMuted)
                        )
                        Spacer(
                            modifier = Modifier
                                .height(12.dp)
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Остальные",
                            fontFamily = onestFontFamily,
                            fontSize = 10.sp,
                            fontWeight = FontWeight(400),
                            color = ForegroundMuted
                        )
                    }
                    items(listItem.size) {
                        with(listItem[it]) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 12.dp,
                                        vertical = 12.dp
                                    )
                                    .clickable {
                                        onClick(
                                            text,
                                            icon,
                                            color
                                        )
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    if (icon !== null)
                                        Icon(
                                            modifier = Modifier.size(12.dp),
                                            imageVector = icon,
                                            contentDescription = "",
                                            tint = color
                                        )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = text,
                                        fontFamily = onestFontFamily,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight(400),
                                        color = ForegroundMuted
                                    )
                                }
                                if (chosenItem == text)
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
}

