package net.softwarevillage.moneydragon.presentation.ui.screens.wallet.cardColor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.common.utils.toColor
import net.softwarevillage.moneydragon.common.utils.toHexCode
import net.softwarevillage.moneydragon.domain.model.CardFaceUiModel
import net.softwarevillage.moneydragon.domain.model.CardUiModel
import net.softwarevillage.moneydragon.presentation.ui.components.MainButton
import net.softwarevillage.moneydragon.presentation.ui.components.NavigationButton
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.MainCardFrontItem
import net.softwarevillage.moneydragon.presentation.ui.theme.BlackCard
import net.softwarevillage.moneydragon.presentation.ui.theme.BlueCard
import net.softwarevillage.moneydragon.presentation.ui.theme.DarkBlueCard
import net.softwarevillage.moneydragon.presentation.ui.theme.GreenCard
import net.softwarevillage.moneydragon.presentation.ui.theme.Grey87
import net.softwarevillage.moneydragon.presentation.ui.theme.GreyCard
import net.softwarevillage.moneydragon.presentation.ui.theme.RedCard
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardColorScreen(
    onBackPressed: () -> Unit,
    cardFaceUiModel: CardFaceUiModel,
) {

    val cardColorList = listOf(
        BlueCard.toHexCode(),
        RedCard.toHexCode(),
        GreenCard.toHexCode(),
        GreyCard.toHexCode(),
        DarkBlueCard.toHexCode(),
        BlackCard.toHexCode(),

        )

    val scrollState = rememberScrollState()


    val cardDataState = remember {

        mutableStateOf(
            CardUiModel(
                holdersName = cardFaceUiModel.holdersName,
                cardScheme = cardFaceUiModel.cardScheme,
                cardNumber = cardFaceUiModel.cardNumber,
                cvv = cardFaceUiModel.cvv,
                expiryDate = cardFaceUiModel.expiryDate,
                cardColor = BlueCard.toHexCode(),
                balance = cardFaceUiModel.balance,
                id = 1
            )
        )

    }


    val modifier = Modifier

    Surface(modifier = modifier
        .fillMaxSize()
        .verticalScroll(scrollState)) {
        Column(modifier = modifier.fillMaxSize()) {

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 30.dp),
            ) {

                NavigationButton(
                    navigate = { onBackPressed() },
                    modifier = modifier.padding(start = 10.dp)
                )

                Text(
                    modifier = modifier.align(Alignment.Center),
                    text = stringResource(id = R.string.add_card),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

            }

            Text(
                modifier = modifier.padding(horizontal = 30.dp),
                text = stringResource(id = R.string.card_color_title),
                fontSize = 15.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.W400,
                color = Grey87
            )

            Spacer(modifier = modifier.size(35.dp))

            Box(modifier = modifier.padding(horizontal = 30.dp)) {
                MainCardFrontItem(cardDataState.value)
            }

            Spacer(modifier = modifier.size(25.dp))

            FlowRow(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                maxItemsInEachRow = 3,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                cardColorList.forEach {
                    Box(modifier = modifier.padding(vertical = 10.dp)) {
                        Button(
                            onClick = {
                                cardDataState.value = changeCardColor(cardDataState.value, it)
                            },

                            shape = RoundedCornerShape(28.dp),
                            modifier = modifier.size(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = it.toColor()
                            )
                        ) {}
                    }
                }
            }

            Box(
                modifier = modifier
                    .padding(vertical = 30.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                MainButton(title = R.string.confirm) {

                }
            }


        }
    }

}

private fun changeCardColor(data: CardUiModel, newColor: String): CardUiModel {
    with(data) {
        return CardUiModel(
            holdersName, cardScheme, cardNumber, expiryDate, cvv, newColor, balance, id
        )
    }
}


