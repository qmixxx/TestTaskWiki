package com.example.project.api

import com.example.project.config.AppAuth
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.json.JSONObject

class ApiClient(private val baseUrl: String) {


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

    fun createNewPage(
        source: String,
        title: String,
        comment: String,
        project: String,
        language: String,
    ): Int {

        val token = AppAuth().token()

        val requestBody = JSONObject()
            .put("source", source)
            .put("title", title)
            .put("comment", comment)

        val response =  RestAssured.given()
            .baseUri("https://api.wikimedia.org/core/v1/")
            .contentType(ContentType.JSON)
            .header("Authorization","Bearer $token")
            .body(requestBody.toString())
            .post("/$project/$language/page")

        return response.statusCode

    }

}
