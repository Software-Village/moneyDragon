package net.softwarevillage.moneydragon.presentation.ui.screens.feed.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel
import net.softwarevillage.moneydragon.presentation.ui.theme.Grey87
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily


@Composable
fun BaseFeedItem(
    modifier: Modifier = Modifier,
    image: Int,
    transactionUiModel: TransactionUiModel,
) {

    Surface {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 20.dp)
                .clickable {

                },
        ) {
            Card(
                modifier = modifier.size(height = 190.dp, width = 140.dp),
                shape = RoundedCornerShape(size = 24.dp)
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "",
                    modifier = modifier.fillMaxSize()
                )
            }

            Spacer(modifier = modifier.size(16.dp))

            Column(modifier.fillMaxWidth()) {
                Text(
                    text = transactionUiModel.title,
                    fontSize = 26.sp,
                    color = Color.Black,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W600
                )
                Spacer(modifier = modifier.size(16.dp))
                Row(modifier = modifier.fillMaxWidth()) {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar_icon),
                        contentDescription = ""
                    )
                    Spacer(modifier = modifier.size(8.dp))
                    Text(
                        text = transactionUiModel.date,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.W500,
                        fontSize = 11.sp,
                        color = Grey87
                    )
                }
            }
        }
    }

}