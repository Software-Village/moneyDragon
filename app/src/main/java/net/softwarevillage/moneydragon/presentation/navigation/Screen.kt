package net.softwarevillage.moneydragon.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import net.softwarevillage.moneydragon.R

sealed class Screen(
    val route: String,
    @DrawableRes val icon: Int? = null,
    @StringRes val name: Int
) {

    object SplashScreen : Screen("splashScreen", name = R.string.splash)

    object WelcomeScreen : Screen("welcomeScreen", name = R.string.welcome)

    object AddCardScreen : Screen("addCardScreen", name = R.string.addCard)


    //BOTTOM NAVIGATION
    object WalletScreen :
        Screen("walletScreen", name = R.string.wallet, icon = R.drawable.wallet_icon)

    object ChartScreen : Screen("chartScreen", name = R.string.chart, icon = R.drawable.chart_icon)

    object HomeScreen : Screen("homeScreen", name = R.string.home, icon = R.drawable.home_icon)

    object OnboardingScreen : Screen(route = "onboardingScreen", name = R.string.onboarding)

    object ProfileScreen : Screen(
        "profileScreen",
        name = R.string.profile,
        icon = R.drawable.user_icon
    )

    object CreateUserScreen : Screen(
        route = "createUser",
        name = R.string.create_user
    )

    object CongratsScreen : Screen(
        route = "congratsScreen",
        name = R.string.congrats
    )

}