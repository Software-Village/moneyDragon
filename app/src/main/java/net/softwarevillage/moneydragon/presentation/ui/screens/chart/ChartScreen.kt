package net.softwarevillage.moneydragon.presentation.ui.screens.chart

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.common.utils.totalTransactionUiModel
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel
import net.softwarevillage.moneydragon.presentation.navigation.Screen
import net.softwarevillage.moneydragon.presentation.ui.components.MainButton
import net.softwarevillage.moneydragon.presentation.ui.components.MainLottie
import net.softwarevillage.moneydragon.presentation.ui.screens.chart.components.MainGroupBarTransactionChart
import net.softwarevillage.moneydragon.presentation.ui.screens.chart.components.TotalTransactionItem
import net.softwarevillage.moneydragon.presentation.ui.screens.wallet.components.AccountMovementItem
import net.softwarevillage.moneydragon.presentation.ui.theme.Black1E
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun ChartScreen(
    onNavigate: (String) -> Unit,
    viewModel: ChartViewModel = hiltViewModel(),
) {
    val modifier = Modifier

    val state = viewModel.state.collectAsStateWithLifecycle()
    val effect = viewModel.effect.collectAsStateWithLifecycle(initialValue = null)

    val context = LocalContext.current

    val transactions = remember { mutableStateOf(emptyList<TransactionUiModel>()) }

    val scrollState = rememberScrollState()



    LaunchedEffect(key1 = state.value) {
        if (!state.value.isLoading) {
            when (effect.value) {
                is ChartEffect.ShowMessage -> {
                    val effectValue = effect.value as ChartEffect.ShowMessage
                    Toast.makeText(context, effectValue.message, Toast.LENGTH_SHORT).show()
                }

                else -> Unit
            }
        }
    }

    Surface(
        modifier = modifier
            .fillMaxSize(), color = Color.White
    ) {
        if (state.value.isCardHave) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
            ) {

                viewModel.setEvent(ChartEvent.GetCardDetails)

                if (state.value.cardUiModel != null) {
                    state.value.cardUiModel?.let { cardUiModel ->

                        Spacer(modifier = modifier.size(32.dp))

                        Text(
                            modifier = modifier.align(Alignment.CenterHorizontally),
                            text = stringResource(id = R.string.statistics),
                            color = Color.Black,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.W600,
                            fontSize = 20.sp,
                        )

                        Spacer(modifier = modifier.size(32.dp))

                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 32.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.calendar_icon),
                                contentDescription = ""
                            )

                            Spacer(modifier = modifier.size(8.dp))

                            Text(
                                text = stringResource(id = R.string.all_time),
                                fontSize = 16.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.W500,
                                color = Color.Black
                            )

                        }

                        if (state.value.isTransactionHave) {
                            viewModel.setEvent(ChartEvent.GetTransactions)
                            viewModel.setEvent(ChartEvent.GetHighestTransaction(type = 1))
                            transactions.value = state.value.transactions

                            Spacer(modifier = modifier.size(24.dp))
                            Row(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 32.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                TotalTransactionItem(
                                    modifier = modifier
                                        .height(180.dp)
                                        .padding(vertical = 8.dp)
                                        .weight(1f),
                                    transactionUiModel = totalTransactionUiModel(
                                        transactions.value.filter { it.type == 1 },
                                        type = 1
                                    ),
                                    onNavigate = onNavigate
                                )

                                Spacer(modifier = modifier.size(8.dp))

                                TotalTransactionItem(
                                    modifier = modifier
                                        .height(180.dp)
                                        .padding(vertical = 8.dp)
                                        .weight(1f),
                                    transactionUiModel = totalTransactionUiModel(
                                        transactions.value.filter { it.type != 1 },
                                        type = 0
                                    ),
                                    onNavigate = onNavigate
                                )
                            }

                            Card(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 32.dp, vertical = 32.dp)
                                    .height(300.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 4.dp
                                ),
                                shape = RoundedCornerShape(25.dp)
                            ) {
                                Text(
                                    modifier = modifier.padding(
                                        horizontal = 24.dp,
                                        vertical = 24.dp
                                    ),
                                    text = stringResource(id = R.string.expenses_trends),
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight.W700,
                                    color = Black1E,
                                    fontSize = 18.sp
                                )

                                MainGroupBarTransactionChart(
                                    maxRange = 100,
                                    yStepSize = 5,
                                    transactionIncoming = if (transactions.value.size > 50) transactions.value.subList(
                                        0,
                                        50
                                    )
                                        .filter { it.type == 1 } else transactions.value.filter { it.type == 1 },
                                    transactionOutgoing = if (transactions.value.size > 50) transactions.value.subList(
                                        0,
                                        50
                                    )
                                        .filter { it.type != 1 } else transactions.value.filter { it.type != 1 }
                                )
                            }

                            Text(
                                modifier = modifier.padding(horizontal = 32.dp),
                                text = stringResource(id = R.string.highest_expense),
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.W700,
                                color = Black1E,
                                fontSize = 16.sp
                            )

                            Spacer(modifier = modifier.size(16.dp))

                            state.value.highestTransaction?.let { highestTransactionUiModel ->
                                Box(modifier = modifier.padding(horizontal = 28.dp)) {
                                    AccountMovementItem(
                                        transactionUiModel = highestTransactionUiModel,
                                        onNavigation = onNavigate
                                    )
                                }
                            }
                        } else {
                            MainLottie(showState = true, res = R.raw.lottie_empty_state_anim)
                        }
                    }
                }
            }
        } else {

            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainButton(
                    title = R.string.add_card
                ) {
                    onNavigate(Screen.AddCardScreen.route)
                }
            }
        }
    }


}
