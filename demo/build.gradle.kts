plugins {
    alias(mobilex.plugins.androidApplication)
    alias(mobilex.plugins.kotlinAndroid)
}

android {
    namespace = Configs.namespace
    compileSdk = Configs.compileSdk

    defaultConfig {
        applicationId = Configs.Demo.applicationId
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk
        versionCode = Configs.Demo.versionCode
        versionName = Configs.Demo.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Configs.javaVersion
        targetCompatibility = Configs.javaVersion
    }
    kotlinOptions {
        jvmTarget = Configs.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Configs.kotlinCompilerExtensionVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(Configs.BuildModule.onboarding))

    implementation(libs.financeTheme)
    implementation(libs.financeLaunch)

    implementation(mobilex.androidxCoreSplashscreen)
    implementation(mobilex.androidxCoreKtx)
    implementation(mobilex.androidxLifecycleRuntimeKtx)
    implementation(mobilex.androidxActivityCompose)
    implementation(platform(mobilex.androidxComposeBom))
    implementation(mobilex.androidxComposeUi)
    implementation(mobilex.androidxComposeUiGraphics)
    implementation(mobilex.androidxComposeUiToolingPreview)
    implementation(mobilex.androidxComposeMaterial3)
    testImplementation(mobilex.bundles.composeTestComponents)
    androidTestImplementation(mobilex.bundles.androidTestComponents)
}