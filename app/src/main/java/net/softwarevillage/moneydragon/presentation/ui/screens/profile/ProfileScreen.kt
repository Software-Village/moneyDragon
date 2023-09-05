package net.softwarevillage.moneydragon.presentation.ui.screens.profile

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.presentation.ui.theme.Black
import net.softwarevillage.moneydragon.presentation.ui.theme.BlackText
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.GreyLight
import net.softwarevillage.moneydragon.presentation.ui.theme.GreyText
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.profile),
                fontFamily = fontFamily,
                fontWeight = FontWeight.W500,
                color = Black,
                fontSize = 20.sp,
            )
            Spacer(modifier = Modifier.height(22.dp))
            Text(
                text = stringResource(id = (R.string.profile_title)),
                color = GreyText,
                fontSize = 18.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.W500,
            )
            Spacer(modifier = Modifier.height(32.dp))
            Box {
                Card(modifier = Modifier.size(134.dp), shape = RoundedCornerShape(100)) {
                    AsyncImage(
                        model = "https://metacode.biz/@test/avatar.jpg",
                        contentDescription = "profilePhoto"
                    )
                }
                FilledIconButton(
                    onClick = {

                    }, colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = Blue,
                        contentColor = Color.White
                    ), modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_icon),
                        contentDescription = ""
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp)
        ) {
            Text(
                text = stringResource(id = R.string.personal_information),
                fontSize = 17.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.W700,
                color = Blue
            )
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = GreyLight
                ), shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.account_number),
                        fontSize = 13.sp,
                        color = Blue,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.W500, modifier = Modifier
                            .padding(vertical = 12.dp)
                            .padding(start = 16.dp)
                    )
                    Text(
                        text = "1312312312321",
                        fontSize = 13.sp,
                        color = BlackText,
                        fontFamily = fontFamily,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = GreyLight
                ), shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.name),
                        fontSize = 13.sp,
                        color = Blue,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.W500, modifier = Modifier
                            .padding(vertical = 12.dp)
                            .padding(start = 16.dp)
                    )
                    Text(
                        text = "afsaafsfafasfas",
                        fontSize = 13.sp,
                        color = BlackText,
                        fontFamily = fontFamily,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.info),
                fontSize = 17.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.W700,
                color = Blue
            )
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = GreyLight
                ), shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.privacy),
                        fontSize = 13.sp,
                        color = Blue,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.W500, modifier = Modifier
                            .padding(vertical = 12.dp)
                            .padding(start = 16.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_right),
                        contentDescription = "arrow",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(end = 16.dp)
                    )
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = GreyLight
                ), shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.term_use),
                        fontSize = 13.sp,
                        color = Blue,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.W500, modifier = Modifier
                            .padding(vertical = 12.dp)
                            .padding(start = 16.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_right),
                        contentDescription = "arrow",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(end = 16.dp)
                    )
                }
            }
        }
    }
}