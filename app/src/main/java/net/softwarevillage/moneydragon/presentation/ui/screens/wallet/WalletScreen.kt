package net.softwarevillage.moneydragon.presentation.ui.screens.wallet

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.presentation.navigation.Screen
import net.softwarevillage.moneydragon.presentation.ui.components.MainButton
import net.softwarevillage.moneydragon.presentation.ui.components.MainLottie
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.CardFace
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.FlipCard
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.MainCardBackItem
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.MainCardFrontItem
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.RotationAxis
import net.softwarevillage.moneydragon.presentation.ui.screens.wallet.components.AccountMovementItem
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.Grey87
import net.softwarevillage.moneydragon.presentation.ui.theme.PurpleBF
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun WalletScreen(
    onNavigate: (String) -> Unit,
    viewModel: WalletViewModel = hiltViewModel(),
) {
    val modifier = Modifier

    val context = LocalContext.current

    val state = viewModel.state.collectAsStateWithLifecycle()
    val effect = viewModel.effect.collectAsStateWithLifecycle(initialValue = null)


    val cardState = remember {
        mutableStateOf(CardFace.Front)
    }

    LaunchedEffect(key1 = state) {

        if (!state.value.isLoading) {
            when (effect.value) {
                is WalletEffect.ShowMessage -> {
                    val effectValue = effect.value as WalletEffect.ShowMessage
                    Toast.makeText(context, effectValue.message, Toast.LENGTH_SHORT).show()
                }

                else -> Unit
            }
        }
    }

    Surface(
        modifier = modifier.fillMaxSize()
    ) {

        Column(
            modifier = modifier.fillMaxSize()
        ) {

            Spacer(modifier = modifier.size(35.dp))

            Text(
                modifier = modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.card_Details),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp
            )

            Spacer(modifier = modifier.size(20.dp))


            if (state.value.isCardHave) {
                viewModel.setEvent(WalletEvent.CardDetails)

                state.value.cardUiModel?.let { cardUiModel ->
                    viewModel.setEvent(WalletEvent.IsTransactionHave)
                    Column {
                        FlipCard(
                            cardFace = cardState.value,
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(vertical = 15.dp, horizontal = 15.dp),
                            onClick = {
                                cardState.value = cardState.value.next
                            },
                            axis = RotationAxis.AxisY,
                            back = {
                                MainCardBackItem(cardUiModel = cardUiModel)
                            },
                            front = {
                                MainCardFrontItem(cardUiModel = cardUiModel)
                            }
                        )
                        Spacer(modifier.size(15.dp))

                        MainButton(
                            title = R.string.add_transaction,
                            modifier = modifier.align(Alignment.CenterHorizontally)
                        ) {
                            onNavigate(Screen.TransactionScreen.route)
                        }

                        Spacer(modifier.size(15.dp))

                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 35.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = modifier,
                                text = stringResource(id = R.string.transactions),
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Normal,
                                color = Grey87,
                                fontSize = 18.sp
                            )

                            TextButton(onClick = {
                                onNavigate(Screen.HistoryScreen.route)
                            }) {
                                Text(
                                    text = stringResource(id = R.string.see_all),
                                    fontSize = 14.sp,
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight.W400,
                                    color = Blue
                                )
                                Spacer(modifier = modifier.size(6.dp))
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow_right),
                                    contentDescription = ""
                                )
                            }
                        }

                        Spacer(modifier = modifier.size(4.dp))

                        if (state.value.isTransactionHave) {
                            viewModel.setEvent(WalletEvent.Transactions)
                            if (state.value.transactions.isNotEmpty()) {
                                LazyColumn {
                                    items(state.value.transactions) {
                                        AccountMovementItem(transactionUiModel = it)
                                    }
                                }
                            } else {
                                MainLottie(showState = true, res = R.raw.lottie_empty_state_anim)
                            }
                        } else {
                            MainLottie(showState = true, res = R.raw.lottie_empty_state_anim)
                        }

                    }
                }

            } else {
                AddCardComposable(onNavigate = onNavigate)
            }

        }
    }
}

@Composable
fun AddCardComposable(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit,
) {


    val composition = rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.lottie_card_anim)
    )

    Column {

        LottieAnimation(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .clip(RectangleShape),
            composition = composition.value,
            contentScale = ContentScale.Crop,
            iterations = LottieConstants.IterateForever,
            speed = 0.7f
        )

        Spacer(modifier = modifier.size(30.dp))

        FilledTonalButton(
            onClick = {
                onNavigate(Screen.AddCardScreen.route)
            },
            modifier = modifier.align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = PurpleBF
            )
        ) {
            Text(
                text = stringResource(id = R.string.add_card),
                modifier = modifier.padding(horizontal = 20.dp, vertical = 7.dp),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.White
            )
        }
    }

}



