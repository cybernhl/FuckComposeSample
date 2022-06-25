package com.top.compose.sp

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember


@Composable
fun <T> rememberSharePreferenceState(
    key: String,
    defaultValue: T,
    autoSave: Boolean = true
): ComposeSharePreferenceMutableState<T> {
    return rememberSharePreferenceState(null, key, defaultValue, autoSave)
}


@Composable
fun <T> rememberSharePreferenceState(
    context: Context?,
    key: String,
    defaultValue: T,
    autoSave: Boolean = true
): ComposeSharePreferenceMutableState<T> {

    val composeSharePreference: ComposeSharePreference = if (context == null) {
        ComposeSharePreference()
    } else {
        ComposeSharePreference(context)
    }
    return remember {
        ComposeSharePreferenceMutableState(
            composeSharePreference,
            key,
            composeSharePreference.get(key, defaultValue),
            autoSave = autoSave
        )
    }

}

