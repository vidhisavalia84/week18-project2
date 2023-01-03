package com.gorest.gorestinfo;

import com.gorest.constants.EndPoints;
import com.gorest.models.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {


    @Step("Creating user with name:{0},email:{1},gender:{2},status:{4}")
    public ValidatableResponse createUser(String name, String email, String gender, String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .body(userPojo)
                .when()
                .post()
                .then();
    }

    @Step("Getting user information by email:{0}")
    public HashMap<String, Object> getUserByEmail(String email) {
        String s1 = "findAll{it.email == '";
        String s2 = "'}.get(0)";

        return SerenityRest.given().log().all()
                .headers("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .headers("Accept", "*/*")
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .path(s1 + email + s2);
    }

    @Step("Updating user information with userId:{0},name:{1},emil:{2},gender:{3},ststus:{4}")
    public ValidatableResponse updateUserById(int userId, String name, String email, String gender, String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .headers("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .headers("Accept", "*/*")
                .pathParams("userId", userId)
                .body(userPojo)
                .when()
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then();
    }

    @Step("Deleting user with user id:{0}")
    public ValidatableResponse deleteUserById(int userId) {
        return SerenityRest.given().log().all()
                .headers("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .pathParams("userId", userId)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();

    }

    @Step("Getting user by id:{0}")
    public ValidatableResponse getUserById(int userId) {
        return SerenityRest.given().log().all()
                .headers("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .pathParams("userId", userId)
                .when()
                .get(EndPoints.GET_USER_BY_ID)
                .then();
    }
}
