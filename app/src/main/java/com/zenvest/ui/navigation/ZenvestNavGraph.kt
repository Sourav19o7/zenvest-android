package com.zenvest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zenvest.core.ui.navigation.Routes
import com.zenvest.feature_auth.presentation.screens.LoginScreen
import com.zenvest.feature_auth.presentation.screens.OtpVerifyScreen
import com.zenvest.feature_auth.presentation.viewmodel.AuthViewModel
import com.zenvest.feature_consent.presentation.screens.ConsentScreen
import com.zenvest.feature_consent.presentation.screens.ConsentWebViewScreen
import com.zenvest.feature_consent.presentation.viewmodel.ConsentEvent
import com.zenvest.feature_consent.presentation.viewmodel.ConsentViewModel
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import com.zenvest.feature_dashboard.presentation.screens.debt.DebtScreen
import com.zenvest.feature_dashboard.presentation.screens.emergency.EmergencyFundScreen
import com.zenvest.feature_dashboard.presentation.screens.goals.GoalsScreen
import com.zenvest.feature_dashboard.presentation.screens.help.HelpScreen
import com.zenvest.feature_dashboard.presentation.screens.home.DashboardHomeScreen
import com.zenvest.feature_dashboard.presentation.screens.investments.InvestmentsScreen
import com.zenvest.feature_dashboard.presentation.screens.profile.ProfileScreen
import com.zenvest.feature_dashboard.presentation.screens.settings.SettingsScreen
import com.zenvest.feature_dashboard.presentation.viewmodel.DashboardViewModel
import com.zenvest.feature_dashboard.presentation.viewmodel.DebtViewModel
import com.zenvest.feature_dashboard.presentation.viewmodel.EmergencyFundViewModel
import com.zenvest.feature_dashboard.presentation.viewmodel.GoalsViewModel
import com.zenvest.feature_dashboard.presentation.viewmodel.InvestmentsViewModel
import com.zenvest.feature_onboarding.presentation.screens.OnboardingScreen
import com.zenvest.feature_onboarding.presentation.viewmodel.OnboardingViewModel
import com.zenvest.ui.screens.DashboardScaffold
import com.zenvest.ui.screens.SplashScreen

@Composable
fun ZenvestNavGraph(
    navController: NavHostController,
    startDestination: String = Routes.Splash.route,
    onGoogleSignIn: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Splash
        composable(Routes.Splash.route) {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToDashboard = {
                    navController.navigate(Routes.Dashboard.route) {
                        popUpTo(Routes.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToOnboarding = {
                    navController.navigate(Routes.Onboarding.route) {
                        popUpTo(Routes.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToConsent = {
                    navController.navigate(Routes.Consent.route) {
                        popUpTo(Routes.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        // Auth
        composable(Routes.Login.route) {
            val viewModel: AuthViewModel = hiltViewModel()
            LoginScreen(
                viewModel = viewModel,
                onNavigateToOtp = {
                    navController.navigate(Routes.OtpVerify.route)
                },
                onGoogleSignInClick = onGoogleSignIn
            )
        }

        composable(Routes.OtpVerify.route) {
            val parentEntry = navController.currentBackStack.value
                .firstOrNull { it.destination.route == Routes.Login.route }
            val viewModel: AuthViewModel = if (parentEntry != null) {
                hiltViewModel(parentEntry)
            } else {
                hiltViewModel()
            }

            OtpVerifyScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToOnboarding = {
                    navController.navigate(Routes.Onboarding.route) {
                        popUpTo(0)
                    }
                },
                onNavigateToConsent = {
                    navController.navigate(Routes.Consent.route) {
                        popUpTo(0)
                    }
                },
                onNavigateToDashboard = {
                    navController.navigate(Routes.Dashboard.route) {
                        popUpTo(0)
                    }
                }
            )
        }

        // Onboarding
        composable(Routes.Onboarding.route) {
            val viewModel: OnboardingViewModel = hiltViewModel()
            OnboardingScreen(
                viewModel = viewModel,
                onNavigateToConsent = {
                    navController.navigate(Routes.Consent.route) {
                        popUpTo(Routes.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        // Consent
        composable(Routes.Consent.route) {
            val viewModel: ConsentViewModel = hiltViewModel()
            ConsentScreen(
                viewModel = viewModel,
                onNavigateToWebView = { url ->
                    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                    navController.navigate(Routes.ConsentWebView.createRoute(encodedUrl))
                },
                onNavigateToDashboard = {
                    navController.navigate(Routes.Dashboard.route) {
                        popUpTo(Routes.Consent.route) { inclusive = true }
                    }
                }
            )
        }

        // Consent WebView
        composable(Routes.ConsentWebView.route) { backStackEntry ->
            val encodedUrl = backStackEntry.arguments?.getString("url") ?: ""
            val decodedUrl = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())

            // Get the ConsentViewModel from the parent Consent route
            val parentEntry = navController.currentBackStack.value
                .firstOrNull { it.destination.route == Routes.Consent.route }
            val viewModel: ConsentViewModel = if (parentEntry != null) {
                hiltViewModel(parentEntry)
            } else {
                hiltViewModel()
            }

            ConsentWebViewScreen(
                url = decodedUrl,
                onConsentComplete = { consentId ->
                    // Trigger consent completed event
                    viewModel.onEvent(ConsentEvent.ConsentCompleted)
                    // Navigate to dashboard
                    navController.navigate(Routes.Dashboard.route) {
                        popUpTo(Routes.Consent.route) { inclusive = true }
                    }
                },
                onNavigateBack = {
                    navController.popBackStack()
                },
                onError = { error ->
                    // Navigate back to consent screen on error
                    navController.popBackStack()
                }
            )
        }

        // Dashboard (with bottom navigation)
        composable(Routes.Dashboard.route) {
            DashboardScaffold(
                onLogout = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}
