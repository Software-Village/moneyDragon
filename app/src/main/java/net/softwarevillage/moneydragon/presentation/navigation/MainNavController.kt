package net.softwarevillage.moneydragon.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import net.softwarevillage.moneydragon.common.Screen
import net.softwarevillage.moneydragon.presentation.ui.screens.chart.ChartScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.home.HomeScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.onboarding.OnboardingScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.profile.ProfileScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.splash.SplashScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.wallet.WalletScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.wallet.addCard.AddCardScreen
import net.softwarevillage.moneydragon.presentation.ui.theme.PurpleBF
import net.softwarevillage.moneydragon.presentation.ui.screens.HomeScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.ProfileScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.auth.CreateUserScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.auth.OnboardingScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.auth.SplashScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.auth.WelcomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavController() {

    val navController = rememberNavController()
    val showBottomBar = remember { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val items = listOf(
        Screen.HomeScreen,
        Screen.WalletScreen,
        Screen.ChartScreen,
        Screen.ProfileScreen
    )

    showBottomBar.value = when (currentDestination?.route) {
        Screen.OnboardingScreen.route,
        Screen.SplashScreen.route,
        Screen.AddCardScreen.route,
        -> false

        Screen.WelcomeScreen.route, Screen.SplashScreen.route, Screen.OnboardingScreen.route, Screen.CreateUserScreen.route -> false
        else -> true
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar.value) {
                BottomAppBar(
                    containerColor = Color.White
                ) {
                    items.forEach { screen ->
                        NavigationBarItem(

                            icon = {
                                Icon(
                                    painter = painterResource(id = screen.icon!!),
                                    contentDescription = null
                                )
                            },
                            label = { Text(stringResource(screen.name)) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = PurpleBF,
                                selectedIconColor = Color.White,
                                unselectedIconColor = Color.Unspecified
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.SplashScreen.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.SplashScreen.route) {
                SplashScreen(navigateHome = {
                    navController.navigate(Screen.WelcomeScreen.route)
                })
            }
            composable(Screen.WelcomeScreen.route) {
                WelcomeScreen(navigateOnboarding = {
                    navController.navigate(Screen.OnboardingScreen.route)
                })
            }
            composable(Screen.OnboardingScreen.route) {
                OnboardingScreen()
            }
            composable(Screen.HomeScreen.route) {
                HomeScreen()
            }

            composable(Screen.ProfileScreen.route) { ProfileScreen() }

            composable(Screen.OnboardingScreen.route) {
                OnboardingScreen(navigateLogin = {
                    navController.navigate(Screen.CreateUserScreen.route)
                })
            }

            composable(Screen.CreateUserScreen.route) {
                CreateUserScreen(navigateBack = {
                    navController.popBackStack()
                })
            }
            composable(Screen.WalletScreen.route) {
                WalletScreen(
                    onNavigate = {
                        navController.navigate(it)
                    }
                )
            }
            composable(Screen.ChartScreen.route) {
                ChartScreen()
            }
            composable(Screen.AddCardScreen.route) {
                AddCardScreen(
                    onNavigate = {

                    },
                    onBackNavigate = {
                        navController.popBackStack()
                    }
                )
            }
            composable(Screen.ProfileScreen.route) {
                ProfileScreen()
            }
        }
    }

}