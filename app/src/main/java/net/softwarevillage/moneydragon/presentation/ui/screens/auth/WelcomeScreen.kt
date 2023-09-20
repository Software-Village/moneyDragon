package net.softwarevillage.moneydragon.presentation.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.presentation.ui.components.MainButton
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.Grey87

@Composable
fun WelcomeScreen(
    navigateOnboarding: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {

    viewModel.getOnboardComplete()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "logo")
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(id = R.string.welcome_title),
                fontSize = 45.sp,
                textAlign = TextAlign.Center,
                color = Color.Black, fontWeight = FontWeight.W700
            )
            Text(
                text = stringResource(id = R.string.money_dragon),
                color = Blue,
                fontSize = 48.sp,
                fontWeight = FontWeight.W700
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = stringResource(id = R.string.splash_title), color = Grey87)
        }
        Row(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            MainButton(title = R.string.get_started) {
                navigateOnboarding.invoke()
            }
        }
    }
}