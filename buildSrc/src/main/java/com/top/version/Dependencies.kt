package com.top.version


object BuildConfig {
    const val compileSdk = 32
    const val minSdk = 21
    const val targetSdk = 32
    const val versionCode = 1
    const val versionName = "1.0"
}

object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:7.1.3"


    object Kotlin {
        private const val version = "1.6.0"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"

        object Coroutines {
            private const val version = "1.6.0"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object AndroidX {

        const val core_ktx = "androidx.core:core-ktx:1.7.0"

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.4.0"
        }

        const val appcompat = "androidx.appcompat:appcompat:1.4.1"

        object Compose {

            const val version = "1.1.1"

            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
            const val material = "androidx.compose.material:material:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
            const val animation = "androidx.compose.animation:animation:$version"

            const val ui = "androidx.compose.ui:ui:$version"

            const val constraintlayout = "androidx.constraintlayout:constraintlayout-compose:1.0.1"


            const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
            const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$version"
        }

        object Navigation {
            const val navigationCompose = "androidx.navigation:navigation-compose:2.4.1"

        }

        object Lifecycle {
            private const val version = "2.4.1"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"

            const val viewModelSavedState =
                "androidx.lifecycle:lifecycle-viewmodel-savedstate:2.4.1"

            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"
        }

        object Test {
            const val core = "androidx.test:core:1.4.0"
            const val runner = "androidx.test:runner:1.4.0"
            const val rules = "androidx.test:rules:1.4.0"

            object Ext {
                const val junitKtx = "androidx.test.ext:junit-ktx:1.1.2"
                const val junit = "androidx.test.ext:junit:1.1.3"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"
        }
    }

    object Google {
        const val material = "com.google.android.material:material:1.6.1"
        const val gson = "com.google.code.gson:gson:2.8.9"

    }

    object Hilt {
        private const val version = "2.42"

        //默认配置
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val navigation = "androidx.hilt:hilt-navigation-compose:1.0.0"


        //jetpack集成
        const val hiltLifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha01"
        const val hiltCompiler = "androidx.hilt:hilt-compiler:1.0.0-alpha01"


        object Test {
            const val testing = "com.google.dagger:hilt-android-testing:$version"
        }
    }

    object accompanist {
        const val accompanist_version = "0.24.7-alpha"
        const val systemuicontroller =
            "com.google.accompanist:accompanist-systemuicontroller:${accompanist_version}"
        const val insets = "com.google.accompanist:accompanist-insets:$accompanist_version"
    }

    object JUnit {

        const val junit = "junit:junit:4.13.2"
    }

}

object ThirdLibs {
    object OkHttp {
        const val okhttp3 = "com.squareup.okhttp3:okhttp:4.10.0"
    }

    object Retrofit {
        const val retrofit_versions = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_versions"
        const val converterGson = "com.squareup.retrofit2:converter-gson:$retrofit_versions"
        const val adapterRxjava2 = "com.squareup.retrofit2:adapter-rxjava2:2.9.0"
    }

    object Tencent {
        const val mmkv = "com.tencent:mmkv:1.2.13"
    }

    object Lottie {

        const val mavenUrl = "https://oss.sonatype.org/content/repositories/snapshots/"

        const val lottieCompose = "com.airbnb.android:lottie-compose:5.2.0"

    }
}