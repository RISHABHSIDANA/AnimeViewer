package com.example.animeviewer

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AnimeApiResult(
    val pagination: Pagination,
    @SerializedName("data")
    val animes: List<Anime>
)

data class Pagination(
    val lastVisiblePage: Int,
    val hasNextPage: Boolean,
    val currentPage: Int,
    val items: Items
)

data class Items(
    val count: Int,
    val total: Int,
    val perPage: Int
)


data class Anime(
    val malId: Int,
    val title: String,
    val episodes: String,
    val rating: String,
    val images: Images,
    val trailer: Trailer,
    val synopsis: String,
    val genres: List<Genre>,
    var clickEvent: () -> Unit
)

data class Genre(
    val malId: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Images(
    val jpg: Jpg
)

data class Jpg(
    @SerializedName("image_url")
    val imageUrl: String
)

data class Trailer(
    @SerializedName("embed_url")
    val embedUrl: String
)

data class AnimeDetails(
    val url: String,
    val title: String,
    val synopsis: String,
    val genres: List<String>,
    val numEpisodes: String,
    val rating: String,
    val imageUrl: String
) : Serializable
