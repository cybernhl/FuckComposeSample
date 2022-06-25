package com.top.compose.core

import android.app.Application

private var instance: Application? = null

fun curApplication(): Application? {

    if (instance != null) return instance

    var application: Application? = null

    try {
        val activityThread = Class.forName("android.app.ActivityThread")

        val currentApplicationMethod = activityThread.getDeclaredMethod("currentApplication")
        currentApplicationMethod.isAccessible = true
        application = currentApplicationMethod.invoke(null) as Application

    } catch (e: Exception) {

    }

    if (application != null) {
        instance = application
        return application
    }

    try {
        val appGlobals = Class.forName("android.app.AppGlobals")
        val currentApplicationMethod = appGlobals.getDeclaredMethod("getInitialApplication")
        currentApplicationMethod.isAccessible = true
        application = currentApplicationMethod.invoke(null) as Application
    } catch (e: java.lang.Exception) {

    }
    instance = application

    return instance
}