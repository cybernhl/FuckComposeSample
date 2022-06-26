package com.top.compose.sp

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext


@Composable
fun <T> rememberSharePreferenceState(
    key: String,
    defaultValue: T,
    autoSave: Boolean = true
): ComposeSharePreferenceMutableState<T> {
    val current = LocalContext.current
    return rememberSharePreferenceState(current, key, defaultValue, autoSave)
}


@Composable
fun <T> rememberSharePreferenceState(
    context: Context,
    key: String,
    defaultValue: T,
    autoSave: Boolean = true
): ComposeSharePreferenceMutableState<T> {

    val composeSharePreference = ComposeSharePreference(context)

    return remember {
        ComposeSharePreferenceMutableState(
            composeSharePreference,
            key,
            composeSharePreference.get(key, defaultValue),
            autoSave = autoSave
        )
    }

}

