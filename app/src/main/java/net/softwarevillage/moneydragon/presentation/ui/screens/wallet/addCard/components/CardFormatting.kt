package net.softwarevillage.moneydragon.presentation.ui.screens.wallet.addCard.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText

fun formatAmex(text: AnnotatedString): TransformedText {

    val trimmed = if (text.text.length >= 15) text.text.substring(0..14) else text.text
    var out = ""

    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i == 3 || i == 9 && i != 14) out += "-"
    }

    val creditCardOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 9) return offset + 1
            if (offset <= 15) return offset + 2
            return 17
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return offset
            if (offset <= 11) return offset - 1
            if (offset <= 17) return offset - 2
            return 15
        }
    }
    return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}

fun formatDinnersClub(text: AnnotatedString): TransformedText {
    val trimmed = if (text.text.length >= 14) text.text.substring(0..13) else text.text
    var out = ""

    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i == 3 || i == 9 && i != 13) out += "-"
    }

    val creditCardOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 9) return offset + 1
            if (offset <= 14) return offset + 2
            return 16
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return offset
            if (offset <= 11) return offset - 1
            if (offset <= 16) return offset - 2
            return 14
        }
    }
    return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}

fun formatOtherCardNumbers(text: AnnotatedString): TransformedText {

    val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
    var out = ""

    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 4 == 3 && i != 15) out += "-"
    }
    val creditCardOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset + 1
            if (offset <= 11) return offset + 2
            if (offset <= 16) return offset + 3
            return 19
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return offset
            if (offset <= 9) return offset - 1
            if (offset <= 14) return offset - 2
            if (offset <= 19) return offset - 3
            return 16
        }
    }

    return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}
