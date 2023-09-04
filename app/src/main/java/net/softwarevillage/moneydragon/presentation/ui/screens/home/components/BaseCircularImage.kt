package net.softwarevillage.moneydragon.presentation.ui.screens.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun BaseCircularImage(
    size: Dp,
    shape: Dp,
    image: Int,
    modifier: Modifier = Modifier,
    border: BorderStroke? = null,
    contentDescription: String? = null,
) {

    Card(
        modifier = modifier
            .size(size),
        shape = RoundedCornerShape(shape),
        border = border
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = contentDescription
        )
    }

}