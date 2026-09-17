package com.example.explooapp.ru.ui.UIKit.widgets.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alfatesttask.ui.theme.ForegroundMuted
import com.example.explooapp.R


@Composable
fun NavigationBar(){
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Icon(
            painter = painterResource(R.drawable.ic_plus),
            contentDescription = "",
            modifier = Modifier.size(24.dp),
            tint = ForegroundMuted
        )
        Row(){
            Icon(
                painter = painterResource(R.drawable.ic_plus),
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = ForegroundMuted
            )
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                painter = painterResource(R.drawable.ic_plus),
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = ForegroundMuted
            )
        }
    }
}


@Preview
@Composable
fun NavigationBarPreview(){
    NavigationBar()
}