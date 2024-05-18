package com.monkeybased.hearthtechnicalexercise.ui.screen

import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import com.monkeybased.hearthtechnicalexercise.R
import com.monkeybased.hearthtechnicalexercise.data.model.Card
import com.monkeybased.hearthtechnicalexercise.data.model.CardSet
import com.monkeybased.hearthtechnicalexercise.ui.circleBackground
import com.monkeybased.hearthtechnicalexercise.ui.theme.DarkLava
import com.monkeybased.hearthtechnicalexercise.ui.theme.DeathKnightClassColor
import com.monkeybased.hearthtechnicalexercise.ui.theme.HearthTechnicalExerciseTheme
import com.monkeybased.hearthtechnicalexercise.ui.theme.HearthstoneMango
import com.monkeybased.hearthtechnicalexercise.ui.theme.HearthstoneWhite
import com.monkeybased.hearthtechnicalexercise.ui.theme.ShamanClassColor

@Composable
fun CardDetails(card: Card) {
    val showImageDialog = remember { mutableStateOf(false) }

    if (showImageDialog.value && card.img != null) {
        ShowImageDialog(card.img) { showImageDialog.value = false }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                if (card.cost != null) {
                    Text(
                        text = card.cost.toString(),
                        modifier = Modifier.circleBackground(ShamanClassColor, 6.dp).align(Alignment.CenterStart),
                        color = HearthstoneWhite
                    )
                }

                Text(
                    text = card.name,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 24.sp,
                    lineHeight = 1.em,
                    fontWeight = FontWeight.Bold,
                    color = DarkLava
                )
            }

            Text(
                text = card.type,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp,
                lineHeight = 1.em,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.size(6.dp))
            Icon(
                painter = painterResource(CardSet.getSetFromCard(card).iconResId),
                contentDescription = card.cardSet,
                tint = card.getRarityColor(),
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterHorizontally)
            )
            if (card.text != null) {
                AndroidView(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 24.dp, end = 24.dp),
                    factory = { context ->
                        TextView(context).apply {
                            text = HtmlCompat.fromHtml(card.text.replace("\\n", "<br />"), HtmlCompat.FROM_HTML_MODE_LEGACY)
                            setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18F)
                            gravity = Gravity.CENTER
                            setTextColor(ContextCompat.getColor(context, R.color.black))
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.size(6.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                if (card.attack != null) {
                    Text(
                        text = card.attack.toString(),
                        modifier = Modifier
                            .circleBackground(HearthstoneMango, 6.dp)
                            .align(Alignment.CenterStart),
                        color = HearthstoneWhite
                    )
                }

                if (card.health != null) {
                    Text(
                        text = card.health.toString(),
                        Modifier
                            .circleBackground(DeathKnightClassColor, 6.dp)
                            .align(Alignment.CenterEnd),
                        color = HearthstoneWhite
                    )
                }
            }
            Spacer(modifier = Modifier.size(6.dp))
            Row(modifier = Modifier.fillMaxSize()) {
                if (card.img != null) {
                    AsyncImage(
                        model = card.img,
                        contentDescription = null,
                        Modifier
                            .weight(1f)
                            .clickable {
                                showImageDialog.value = true
                            }
                    )
                }
                Column(Modifier.weight(1f)) {
                    Text(
                        text = stringResource(id = R.string.card_details),
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.size(6.dp))
                    Text(
                        text = stringResource(id = R.string.card_set, card.cardSet),
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start,
                        lineHeight = 1.em,
                        color = DarkLava
                    )
                    if (card.faction != null) {
                        Text(
                            text = stringResource(id = R.string.card_faction, card.faction),
                            fontSize = 12.sp,
                            textAlign = TextAlign.Start,
                            lineHeight = 1.em,
                            color = DarkLava
                        )
                    }
                    if (card.rarity != null) {
                        Text(
                            text = stringResource(id = R.string.card_rarity, card.rarity),
                            fontSize = 12.sp,
                            textAlign = TextAlign.Start,
                            lineHeight = 1.em,
                            color = DarkLava
                        )
                    }
                    if (card.artist != null) {
                        Text(
                            text = stringResource(id = R.string.card_artist, card.artist),
                            fontSize = 12.sp,
                            textAlign = TextAlign.Start,
                            color = DarkLava
                        )
                    }
                    if (card.flavor != null) {
                        Text(
                            text = stringResource(id = R.string.card_flavor, card.flavor),
                            fontSize = 12.sp,
                            textAlign = TextAlign.Start,
                            lineHeight = 1.em,
                            color = DarkLava
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ShowImageDialog(imageUrl: String, onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize().clickable { onDismissRequest() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardDetailsPreview() {
    HearthTechnicalExerciseTheme {
        // A surface container using the 'background' color from the theme
        CardDetails(
            Card(
                "REV_000e",
                "Suspicious Alchemist",
                "Murder at Castle Nathria",
                "Minion",
                "Horde",
                "Rare",
                3,
                1,
                3,
                "<b> Battlecry: Discover</b> a spell. If your opponent guesses your choice, they get a copy.",
                "\"Making a dredger is easier than you'd think. You don't need to worry about mucking it up!\"",
                "Jakub Kasper",
                true,
                null,
                "https://d15f34w2p8l1cc.cloudfront.net/hearthstone/f2b8f8befcbeb8b829358f727a3b34ecea285a23f6235d6c9a2fa57b7ed7c17b.png",
                null,
                "enUS",
                75867,
                "Mage"
            )
        )
    }
}