package com.marius.tests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestApi {

    //Check that a post exists with the title "qui est esse"
    @Test
    public void checkTitleExistsTest() {
        String titleValue = "qui est esse";
        when().get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200)
                .body("title", hasItems(titleValue, "eum et est occaecati"));
    }

    //Add a new user and specify a name, username and email
    @Test
    public void addANewUserTest() {
        baseURI = "https://jsonplaceholder.typicode.com";

        JSONObject request = new JSONObject();
        request.put("name", "Marius Sturza");
        request.put("email", "marius.sturza@gmail.com");
        request.put("username", "Mariuss");


        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())

                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().all();
    }

    //Write a test for one of the API endpoints that will fail if the response time passes a given threshold.
    @Test
    public void threasholdTest() {
        baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given().get("https://jsonplaceholder.typicode.com/posts");
        Assert.assertTrue(response.getTime()<500?true:false, "Response time is higher than 500 milliseconds");
    }


}
