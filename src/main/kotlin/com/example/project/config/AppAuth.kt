package com.example.project.config

import com.example.project.utils.Properties
import io.restassured.RestAssured
import org.json.JSONObject


class AppAuth() {


    private val properties = Properties.properties()

    fun token(): String {

        val results =
            RestAssured
                .given()
                .formParam("grant_type", "client_credentials")
                .formParam("client_id", properties.clientID)
                .formParam("client_secret", properties.clientSecret)
                .post("https://meta.wikimedia.org/w/rest.php/oauth2/access_token")

        val response = JSONObject(results.body.prettyPrint())

        return response["access_token"].toString()
    }
}
