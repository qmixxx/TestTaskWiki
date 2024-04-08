package com.example.yourproject

import com.example.project.api.ApiClient
import org.hamcrest.Matchers.*
import org.json.JSONObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat

class SearchTest {

    private val apiClient = ApiClient("https://api.wikimedia.org/core/v1")

    @Test
    @DisplayName("Test Search Pages For Furry Rabbits Title")
    fun searchPagesForEFurryRabbitsTitleTest() {
        val searchQuery = "furry rabbits"
        val expectedPageTitle = "Sesame Street"

        val response = apiClient.searchForPageTitle(searchQuery)

        // Assert that the response contains the expected page title
        assert(response.contains(expectedPageTitle)) { "Expected page title '$expectedPageTitle' not found in the response." }
    }


    @Test
    @DisplayName("Test Search Page Details For Sesame Street")
    fun getPageDetailsForSesameStreet() {
        val pageName = "Sesame_Street"
        val response = apiClient.searchForPageContent(pageName)

        // Extracting the timestamp from response and converting to Date object
        // Assume jsonResponse is the string containing the JSON response from the API
        val jsonObject = JSONObject(response)

        // Access the "latest" object from the JSON object
        val latestObject = jsonObject.getJSONObject("latest")

        // Access the "timestamp" field from the "latest" object
        val timestampString = latestObject.getString("timestamp")

        val timestamp = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(timestampString)

        // Constructing the reference date (2023-08-17)
        val referenceDate = SimpleDateFormat("yyyy-MM-dd").parse("2023-08-17")

        // Asserting that the timestamp is greater than the reference date
        assert(timestamp.after(referenceDate)) { "Timestamp is not greater than 2023-08-17." }
    }

    @Test
    @DisplayName("Test Create New Page for Registered User")
    fun postNewPageForRegisteredUser() {

        val source = "Creating a test page with the Wikimedia API"
        val title = "User:IvanovIvan88/newTest"
        val comment = "Creating a test page with the Wikimedia API"
        val project = "wikiquote"
        val language = "en"

        val result = apiClient.createNewPage(source, title, comment, project, language)
        assertEquals(
            /* expected = */ 409, //
            /* actual = */ result,
            /* message = */ "Error. New page creation method returns not proper status code"
        )

    }

    // Part 2
    // Some case can be designed and implemented according to the documentation of provided end points.
    // https://api.wikimedia.org/wiki/Core_REST_API/Reference/Search/
    // Some examples can be found below.
    // The goal is to check possible status codes to be sure end points are valid and returning relevant results
    // Also as way could be added some JSON schema validation

    /**
     * GIVEN
     * A client of the API. Searches wiki pages for the given search terms
     *
     * WHEN
     * A search for pages containing for ‘[SOME NOT VALID QUERY]’ is executed // could be tested with "sdfwerf" or cyrillic "укксвсв" and etc
     *
     * THEN
     * Success: No results found. Returns an empty pages array. Status code: 200
     *
     */

    /**
     * GIVEN
     * A client of the API. Searches wiki pages for the given search terms
     *
     * WHEN
     * A search for pages without adding q parameter
     *
     * THEN
     * SQuery parameter not set. Add q parameter. Status code: 400
     *
     */

    /**
     * GIVEN
     * A client of the API. Searches wiki pages for the given search terms
     *
     * WHEN
     * A search for pages with adding limit parametr > 100
     *
     * THEN
     * Invalid limit requested. Set limit parameter to between 1 and 100. Status code: 400

     *
     */

}
