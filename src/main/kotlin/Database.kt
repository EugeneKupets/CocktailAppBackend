package com.example

import com.mongodb.kotlin.client.coroutine.MongoClient
import kotlinx.serialization.Serializable

@Serializable
data class UserInteraction(
    val userId: String,
    val cocktailId: String,
    val cocktailName: String,
    val imgSrc: String,
    val isFavorite: Boolean,
    val rating: Int
)

object Database {
    // Тут ми використовуємо SRV посилання, бо на сервері воно ПРАЦЮЄ!
    private const val URI = "mongodb+srv://papery_db_user:HDPgswX8QabAK5dy@cocktailcluster.4ayb3nu.mongodb.net/?retryWrites=true&w=majority"

    private val client = MongoClient.create(URI)
    val db = client.getDatabase("cocktail_db")
    val collection = db.getCollection<UserInteraction>("user_interactions")
}