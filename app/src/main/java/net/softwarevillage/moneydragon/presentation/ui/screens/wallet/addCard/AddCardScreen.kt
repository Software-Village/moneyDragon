package net.softwarevillage.moneydragon.presentation.ui.screens.wallet.addCard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.presentation.ui.components.MainButton
import net.softwarevillage.moneydragon.presentation.ui.components.MainTextInput
import net.softwarevillage.moneydragon.presentation.ui.screens.wallet.addCard.components.CardNumberTextField
import net.softwarevillage.moneydragon.presentation.ui.screens.wallet.addCard.components.CvvTextInput
import net.softwarevillage.moneydragon.presentation.ui.screens.wallet.addCard.components.MonthPicker
import net.softwarevillage.moneydragon.presentation.ui.theme.Gray87
import net.softwarevillage.moneydragon.presentation.ui.theme.PurpleBF
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCardScreen(
    onNavigate: (String) -> Unit,
    onBackNavigate: () -> Unit,
) {

    val modifier = Modifier

    val pickerVisiblity = remember {
        mutableStateOf(false)
    }

    val date = remember {
        mutableStateOf("")
    }

    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
    val year = Calendar.getInstance().get(Calendar.YEAR)


    val cardNumber = remember {
        mutableStateOf("")
    }

    val holdersName = remember { mutableStateOf("") }

    val cvv = remember { mutableStateOf("") }

    val expiryDate = remember { mutableStateOf("") }


    Surface(
        modifier = modifier.fillMaxSize()
    ) {

        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 30.dp),
            ) {

                FilledTonalButton(
                    modifier = modifier.padding(start = 10.dp),
                    onClick = {
                        onBackNavigate()
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = PurpleBF
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        tint = Color.White,
                        contentDescription = null
                    )

                }

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

            Spacer(modifier = modifier.size(15.dp))

            Text(
                modifier = modifier.padding(horizontal = 35.dp),
                text = stringResource(id = R.string.add_Card_Info),
                fontSize = 15.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                color = Gray87
            )

            Spacer(modifier = modifier.size(20.dp))

            CardNumberTextField(
                cardNumber = cardNumber,
                action = ImeAction.Next,
                label = stringResource(id = R.string.card_number),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            )


            Spacer(modifier = modifier.size(12.dp))

            MainTextInput(
                text = holdersName,
                label = stringResource(id = R.string.holders_name),
                action = ImeAction.Next,
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            )

            Spacer(modifier = modifier.size(12.dp))


            MonthPicker(
                visible = pickerVisiblity.value,
                currentMonth = currentMonth,
                currentYear = year,
                confirmButtonClicked = { selectedMonth, selectedYear ->
                    val newYear = selectedYear.toString().substring(2, 4)
                    val newMonth =
                        if (selectedMonth < 10) "0$selectedMonth" else selectedMonth.toString()
                    date.value = "$newMonth/$newYear"
                    pickerVisiblity.value = false
                },
                cancelClicked = {
                    pickerVisiblity.value = false
                }
            )

            MainTextInput(
                text = date,
                enabled = false,
                label = stringResource(id = R.string.expiry_date),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .clickable {
                        pickerVisiblity.value = true
                    }
            )

            Spacer(modifier = modifier.size(12.dp))


            CvvTextInput(
                text = cvv,
                label = stringResource(id = R.string.cvv),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                action = ImeAction.Done
            )

            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                MainButton(
                    onClick = {


                    },
                    title = R.string.confirm
                )
            }


        }
    }
}

