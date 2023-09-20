package net.softwarevillage.moneydragon.presentation.ui.screens.auth

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.domain.model.OnboardingPageUiModel
import net.softwarevillage.moneydragon.presentation.ui.components.MainButton
import net.softwarevillage.moneydragon.presentation.ui.screens.auth.components.OnboardPagerItem
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.LightBlue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    navigateLogin: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val onboardList = listOf(
        OnboardingPageUiModel(
            R.drawable.savings_pana,
            R.string.onboard1_title,
            R.string.onboard1_description,
            1
        ),
        OnboardingPageUiModel(
            R.drawable.wallet_amico,
            R.string.onboard2_title,
            R.string.onboard1_description,
            2
        ),
        OnboardingPageUiModel(
            R.drawable.group_card,
            R.string.onboard3_title, R.string.onboard1_description, 3
        )
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.fillMaxHeight(0.09f))
        Column(
            modifier = Modifier
                .fillMaxHeight(0.75f)
        ) {
            HorizontalPager(pageCount = onboardList.size, state = pagerState, key = {
                onboardList[it].id
            }, modifier = Modifier.padding(horizontal = 8.dp)) {
                OnboardPagerItem(onboardingPageUiModel = onboardList[it])
            }
            Spacer(modifier = Modifier.height(36.dp))
            Row(
                Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(onboardList.size) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Blue else LightBlue
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(8.dp)
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.16f), contentAlignment = Alignment.TopCenter
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            MainButton(title = R.string.next_page) {
                scope.launch {
                    if (pagerState.currentPage == 2) {
                        navigateLogin.invoke()
                        viewModel.setEvent(AuthEvent.OnboardComplete)
                    } else {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }

                }
            }
        }
    }

}