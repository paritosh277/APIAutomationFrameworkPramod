package com.thetestingacademy.modules;

import com.google.gson.Gson;
import com.thetestingacademy.pojos.*;

public class PayloadManager {
    Gson gson;
    public String createBookingPayloadAsString(){

        Booking booking = new Booking();
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");

        booking.setAdditionalneeds("Breakfast");
        booking.setDepositpaid(true);
        booking.setFirstname("Jim");
        booking.setLastname("Brown");
        booking.setTotalprice(111);
        booking.setBookingdates(bookingdates);
        gson = new Gson();
        return gson.toJson(booking);
    }

    public String fullUpdatePayloadAsString(){
        Booking booking = new Booking();
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");

        booking.setAdditionalneeds("Breakfast");
        booking.setDepositpaid(true);
        booking.setFirstname("Paritosh");
        booking.setLastname("Brown");
        booking.setTotalprice(111);
        booking.setBookingdates(bookingdates);
        gson = new Gson();
        return gson.toJson(booking);

    }

    public BookingResponse bookingResponseJava(String responseString){

        gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);

        return bookingResponse;
    }

    public String setAuthPayload(){
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson = new Gson();
        String jsonPayloadString = gson.toJson(auth);
        System.out.println(" Payload set to "+jsonPayloadString);
        return jsonPayloadString;
    }

    public String getTokenFromJSON(String tokenResponse){
        gson = new Gson();
        //Response (JSON) -> Object TokenResponse
        //Deserialization
        TokenResponse tokenResponse1 = gson.fromJson(tokenResponse, TokenResponse.class);
        return tokenResponse1.getToken();
    }

    public Booking getResponseFromJSON(String getResponse){
        gson = new Gson();
        //Response (JSON) -> Object TokenResponse
        //Deserialization
        Booking booking = gson.fromJson(getResponse, Booking.class);
        return booking;

    }
}
