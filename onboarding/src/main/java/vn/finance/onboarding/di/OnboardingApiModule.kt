package vn.finance.onboarding.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vn.finance.onboarding.api.OnboardingApi
import vn.finance.onboarding.api.OnboardingApiImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class OnboardingApiModule {

    @Provides
    @Singleton
    fun provideOnboardingPage(): OnboardingApi = OnboardingApiImpl()
}