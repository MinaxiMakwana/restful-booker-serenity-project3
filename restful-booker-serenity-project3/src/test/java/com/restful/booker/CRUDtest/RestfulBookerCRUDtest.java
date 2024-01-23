package com.restful.booker.CRUDtest;

import com.restful.booker.model.RestfulBookerAuthPojo;
import com.restful.booker.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.hasKey;

@RunWith(SerenityRunner.class)
public class RestfulBookerCRUDtest extends TestBase {

    static String username = "admin";
    static String password = "password123";

    @Steps
    RestfulBookerAuthPojo steps;

    @Title("This will crate username")
    @Test
    public void test001() {

        ValidatableResponse response = steps.creatingAuth(username, password);
        response.statusCode(201);

        username = response.extract().path("/auth");
        password = response.extract().path("/auth");

        response.body("$", hasKey("token"));

    }




}
