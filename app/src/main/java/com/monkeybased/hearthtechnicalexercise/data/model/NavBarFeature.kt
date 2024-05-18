package com.monkeybased.hearthtechnicalexercise.data.model

import com.monkeybased.hearthtechnicalexercise.ui.screen.HearthstoneScreen
import com.monkeybased.hearthtechnicalexercise.R

sealed class NavBarFeature(val label: String, val iconResId: Int, val screen: HearthstoneScreen) {
    data object Class : NavBarFeature("Browse by Class", R.drawable.misc_battlenet, HearthstoneScreen.CLASS_LIST)
    data object Set : NavBarFeature("Browse by Set", R.drawable.misc_standardbook, HearthstoneScreen.SET_LIST)
    data object Search : NavBarFeature("Search", R.drawable.icon_search, HearthstoneScreen.SEARCH)
}