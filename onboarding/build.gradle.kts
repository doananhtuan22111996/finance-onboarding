import vn.finance.buildSrc.Configs

plugins {
    vn.core.plugins.androidLibrary
    vn.core.plugins.androidCompose
    vn.core.plugins.androidPublishing
}

android {
    namespace = Configs.NAMESPACE
}

publishing {
    publications {
        create<MavenPublication>(Configs.Artifact.ARTIFACT_ONBOARDING_ID) {
            afterEvaluate {
                from(components["all"])
            }
            groupId = Configs.Artifact.GROUP_ID
            artifactId = Configs.Artifact.ARTIFACT_ONBOARDING_ID
            version = Configs.Artifact.VERSION
        }
    }
}

dependencies {
    implementation(project(Configs.BuildModule.ONBOARDING_API))

    implementation(libs.financeTheme)
}
