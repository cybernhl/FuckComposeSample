package com.top.compose.sample.business.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.top.compose.sample.ui.theme.Purple700
import com.top.compose.widget.TopAppBarCenter

private enum class DemoTabs(val value: String) {
    APPLE("Apple"),
    GOOGLE("Google"),
    AMAZON("Amazon")
}

@Composable
fun SquareScreen(title: String) {

    val tabsName = remember { DemoTabs.values().map { it.value } }
    val selectedIndex = remember { mutableStateOf(DemoTabs.APPLE.ordinal) }


    TopAppBarCenter(
        title = {
            Text(text = title, color = Color.White)
        },
        backgroundColor = Purple700,
        isImmersive = true
    ) {
        Column(modifier = Modifier.height(56.dp)) {
            TabRow(selectedTabIndex = selectedIndex.value) {
                tabsName.forEachIndexed { index, title ->
                    Tab(selected = index == selectedIndex.value, onClick = {
                        when (title) {
                            DemoTabs.APPLE.value -> {
                                selectedIndex.value = DemoTabs.APPLE.ordinal
                            }
                            DemoTabs.GOOGLE.value -> {
                                selectedIndex.value = DemoTabs.GOOGLE.ordinal
                            }
                            DemoTabs.AMAZON.value -> {
                                selectedIndex.value = DemoTabs.AMAZON.ordinal
                            }
                        }
                    }) {
                        Text(title)
                    }
                }
            }
        }
    }

}