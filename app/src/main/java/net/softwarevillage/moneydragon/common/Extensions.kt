package net.softwarevillage.moneydragon.common

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

fun Color.toHexCode() = String.format("#%06X", (this.toArgb() and 0xFFFFFF))

fun String.toColor() = Color(android.graphics.Color.parseColor(this))
