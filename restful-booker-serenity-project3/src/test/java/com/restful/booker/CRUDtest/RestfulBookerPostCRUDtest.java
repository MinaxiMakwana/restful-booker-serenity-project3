package com.restful.booker.CRUDtest;

import com.restful.booker.constants.EndPoints;
import com.restful.booker.restfulbooker.RestfulBookerPostSteps;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import org.junit.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;

public class RestfulBookerPostCRUDtest {

    static String firstName = "abc";
    static String lastName = "xyz";
    static int totalPrice = 200;
    static Boolean depositPaid = Boolean.valueOf("true");
    static Object bookingDates;
    static Date checkIn = 2014-10-23;
    static Date checkOut = 2014-10-23;
    static String additionalNeeds = "breakfast";


    static int bookingId;

    @Steps
    RestfulBookerPostSteps steps;

    @Title("This will crate new booking")
    @Test
    public void test001() {

        ValidatableResponse response = steps.creatingBooking (firstName, lastName, totalPrice, depositPaid, checkIn, checkOut, additionalNeeds);
        response.statusCode(201);

        bookingId = response.extract().path("id");

    }

    @Title("Verify if the new booking has been added to the system")
    @Test
    public void test002(){

        given()
                .pathParam("id", bookingId)
                .when()
                .get(EndPoints.UPDATE_BOOKING)
                .then().log().all()
                .statusCode(200);
    }


    @Title("Verify if the product was updated")
    @Test //updating record
    public void test003(){
        firstName = "Amee";
        ValidatableResponse  response = steps.updatingBooking(firstName, lastName, totalPrice, depositPaid, checkIn, checkOut, additionalNeeds, bookingId);
        response.statusCode(200);
    }

    @Title("Delete the product and verify if the store is deleted")
    @Test
    public void test004() {
        steps.deleteBooking(bookingId).statusCode(200);
        steps.getBooking(bookingId).statusCode(404);
    }


}
