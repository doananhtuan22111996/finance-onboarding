plugins {
    alias(mobilex.plugins.androidLibrary)
    alias(mobilex.plugins.kotlinAndroid)
    `maven-publish`
}

android {
    namespace = Configs.namespace
    compileSdk = Configs.compileSdk

    defaultConfig {
        minSdk = Configs.minSdk

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

publishing {
    val ghUsername = System.getenv("USERNAME")
    val ghPassword = System.getenv("TOKEN")
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("${Configs.mavenDomain}/${ghUsername}/finance-onboarding")
            credentials {
                username = ghUsername
                password = ghPassword
            }
        }
    }
    publications {
        create<MavenPublication>("mavenAndroid") {
            afterEvaluate {
                from(components["release"])
            }
            groupId = "vn.finance.libs"
            artifactId = "feature-onboarding"
            version = "1.0.0" // Set your desired version here
        }
    }
}

dependencies {
    implementation(libs.financeTheme)
    implementation(mobilex.androidxCoreKtx)
    implementation(mobilex.androidxLifecycleRuntimeKtx)
    implementation(mobilex.androidxActivityCompose)
    implementation(platform(mobilex.androidxComposeBom))
    implementation(mobilex.androidxComposeUi)
    implementation(mobilex.androidxComposeUi)
    implementation(mobilex.androidxComposeUiGraphics)
    implementation(mobilex.androidxComposeUiToolingPreview)
    implementation(mobilex.androidxComposeMaterial3)

}