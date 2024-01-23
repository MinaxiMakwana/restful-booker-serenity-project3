package com.restful.booker.restfulbooker;

import com.restful.booker.constants.EndPoints;
import com.restful.booker.model.RestfulBookerAuthPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import static io.restassured.RestAssured.given;

public class RestfulBookerAuthSteps {

    @Step("Creating booking authorisation")
    public ValidatableResponse creatingAuth (String username, String password){

        RestfulBookerAuthPojo restfulBookerAuthPojo = new RestfulBookerAuthPojo();

        RestfulBookerAuthPojo.setUsername(username);
        RestfulBookerAuthPojo.setPassword(password);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(restfulBookerAuthPojo)
                .post(EndPoints.BOOKING_AUTH)
                .then().log().all();
    }

}
