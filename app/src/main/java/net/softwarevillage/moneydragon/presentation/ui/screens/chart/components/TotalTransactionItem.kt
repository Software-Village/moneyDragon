package net.softwarevillage.moneydragon.presentation.ui.screens.chart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel
import net.softwarevillage.moneydragon.presentation.ui.theme.BlueEB
import net.softwarevillage.moneydragon.presentation.ui.theme.PurpleBF
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TotalTransactionItem(
    modifier: Modifier = Modifier,
    transactionUiModel: TransactionUiModel,
    onNavigate: (String) -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = {
            //navigation
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(
                    id = if (transactionUiModel.type == 1) R.drawable.arrow_incoming else R.drawable.arrow_outgoing
                ), contentDescription = ""
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                text = if (transactionUiModel.type == 1) stringResource(id = R.string.income) else stringResource(
                    id = R.string.outgoing
                ),
                fontFamily = fontFamily,
                fontWeight = FontWeight.W700,
                fontSize = 18.sp,
                color = if (transactionUiModel.type == 1) BlueEB else PurpleBF
            )

            Spacer(modifier = Modifier.size(8.dp))


            Text(
                text = "$ ${transactionUiModel.amount}",
                fontFamily = fontFamily,
                fontWeight = FontWeight.W700,
                fontSize = 24.sp,
                color = if (transactionUiModel.type == 1) BlueEB else PurpleBF
            )
        }
    }
}




