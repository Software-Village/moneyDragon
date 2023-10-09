package net.softwarevillage.moneydragon.presentation.ui.screens.wallet.components

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun TransactionItem(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    price: String,
    @ColorRes bgColor: Color = Blue,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = bgColor
        ), shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = title),
                    fontSize = 13.sp,
                    color = Color.White,
                    fontFamily = fontFamily
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Text(
                text = price,
                fontSize = 16.sp,
                color = Color.White,
                fontFamily = fontFamily,
                fontWeight = FontWeight.W600, modifier = Modifier.align(Alignment.End)
            )
        }
    }
}