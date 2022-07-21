import com.top.version.BuildConfig

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = BuildConfig.compileSdk

    defaultConfig {
        minSdk = BuildConfig.minSdk
        targetSdk = BuildConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        externalNativeBuild {
            cmake {
                abiFilters("armeabi-v7a")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))
    //implementation("com.facebook.react:react-native:0.69.1") // From node_modules
    //implementation("org.webkit:android-jsc:+")
    implementation("com.facebook.infer.annotation:infer-annotation:0.18.0")

    implementation("com.facebook.soloader:soloader:0.10.3")
    implementation("com.facebook.fresco:ui-common:2.6.0")
    implementation("com.facebook.fresco:fresco:2.6.0")
    implementation("com.facebook.fbjni:fbjni-java-only:0.2.2")

    implementation("com.facebook.infer.annotation:infer-annotation:0.18.0")
    implementation("com.facebook.yoga:proguard-annotations:1.19.0")
    implementation("javax.inject:javax.inject:1")
    implementation("com.facebook.soloader:soloader:0.10.3")
    implementation("androidx.appcompat:appcompat-resources:1.4.1")
    implementation("androidx.autofill:autofill:1.1.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("com.facebook.fresco:fresco:2.6.0")
    implementation("com.facebook.fresco:imagepipeline-okhttp3:2.5.0")
    implementation("com.facebook.fresco:ui-common:2.6.0")
    implementation("com.google.code.findbugs:jsr305:3.0.2")
    implementation("com.squareup.okhttp3:okhttp-urlconnection:4.9.2")
    implementation("com.squareup.okio:okio:2.9.0")
    implementation("com.facebook.fbjni:fbjni-java-only:0.2.2")

    //implementation( "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.20")

    implementation ("com.airbnb.android:lottie:5.2.0")
    implementation ("com.wang.avi:library:2.1.3")
    implementation("com.gitee.leo94666.an-library:AnArch:0.0.1-beta-2.9@aar")

}