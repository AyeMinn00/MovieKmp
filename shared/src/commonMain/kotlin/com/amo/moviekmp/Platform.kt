package com.amo.moviekmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform