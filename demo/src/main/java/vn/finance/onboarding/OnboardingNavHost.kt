package vn.finance.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
internal fun OnboardingNavHost(navController: NavHostController) {
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = ONBOARDING) {
        composable(ONBOARDING) {
            OnboardingPage(navigateTo = {
                println("Func navigationTo invoked ")
                context.getActivity()?.finish()
            })
        }
    }
}