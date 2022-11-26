package client

import constant.User.LOGIN
import constant.User.LOGOUT
import data.LoginResponse
import io.qameta.allure.kotlin.Step
import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured
import io.restassured.RestAssured.UNDEFINED_PORT
import io.restassured.config.EncoderConfig
import io.restassured.config.HeaderConfig
import io.restassured.config.LogConfig
import io.restassured.config.ObjectMapperConfig
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.internal.RequestSpecificationImpl
import io.restassured.internal.log.LogRepository
import io.restassured.mapper.ObjectMapperType
import io.restassured.specification.RequestSpecification

class PetStoreClient : RestClient() {
    private val baseUri = "https://petstore.swagger.io/v2" // вынести в проперти

    inner class User {
        @Step("Log user into system")
        fun login(username: String, password: String): LoginResponse? {
            val spec = getDefaultRequestSpecification().pathParams(
                "username", username,
                "password", password
            )
            return get<LoginResponse>(LOGIN, spec, 200)
        }

        @Step("Log out user")
        fun logout(): String? {
            return get<String>(LOGOUT, getDefaultRequestSpecification(), 200);
        }


    }

    private fun getDefaultRequestSpecification(): RequestSpecification {
        val config = RestAssured.config()
            .logConfig(
                LogConfig.logConfig()
                    .enableLoggingOfRequestAndResponseIfValidationFails()
            )
            .headerConfig(
                HeaderConfig.headerConfig().overwriteHeadersWithName("Content-Type", "Accept")
            )
            .objectMapperConfig(ObjectMapperConfig.objectMapperConfig().defaultObjectMapperType(ObjectMapperType.GSON))
            .encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))

        return RequestSpecificationImpl(
            baseUri,
            UNDEFINED_PORT,
            "",
            RestAssured.DEFAULT_AUTH,
            listOf(
                ResponseLoggingFilter(),
                AllureRestAssured()
            ),
            null,
            true,
            config,
            LogRepository(),
            null,
            true
        )
    }
}