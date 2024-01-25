package com.amo.moviekmp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("original_title") val originTitle: String,
    @SerialName("vote_count") val voteCount: Int,
    val isVoted : Boolean = false
)

@Serializable
class MoviesResponse(
    @SerialName("results") val results: List<Movie>
)