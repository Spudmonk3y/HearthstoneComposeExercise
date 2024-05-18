package com.monkeybased.hearthtechnicalexercise.data.networking

import com.monkeybased.hearthtechnicalexercise.data.networking.api.CardApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    private val BASE_URL = "https://omgvamp-hearthstone-v1.p.rapidapi.com/"
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient().newBuilder().addInterceptor {
            val request = it.request().newBuilder()
                .addHeader("X-RapidAPI-Key", "cf0ebe1325mshb0292c54e93402cp136cc6jsnf70c84300f90")
                .addHeader("X-RapidAPI-Host", "omgvamp-hearthstone-v1.p.rapidapi.com")
                .build()
            it.proceed(request)
        }.build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val cardApi: CardApi by lazy {
        retrofit.create(CardApi::class.java)
    }
}