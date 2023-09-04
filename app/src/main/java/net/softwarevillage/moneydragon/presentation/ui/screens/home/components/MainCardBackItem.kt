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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.presentation.ui.theme.BlueCard
import net.softwarevillage.moneydragon.presentation.ui.theme.White
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun MainCardBackItem() {

    val modifier = Modifier

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(190.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = BlueCard
        ),

        ) {
        Box(
            modifier = modifier.fillMaxSize(),

            ) {
            Image(
                painterResource(id = R.drawable.card_mask),
                contentDescription = null,
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = modifier
                    .padding(
                        horizontal = 15.dp,
                        vertical = 15.dp
                    )
                    .fillMaxSize()
                    .align(Alignment.BottomStart),

                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = modifier.size(20.dp))
                Text(
                    text = "0000 0000 0000 0000",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.White
                )



                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        Text(
                            text = stringResource(id = R.string.cvv),
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            color = Color.White
                        )

                        Text(
                            text = "174",
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 22.sp,
                            color = Color.White
                        )

                    }



                    Column {
                        Text(
                            modifier = modifier,
                            textAlign = TextAlign.Center,
                            text = stringResource(id = R.string.date),
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            color = Color.White

                        )

                        Text(
                            text = "00/00",
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 22.sp,
                            color = Color.White
                        )

                    }

                }


            }
        }

    }

}