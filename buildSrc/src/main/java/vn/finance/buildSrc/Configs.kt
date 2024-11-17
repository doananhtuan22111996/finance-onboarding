package vn.finance.buildSrc

object Configs {
    const val namespace = "vn.finance.onboarding"

    object BuildModule {
        const val onboarding = ":onboarding"
    }

    object Demo {
        const val namespace = "vn.finance.demo"
        const val applicationId = "vn.finance.demo"
        const val versionCode = 1
        const val versionName = "1.0.0"
    }

    object Artifact {
        const val GROUP_ID = "vn.core.libs"
        const val ARTIFACT_ID = "feature-onboarding"
        const val VERSION = "1.0.1"
    }
}
