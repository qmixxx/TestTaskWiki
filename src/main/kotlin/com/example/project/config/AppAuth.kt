package com.example.project.config

import com.example.project.utils.Properties
import io.restassured.RestAssured
import org.json.JSONObject


class AppAuth() {


    private val properties = Properties.properties()

    // Send a POST request to the OAuth2 endpoint to obtain the access token
    fun token(): String {

        val results =
            RestAssured
                .given()
                .formParam("grant_type", "client_credentials")
                .formParam("client_id", properties.clientID)
                .formParam("client_secret", properties.clientSecret)
                .post("https://meta.wikimedia.org/w/rest.php/oauth2/access_token")

        // Parse the response body to obtain the access token
        val response = JSONObject(results.body.prettyPrint())

        // Return the access token
        return response["access_token"].toString()
    }
}
