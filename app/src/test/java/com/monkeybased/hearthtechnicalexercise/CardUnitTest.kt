package com.monkeybased.hearthtechnicalexercise

import com.monkeybased.hearthtechnicalexercise.data.model.Card
import com.monkeybased.hearthtechnicalexercise.data.model.CardSet
import com.monkeybased.hearthtechnicalexercise.ui.theme.CommonRarity
import com.monkeybased.hearthtechnicalexercise.ui.theme.EpicRarity
import com.monkeybased.hearthtechnicalexercise.ui.theme.LegendaryRarity
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CardUnitTest {
    @Test
    fun card_rarity_isCorrect() {
        val cards = getCards()
        assertEquals(EpicRarity, cards[0].getRarityColor())
        assertEquals(CommonRarity, cards[1].getRarityColor())
        assertEquals(LegendaryRarity, cards[2].getRarityColor())
    }

    @Test
    fun card_set_isCorrect() {
        val cards = getCards()
        assertEquals(CardSet.SAVIORS_OF_ULDUM, CardSet.getSetFromCard(cards[0]))
        assertEquals(CardSet.FORGED_IN_THE_BARRENS, CardSet.getSetFromCard(cards[1]))
        assertEquals(CardSet.MURDER_AT_CASTLE_NATHRIA, CardSet.getSetFromCard(cards[2]))
    }

    private fun getCards(): List<Card> {
        return listOf(
            Card(
                "ULDA_RENO_HP2",
                "Relicologist",
                "Saviors of Uldum",
                "Hero Power",
                null,
                "Epic",
                1,
                null,
                null,
                "Your next spell this turn\nhas <b> Spell Damage _ 2 </b>",
                null,
                null,
                null,
                null,
                null,
                null,
                "enUS",
                57647,
                "Mage"
            ),
            Card(
                "BOM_02_Octobot_007hb",
                "0CT0-Bot",
                "Forged in the Barrens",
                "Hero",
                "Neutral",
                "Common",
                null,
                null,
                30,
                "<i>On the Black Lake, at the top of the Iron Tower, Xyrella finds what she has long searched for...</i>",
                null,
                null,
                null,
                null,
                null,
                null,
                "enUS",
                71957,
                "Mage"
            ),
            Card(
                "REV_000e",
                "A Mystery!",
                "Murder at Castle Nathria",
                "Enchantment",
                null,
                "Legendary",
                null,
                null,
                null,
                "At the start of your turn, guess what card your opponent chose to get a copy of it.",
                null,
                null,
                null,
                null,
                "",
                null,
                "enUS",
                75867,
                "Mage"
            )
        )
    }
}