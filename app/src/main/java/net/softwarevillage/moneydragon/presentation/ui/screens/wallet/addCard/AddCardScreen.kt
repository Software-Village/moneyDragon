package net.softwarevillage.moneydragon.presentation.ui.screens.wallet.addCard

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.common.objectToJson
import net.softwarevillage.moneydragon.common.validateCVV
import net.softwarevillage.moneydragon.common.validateCreditCard
import net.softwarevillage.moneydragon.common.validateFields
import net.softwarevillage.moneydragon.domain.model.CardFaceUiModel
import net.softwarevillage.moneydragon.presentation.navigation.Screen
import net.softwarevillage.moneydragon.presentation.ui.components.MainButton
import net.softwarevillage.moneydragon.presentation.ui.components.MainTextInput
import net.softwarevillage.moneydragon.presentation.ui.components.NavigationButton
import net.softwarevillage.moneydragon.presentation.ui.screens.wallet.addCard.components.CardNumberTextField
import net.softwarevillage.moneydragon.presentation.ui.screens.wallet.addCard.components.CvvTextInput
import net.softwarevillage.moneydragon.presentation.ui.screens.wallet.addCard.components.MonthPicker
import net.softwarevillage.moneydragon.presentation.ui.screens.wallet.addCard.components.identifyCardScheme
import net.softwarevillage.moneydragon.presentation.ui.theme.Grey87
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily
import java.util.Calendar


@Composable
fun AddCardScreen(
    onNavigate: (String) -> Unit,
    onBackNavigate: () -> Unit,
) {

    val modifier = Modifier

    val context = LocalContext.current

    val pickerVisibility = remember {
        mutableStateOf(false)
    }

    val balance = remember {
        mutableStateOf("")
    }

    val expiryDate = remember {
        mutableStateOf("")
    }

    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
    val year = Calendar.getInstance().get(Calendar.YEAR)


    val cardNumber = remember {
        mutableStateOf("")
    }

    val holdersName = remember { mutableStateOf("") }

    val cvv = remember { mutableStateOf("") }



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

                NavigationButton(
                    navigate = { onBackNavigate() },
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

            Spacer(modifier = modifier.size(15.dp))

            Text(
                modifier = modifier.padding(horizontal = 35.dp),
                text = stringResource(id = R.string.add_Card_Info),
                fontSize = 15.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                color = Grey87
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
                visible = pickerVisibility.value,
                currentMonth = currentMonth,
                currentYear = year,
                confirmButtonClicked = { selectedMonth, selectedYear ->
                    val newYear = selectedYear.toString().substring(2, 4)
                    val newMonth =
                        if (selectedMonth < 10) "0$selectedMonth" else selectedMonth.toString()
                    expiryDate.value = "$newMonth/$newYear"
                    pickerVisibility.value = false
                },
                cancelClicked = {
                    pickerVisibility.value = false
                }
            )

            MainTextInput(
                text = expiryDate,
                enabled = false,
                label = stringResource(id = R.string.expiry_date),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .clickable {
                        pickerVisibility.value = true
                    }
            )

            Spacer(modifier = modifier.size(12.dp))

            MainTextInput(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                text = balance,
                label = stringResource(id = R.string.balance),
                singleLine = true,
                action = ImeAction.Next,
                type = KeyboardType.Decimal
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
                        checkState(
                            onNavigate = onNavigate,
                            context = context,
                            cvv = cvv.value,
                            holdersName = holdersName.value,
                            cardNumber = cardNumber.value,
                            expiryDate = expiryDate.value,
                            balance = balance.value
                        )
                    },
                    title = R.string.confirm
                )
            }
        }
    }
}

private fun checkState(
    onNavigate: (String) -> Unit,
    context: Context,
    expiryDate: String,
    cvv: String,
    holdersName: String,
    cardNumber: String,
    balance: String,
) {

    if (!validateFields(listOf(expiryDate, cvv, holdersName, cardNumber, balance))) {
        Toast.makeText(context, R.string.empty_field, Toast.LENGTH_SHORT).show()
        return
    }

    if (!validateCreditCard(cardNumber)) {
        Toast.makeText(context, R.string.short_card_number, Toast.LENGTH_SHORT).show()
        return
    }

    if (!validateCVV(cvv)) {
        Toast.makeText(context, R.string.short_cvv, Toast.LENGTH_SHORT).show()
        return
    }


    val cardFaceUiModel = CardFaceUiModel(
        holdersName = holdersName,
        cardNumber = cardNumber,
        cardScheme = identifyCardScheme(cardNumber).toString(),
        expiryDate = expiryDate,
        cvv = cvv.toInt(),
        balance = balance.toDouble()
    )

    val data = objectToJson(cardFaceUiModel)

    onNavigate(Screen.CardColorScreen.route + "?$data")


}

