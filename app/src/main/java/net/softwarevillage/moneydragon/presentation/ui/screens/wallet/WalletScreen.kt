package net.softwarevillage.moneydragon.presentation.ui.screens.wallet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.presentation.navigation.Screen
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.CardFace
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.FlipCard
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.MainCardBackItem
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.MainCardFrontItem
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.RotationAxis
import net.softwarevillage.moneydragon.presentation.ui.theme.Gray87
import net.softwarevillage.moneydragon.presentation.ui.theme.PurpleBF
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun WalletScreen(
    onNavigate: (String) -> Unit,
) {
    val modifier = Modifier

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

            WalletState(false, onNavigate)

        }
    }
}

@Composable
fun WalletState(
    state: Boolean,
    onNavigate: (String) -> Unit,
) {

    if (state) {
        CardAvailableComposable()
    } else {
        AddCardComposable(onNavigate = onNavigate)
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


@Composable
fun CardAvailableComposable(
    modifier: Modifier = Modifier,
) {
    val composition = rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.lottie_empty_state_anim)
    )

    val cardState = remember {
        mutableStateOf(CardFace.Front)
    }

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
                MainCardBackItem()
            },
            front = {
                MainCardFrontItem()
            }
        )

        Spacer(modifier.size(15.dp))

        Row(modifier = modifier.fillMaxWidth()) {
            Text(
                modifier = modifier.padding(horizontal = 35.dp),
                text = stringResource(id = R.string.transactions),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                color = Gray87,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = modifier.size(15.dp))

        TransactionState(state = false)

    }

}

@Composable
fun TransactionState(
    state: Boolean,
) {

    if (state) {

    } else {
        EmptyTransactions()
    }
}

@Composable
fun EmptyTransactions() {

    val modifier = Modifier

    val composition = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.lottie_empty_state_anim)
    )

    Column {
        LottieAnimation(
            modifier = modifier
                .fillMaxSize()
                .padding(30.dp)
                .clip(RectangleShape),
            composition = composition.value,
            iterations = LottieConstants.IterateForever,
        )
    }
}