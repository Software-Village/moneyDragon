package net.softwarevillage.moneydragon.presentation.ui.screens.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel
import net.softwarevillage.moneydragon.presentation.ui.components.NavigationButton
import net.softwarevillage.moneydragon.presentation.ui.screens.wallet.components.AccountMovementItem
import net.softwarevillage.moneydragon.presentation.ui.screens.wallet.components.TransactionItem
import net.softwarevillage.moneydragon.presentation.ui.theme.Black
import net.softwarevillage.moneydragon.presentation.ui.theme.Grey87
import net.softwarevillage.moneydragon.presentation.ui.theme.LightBlue
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun HistoryScreen() {

    val transaction = listOf(
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            NavigationButton(
                navigate = {},
                modifier = Modifier.padding(start = 32.dp)
            )
            Text(
                text = stringResource(id = R.string.history),
                color = Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier.padding(horizontal = 22.dp)) {
            Text(
                text = stringResource(id = R.string.date),
                color = Grey87,
                fontSize = 18.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.W500
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.calendar_icon),
                    contentDescription = "calendar"
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = stringResource(id = R.string.may),
                    color = Black,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W500,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(14.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                TransactionItem(
                    title = R.string.transactions,
                    icon = R.drawable.arrow_top,
                    price = "200$"
                )
                Spacer(modifier = Modifier.width(12.dp))
                TransactionItem(
                    title = R.string.tickets,
                    icon = R.drawable.arrow_top_right,
                    price = "100$",
                    bgColor = LightBlue
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn() {
                items(transaction) {
                    AccountMovementItem(
                        transactionUiModel = it
                    )
                }
            }

        }
    }
}