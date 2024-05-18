package com.monkeybased.hearthtechnicalexercise.data.networking.service

import com.monkeybased.hearthtechnicalexercise.data.model.Card
import com.monkeybased.hearthtechnicalexercise.data.networking.RetrofitObject

class CardService {
    private val cardApi = RetrofitObject.cardApi

    suspend fun getCardsByClass(playerClass: String): List<Card> {
        return cardApi.getCardsByClass(playerClass)
    }

    suspend fun getCardsBySet(setName: String): List<Card> {
        return cardApi.getCardsBySet(setName)
    }

    suspend fun getCardsBySearch(query: String): List<Card> {
        return cardApi.searchForCard(query)
    }
}