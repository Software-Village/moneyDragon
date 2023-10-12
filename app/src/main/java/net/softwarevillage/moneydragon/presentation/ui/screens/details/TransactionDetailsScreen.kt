package net.softwarevillage.moneydragon.presentation.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import net.softwarevillage.moneydragon.presentation.ui.components.NavigationButton
import net.softwarevillage.moneydragon.presentation.ui.theme.BlueEB
import net.softwarevillage.moneydragon.presentation.ui.theme.GreyText
import net.softwarevillage.moneydragon.presentation.ui.theme.PurpleBF
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun TransactionDetailsScreen(
    modifier: Modifier = Modifier,
    id: Int,
    viewModel: TransactionDetailsViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {

    val state = viewModel.state.collectAsStateWithLifecycle()
    val effect = viewModel.effect.collectAsStateWithLifecycle(initialValue = null)

    val context = LocalContext.current



    Surface(
        modifier = modifier
            .fillMaxSize(),
    ) {
        viewModel.setEvent(TransactionDetailsEvent.TransactionDetails(id))
        Column(
            modifier = modifier
        ) {

            NavigationButton(navigate = { onBack() }, modifier = modifier.padding(32.dp))

            if (state.value.isLoading) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                state.value.transactionUiModel?.let { transactionUiModel ->

                    Spacer(modifier = modifier.fillMaxSize(0.07f))

                    Card(
                        modifier = modifier
                            .size(100.dp)
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(percent = 100),
                        colors = CardDefaults.cardColors(
                            containerColor = if (transactionUiModel.type == 1) BlueEB else PurpleBF
                        )
                    ) {
                        Image(
                            modifier = modifier.padding(32.dp),
                            alignment = Alignment.Center,
                            painter = painterResource(id = R.drawable.congrats_icon),
                            contentDescription = ""
                        )
                    }

                    Spacer(modifier = modifier.size(16.dp))

                    Text(
                        modifier = modifier.align(Alignment.CenterHorizontally),
                        text = stringResource(id = R.string.transaction_details),
                        fontSize = 36.sp,
                        fontWeight = FontWeight.W700,
                        lineHeight = 30.sp,
                        color = PurpleBF,
                        fontFamily = fontFamily
                    )

                    Spacer(modifier = modifier.size(16.dp))

                    Text(
                        modifier = modifier.align(Alignment.CenterHorizontally),
                        text = transactionUiModel.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W700,
                        color = Color.Black,
                        fontFamily = fontFamily
                    )

                    Spacer(modifier = modifier.size(16.dp))

                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = stringResource(id = R.string.date),
                            fontSize = 18.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.W700,
                            color = PurpleBF
                        )

                        Text(
                            text = transactionUiModel.date,
                            fontSize = 18.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.W400,
                            color = Color.Black
                        )
                    }

                    Divider(
                        modifier = modifier.padding(vertical = 16.dp, horizontal = 24.dp),
                        thickness = 1.dp,
                        color = GreyText
                    )

                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = stringResource(id = R.string.amount),
                            fontSize = 18.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.W700,
                            color = PurpleBF
                        )

                        Text(
                            text = if (transactionUiModel.type == 1) "$${transactionUiModel.amount}" else "-$${transactionUiModel.amount}",
                            fontSize = 18.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.W400,
                            color = Color.Black
                        )
                    }

                    Divider(
                        modifier = modifier.padding(vertical = 16.dp, horizontal = 24.dp),
                        thickness = 1.dp,
                        color = GreyText
                    )

                }
            }


        }
    }
}