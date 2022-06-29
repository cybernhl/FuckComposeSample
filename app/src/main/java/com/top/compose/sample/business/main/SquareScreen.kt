package com.top.compose.sample.business.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
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
import com.top.compose.sample.ui.widget.EmptyScreen
import com.top.compose.widget.TopAppBarCenter

private enum class DemoTabs(val value: String) {
    APPLE("Apple"),
    GOOGLE("Google"),
    AMAZON("Amazon"),
    XIAOMI("XiaoMi"),
    HUAWEI("HuaWei"),
    OPPO("Oppo");
    fun msg() {

    }
}

@Composable
fun SquareScreen(title: String) {

    val tabsName = remember { DemoTabs.values().map { it.value } }
    val selectedTab = remember { mutableStateOf(DemoTabs.APPLE) }


    TopAppBarCenter(
        title = {
            Text(text = title, color = Color.White)
        },
        backgroundColor = Purple700,
        isImmersive = true
    ) {
        Column {
            TabRow(
                selectedTabIndex = selectedTab.value.ordinal,
                modifier = Modifier.height(56.dp)
            ) {
                tabsName.forEachIndexed { index, title ->
                    Tab(selected = index == selectedTab.value.ordinal, onClick = {
                        DemoTabs.values().forEach {
                            if (title == it.value) {
                                selectedTab.value = it
                            }
                        }
                    }) {
                        Text(title)
                    }
                }
            }

            Surface(modifier = Modifier.weight(0.5f)) {
                EmptyScreen(msg = selectedTab.value.value)
            }
        }
    }

}