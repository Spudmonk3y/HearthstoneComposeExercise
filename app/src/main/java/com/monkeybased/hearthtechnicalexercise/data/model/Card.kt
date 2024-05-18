package com.monkeybased.hearthtechnicalexercise.data.model

import androidx.compose.ui.graphics.Color
import com.monkeybased.hearthtechnicalexercise.ui.theme.CommonRarity
import com.monkeybased.hearthtechnicalexercise.ui.theme.EpicRarity
import com.monkeybased.hearthtechnicalexercise.ui.theme.LegendaryRarity
import com.monkeybased.hearthtechnicalexercise.ui.theme.RareRarity

data class Card(
    val cardId: String,
    val name: String,
    val cardSet: String,
    val type: String,
    val faction: String?,
    val rarity: String?,
    val cost: Int?,
    val attack: Int?,
    val health: Int?,
    val text: String?,
    val flavor: String?,
    val artist: String?,
    val collectible: Boolean?,
    val elite: Boolean?,
    val img: String?,
    val imgGold: String?,
    val locale: String?,
    val dbfId: Int?,
    val playerClass: String?
) {
    fun getRarityColor(): Color {
        return when (rarity) {
            "Rare" -> RareRarity
            "Epic" -> EpicRarity
            "Legendary" -> LegendaryRarity
            else -> CommonRarity
        }
    }
}
