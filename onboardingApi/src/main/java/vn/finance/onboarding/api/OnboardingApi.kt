package vn.finance.onboarding.api

import androidx.compose.runtime.Composable

interface OnboardingApi {

    val path: String

    @Composable
    fun OnboardingPage(navigateTo: () -> Unit)
}