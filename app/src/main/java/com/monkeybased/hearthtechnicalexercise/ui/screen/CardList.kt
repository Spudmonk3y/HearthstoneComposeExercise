package com.monkeybased.hearthtechnicalexercise.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.monkeybased.hearthtechnicalexercise.R
import com.monkeybased.hearthtechnicalexercise.data.AsyncResult
import com.monkeybased.hearthtechnicalexercise.data.model.Card
import com.monkeybased.hearthtechnicalexercise.data.model.CardSet
import com.monkeybased.hearthtechnicalexercise.ui.theme.DarkLava
import com.monkeybased.hearthtechnicalexercise.ui.theme.HearthTechnicalExerciseTheme
import com.monkeybased.hearthtechnicalexercise.ui.theme.SlightlyDarkerLightGray

@Composable
fun CardList(cards: AsyncResult<List<Card>>?, onCardSelected: (Card) -> Unit, onResetAfterError: () -> Unit, onErrorRetry: () -> Unit) {
    val showErrorDialog = remember { mutableStateOf(false) }

    when (cards) {
        is AsyncResult.Success -> {
            LazyColumn(
                Modifier
                    .background(Color.LightGray)
                    .fillMaxSize()
            ) {
                itemsIndexed(cards.data) { index, it ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .clickable(onClick = { onCardSelected(it) })
                    ) {
                        Icon(
                            painter = painterResource(CardSet.getSetFromCard(it).iconResId),
                            contentDescription = it.cardSet,
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.CenterVertically)
                                .padding(start = 12.dp, end = 12.dp)
                                .weight(0.15f),
                            tint = it.getRarityColor()
                        )
                        Box(modifier = Modifier.weight(0.85f)) {
                            Column(Modifier.fillMaxWidth()) {
                                Spacer(modifier = Modifier.size(4.dp))
                                Text(
                                    modifier = Modifier.align(Alignment.Start),
                                    text = it.name,
                                    fontSize = 20.sp,
                                    style = TextStyle(lineHeight = 2.em),
                                    color = DarkLava
                                )
                                Text(
                                    modifier = Modifier.align(Alignment.Start),
                                    text = stringResource(id = R.string.card_type, it.type),
                                    fontSize = 14.sp,
                                    style = TextStyle(lineHeight = 2.em),
                                    color = DarkLava
                                )
                                Spacer(modifier = Modifier.size(6.dp))
                                Text(
                                    modifier = Modifier.align(Alignment.Start),
                                    text = stringResource(id = R.string.card_set, it.cardSet),
                                    fontSize = 12.sp,
                                    style = TextStyle(lineHeight = 2.em),
                                    color = DarkLava
                                )
                                Spacer(modifier = Modifier.size(4.dp))
                            }
                            if (it.img != null) {
                                Icon(
                                    painterResource(id = R.drawable.icon_image),
                                    tint = DarkLava,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .align(Alignment.CenterEnd)
                                        .padding(start = 24.dp, end = 24.dp)
                                        .testTag("image_indicator:${it.cardId}")
                                )
                            }
                        }
                    }
                    if (index < cards.data.lastIndex) {
                        Divider(color = SlightlyDarkerLightGray, thickness = 1.dp)
                    }
                }
            }
        }
        is AsyncResult.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.testTag("spinner"))
            }
        } is AsyncResult.Error -> {
            showErrorDialog.value = true
            if (showErrorDialog.value) {
                ErrorDialog(
                    onDismissRequest = {
                        showErrorDialog.value = false
                        onResetAfterError()
                    },
                    onRetryRequest = {
                        showErrorDialog.value = false
                        onErrorRetry()
                    },
                    exception = cards.exception
                )
            }
        }
        null -> {
            onResetAfterError()
        }
    }
}

@Composable
fun ErrorDialog(onDismissRequest: () -> Unit, onRetryRequest: () -> Unit, exception: Throwable? = null) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = {
            Text(text = stringResource(id = R.string.global_error))
        },
        text = {
            Text(text = exception?.localizedMessage ?: stringResource(id = R.string.generic_error_fetch))
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onRetryRequest()
                }
            ) {
                Text(stringResource(id = R.string.global_retry))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(id = R.string.global_reset))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CardListPreview() {
    HearthTechnicalExerciseTheme {
        // A surface container using the 'background' color from the theme
        CardList(
            AsyncResult.Success(
                listOf(
                    Card(
                        "ULDA_RENO_HP2",
                        "Relicologist",
                        "Saviors of Uldum",
                        "Hero Power",
                        null,
                        null,
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
                        null,
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
                        null,
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
            ),
            {},
            {},
            {}
        )
    }
}