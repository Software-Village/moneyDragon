package net.softwarevillage.moneydragon.presentation.ui.screens.wallet.addCard.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.Grey

@Composable
fun CardNumberTextField(
    cardNumber: MutableState<String>,
    label: String,
    action: ImeAction = ImeAction.Default,
    modifier: Modifier = Modifier,
) {


    TextField(
        value = cardNumber.value,
        singleLine = true,
        onValueChange = { it ->
            if (it.length <= 16) {
                cardNumber.value = it
            }
        },
        modifier = modifier,
        label = {
            Text(text = label)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = action
        ),
        visualTransformation = VisualTransformation { number ->
            when (identifyCardScheme(cardNumber.value)) {
                CardScheme.AMEX -> formatAmex(number)
                CardScheme.DINERS_CLUB -> formatDinnersClub(number)
                else -> formatOtherCardNumbers(number)
            }
        },
        trailingIcon = {
            when (identifyCardScheme(cardNumber.value)) {
                CardScheme.MASTERCARD -> {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified,
                        painter = painterResource(id = R.drawable.master_card_icon),
                        contentDescription = null
                    )
                }

                CardScheme.VISA -> {
                    Icon(
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.visa_icon),
                        contentDescription = null
                    )
                }

                else -> {}
            }
        },
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Blue,
            unfocusedLabelColor = Grey,
            focusedIndicatorColor = Blue,
            unfocusedIndicatorColor = Grey,
            cursorColor = Blue,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
        )
    )
}