package com.monkeybased.hearthtechnicalexercise.data.model

import androidx.compose.ui.res.stringResource
import com.monkeybased.hearthtechnicalexercise.ui.screen.HearthstoneScreen
import com.monkeybased.hearthtechnicalexercise.R

sealed class NavBarFeature(val labelResId: Int, val iconResId: Int, val screen: HearthstoneScreen) {
    data object Class : NavBarFeature(R.string.browse_by_class, R.drawable.misc_battlenet, HearthstoneScreen.CLASS_LIST)
    data object Set : NavBarFeature(R.string.browse_by_set, R.drawable.misc_standardbook, HearthstoneScreen.SET_LIST)
    data object Search : NavBarFeature(R.string.global_search, R.drawable.icon_search, HearthstoneScreen.SEARCH)
}