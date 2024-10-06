package vn.finance.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import vn.finance.navigation.NavigationKey
import vn.finance.navigation.NavigationManager

const val ONBOARDING = "onboarding"

@Composable
fun OnboardingNavHost(navigationManager: NavigationManager, navController: NavHostController) {
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = ONBOARDING) {
        composable(ONBOARDING) {
            OnboardingPage(navigateTo = {
                navigationManager.startActivityByKey(context, NavigationKey.Authentication())
                context.getActivity()?.finish()
            })
        }
    }
}