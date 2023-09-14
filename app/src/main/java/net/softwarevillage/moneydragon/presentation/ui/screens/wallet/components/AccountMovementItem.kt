package net.softwarevillage.moneydragon.presentation.ui.screens.wallet.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel
import net.softwarevillage.moneydragon.presentation.ui.theme.Black
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.BlueEB
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun AccountMovementItem(
    transactionUiModel: TransactionUiModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(12.dp)) {
            Column(modifier = Modifier.weight(0.25f)) {
                Box(
                    modifier = Modifier
                        .background(
                            color = if (transactionUiModel.type == 1) BlueEB else Blue,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(16.dp), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.shopping_bag),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                }
            }
            Column(modifier = Modifier.weight(0.54f)) {
                Text(
                    text = transactionUiModel.title,
                    fontSize = 18.sp,
                    color = Black,
                    fontWeight = FontWeight.W600,
                    fontFamily = fontFamily
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = transactionUiModel.date,
                    color = Black,
                    fontFamily = fontFamily,
                    fontSize = 12.sp
                )
            }
            Column(modifier = Modifier.weight(0.21f), horizontalAlignment = Alignment.End) {
                Icon(
                    painter = if (transactionUiModel.type == 1) painterResource(id = R.drawable.arrow_top) else painterResource(
                        id = R.drawable.arrow_top_right
                    ), contentDescription = "", tint = Blue
                )
                Text(
                    text = "$${transactionUiModel.amount}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700,
                    color = Blue,
                    fontFamily = fontFamily
                )
            }
        }
    }
}