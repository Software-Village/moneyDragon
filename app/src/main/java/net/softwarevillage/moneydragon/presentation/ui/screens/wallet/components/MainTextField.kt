package net.softwarevillage.moneydragon.presentation.ui.screens.wallet.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeOptions
import androidx.compose.ui.unit.sp
import net.softwarevillage.moneydragon.presentation.ui.theme.PurpleBF
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun MainTextField(
    textStyle: TextStyle,
    isError: Boolean,
    singleLine: Boolean,
    colors: TextFieldColors,
    label: String? = null,
    placeHolder: String? = null,
    keyboardOptions: KeyboardOptions? = null,
    keyboardActions: KeyboardActions? = null,
    modifier: Modifier = Modifier,
) {

    val textState = remember { mutableStateOf("") }

    TextField(
        modifier = modifier,
        value = textState.value,
        onValueChange = {
            textState.value = it
        },
        label = {
            if (label != null) {
                Text(
                    text = label,
                    fontSize = 14.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        placeholder = {
            if (placeHolder != null) {
                Text(
                    text = placeHolder,
                    fontSize = 14.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        textStyle = textStyle,
        isError = isError,
        singleLine = singleLine,
        colors = colors,
        keyboardActions = keyboardActions ?: KeyboardActions.Default,
        keyboardOptions = keyboardOptions ?: KeyboardOptions.Default,
    )
}