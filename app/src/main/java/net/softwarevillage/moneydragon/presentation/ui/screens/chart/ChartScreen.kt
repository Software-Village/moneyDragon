package net.softwarevillage.moneydragon.presentation.ui.screens.chart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.common.utils.cardNumberHider
import net.softwarevillage.moneydragon.common.utils.toHexCode
import net.softwarevillage.moneydragon.domain.model.CardUiModel
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel
import net.softwarevillage.moneydragon.presentation.ui.screens.chart.components.MainGroupBarTransactionChart
import net.softwarevillage.moneydragon.presentation.ui.screens.chart.components.TotalTransactionItem
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.BlueCard
import net.softwarevillage.moneydragon.presentation.ui.theme.BlueEB
import net.softwarevillage.moneydragon.presentation.ui.theme.Grey87
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun ChartScreen(

) {
    val modifier = Modifier

    val transactionIncoming = listOf(
        TransactionUiModel(
            1,
            2,
            "fasfafasf",
            "10:25",
            "5.45",
            1
        ),
        TransactionUiModel(
            1,
            2,
            "fasfafasf",
            "10:25",
            "5.45",
            1
        ), TransactionUiModel(
            1,
            2,
            "fasfafasf",
            "10:25",
            "5.45",
            1
        ), TransactionUiModel(
            1,
            2,
            "fasfafasf",
            "10:25",
            "5.45",
            2
        ),
        TransactionUiModel(
            1,
            2,
            "fasfafasf",
            "10:25",
            "5.45",
            2
        )
    )

    val transactionOutgoing = listOf(
        TransactionUiModel(
            1,
            2,
            "fasfafasf",
            "10:25",
            "2.45",
            1
        ),
        TransactionUiModel(
            1,
            2,
            "fasfafasf",
            "10:25",
            "2.45",
            1
        ), TransactionUiModel(
            1,
            2,
            "fasfafasf",
            "10:25",
            "2.45",
            1
        )
    )

    val scrollState = rememberScrollState()

    val fakeCardData = CardUiModel(
        holdersName = "Test",
        cardScheme = "Test",
        cardNumber = cardNumberHider("1234567891234567"),
        cvv = 111,
        expiryDate = "07/77",
        cardColor = BlueCard.toHexCode(),
        balance = 500545.28,
        id = 1
    )



    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        Spacer(modifier = modifier.size(32.dp))

        Text(
            modifier = modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.statistics),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = modifier.size(30.dp))

        Text(
            modifier = modifier.padding(horizontal = 20.dp),
            text = stringResource(id = R.string.total_balance),
            color = Grey87,
            fontFamily = fontFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = modifier.size(5.dp))

        Text(
            modifier = modifier.padding(horizontal = 20.dp),
            text = "$${fakeCardData.balance}",
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = modifier.size(20.dp))

        Text(
            modifier = modifier.padding(horizontal = 20.dp),
            text = stringResource(id = R.string.overview),
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp,
            color = Color.Black
        )

        Spacer(modifier = modifier.size(20.dp))

        MainGroupBarTransactionChart(
            modifier = modifier
                .height(300.dp)
                .padding(0.dp),
            maxRange = 100,
            yStepSize = 5,
            transactionIncoming = transactionIncoming,
            transactionOutgoing = transactionOutgoing
        )


        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = modifier.size(20.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = BlueEB
                    )
                ) {}
                Spacer(modifier = modifier.size(5.dp))
                Text(
                    text = stringResource(id = R.string.incoming),
                    fontSize = 14.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Grey87
                )
            }

            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = modifier.size(20.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Blue
                    )
                ) {}
                Spacer(modifier = modifier.size(5.dp))
                Text(
                    text = stringResource(id = R.string.outgoing),
                    fontSize = 14.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Grey87
                )
            }


        }

        Spacer(modifier = modifier.size(30.dp))

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TotalTransactionItem(
                transactionUiModel = TransactionUiModel(
                    1, 2, "a", "2", "1", 1
                ), 160.dp
            )
            TotalTransactionItem(
                transactionUiModel = TransactionUiModel(
                    2, 2, "a", "2", "1", 2
                ), 160.dp
            )

        }


    }
}