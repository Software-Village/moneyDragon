package net.softwarevillage.moneydragon.presentation.ui.screens.wallet.components

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.softwarevillage.moneydragon.common.utils.HistoryFilterEnum
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionItem(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    chipState: MutableState<HistoryFilterEnum>,
    state: HistoryFilterEnum,
    price: String,
    @ColorRes bgColor: Color = Blue,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = bgColor
        ),
        onClick = {
            chipState.value =
                if (chipState.value == HistoryFilterEnum.NOTHING) state else HistoryFilterEnum.NOTHING
        },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (chipState.value == state) {
                Icon(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    imageVector = Icons.Default.Check,
                    contentDescription = null
                )
            }
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
}