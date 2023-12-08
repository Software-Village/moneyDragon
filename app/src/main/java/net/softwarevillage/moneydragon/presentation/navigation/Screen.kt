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

    object CardColorScreen : Screen("cardColorScreen", name = R.string.cardColor)

    object AddCardScreen : Screen("addCardScreen", name = R.string.addCard)

    object CreateUserScreen : Screen(route = "createUser", name = R.string.create_user)

    object CongratsScreen : Screen(route = "congratsScreen", name = R.string.congrats)

    object OnboardingScreen : Screen(route = "onboardingScreen", name = R.string.onboarding)

    object HistoryScreen : Screen(route = "historyScreen", name = R.string.history)

    object LoginScreen : Screen(route = "loginScreen", name = R.string.login)

    object RegisterScreen : Screen("routeScreen", name = R.string.register)

    object TransactionScreen : Screen("transactionScreen", name = R.string.transaction)

    object TransactionSuccessScreen :
        Screen("transactionSuccessScreen", name = R.string.transaction_success)

    object TransactionDetailsScreen :
        Screen(route = "TransactionDetailsScreen", name = R.string.transaction_details)

    object FeedScreen : Screen(route = "FeedScreen", name = R.string.feed_screen)


    //BOTTOM NAVIGATION
    object WalletScreen :
        Screen("walletScreen", name = R.string.wallet, icon = R.drawable.wallet_icon)

    object ChartScreen : Screen("chartScreen", name = R.string.chart, icon = R.drawable.chart_icon)

    object HomeScreen : Screen("homeScreen", name = R.string.home, icon = R.drawable.home_icon)

    object ProfileScreen : Screen(
        "profileScreen",
        name = R.string.profile,
        icon = R.drawable.user_icon
    )

}