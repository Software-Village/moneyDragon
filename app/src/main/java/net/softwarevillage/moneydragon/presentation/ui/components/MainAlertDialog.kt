package net.softwarevillage.moneydragon.presentation.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun MainAlertDialog(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    onDismissListener: () -> Unit,
    onConfirmListener: () -> Unit,
) {

    AlertDialog(
        onDismissRequest = {
            onDismissListener()
        },
        confirmButton = {

            TextButton(onClick = {
                onConfirmListener()
            }) {
                Text(
                    text = stringResource(id = R.string.yes),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismissListener()
            }) {
                Text(
                    text = stringResource(id = R.string.no),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal
                )
            }
        },
        modifier = modifier,
        title = {
            Text(text = title, fontFamily = fontFamily, fontWeight = FontWeight.SemiBold)
        },
        text = {
            Text(text = text, fontFamily = fontFamily, fontWeight = FontWeight.Medium)
        },
        shape = RoundedCornerShape(10.dp)
    )

}