package net.softwarevillage.moneydragon.presentation.ui.screens.chart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.BlueEB
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun TotalTransactionItem(
    transactionUiModel: TransactionUiModel,
    size: Dp,
) {

    val modifier = Modifier

    Card(
        modifier = modifier.size(size),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (transactionUiModel.type == 1) BlueEB else Blue
        )
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {

            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                Card(
                    modifier = modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .size(30.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Box(
                        modifier = modifier
                            .padding(3.dp)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            tint = if (transactionUiModel.type == 1) BlueEB else Blue,
                            painter = if (transactionUiModel.type == 1) painterResource(id = R.drawable.arrow_top) else painterResource(
                                id = R.drawable.arrow_top_right
                            ),
                            contentDescription = null
                        )
                    }
                }
            }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.Bottom,
            ) {
                Text(
                    text = if (transactionUiModel.type == 1) stringResource(id = R.string.income) else stringResource(
                        id = R.string.outgoing
                    ),
                    fontSize = 14.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
                Spacer(modifier = modifier.size(5.dp))
                Text(
                    text = "$${transactionUiModel.amount}",
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )

            }

        }

    }
}


