package com.thetestingacademy.tests.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;


public class testCreateBookingPOST extends BaseTest {


    @Test
    @Owner("Paritosh")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC#1 - Verify that the Booking can be created")
    public void testCreateBooking(){
        response = requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL)
                .body(payloadManager.createBookingPayloadAsString())
                .when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        //Deserialization
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());


        //AssertJ
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Jim");


        //TestNG Assertions
        assertActions.verifyStatusCode(response);
        assertActions.verifyStatusCode(response,200);



    }

}
