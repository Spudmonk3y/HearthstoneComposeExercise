package com.monkeybased.hearthtechnicalexercise.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monkeybased.hearthtechnicalexercise.data.AsyncResult
import com.monkeybased.hearthtechnicalexercise.data.model.Card
import com.monkeybased.hearthtechnicalexercise.data.model.UiState
import com.monkeybased.hearthtechnicalexercise.data.networking.service.CardService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CardViewModel : ViewModel() {
    private val cardService = CardService()

    private val _cards = MutableLiveData<AsyncResult<List<Card>>>()
    val cards: LiveData<AsyncResult<List<Card>>> = _cards

    private val _searchCards = MutableLiveData<AsyncResult<List<Card>>>()
    val searchCards: LiveData<AsyncResult<List<Card>>> = _searchCards

    private val _uiState = MutableStateFlow<UiState>(UiState(null))
    val uiState: StateFlow<UiState> = _uiState

    init {
        _cards.value = AsyncResult.Success(emptyList())
        _searchCards.value = AsyncResult.Success(emptyList())
    }

    fun fetchCardBySet(set: String) {
        viewModelScope.launch {
            try {
                _cards.value = AsyncResult.Loading
                _cards.value = AsyncResult.Success(cardService.getCardsBySet(set))
            } catch (e: Exception) {
                _cards.value = AsyncResult.Error(e)
            }
        }
    }

    fun fetchCardByClass(playerClass: String) {
        viewModelScope.launch {
            try {
                _cards.value = AsyncResult.Loading
                _cards.value = AsyncResult.Success(cardService.getCardsByClass(playerClass))
            } catch (e: Exception) {
                _cards.value = AsyncResult.Error(e)
            }
        }
    }

    fun fetchCardBySearch(query: String) {
        viewModelScope.launch {
            try {
                _searchCards.value = AsyncResult.Loading
                _searchCards.value = AsyncResult.Success(cardService.getCardsBySearch(query))
            } catch (e: Exception) {
                _searchCards.value = AsyncResult.Error(e)
            }
        }
    }

    fun setSelectedCard(card: Card) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedCard = card
            )
        }
    }
}