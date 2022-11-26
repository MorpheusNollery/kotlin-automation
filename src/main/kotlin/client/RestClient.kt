package client

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.restassured.RestAssured.given
import io.restassured.http.Method
import io.restassured.specification.RequestSpecification


abstract class RestClient {

    inline fun <reified T> request(
        method: Method,
        path: String? = "",
        requestSpecification: RequestSpecification,
        expectedStatus: Int
    ): T? {
        val res = given()
            .spec(requestSpecification)
            .log()
            .all()
            .request(method, path)
            .then()
            .statusCode(expectedStatus)
            .extract()
        return if (res.asString().isNotBlank())
            Gson().fromJson(res.asString(), object : TypeToken<T>() {}.type)
        else
            null
    }

    inline fun <reified T> get(
        path: String?,
        requestSpecification: RequestSpecification,
        expectedStatus: Int
    ): T? {
        return request(Method.GET, path, requestSpecification, expectedStatus)
    }

    inline fun <reified T> post(
        path: String?,
        requestSpecification: RequestSpecification,
        expectedStatus: Int
    ): T? {
        return request(Method.POST, path, requestSpecification, expectedStatus)
    }

    inline fun <reified T> put(
        path: String?,
        requestSpecification: RequestSpecification,
        expectedStatus: Int
    ): T? {
        return request(Method.PUT, path, requestSpecification, expectedStatus)
    }

    inline fun <reified T> patch(
        path: String?,
        requestSpecification: RequestSpecification,
        expectedStatus: Int
    ): T? {
        return request(Method.PATCH, path, requestSpecification, expectedStatus)
    }

    fun delete(
        path: String?,
        requestSpecification: RequestSpecification,
        expectedStatus: Int
    ) {
        request<Any>(Method.DELETE, path, requestSpecification, expectedStatus)
    }

    inline fun <reified T> requestWithoutStatus(
        method: Method,
        path: String?,
        requestSpecification: RequestSpecification
    ): T? {
        val res = given()
            .spec(requestSpecification)
            .log()
            .all()
            .request(method, path)
            .thenReturn()
        return if (res.asString().isNotBlank())
            Gson().fromJson(res.asString(), object : TypeToken<T>() {}.type)
        else
            null
    }
}