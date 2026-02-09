package com.thetestingacademy.tests.integration;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.modules.PayloadManager;
import com.thetestingacademy.pojos.Booking;
import com.thetestingacademy.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class testIntegration extends BaseTest {

    //Create a booking, create a token
    //Get booking
    //Update the booking
    //Delete the booking

    //share data between tests using ITestContext


    @Test(groups = "integration", priority = 1)
    @Owner("Paritosh")
    @Description("TC#INT1 - Step1. Verify that the booking can be created")
    public void testCreateBooking(ITestContext iTestContext){

        //set the token
        iTestContext.setAttribute("token" , getToken());
        response = requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL)
                .body(payloadManager.createBookingPayloadAsString())
                .when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        //Deserialization
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        //Assertion
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Jim");

        //set the booking id
        iTestContext.setAttribute("bookingId", bookingResponse.getBookingid());

    }

    @Test(groups = "integration", priority = 2)
    @Owner("Paritosh")
    @Description("TC#INT1 - Step2. Verify that the booking by id")
    public void testVerifyBookingById(ITestContext iTestContext){
        System.out.println(iTestContext.getAttribute("token"));
        String bookingId = iTestContext.getAttribute("bookingId").toString();
        String basepath = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingId;
        response = requestSpecification.basePath(basepath)
                .when().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());
        //Assertion
        assertThat(booking.getFirstname()).isNotNull();
        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("Jim");
        System.out.println(bookingId);
    }

    @Test(groups = "integration", priority = 3)
    @Owner("Paritosh")
    @Description("TC#INT1 - Step3. Verify updated booking by id")
    public void testUpdateBookingById(ITestContext iTestContext){
        String token = iTestContext.getAttribute("token").toString();
        String bookingId = iTestContext.getAttribute("bookingId").toString();
        String basepath = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingId;
        response = requestSpecification.basePath(basepath).cookie("token", token).body(payloadManager.fullUpdatePayloadAsString())
                .when().put();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        Booking booking = payloadManager.getResponseFromJSON(response.asString());
        //Assertion
        assertThat(booking.getFirstname()).isNotNull();
        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("Paritosh");
    }

    @Test(groups = "integration", priority = 4)
    @Owner("Paritosh")
    @Description("TC#INT1 - Step4. Delete the booking by id")
    public void testDeleteBookingById(ITestContext iTestContext){
        String token = iTestContext.getAttribute("token").toString();
        String bookingId = iTestContext.getAttribute("bookingId").toString();
        String basepath = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingId;

        response = requestSpecification.basePath(basepath).cookie("token", token).when().delete();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);

    }

}
