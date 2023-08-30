package net.softwarevillage.moneydragon.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import net.softwarevillage.moneydragon.common.Screen
import net.softwarevillage.moneydragon.presentation.ui.screens.HomeScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.OnboardingScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.ProfileScreen
import net.softwarevillage.moneydragon.presentation.ui.screens.SplashScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavController() {

    val navController = rememberNavController()
    val showBottomBar = remember { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val items = listOf(
        Screen.HomeScreen,
        Screen.ProfileScreen
    )

    showBottomBar.value = when (currentDestination?.route) {
        Screen.OnboardingScreen.route, Screen.SplashScreen.route -> false
        else -> true
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar.value) {
                BottomAppBar {
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
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.HomeScreen.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.SplashScreen.route) {
                SplashScreen()
            }
            composable(Screen.OnboardingScreen.route) { OnboardingScreen() }
            composable(Screen.HomeScreen.route) {
                HomeScreen()
            }
            composable(Screen.ProfileScreen.route) { ProfileScreen() }
        }
    }

}