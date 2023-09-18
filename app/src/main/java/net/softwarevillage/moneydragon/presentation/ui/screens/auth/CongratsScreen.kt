package net.softwarevillage.moneydragon.presentation.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.presentation.ui.components.MainButton
import net.softwarevillage.moneydragon.presentation.ui.components.MainLottie
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun CongratsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxSize(0.19f))
        MainLottie(
            showState = true,
            res = R.raw.animation_success,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.fillMaxSize(0.11f))
        Text(
            text = stringResource(id = R.string.congrats),
            fontSize = 55.sp,
            fontWeight = FontWeight.W700,
            fontFamily = fontFamily,
            color = Blue
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.account_register),
            fontSize = 16.sp,
            color = Blue,
            fontFamily = fontFamily,
            fontWeight = FontWeight.W600, textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.fillMaxSize(0.15f))
        MainButton(title = R.string.great) {

        }
    }
}

@Preview
@Composable
fun PreviewCongrats() {
    CongratsScreen()
}
