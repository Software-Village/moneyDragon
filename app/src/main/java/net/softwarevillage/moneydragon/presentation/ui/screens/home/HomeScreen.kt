package net.softwarevillage.moneydragon.presentation.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.common.utils.toHexCode
import net.softwarevillage.moneydragon.domain.model.CardUiModel
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel
import net.softwarevillage.moneydragon.presentation.navigation.Screen
import net.softwarevillage.moneydragon.presentation.ui.components.MainButton
import net.softwarevillage.moneydragon.presentation.ui.components.MainLoading
import net.softwarevillage.moneydragon.presentation.ui.components.MainLottie
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.BaseLazyHomeIncomingItem
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.BaseLazyHomeOutgoingItem
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.CardFace
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.FlipCard
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.MainCardBackItem
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.MainCardFrontItem
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.RotationAxis
import net.softwarevillage.moneydragon.presentation.ui.screens.wallet.components.AccountMovementItem
import net.softwarevillage.moneydragon.presentation.ui.theme.Black
import net.softwarevillage.moneydragon.presentation.ui.theme.BlueCard
import net.softwarevillage.moneydragon.presentation.ui.theme.Grey87
import net.softwarevillage.moneydragon.presentation.ui.theme.PurpleBF
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit,
) {

    val state = viewModel.state.collectAsStateWithLifecycle()
    val effect = viewModel.effect.collectAsStateWithLifecycle(initialValue = null)


    val mainHorizontalPadding = 20.dp

    val modifier = Modifier

    val scrollState = rememberScrollState()

    val context = LocalContext.current


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


    LaunchedEffect(key1 = state.value) {
        when (state.value) {
            HomeUiState.Error -> {
                when (effect.value) {
                    is HomeEffect.ShowMessage -> {
                        val effectValue = effect.value as HomeEffect.ShowMessage
                        Toast.makeText(context, effectValue.message, Toast.LENGTH_SHORT).show()
                    }

                    else -> Unit
                }
            }

            else -> Unit
        }

    }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Spacer(modifier = modifier.size(24.dp))

            Row(
                modifier = modifier
                    .padding(horizontal = mainHorizontalPadding)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu_icon),
                        contentDescription = null
                    )
                }

                IconButton(onClick = {

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.notification_icon),
                        contentDescription = null
                    )
                }

            }

            Spacer(modifier = modifier.size(24.dp))


            HomeCardStateCheck(state = state.value, onNavigate = onNavigate)

            Spacer(modifier = modifier.size(24.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = mainHorizontalPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.incoming_transactions),
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Grey87
                )

                TextButton(onClick = { }) {
                    Text(
                        text = stringResource(id = R.string.see_all),
                        fontSize = 14.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Normal,
                        color = PurpleBF
                    )
                    Spacer(modifier = modifier.size(6.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_right),
                        contentDescription = null
                    )
                }


            }

            when (state.value) {
                is HomeUiState.IsTransactionHave -> {
                    val stateValue = state.value as HomeUiState.IsTransactionHave
                    if (stateValue.isHave) {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = mainHorizontalPadding)
                        ) {
                            items(count = 5) {
                                BaseLazyHomeIncomingItem()
                            }
                        }
                    } else {
                        Box(
                            modifier = modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            MainLottie(
                                showState = true,
                                res = R.raw.lottie_empty_state_anim,
                                modifier = modifier.size(200.dp)
                            )
                        }
                    }
                }

                HomeUiState.Loading -> MainLoading()
                else -> Unit
            }


            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = mainHorizontalPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.outgoing_transactions),
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Grey87
                )

                TextButton(onClick = { }) {
                    Text(
                        text = stringResource(id = R.string.see_all),
                        fontSize = 14.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Normal,
                        color = PurpleBF
                    )
                    Spacer(modifier = modifier.size(6.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_right),
                        contentDescription = null
                    )
                }


            }

            when (state.value) {
                is HomeUiState.IsTransactionHave -> {
                    val stateValue = state.value as HomeUiState.IsTransactionHave
                    if (stateValue.isHave) {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = mainHorizontalPadding)
                        ) {
                            items(count = 5) {
                                BaseLazyHomeOutgoingItem()
                            }
                        }
                    } else {
                        Box(
                            modifier = modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            MainLottie(
                                showState = true,
                                res = R.raw.lottie_empty_state_anim,
                                modifier = modifier.size(200.dp)
                            )
                        }
                    }

                }

                HomeUiState.Loading -> MainLoading()
                else -> Unit
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = mainHorizontalPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.other_transactions),
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Grey87
                )

                TextButton(onClick = { }) {
                    Text(
                        text = stringResource(id = R.string.see_all),
                        fontSize = 14.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Normal,
                        color = PurpleBF
                    )
                    Spacer(modifier = modifier.size(6.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_right),
                        contentDescription = null
                    )
                }


            }
            when (state.value) {
                is HomeUiState.IsCardRegistered -> {
                    val stateValue = state.value as HomeUiState.IsCardRegistered
                    if (stateValue.isRegistered) {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 15.dp)
                        ) {
                            items(transaction) {
                                Box(
                                    modifier = modifier
                                        .fillParentMaxWidth()
                                        .padding(vertical = 5.dp)
                                ) {
                                    AccountMovementItem(transactionUiModel = it)
                                }
                            }
                        }
                    } else {
                        Box(
                            modifier = modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            MainLottie(
                                showState = true,
                                res = R.raw.lottie_empty_state_anim,
                                modifier = modifier.size(200.dp)
                            )
                        }
                    }
                }

                HomeUiState.Loading -> MainLoading()
                else -> Unit
            }


        }
    }

}

