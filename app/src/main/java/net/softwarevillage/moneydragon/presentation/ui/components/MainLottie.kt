package net.softwarevillage.moneydragon.presentation.ui.components

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun MainLottie(showState: Boolean, @RawRes res: Int, modifier: Modifier = Modifier) {

    val composition = rememberLottieComposition(LottieCompositionSpec.RawRes(res))

    if (showState) {
        Box(contentAlignment = Alignment.Center) {
            LottieAnimation(
                composition = composition.value,
                modifier = modifier,
                iterations = LottieConstants.IterateForever
            )
        }
    }
}