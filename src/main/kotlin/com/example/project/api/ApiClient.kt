package com.example.project.api

import com.example.project.config.AppAuth
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.json.JSONObject

/**
 * Class responsible for making API requests to Wikimedia API endpoints.
 */
class ApiClient(private val baseUrl: String) {

    /**
     * Searches for a page title using the provided query.
     */
    fun searchForPageTitle(query: String): String {
        return RestAssured.given()
            .baseUri(baseUrl)
            .contentType(ContentType.JSON)
            .param("q", query)
            .get("/wikipedia/en/search/page")
            .then()
            .statusCode(200)
            .extract()
            .response()
            .asString()
    }

    /**
     * Searches for page content using the provided query.
     */
    fun searchForPageContent(query: String): String {
        return RestAssured.given()
            .baseUri(baseUrl)
            .contentType(ContentType.JSON)
            .get("/wikipedia/en/page/$query")
            .then()
            .statusCode(200)
            .extract()
            .response()
            .asString()
    }

    /**
     * Creates a new page with the provided details.
     */
    fun createNewPage(
        source: String,
        title: String,
        comment: String,
        project: String,
        language: String,
    ): Int {
        // Obtain the access token for authentication
        val token = AppAuth().token()

        // Create a JSON object representing the request body
        val requestBody = JSONObject()
            .put("source", source)
            .put("title", title)
            .put("comment", comment)

        // Send a POST request to create the new page
        val response = RestAssured.given()
            .baseUri("https://api.wikimedia.org/core/v1/")
            .contentType(ContentType.JSON)
            .header("Authorization","Bearer $token")
            .body(requestBody.toString())
            .post("/$project/$language/page")

        // Return the status code of the response
        return response.statusCode
    }
}
