package com.monkeybased.hearthtechnicalexercise

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.monkeybased.hearthtechnicalexercise.data.AsyncResult
import com.monkeybased.hearthtechnicalexercise.data.model.Card
import com.monkeybased.hearthtechnicalexercise.ui.screen.CardList
import org.junit.Rule
import org.junit.Test

class CardListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun cardListLoadingTest() {
        composeTestRule.setContent {
            CardList(cards = AsyncResult.Loading, onCardSelected = {}) {
            }
        }

        composeTestRule.onNodeWithTag("spinner").assertIsDisplayed()
    }

    @Test
    fun cardListSuccess() {
        composeTestRule.setContent {
            CardList(cards = AsyncResult.Success(getCards()), onCardSelected = {}) {
            }
        }

        composeTestRule.onNodeWithText("Relicologist").assertIsDisplayed()
        composeTestRule.onNodeWithText("0CT0-Bot").assertIsDisplayed()
        composeTestRule.onNodeWithText("A Mystery!").assertIsDisplayed()
    }

    @Test
    fun cardListImageIcon() {
        composeTestRule.setContent {
            CardList(cards = AsyncResult.Success(getCards()), onCardSelected = {}) {
            }
        }
        composeTestRule.onNodeWithTag("image_indicator:ULDA_RENO_HP2", true).assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("image_indicator:BOM_02_Octobot_007hb", true).assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("image_indicator:REV_000e", true).assertIsDisplayed()
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