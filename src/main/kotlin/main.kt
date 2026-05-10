package com.example

import io.ktor.server.application.Application
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    println("Запуск сервера...")
    try {
        embeddedServer(Netty, port = 8081, host = "0.0.0.0", module = Application::module)
            .start(wait = true)
    } catch (e: Exception) {
        println("Помилка під час запуску сервера: ${e.message}")
        e.printStackTrace()
    }
}
