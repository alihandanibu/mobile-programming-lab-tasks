plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)

    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    implementation(libs.androidx.material3)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)

    ksp(libs.room.compiler)

    // Hilt
    implementation(libs.hilt.android)

    ksp(libs.hilt.compiler)

    implementation(libs.hilt.navigation.compose)
    implementation("androidx.navigation:navigation-compose:2.8.0")

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    // Firebase BOM
    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))

    // Firebase Auth
    implementation("com.google.firebase:firebase-auth")

    // Firebase Firestore
    implementation("com.google.firebase:firebase-firestore")

    // Coroutines Firebase await()
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.8.1")

    // Google Sign In (Stable version for university labs)
    implementation("com.google.android.gms:play-services-auth:21.1.1")

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation(platform(libs.androidx.compose.bom))

    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)

    debugImplementation(libs.androidx.ui.test.manifest)
}
