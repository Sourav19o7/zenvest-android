package com.zenvest.core.ui.navigation

sealed class Routes(val route: String) {
    // Splash & Auth
    data object Splash : Routes("splash")
    data object Login : Routes("login")
    data object OtpVerify : Routes("otp_verify")

    // Onboarding
    data object Onboarding : Routes("onboarding")

    // Consent
    data object Consent : Routes("consent")
    data object ConsentWebView : Routes("consent_webview/{url}") {
        fun createRoute(url: String) = "consent_webview/$url"
    }

    // Dashboard
    data object Dashboard : Routes("dashboard")
    data object DashboardHome : Routes("dashboard_home")
    data object Goals : Routes("goals")
    data object AddGoal : Routes("add_goal")
    data object Investments : Routes("investments")
    data object AddInvestment : Routes("add_investment")
    data object Debt : Routes("debt")
    data object AddDebt : Routes("add_debt")
    data object EmergencyFund : Routes("emergency_fund")
    data object Profile : Routes("profile")
    data object Settings : Routes("settings")
    data object Help : Routes("help")

    companion object {
        fun fromRoute(route: String?): Routes? {
            return when (route) {
                Splash.route -> Splash
                Login.route -> Login
                OtpVerify.route -> OtpVerify
                Onboarding.route -> Onboarding
                Consent.route -> Consent
                Dashboard.route -> Dashboard
                DashboardHome.route -> DashboardHome
                Goals.route -> Goals
                Investments.route -> Investments
                Debt.route -> Debt
                EmergencyFund.route -> EmergencyFund
                Profile.route -> Profile
                Settings.route -> Settings
                Help.route -> Help
                else -> null
            }
        }
    }
}
