package net.softwarevillage.moneydragon.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.Grey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTextInput(
    text: MutableState<String>,
    label: String,
    action: ImeAction = ImeAction.Default,
    type: KeyboardType = KeyboardType.Text
) {
    TextField(value = text.value, onValueChange = { text.value = it }, label = {
        Text(text = label)
    }, colors = TextFieldDefaults.textFieldColors(
        focusedLabelColor = Blue,
        unfocusedLabelColor = Grey,
        focusedIndicatorColor = Blue,
        unfocusedIndicatorColor = Grey,
        cursorColor = Blue,
        containerColor = Color.Transparent
    ), modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(
        imeAction = action, keyboardType = type
    )
    )
}