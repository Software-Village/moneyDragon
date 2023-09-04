package net.softwarevillage.moneydragon.presentation.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue

@Composable
fun NavigationButton(
    navigate: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { navigate.invoke() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue,
            contentColor = Color.White
        ),
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_left),
            contentDescription = "navButton"
        )
    }
}