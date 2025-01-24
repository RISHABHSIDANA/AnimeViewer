package com.example.animeviewer

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RetrofitInstance {
    val api: MainNetwork by lazy {
        Retrofit.Builder().baseUrl(AnimeUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MainNetwork::class.java)

    }
}

interface MainNetwork {
    @GET(AnimeUtils.GET_ANIME)
    suspend fun getAnimeList(): AnimeApiResult
}