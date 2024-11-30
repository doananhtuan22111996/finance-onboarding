package vn.finance.onboarding.api

import androidx.compose.runtime.Composable
import vn.finance.onboarding.ONBOARDING
import vn.finance.onboarding.OnboardingPage as Page

class OnboardingApiImpl : OnboardingApi {
    override val path: String
        get() = ONBOARDING

    @Composable
    override fun OnboardingPage(navigateTo: () -> Unit) {
        Page(navigateTo = navigateTo)
    }
}