package com.angelalonso.kanara

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform