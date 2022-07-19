package com.top.react


data class ReactNativeBundleEntity constructor(
    val remoteUrl: String,    // 远程下载地址
    val filePath: String,     // 本地存储地址
    val version: String,      // 版本号
    val componentName: String = "AwesomeProject"// ReactNative入口组件
)