package com.example.project.utils


import java.util.Properties


@Suppress("JAVA_CLASS_ON_COMPANION")
class Properties {

    companion object {
        fun properties(): Props {
            val prop = Properties()
            System.setProperty("prop_file", "test.properties")
            prop.load(this.javaClass.classLoader.getResourceAsStream(System.getProperty("prop_file")))

            return Props(
                clientID = prop.getProperty("clientID"),
                clientSecret = prop.getProperty("clientSecret"),
            )
        }
    }

    data class Props(
        val clientID: String,
        val clientSecret: String,
    )
}