package com.amo.moviekmp.network

import com.amo.moviekmp.model.MoviesResponse
import io.ktor.client.call.body
import io.ktor.client.request.get

class DefaultTmdbRemoteDataSource : TmdbRemoteDataSource {

    override suspend fun getMovies(): MoviesResponse {
        return TmdbApi.httpClient.get("discover/movie?api_key=2232fb8905b56f7a05e77ccf32ac93ce").body()
    }

}