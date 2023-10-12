package net.softwarevillage.moneydragon.presentation.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.presentation.navigation.Screen
import net.softwarevillage.moneydragon.presentation.ui.screens.auth.AuthViewModel
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue

@Composable
fun SplashScreen(
    onNavigate: (String) -> Unit,
    viewModel: AuthViewModel = hiltViewModel(),
) {

    val state = viewModel.state.collectAsStateWithLifecycle()


    LaunchedEffect(key1 = state.value) {
        delay(2500)
        if (state.value.isTokenHave && state.value.isCompleted) {
            onNavigate(Screen.HomeScreen.route)
        } else if (!state.value.isTokenHave && state.value.isCompleted) {
            onNavigate(Screen.LoginScreen.route)
        } else {
            onNavigate(Screen.WelcomeScreen.route)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "logo")
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = R.string.money_dragon),
            color = Blue,
            fontSize = 48.sp,
            fontWeight = FontWeight.W600,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(id = R.string.splash_title), color = Blue)
    }

}