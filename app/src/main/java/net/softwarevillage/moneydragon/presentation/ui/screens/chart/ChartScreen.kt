package net.softwarevillage.moneydragon.presentation.ui.screens.chart

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.BlueEB
import net.softwarevillage.moneydragon.presentation.ui.theme.Grey87
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
    if (state.value.isCardHave) {
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .fillMaxSize(),
        ) {

            viewModel.setEvent(ChartEvent.GetCardDetails)

            if (state.value.cardUiModel != null) {
                state.value.cardUiModel?.let { cardUiModel ->

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
                        text = "$${cardUiModel.balance}",
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

                    if (state.value.isTransactionHave) {

                        viewModel.setEvent(ChartEvent.GetTransactions)

                        transactions.value = state.value.transactions

                        MainGroupBarTransactionChart(
                            modifier = modifier
                                .height(300.dp)
                                .padding(0.dp),
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

                            if (state.value.transactions.isNotEmpty()) {
                                TotalTransactionItem(
                                    transactionUiModel = totalTransactionUiModel(transactions.value.filter { it.type == 1 }),
                                    size = 160.dp
                                )

                                TotalTransactionItem(
                                    transactionUiModel = totalTransactionUiModel(transactions.value.filter { it.type != 1 }),
                                    size = 160.dp
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
