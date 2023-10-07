package net.softwarevillage.moneydragon.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@Composable
fun BaseCircularImage(
    size: Dp,
    shape: Dp,
    image: Int,
    modifier: Modifier = Modifier,
    cardColors: CardColors,
    border: BorderStroke? = null,
    contentDescription: String? = null,
) {

    Card(
        modifier = modifier
            .size(size),
        shape = RoundedCornerShape(shape),
        border = border,
        colors = cardColors
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = contentDescription
            )
        }
    }

}