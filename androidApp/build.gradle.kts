plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
}

//repositories {
//    google()
//    mavenCentral()
//    maven { url = uri("https://jitpack.io") }
//}

android {
    namespace = "com.io.tea.android"
    compileSdk = 36
    defaultConfig {
        applicationId = "com.io.tea.android"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
//    buildToolsVersion = "34.0.0"
}

//dependencies {
//    implementation(projects.shared)
//    implementation(libs.compose.ui)
//    implementation(libs.compose.ui.tooling.preview)
//    implementation(libs.compose.material3)
//    implementation(libs.androidx.activity.compose)
//    debugImplementation(libs.compose.ui.tooling)
//}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.kotlinx.datetime)
    // Applicationクラスで、startKoinするために必要
    implementation(libs.koin.android)
    // koinViewModel()のために必要
    implementation(libs.koin.androidx.compose)
    implementation(libs.androidx.window)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.webkit)
    implementation(libs.material)
    // 画像読み込み用ライブラリ
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.napier)
    // Camera系
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)
    implementation(libs.barcode.scanning)
    // Permission
    implementation(libs.accompanist.permissions)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.accompanist.placeholder.material)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.accompanist.navigation.animation)
    // Splash
    implementation(libs.core.splashscreen)
    // DataStore
    implementation(libs.datastore)
    implementation(libs.datastore.core)
    implementation(libs.datastore.preferences)
    // Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    // DataDog
    implementation(libs.datadog)
    implementation(libs.datadog.coil)
    implementation(libs.datadog.compose)
}