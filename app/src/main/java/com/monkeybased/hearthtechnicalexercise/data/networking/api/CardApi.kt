package com.monkeybased.hearthtechnicalexercise.data.networking.api

import com.monkeybased.hearthtechnicalexercise.data.model.Card
import retrofit2.http.GET
import retrofit2.http.Path

interface CardApi {
    @GET("cards/classes/{class}")
    suspend fun getCardsByClass(@Path("class") playerClass: String): List<Card>

    @GET("cards/sets/{set}")
    suspend fun getCardsBySet(@Path("set") set: String): List<Card>

    @GET("cards/search/{query}")
    suspend fun searchForCard(@Path("query") query: String): List<Card>
}