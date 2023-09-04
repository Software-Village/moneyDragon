package net.softwarevillage.moneydragon.presentation.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.BaseLazyHomeIncomingItem
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.BaseLazyHomeOutgoingItem
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.CardFace
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.FlipCard
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.MainCardBackItem
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.MainCardFrontItem
import net.softwarevillage.moneydragon.presentation.ui.screens.home.components.RotationAxis
import net.softwarevillage.moneydragon.presentation.ui.theme.Gray87
import net.softwarevillage.moneydragon.presentation.ui.theme.PurpleBF
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun HomeScreen(

) {

    val modifier = Modifier

    val scrollState = rememberScrollState()

    val cardFace = remember {
        mutableStateOf(CardFace.Front)
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
                    .padding(horizontal = 30.dp)
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
            Text(
                modifier = modifier.padding(horizontal = 35.dp),
                text = stringResource(id = R.string.current_balance),
                fontSize = 18.sp,
                fontFamily = fontFamily,
                color = Gray87,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = modifier.size(10.dp))
            Text(
                text = "$23,231,233",
                modifier = modifier.padding(horizontal = 35.dp),
                fontSize = 35.sp,
                color = PurpleBF,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = modifier.size(16.dp))


            FlipCard(
                cardFace = cardFace.value,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 15.dp),
                onClick = {
                    cardFace.value = cardFace.value.next
                },
                axis = RotationAxis.AxisY,
                back = {
                    MainCardBackItem()
                },
                front = {
                    MainCardFrontItem()
                }
            )



            Spacer(modifier = modifier.size(24.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 35.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.incoming_transactions),
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Gray87
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

            LazyRow {
                items(count = 5) {
                    BaseLazyHomeIncomingItem()
                }
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 35.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.outgoing_transactions),
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Gray87
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

            LazyRow {
                items(count = 5) {
                    BaseLazyHomeOutgoingItem()
                }
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 35.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.other_transactions),
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Gray87
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

        }
    }

}

