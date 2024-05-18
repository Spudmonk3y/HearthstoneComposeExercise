package com.monkeybased.hearthtechnicalexercise.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.monkeybased.hearthtechnicalexercise.R
import com.monkeybased.hearthtechnicalexercise.data.AsyncResult
import com.monkeybased.hearthtechnicalexercise.data.model.Card

@Composable
fun SearchCardList(
    searchCards: AsyncResult<List<Card>>?,
    onSearchAction: (String) -> Unit,
    onCardSelected: (Card) -> Unit,
    onErrorLoadingCards: () -> Unit
) {
    val showSearchDialog = remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showSearchDialog.value = true }) {
                Icon(painter = painterResource(id = R.drawable.icon_search), contentDescription = "Search for Cards")
            }
        },
        floatingActionButtonPosition = FabPosition.End

    ) {
        Box(Modifier.padding(it)) {
            CardList(searchCards, onCardSelected, onErrorLoadingCards)
        }
    }

    if (showSearchDialog.value) {
        ShowSearchDialog({ showSearchDialog.value = false }, {
            showSearchDialog.value = false
            onSearchAction(it)
        })
    }
}

@Composable
fun ShowSearchDialog(onDismissRequest: () -> Unit, onSearchRequest: (String) -> Unit) {
    val searchText = remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = {
            Text(text = "Please enter your search term.")
        },
        text = {
            TextField(searchText.value, onValueChange = { searchText.value = it }, singleLine = true)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onSearchRequest(searchText.value)
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancel")
            }
        }
    )
}

@Composable
@Preview
fun SearchCardListPreview() {
    SearchCardList(searchCards = AsyncResult.Success(emptyList()), {}, {}, {})
}