package com.example

import io.ktor.server.application.Application
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    // Railway сам скаже, на якому порті слухати. Якщо локально — беремо 8081.
    val port = System.getenv("PORT")?.toInt() ?: 8081

    println("Запуск сервера на порту $port...")
    try {
        embeddedServer(Netty, port = port, host = "0.0.0.0", module = Application::module)
            .start(wait = true)
    } catch (e: Exception) {
        println("Помилка під час запуску: ${e.message}")
        e.printStackTrace()
    }
}
