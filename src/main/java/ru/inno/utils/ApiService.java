package ru.inno.utils;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiService {
    public ApiService() {
    }

    private Map<String, String> getToken() {
        Map<String, String> authorizationInf = new HashMap<>();
        Response response =
                given()
                        .baseUri(TestProperties.getProperties().getBaseUrl() + TestProperties.getProperties().getAuthEndpoint())
                        .contentType("application/json; charset=utf-8")
                        .body("{\"userName\": \"" + TestProperties.getProperties().getUserName() + "\", \"password\": \"" + TestProperties.getProperties().getPassword() + "\"}")
                        .when()
                        .post();
        response.then().log().ifValidationFails().statusCode(200);
        authorizationInf.put("userId", response.then().extract().path("userId").toString());
        authorizationInf.put("token", response.then().extract().path("token").toString());
        return authorizationInf;
    }

    public void deleteAllBooks() {
        given()
                .baseUri(TestProperties.getProperties().getBaseUrl() + TestProperties.getProperties().getDeleteAllEndpoint())
                .param("UserId", getToken().get("userId"))
                .contentType("application/json")
                .header("Authorization", "Bearer " + getToken().get("token"))
                .log().ifValidationFails()
                .delete()
                .then()
                .log().ifValidationFails()
                .statusCode(204);
    }
}
