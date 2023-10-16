package net.softwarevillage.moneydragon.presentation.ui.screens.transaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
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
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.presentation.navigation.Screen
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.BlueEB
import net.softwarevillage.moneydragon.presentation.ui.theme.PurpleBF
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun TransactionSuccessScreen(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxSize(), color = Color.White
    ) {
        Column(
            modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = modifier.fillMaxSize(0.18f))

            Card(
                modifier = modifier.size(200.dp),
                shape = RoundedCornerShape(percent = 100),
                colors = CardDefaults.cardColors(
                    containerColor = BlueEB
                )
            ) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        alignment = Alignment.Center,
                        painter = painterResource(id = R.drawable.congrats_icon),
                        contentDescription = ""
                    )
                }
            }

            Spacer(modifier = modifier.size(40.dp))

            Text(
                text = stringResource(id = R.string.congrats),
                fontFamily = fontFamily,
                fontWeight = FontWeight.W700,
                color = PurpleBF,
                fontSize = 55.sp,
                lineHeight = 35.sp
            )

            Spacer(modifier = modifier.size(32.dp))

            Text(
                text = stringResource(id = R.string.transaction_success),
                fontFamily = fontFamily,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center,
                color = PurpleBF,
                fontSize = 16.sp
            )

            Box(
                modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(50.dp),
                    contentPadding = PaddingValues(horizontal = 42.dp, vertical = 14.dp),
                    modifier = modifier,
                    onClick = { onNavigate(Screen.WalletScreen.route) }
                ) {
                    Text(
                        text = stringResource(id = R.string.great),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W600
                    )
                }
            }
        }
    }
}