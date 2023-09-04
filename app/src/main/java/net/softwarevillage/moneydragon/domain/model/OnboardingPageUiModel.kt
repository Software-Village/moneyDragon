package net.softwarevillage.moneydragon.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class OnboardingPageUiModel(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val description: Int,
    val id: Int
)