@Composable
fun HomeCardStateCheck(
    state: HomeUiState,
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit,
) {

    val cardUiModel = remember {
        mutableStateOf(
            CardUiModel(
                holdersName = "a",
                cardScheme = "A",
                cardNumber = "0",
                cvv = 1,
                expiryDate = "a",
                cardColor = BlueCard.toHexCode(),
                balance = 0.0,
                id = 1
            )
        )
    }


    when (state) {
        is HomeUiState.CardData -> cardUiModel.value = state.cardUiModel
        is HomeUiState.IsCardRegistered -> {
            if (state.isRegistered) {
                CardUI(cardUiModel = cardUiModel.value)
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.register_card),
                        fontSize = 18.sp,
                        fontFamily = fontFamily,
                        color = Black,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    MainButton(title = R.string.add_card) {
                        onNavigate(Screen.AddCardScreen.route)
                    }

                }
            }

        }

        HomeUiState.Loading -> MainLottie(showState = true, res = R.raw.loading)
        else -> Unit
    }

}


@Composable
fun CardUI(
    cardUiModel: CardUiModel,
) {

    val modifier = Modifier

    val mainHorizontalPadding = 20.dp

    val cardFace = remember {
        mutableStateOf(CardFace.Front)
    }

    Text(
        modifier = modifier.padding(horizontal = mainHorizontalPadding),
        text = stringResource(id = R.string.current_balance),
        fontSize = 18.sp,
        fontFamily = fontFamily,
        color = Grey87,
        fontWeight = FontWeight.Medium
    )
    Spacer(modifier = modifier.size(10.dp))
    Text(
        text = "$${cardUiModel.balance}",
        modifier = modifier.padding(horizontal = mainHorizontalPadding),
        fontSize = 35.sp,
        color = PurpleBF,
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = modifier.size(16.dp))

    FlipCard(
        cardFace = cardFace.value,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp, horizontal = 20.dp),
        onClick = {
            cardFace.value = cardFace.value.next
        },
        axis = RotationAxis.AxisY,
        back = {
            MainCardBackItem()
        },
        front = {
            MainCardFrontItem(cardUiModel = cardUiModel)
        }
    )
}



