package com.example.project.utils


import java.util.Properties

/**
 * Utility class responsible for loading properties from a properties file.
 */
@Suppress("JAVA_CLASS_ON_COMPANION")
class Properties {

    companion object {

        fun properties(): Props {
            // Create a new Properties object
            val prop = Properties()

            // Set the system property for the properties file
            System.setProperty("prop_file", "test.properties")

            // Load properties from the properties file
            prop.load(this.javaClass.classLoader.getResourceAsStream(System.getProperty("prop_file")))

            // Create and return Props object with loaded properties
            return Props(
                clientID = prop.getProperty("clientID"),
                clientSecret = prop.getProperty("clientSecret"),
            )
        }
    }

    /**
     * Data class representing the loaded properties.
     */
    data class Props(
        val clientID: String,
        val clientSecret: String,
    )
}