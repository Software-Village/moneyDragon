package net.softwarevillage.moneydragon.presentation.ui.screens.wallet.addCard.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.presentation.ui.theme.PurpleBF
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MonthPicker(
    visible: Boolean,
    currentMonth: Int,
    currentYear: Int,
    confirmButtonClicked: (Int, Int) -> Unit,
    cancelClicked: () -> Unit,
) {

    val months = listOf(
        "JAN",
        "FEB",
        "MAR",
        "APR",
        "MAY",
        "JUN",
        "JUL",
        "AUG",
        "SEP",
        "OCT",
        "NOV",
        "DEC"
    )

    var month by remember {
        mutableStateOf(months[currentMonth])
    }

    var year by remember {
        mutableStateOf(currentYear)
    }

    val interactionSource = remember {
        MutableInteractionSource()
    }

    if (visible) {

        AlertDialog(
            confirmButton = {
                Text(text = stringResource(id = R.string.OK),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    color = PurpleBF,
                    fontSize = 16.sp,
                    modifier = Modifier.clickable {
                        confirmButtonClicked(
                            months.indexOf(month) + 1,
                            year
                        )
                    }
                )
            },
            containerColor = Color.White,
            shape = RoundedCornerShape(10),
            title = {
                Text(
                    text = stringResource(id = R.string.selectExpiryDate),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    color = PurpleBF,
                    fontSize = 16.sp
                )
            },

            text = {

                Column {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            modifier = Modifier
                                .size(35.dp)
                                .rotate(90f)
                                .clickable(
                                    onClick = {
                                        year--
                                    }
                                ),
                            imageVector = Icons.Rounded.KeyboardArrowDown,
                            contentDescription = null
                        )

                        Text(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            text = year.toString(),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Icon(
                            modifier = Modifier
                                .size(35.dp)
                                .rotate(-90f)
                                .clickable(
                                    onClick = {
                                        year++
                                    }
                                ),
                            imageVector = Icons.Rounded.KeyboardArrowDown,
                            contentDescription = null
                        )

                    }


                    Card(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth(),

                        ) {

                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            months.forEach {
                                Box(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clickable(
                                            indication = null,
                                            interactionSource = interactionSource,
                                            onClick = {
                                                month = it
                                            }
                                        )
                                        .background(
                                            color = Color.Transparent
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {


                                    Box(
                                        modifier = Modifier
                                            .size(50.dp)
                                            .background(
                                                color = if (month == it) PurpleBF else Color.Transparent,
                                                shape = CircleShape
                                            )
                                    )

                                    Text(
                                        text = it,
                                        color = if (month == it) Color.White else Color.Black,
                                        fontWeight = FontWeight.Medium
                                    )

                                }
                            }

                        }

                    }

                }

            },
            onDismissRequest = {

            },
            dismissButton = {
                Text(text = stringResource(id = R.string.cancel),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = PurpleBF,
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .clickable {
                            cancelClicked()
                        }
                )
            }
        )

    }

}