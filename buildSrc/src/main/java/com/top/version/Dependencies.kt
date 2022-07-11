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
            const val swiperefresh =  "com.google.accompanist:accompanist-swiperefresh:0.23.1"


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

            const val lifecycleLivedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"


            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"
        }

        object DataStore {
            const val preferences = "androidx.datastore:datastore-preferences:1.0.0"

            // optional - RxJava2 support
            const val preferencesRxjava2 = "androidx.datastore:datastore-preferences-rxjava2:1.0.0"

            // optional - RxJava3 support
            const val preferencesRxjava3 = "androidx.datastore:datastore-preferences-rxjava3:1.0.0"

            const val datastore = "androidx.datastore:datastore:1.0.0"

            // optional - RxJava2 support
            const val datastoreRxJava2 = "androidx.datastore:datastore-rxjava2:1.0.0"

            // optional - RxJava3 support
            const val datastoreRxJava3 = "androidx.datastore:datastore-rxjava3:1.0.0"
        }

        object Paging {
            //https://developer.android.google.cn/jetpack/androidx/releases/paging
            const val paging_version = "3.1.1"

            const val pagingRuntime = "androidx.paging:paging-runtime:$paging_version"
            const val pagingCompose = "androidx.paging:paging-compose:1.0.0-alpha15"

        }

        object Room{
            const val roomVersion = "2.4.2"

            const val runtime = "androidx.room:room-runtime:$roomVersion"
            const val compiler = "androidx.room:room-compiler:$roomVersion"


            // optional - Kotlin Extensions and Coroutines support for Room
            const val ktx = ("androidx.room:room-ktx:$roomVersion")

            // optional - RxJava2 support for Room
            const val rxjava2 = ("androidx.room:room-rxjava2:$roomVersion")

            // optional - RxJava3 support for Room
            const val rxjava3 = ("androidx.room:room-rxjava3:$roomVersion")

            // optional - Guava support for Room, including Optional and ListenableFuture
            const val guava = ("androidx.room:room-guava:$roomVersion")

            // optional - Test helpers
            const val testing = ("androidx.room:room-testing:$roomVersion")

            // optional - Paging 3 Integration
            const val paging = ("androidx.room:room-paging:2.5.0-alpha02")
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
        //https://dagger.dev/hilt/quick-start

        private const val version = "2.42"

        //默认配置
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"

        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val navigation = "androidx.hilt:hilt-navigation-compose:1.0.0"


        //jetpack集成,https://developer.android.google.cn/training/dependency-injection/hilt-jetpack#kotlin,已经被移除
        const val hiltLifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha01"
        const val hiltCompiler = "androidx.hilt:hilt-compiler:1.0.0-alpha01"


        object Test {
            const val testing = "com.google.dagger:hilt-android-testing:$version"
        }
    }

    object accompanist {
        //https://google.github.io/accompanist/
        //https://search.maven.org/search?q=g:com.google.accompanist
        const val accompanist_version = "0.24.13-rc"
        const val systemuicontroller =
            "com.google.accompanist:accompanist-systemuicontroller:${accompanist_version}"
        const val insets = "com.google.accompanist:accompanist-insets:$accompanist_version"

        //coil是一个图片库，可以用来加载Compose中的远程图片
        // const val coil = "com.google.accompanist:accompanist-coil:0.15.0"

        const val flowlayout = "com.google.accompanist:accompanist-flowlayout:$accompanist_version"


        const val permissions =
            "com.google.accompanist:accompanist-permissions:$accompanist_version"


        const val navigationAnimation =
            "com.google.accompanist:accompanist-navigation-animation:$accompanist_version"


        const val swiperefresh =
            "com.google.accompanist:accompanist-swiperefresh:$accompanist_version"

        // If you're using Material, use accompanist-placeholder-material
        const val placeholderMaterial =
            "com.google.accompanist:accompanist-placeholder-material:$accompanist_version"

        // Otherwise use the foundation version
        const val placeholder =
            "com.google.accompanist:accompanist-placeholder:$accompanist_version"


        const val pager = "com.google.accompanist:accompanist-pager:$accompanist_version"
        const val pagerIndicators =
            "com.google.accompanist:accompanist-pager-indicators:$accompanist_version"


        const val drawablepainter =
            "com.google.accompanist:accompanist-drawablepainter:$accompanist_version"


    }

    object JUnit {

        const val junit = "junit:junit:4.13.2"
    }

}

object ThirdLibs {
    object OkHttp {
        const val okhttp3 = "com.squareup.okhttp3:okhttp:4.10.0"

        const val  okhttpLoggingInterceptorVersion = "4.9.0"

        const val interceptor =  "com.squareup.okhttp3:logging-interceptor:$okhttpLoggingInterceptorVersion"

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


    object Glide {
        const val glide = "com.github.bumptech.glide:glide:4.12.0"
        const val glideCompiler = "com.github.bumptech.glide:compiler:4.12.0"
    }

    object Lottie {

        const val mavenUrl = "https://oss.sonatype.org/content/repositories/snapshots/"

        const val lottieCompose = "com.airbnb.android:lottie-compose:5.2.0"

    }
}