package com.example.yourproject

import com.example.project.config.AppAuth
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.util.*

class SimpleTests {

    private val authToken = AppAuth().token()

    @Test
    fun `Test search for particular page contain`() {

        val response =
            given()
                .param("q", "furry rabbits")
                .`when`()
                .get("https://api.wikimedia.org/core/v1/wikipedia/en/search/page")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString()

        assert(response.contains("Sesame Street"))
    }

    @Test
    fun `Test details for particular page contain`() {

        val response =
            given()
                .`when`()
                .get("https://api.wikimedia.org/core/v1/wikipedia/en/page/Sesame_Street")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .asString().trimIndent()

        val timestamp = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH).parse(extractTimestamp(response))
        val comparisonDate = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2023-08-17")
        assert(timestamp.after(comparisonDate)) { "Timestamp is not greater than 2023-08-17" }
    }

    private fun extractTimestamp(response: String): String {
        // Extract the timestamp from the JSON response
        return response.split("\"timestamp\":")[1].substringBefore(",").replace("\"", "")
    }


    @Test
    fun `create a new page via API`() {

        val pageId = (1..9999).random().toString()

        given()
            .header("Authorization", "Bearer $authToken")
            .body(
                "{ \"source\": \"Creating a test page with the Wikimedia API\"," +
                        " \"title\": \"User:IvanovIvan88/newTest$pageId\"," +
                        " \"comment\": \"Creating a test page with the Wikimedia API\" }"
            )
            .contentType("application/json")
            .`when`()
            .post("https://api.wikimedia.org/core/v1/wikiquote/en/page")
            .then()
            .assertThat()
            .statusCode(201)
    }

}