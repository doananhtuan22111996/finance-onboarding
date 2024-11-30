package vn.finance.buildSrc

object Configs {
    const val NAMESPACE = "vn.finance.onboarding"
    const val NAMESPACE_API = "vn.finance.onboarding.api"

    object BuildModule {
        const val ONBOARDING = ":onboarding"
        const val ONBOARDING_API = ":onboardingApi"
    }

    object Demo {
        const val NAMESPACE = "vn.finance.demo"
        const val APPLICATION_ID = "vn.finance.demo"
        const val VERSION_CODE = 1
        const val VERSION_NAME = "1.0.0"
    }

    object Artifact {
        const val GROUP_ID = "vn.finance.libs"
        const val ARTIFACT_ONBOARDING_ID = "feature-onboarding"
        const val ARTIFACT_ONBOARDING_API_ID = "feature-onboarding-api"
        const val VERSION = "1.0.2"
    }
}
