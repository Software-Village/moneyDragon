package net.softwarevillage.moneydragon.presentation.ui.screens.feed

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel
import net.softwarevillage.moneydragon.presentation.ui.components.MainLottie
import net.softwarevillage.moneydragon.presentation.ui.components.NavigationButton
import net.softwarevillage.moneydragon.presentation.ui.screens.feed.components.BaseFeedItem
import net.softwarevillage.moneydragon.presentation.ui.theme.Black1E
import net.softwarevillage.moneydragon.presentation.ui.theme.Grey87
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit,
    onBack: () -> Unit,
    viewModel: FeedViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state = viewModel.state.collectAsStateWithLifecycle()
    val effect = viewModel.effect.collectAsStateWithLifecycle(initialValue = null)

    val transactionList = remember {
        mutableStateOf(emptyList<TransactionUiModel>())
    }

    LaunchedEffect(key1 = state) {
        if (!state.value.isLoading) {
            when (effect.value) {
                is FeedUiEffect.ShowMessage -> {
                    val ev = effect.value as FeedUiEffect.ShowMessage
                    Toast.makeText(context, ev.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    Surface(modifier = modifier.fillMaxSize()) {
        Column(modifier = modifier.fillMaxSize()) {
            Spacer(modifier = modifier.size(32.dp))
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                NavigationButton(navigate = { onBack() })
                Text(
                    text = stringResource(id = R.string.feed),
                    modifier = modifier.align(Alignment.Center),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W600,
                    color = Black1E,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = modifier.size(24.dp))
            Text(
                modifier = modifier.padding(horizontal = 32.dp),
                text = stringResource(id = R.string.latest_update),
                fontFamily = fontFamily,
                fontWeight = FontWeight.W500,
                color = Grey87,
                fontSize = 20.sp
            )
            if (!state.value.isLoading) {
                if (state.value.isTransactionHave) {
                    viewModel.setEvent(FeedUiEvent.GetTransactions)
                    transactionList.value = state.value.transactions
                    LazyColumn {
                        items(transactionList.value) {
                            BaseFeedItem(image = R.drawable.logo, transactionUiModel = it)
                        }
                    }
                } else {
                    MainLottie(
                        showState = true, res = R.raw.lottie_empty_state_anim,
                        modifier = modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}