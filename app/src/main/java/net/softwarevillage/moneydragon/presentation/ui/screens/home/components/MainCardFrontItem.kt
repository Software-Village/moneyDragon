package net.softwarevillage.moneydragon.presentation.ui.screens.home.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.common.utils.cardNumberHider
import net.softwarevillage.moneydragon.common.utils.toColor
import net.softwarevillage.moneydragon.domain.model.CardUiModel
import net.softwarevillage.moneydragon.presentation.ui.theme.White
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun MainCardFrontItem(
    cardUiModel: CardUiModel,
) {

    val modifier = Modifier

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(190.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardUiModel.cardColor.toColor()
        )
    ) {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            Image(
                painterResource(id = R.drawable.card_mask),
                contentDescription = null,
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 15.dp,
                        vertical = 15.dp
                    ),
                verticalArrangement = Arrangement.Center
            ) {

                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {

                    Text(
                        text = cardUiModel.holdersName,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = Color.White
                    )


                }

                Spacer(modifier = modifier.size(5.dp))



                Text(
                    text = cardNumberHider(cardUiModel.cardNumber),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )


                Spacer(modifier = modifier.size(50.dp))

                Text(
                    text = stringResource(id = R.string.yourBalance),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = White
                )

                Spacer(modifier = modifier.size(5.dp))


                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {


                    Text(
                        text = "$${cardUiModel.balance}",
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = Color.White
                    )

                    Text(
                        text = cardUiModel.cardScheme,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = Color.White
                    )

                }

            }
        }

    }

}

