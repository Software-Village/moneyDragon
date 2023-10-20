package net.softwarevillage.moneydragon.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.GsonBuilder
import net.softwarevillage.moneydragon.domain.model.CardFaceUiModel
import net.softwarevillage.moneydragon.presentation.ui.screens.auth.CongratsScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.auth.CreateUserScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.auth.LoginScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.auth.RegisterScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.auth.WelcomeScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.chart.ChartScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.details.TransactionDetailsScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.history.HistoryScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.home.HomeScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.onboarding.OnboardingScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.profile.ProfileScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.splash.SplashScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.transaction.TransactionScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.transaction.TransactionSuccessScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.wallet.WalletScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.wallet.addCard.AddCardScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.wallet.cardColor.CardColorScreen
import net.softwarevillage.moneydragon.presentation.ui.theme.PurpleBF

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
        Screen.WelcomeScreen.route,
        Screen.CreateUserScreen.route,
        Screen.CongratsScreen.route,
        Screen.CardColorScreen.route + "?{cardFaceUiModel}",
        Screen.LoginScreen.route,
        Screen.RegisterScreen.route,
        Screen.TransactionScreen.route,
        Screen.HistoryScreen.route,
        Screen.TransactionSuccessScreen.route,
        Screen.TransactionDetailsScreen.route + "?{id}",
        -> false

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
            startDestination = Screen.HistoryScreen.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.SplashScreen.route) {
                SplashScreen(
                    onNavigate = {
                        navController.navigate(it) {
                            popUpTo(Screen.SplashScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(
                route = Screen.CardColorScreen.route + "?{cardFaceUiModel}"
            ) {

                val gson = GsonBuilder().create()
                val dataJson = it.arguments?.getString("cardFaceUiModel")

                CardColorScreen(
                    onBackPressed = { navController.popBackStack() },
                    onNavigate = {
                        navController.navigate(it) {
                            popUpTo(Screen.AddCardScreen.route) {
                                inclusive = true
                            }
                        }
                    },
                    cardFaceUiModel = gson.fromJson(dataJson, CardFaceUiModel::class.java)
                )

            }

            composable(Screen.WelcomeScreen.route) {
                WelcomeScreen(navigateOnboarding = {
                    navController.navigate(Screen.OnboardingScreen.route)
                })
            }
            composable(Screen.OnboardingScreen.route) {
                OnboardingScreen(navigateLogin = {
                    navController.navigate(Screen.LoginScreen.route)
                })
            }
            composable(Screen.HomeScreen.route) {
                HomeScreen(onNavigate = {
                    navController.navigate(it)
                })
            }

            composable(Screen.ProfileScreen.route) { ProfileScreen() }

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
                ChartScreen(
                    onNavigate = {
                        navController.navigate(it)
                    }
                )
            }
            composable(Screen.AddCardScreen.route) {
                AddCardScreen(
                    onNavigate = {
                        navController.navigate(it)
                    },
                    onBackNavigate = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Screen.CongratsScreen.route) {
                CongratsScreen()
            }

            composable(route = Screen.HistoryScreen.route) {
                HistoryScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                    onNavigate = {
                        navController.navigate(it)
                    }
                )
            }

            composable(route = Screen.LoginScreen.route) {
                LoginScreen(navigateRegister = {
                    navController.navigate(Screen.RegisterScreen.route)
                },
                    navigateHome = {
                        navController.navigate(Screen.HomeScreen.route)
                    }
                )
            }

            composable(route = Screen.RegisterScreen.route) {
                RegisterScreen(navigateLogin = {
                    navController.popBackStack()
                })
            }

            composable(route = Screen.TransactionSuccessScreen.route) {
                TransactionSuccessScreen(
                    onNavigate = {
                        navController.navigate(it) {
                            popUpTo(Screen.TransactionScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(
                route = Screen.TransactionDetailsScreen.route + "?{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) {
                TransactionDetailsScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                    id = it.arguments?.getInt("id") ?: 0
                )
            }

            composable(route = Screen.TransactionScreen.route) {
                TransactionScreen(
                    onNavigate = {
                        navController.navigate(it)
                    },
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }

        }
    }

}