package net.softwarevillage.moneydragon.presentation.ui.screens.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.softwarevillage.moneydragon.domain.model.OnboardingPageUiModel
import net.softwarevillage.moneydragon.presentation.ui.theme.Grey87


@Composable
fun OnboardPagerItem(onboardingPageUiModel: OnboardingPageUiModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = onboardingPageUiModel.image),
            contentDescription = "",
            modifier = Modifier.size(320.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = onboardingPageUiModel.title),
            fontSize = 32.sp,
            fontWeight = FontWeight.W700,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = onboardingPageUiModel.description),
            color = Grey87,
            textAlign = TextAlign.Center
        )
    }
}