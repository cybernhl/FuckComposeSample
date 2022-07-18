import com.top.version.BuildConfig
import com.top.version.Libs
import com.top.version.ThirdLibs

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

    implementation("com.facebook.infer.annotation:infer-annotation:0.18.0")

    implementation("com.facebook.soloader:soloader:0.10.3")
    implementation( "com.facebook.fresco:ui-common:2.6.0")
    implementation(  "com.facebook.fresco:fresco:2.6.0")
    implementation(  "com.facebook.fbjni:fbjni-java-only:0.2.2")

}