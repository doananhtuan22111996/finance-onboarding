package vn.finance.buildSrc

import org.gradle.api.Project
import java.io.File
import java.util.Properties

fun Project.getLocalProperty(propertyName: String): String {
    val localProperties = Properties().apply {

        val localPropertiesFile = File(rootDir, "local.properties")
        if (localPropertiesFile.exists()) {
            load(localPropertiesFile.inputStream())
        }
    }

    return localProperties.getProperty(propertyName) ?: run {
        throw NoSuchFieldException("Not defined property: $propertyName")
    }
}

fun Project.repository() = uri("${Configs.mavenDomain}/${username()}/finance-authentication")

fun Project.username() = System.getenv("USERNAME") ?: getLocalProperty("USERNAME")
fun Project.password() = System.getenv("TOKEN") ?: getLocalProperty("TOKEN")
