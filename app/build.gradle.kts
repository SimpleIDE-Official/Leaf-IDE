plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.mucheng.leafide"

    defaultConfig {
        applicationId = "com.mucheng.leafide"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        versionCode = Versions.versionCode
        versionName = Versions.versionName + "-" + System.currentTimeMillis()
    }
    signingConfigs {
        create("general") {
            storeFile = file("../debug.jks")
            storePassword = "112233"
            keyAlias = "debug"
            keyPassword = "112233"
            enableV1Signing = true
            enableV2Signing = true
            enableV3Signing = true
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("general")
            proguardFiles("proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("general")
            proguardFiles("proguard-rules.pro")
        }
    }
    viewBinding {
        enable = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.core.splashscreen)
    implementation(libs.toasty)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}