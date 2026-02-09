package com.thetestingacademy.base;

import com.thetestingacademy.actions.AssertActions;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;

    public Response response;

    public ValidatableResponse validatableResponse;

    @BeforeTest
    public void setConfig(){
        System.out.println("Before test");
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();
        requestSpecification = RestAssured.given().baseUri(APIConstants.BASE_URL)
                .contentType(ContentType.JSON).log().all();

//        requestSpecification = new RequestSpecBuilder()
//                .setBaseUri(APIConstants.BASE_URL)
//                .addHeader("Content-Type", "application/json")
//                .build().log().all();
    }

    public String getToken(){
        String payload = payloadManager.setAuthPayload();
        requestSpecification = RestAssured.given().baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_URL).contentType(ContentType.JSON)
                .body(payload);

        response = requestSpecification.when().post();


        //Extracting of the token via deserialization

        String token = payloadManager.getTokenFromJSON(response.asString());
        return token;

    }


}
