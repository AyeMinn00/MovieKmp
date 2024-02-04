package com.amo.moviekmp.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object TmdbApi {

    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        defaultRequest {
            url("https://api.themoviedb.org/3/")
        }
        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 3)
            exponentialDelay()
        }
    }
//        .plugin(HttpSend).intercept { request ->
//        val originalCall = execute(request)
//        if (originalCall.response.status.value !in 100..399) {
//            execute(request)
//        } else {
//            originalCall
//        }
//    }

}