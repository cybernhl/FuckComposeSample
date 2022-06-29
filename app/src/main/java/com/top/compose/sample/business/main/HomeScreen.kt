package com.top.compose.sample.business.main

import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.top.compose.icon.FaIcon
import com.top.compose.icon.FaIcons
import com.top.compose.sample.ui.theme.Purple700
import com.top.compose.widget.TopAppBarCenter

@Composable
fun HomeScreen(title: String) {
    TopAppBarCenter(
        title = {
            Text(text = title, color = Color.White)
        },
        actions = {
            IconButton(onClick = {

            }) {
                FaIcon(faIcon = FaIcons.Search, tint = Color.White)
            }
        },
        backgroundColor = Purple700,
        isImmersive = true
    ) {

    }
}