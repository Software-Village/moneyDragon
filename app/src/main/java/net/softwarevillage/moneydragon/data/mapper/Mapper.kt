package net.softwarevillage.moneydragon.data.mapper

import net.softwarevillage.moneydragon.data.dto.local.AuthDTO
import net.softwarevillage.moneydragon.data.dto.local.CardDTO
import net.softwarevillage.moneydragon.data.dto.local.TransactionDTO
import net.softwarevillage.moneydragon.domain.model.AuthUiModel
import net.softwarevillage.moneydragon.domain.model.CardUiModel
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel

fun AuthDTO.toAuthUiModel() = AuthUiModel(
    id, name, surname, image, pin
)

fun CardDTO.toCardUiModel() = CardUiModel(
    holdersName, cardScheme, cardNumber, expiryDate, cvv, cardColor, balance, id
)

fun TransactionDTO.toTransactionUiModel() = TransactionUiModel(
    id, category, title, date, amount, type
)

fun List<TransactionDTO>.toTransactionUiModel() = map {
    with(it) {
        TransactionUiModel(
            id, category, title, date, amount, type
        )
    }
}

fun CardUiModel.toCardDTO() = CardDTO(
    id, holdersName, cardScheme, cardNumber, expiryDate, cvv, cardColor, balance
)
