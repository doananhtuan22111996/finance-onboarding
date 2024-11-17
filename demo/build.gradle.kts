import vn.finance.buildSrc.Configs

plugins {
    vn.core.plugins.androidApplication
}

android {
    namespace = Configs.namespace

    defaultConfig {
        applicationId = Configs.Demo.applicationId
        versionCode = Configs.Demo.versionCode
        versionName = Configs.Demo.versionName
    }
}

dependencies {
    implementation(libs.financeTheme)
    implementation(libs.financeLaunch)
    implementation(libs.financeNavigation)
    implementation(project(Configs.BuildModule.onboarding))
}
