package com.example.explooapp.ru.ui.UIKit.items.pickers

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alfatesttask.ui.theme.DarkBackground
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.alfatesttask.ui.theme.Primary
import com.example.alfatesttask.ui.theme.onestFontFamily
import com.example.explooapp.ru.ui.UIKit.items.AnimatedColoredSurfaceSheet
import com.example.explooapp.ru.ui.UIKit.items.icons.DefaultIcons
import com.example.explooapp.ru.ui.UIKit.items.icons.check

@Preview(
    showBackground = true
)
@Composable
fun StudentsPicker(items: List<String> = listOf("Максим 9кл", "Маша 11кл профиль", "Андрей ОГЭ")){
    var choosenitem = ""
    LazyRow(){
        items(items.count()){
            TimeDurationPickerItem(items[it]){
                choosenitem = items[it]
            }
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}




@Composable
private fun TimeDurationPickerItem(time: String, clickable: (() -> Unit)){
    var isActive by remember { mutableStateOf(false) }
    val isAlpha = isActive
    val alpha by animateFloatAsState(
        targetValue = if (isAlpha) 1f else 0f
    )
    AnimatedColoredSurfaceSheet(
        clickable = {
            isActive = !isActive
            clickable()
        },
        color = if (isActive) Primary else ForegroundMuted.copy(0.4f)
    ){
        Box(modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center) {
            Row() {
                AnimatedVisibility(
                    visible = isActive,
                    enter = expandHorizontally(animationSpec = spring()),
                    exit = shrinkHorizontally(animationSpec = spring()),
                ){
                    Icon(
                        modifier = Modifier.size(12.dp).alpha(alpha),
                        imageVector = DefaultIcons.check,
                        contentDescription = "",
                        tint = Primary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }

                Text(modifier = Modifier,
                    text = time,
                    fontFamily = onestFontFamily,
                    fontSize = 10.sp,
                    fontWeight = FontWeight(400),
                    color = if (isActive) Primary else ForegroundMuted)
            }

        }
    }
}