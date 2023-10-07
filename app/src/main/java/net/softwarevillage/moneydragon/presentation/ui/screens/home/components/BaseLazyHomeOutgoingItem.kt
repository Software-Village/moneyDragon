package net.softwarevillage.moneydragon.presentation.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel
import net.softwarevillage.moneydragon.presentation.ui.theme.Grey87
import net.softwarevillage.moneydragon.presentation.ui.theme.PurpleBF
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseLazyHomeOutgoingItem(
    transactionUiModel: TransactionUiModel,
) {

    val modifier = Modifier

    Card(
        modifier = modifier
            .padding(vertical = 10.dp)
            .padding(end = 10.dp)
            .width(160.dp)
            .height(200.dp),
        onClick = {

            //OnClickAction
        },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 4.dp
        )
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    modifier = modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.graph_outgoing_1),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )

                Text(
                    modifier = modifier.padding(bottom = 9.dp, start = 12.dp),
                    text = transactionUiModel.date,
                    fontFamily = fontFamily,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal,
                    color = Grey87
                )

            }


            Column(modifier = modifier.fillMaxSize()) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        modifier = modifier.size(40.dp),
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null
                    )

                    Column(
                        modifier = modifier.padding(vertical = 12.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.End
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.arrow_outgoing),
                            contentDescription = null
                        )
                        Text(
                            text = "- $${transactionUiModel.amount}",
                            fontFamily = fontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = PurpleBF
                        )
                    }

                }



                Text(
                    modifier = modifier.padding(start = 12.dp),
                    lineHeight = 14.sp,
                    text = transactionUiModel.title,
                    fontFamily = fontFamily,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )

            }

        }


    }
}
