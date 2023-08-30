package net.softwarevillage.moneydragon.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import net.softwarevillage.moneydragon.R

sealed class Screen(
    val route: String,
    @DrawableRes val icon: Int? = null,
    @StringRes val name: Int
) {

    object SplashScreen : Screen("splashScreen", name = R.string.splash)

    object OnboardingScreen : Screen("onboardingScreen", name = R.string.onboarding)

    object HomeScreen :
        Screen("homeScreen", name = R.string.home, icon = R.drawable.baseline_home_24)

    object ProfileScreen : Screen(
        "profileScreen",
        name = R.string.profile,
        icon = R.drawable.baseline_account_circle_24
    )


}