package com.example

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*
import com.mongodb.client.model.Filters.and
import com.mongodb.client.model.Filters.eq
import io.ktor.server.plugins.cors.routing.CORS
import kotlinx.coroutines.flow.toList


fun Application.module() {
    // Налаштування JSON
    install(ContentNegotiation) {
        json()
    }

    install(CORS) {
        anyHost()
        allowHeader(HttpHeaders.ContentType)
    }

    routing {
        // Тестовий маршрут, щоб перевірити чи працює сервер
        get("/") {
            call.respondText("Сервер коктейлів працює!")
        }

        // Отримати улюблені
        get("/favorites/{email}") {
            val email = call.parameters["email"] ?: return@get call.respond(HttpStatusCode.BadRequest)

            try {
                // Отримуємо всі записи з бази і перетворюємо в список
                val favorites = Database.collection
                    .find(and(eq("userId", email), eq("isFavorite", true)))
                    .toList() // Потрібен імпорт kotlinx.coroutines.flow.toList

                call.respond(favorites)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, e.message ?: "Error")
            }
        }

        // Зберегти або оновити
        post("/favorites") {
            try {
                val interaction = call.receive<UserInteraction>()
                val filter = and(
                    eq("userId", interaction.userId),
                    eq("cocktailId", interaction.cocktailId)
                )

                Database.collection.replaceOne(
                    filter,
                    interaction,
                    com.mongodb.client.model.ReplaceOptions().upsert(true)
                )
                call.respond(HttpStatusCode.OK, "Saved")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, e.message ?: "Error")
            }
        }
    }
}
