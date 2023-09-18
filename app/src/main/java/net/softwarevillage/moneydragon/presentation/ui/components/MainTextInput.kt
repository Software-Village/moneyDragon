package net.softwarevillage.moneydragon.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.Grey87


@Composable
fun MainTextInput(
    modifier: Modifier = Modifier,
    text: MutableState<String>,
    @StringRes label: Int,
    singleLine: Boolean = false,
    action: ImeAction = ImeAction.Default,
    type: KeyboardType = KeyboardType.Text,
    enabled: Boolean = true,
) {
    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        label = {
            Text(text = stringResource(id = label))
        },
        enabled = enabled,
        colors = TextFieldDefaults.colors(
            focusedLabelColor = Blue,
            unfocusedLabelColor = Grey87,
            focusedIndicatorColor = Blue,
            unfocusedIndicatorColor = Grey87,
            cursorColor = Blue,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledLabelColor = Grey87,
            disabledTextColor = Color.Black
        ),
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            imeAction = action, keyboardType = type
        ),
        singleLine = singleLine
    )
}