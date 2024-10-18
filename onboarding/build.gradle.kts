plugins {
    alias(mobilex.plugins.androidLibrary)
    alias(mobilex.plugins.kotlinAndroid)
    `maven-publish`
    alias(mobilex.plugins.androidHilt)
    id("kotlin-kapt")
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
    publishing {
        multipleVariants("all") {
            allVariants()
            withSourcesJar()
            withJavadocJar()
        }
    }
}

publishing {
    val ghUsername = System.getenv("GH_USERNAME")
    val ghPassword = System.getenv("GH_TOKEN")
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
                from(components["all"])
            }
            groupId = "vn.finance.libs"
            artifactId = "feature-onboarding"
            version = "1.0.0" // Set your desired version here
        }
    }
}

dependencies {
    implementation(fnlibs.financeTheme)
    implementation(fnlibs.financeNavigation)
    implementation(mobilex.androidxCoreKtx)
    implementation(mobilex.androidxCoreSplashscreen)
    implementation(mobilex.androidxLifecycleRuntimeKtx)
    implementation(mobilex.androidxActivityCompose)
    implementation(platform(mobilex.androidxComposeBom))
    implementation(mobilex.bundles.jetpackComposeComponents)
    implementation(mobilex.androidxHilt)
    kapt(mobilex.androidxHiltCompiler)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}