package net.softwarevillage.moneydragon.presentation.ui.screens.transaction

import android.icu.text.SimpleDateFormat
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.data.dto.local.TransactionDTO
import net.softwarevillage.moneydragon.presentation.navigation.Screen
import net.softwarevillage.moneydragon.presentation.ui.components.MainButton
import net.softwarevillage.moneydragon.presentation.ui.components.MainTextInput
import net.softwarevillage.moneydragon.presentation.ui.components.NavigationButton
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.Grey87
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(
    onNavigate: (String) -> Unit,
    onBack: () -> Unit,
    viewModel: TransactionViewModel = hiltViewModel(),
) {

    val chipGroup =
        listOf(stringResource(id = R.string.outgoing), stringResource(id = R.string.income))

    val selectedChip = remember {
        mutableStateOf(0)
    }

    val transactionAmount = remember {
        mutableStateOf("")
    }

    val transactionTitle = remember {
        mutableStateOf("")
    }

    val state = viewModel.state.collectAsStateWithLifecycle()
    val effect = viewModel.effect.collectAsStateWithLifecycle(initialValue = null)


    val context = LocalContext.current

    val modifier = Modifier

    LaunchedEffect(key1 = state) {
        if (!state.value.isLoading) {
            when (effect.value) {
                is TransactionEffect.ShowMessage -> {
                    val effectValue = effect.value as TransactionEffect.ShowMessage
                    Toast.makeText(context, effectValue.message, Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }
    }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        state.value.cardData?.let { cardUiModel ->
            Column(modifier = modifier.fillMaxSize()) {
                Spacer(modifier = modifier.size(32.dp))
                Box(modifier = modifier.fillMaxWidth()) {
                    NavigationButton(
                        navigate = { onBack() }, modifier = modifier.padding(start = 32.dp)
                    )

                    Text(
                        text = stringResource(id = R.string.transaction),
                        textAlign = TextAlign.Center,
                        modifier = modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 16.dp),
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.W600,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                }

                Spacer(modifier = modifier.size(30.dp))

                Text(
                    modifier = modifier.padding(horizontal = 32.dp),
                    text = stringResource(id = R.string.available_balance),
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W500,
                    color = Grey87
                )

                Spacer(modifier = modifier.size(4.dp))

                Text(
                    modifier = modifier.padding(horizontal = 32.dp),
                    text = "$${cardUiModel.balance}",
                    fontSize = 32.sp,
                    lineHeight = 32.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W700,
                    color = Blue
                )

                Spacer(modifier = modifier.size(16.dp))

                Text(
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 32.dp),
                    text = stringResource(id = R.string.transaction_title),
                    fontFamily = fontFamily,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Grey87
                )

                Spacer(modifier = modifier.size(8.dp))

                Text(
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 32.dp),
                    text = stringResource(id = R.string.transaction_attention),
                    fontFamily = fontFamily,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color.Red
                )

                Spacer(modifier = modifier.size(24.dp))

                MainTextInput(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    text = transactionTitle,
                    label = R.string.title,
                    singleLine = true,
                    action = ImeAction.Next,
                    type = KeyboardType.Text
                )

                Spacer(modifier = modifier.size(24.dp))

                MainTextInput(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    text = transactionAmount,
                    label = R.string.transaction_amount,
                    singleLine = true,
                    action = ImeAction.Done,
                    type = KeyboardType.Decimal
                )

                Spacer(modifier = modifier.size(24.dp))

                LazyRow(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    itemsIndexed(chipGroup) { index, item ->
                        InputChip(
                            modifier = modifier.padding(horizontal = 16.dp),
                            selected = index == selectedChip.value,
                            onClick = {
                                selectedChip.value = index
                            },
                            label = {
                                Text(
                                    modifier = modifier.padding(
                                        horizontal = 16.dp,
                                        vertical = 8.dp
                                    ),
                                    text = item,
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight.W400,
                                    fontSize = 16.sp
                                )
                            },
                            border = InputChipDefaults.inputChipBorder(
                                borderColor = Blue
                            ),
                            colors = InputChipDefaults.inputChipColors(
                                selectedContainerColor = Blue,
                                disabledContainerColor = Color.White,
                                disabledLabelColor = Blue,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }

                Box(modifier = modifier.fillMaxSize()) {
                    MainButton(
                        modifier = modifier
                            .align(Alignment.BottomCenter)
                            .padding(vertical = 32.dp),
                        title = R.string.add_transaction
                    ) {

                        if (transactionTitle.value.isEmpty() || transactionAmount.value.isEmpty()) {
                            Toast.makeText(context, R.string.empty_field, Toast.LENGTH_SHORT).show()
                        } else {
                            if (selectedChip.value == 0 && transactionAmount.value.toDouble() > cardUiModel.balance) {
                                Toast.makeText(
                                    context,
                                    R.string.dont_have_balance,
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {

                                val pattern = "dd/MM/yyyy"
                                val dtLong = Date(System.currentTimeMillis())
                                val sdfDate =
                                    SimpleDateFormat(pattern, Locale.getDefault()).format(dtLong)
                                val data = TransactionDTO(
                                    category = 1,
                                    title = transactionTitle.value,
                                    date = sdfDate,
                                    amount = transactionAmount.value,
                                    type = selectedChip.value
                                )

                                viewModel.setEvent(TransactionEvent.AddTransaction(data))
                                viewModel.setEvent(
                                    TransactionEvent.UpdateBalance(
                                        if (selectedChip.value == 0) cardUiModel.balance - transactionAmount.value.toDouble() else cardUiModel.balance + transactionAmount.value.toDouble()
                                    )
                                )
                                onNavigate(Screen.TransactionSuccessScreen.route)
                            }
                        }
                    }
                }
            }
        }
    }
}

