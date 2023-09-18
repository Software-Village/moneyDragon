package net.softwarevillage.moneydragon.presentation.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.Grey87


@Composable
fun MainPasswordTextInput(
    text: MutableState<String>,
    @StringRes label: Int,
    action: ImeAction = ImeAction.Done
) {

    val passwordVisibility = remember { mutableStateOf(false) }
    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        label = {
            Text(text = stringResource(id = label))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = action
        ),
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
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            if (!passwordVisibility.value) {
                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_visibility_24),
                        contentDescription = "icon",
                        tint = Color.Gray
                    )
                }
            } else {
                IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_visibility_off_24),
                        contentDescription = "icon",
                        tint = Color.Gray
                    )
                }
            }
        },
        visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}