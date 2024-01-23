package com.restful.booker.restfulbooker;

import com.restful.booker.constants.EndPoints;
import com.restful.booker.model.RestfulBookerPostPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.Date;
import java.util.HashMap;

public class RestfulBookerPostSteps {

    @Step("Creating new Booking")
    public ValidatableResponse creatingBooking(String name, String lastName, int totalPrice, boolean depositPaid, Date checkin, Date checkout, String additionalneeds){

        RestfulBookerPostPojo restfulBookerPostPojo = new RestfulBookerPostPojo();

        restfulBookerPostPojo.setFirstName(name);
        restfulBookerPostPojo.setLastName(lastName);
        restfulBookerPostPojo.setTotalPrice(totalPrice);
        restfulBookerPostPojo.setDepositPaid(depositPaid);
        restfulBookerPostPojo.setCheckIn(checkin);
        restfulBookerPostPojo.setCheckOut(checkout);
        restfulBookerPostPojo.setAdditionalNeeds(additionalneeds);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(restfulBookerPostPojo)
                .post(EndPoints.CREATE_BOOKING)
                .then().log().all();
    }

    @Step("Getting the new booking id ")
    public HashMap<String, Object> getBookingId(int Id) {
        String s1 = "findAll{it.Id == '";
        String s2 = "'}.get(0)";

        return SerenityRest.given()
                .when()
                .get(EndPoints.UPDATE_BOOKING)
                .then().statusCode(200)
                .extract()
                .path(s1 + Id + s2);
    }

    @Step("updating new Booking")
    public ValidatableResponse updatingBooking( String name, String lastName, int totalPrice, boolean depositPaid, Date checkin, Date checkout, String additionalneeds, int bookingId) {

        RestfulBookerPostPojo restfulBookerPostPojo = new RestfulBookerPostPojo();

        restfulBookerPostPojo.setFirstName(name);
        restfulBookerPostPojo.setLastName(lastName);
        restfulBookerPostPojo.setTotalPrice(totalPrice);
        restfulBookerPostPojo.setDepositPaid(depositPaid);
        restfulBookerPostPojo.setCheckIn(checkin);
        restfulBookerPostPojo.setCheckOut(checkout);
        restfulBookerPostPojo.setAdditionalNeeds(additionalneeds);

        return SerenityRest.given().log().all()
                .pathParam("bookingId", bookingId)
                .contentType(ContentType.JSON)
                .when()
                .body(restfulBookerPostPojo)
                .put(EndPoints.UPDATE_BOOKING)
                .then().log().all();


    }

    @Step("Deleting booking id")
    public ValidatableResponse deleteBooking(int Id){
        return SerenityRest.given().log().all()
                .pathParam("bookingId", Id)
                .when()
                .delete(EndPoints.Get_DELETE_BOOKING)
                .then();
    }

    @Step("Getting booking info")
    public ValidatableResponse getBooking(int Id){
        return SerenityRest.given().log().all()
                .pathParam("bookingId", Id)
                .when()
                .get(EndPoints.Get_DELETE_BOOKING)
                .then();
    }

}
