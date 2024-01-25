package com.amo.moviekmp.network

import com.amo.moviekmp.model.MoviesResponse

interface TmdbRemoteDataSource {

    suspend fun getMovies(): MoviesResponse

}