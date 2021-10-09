package fixtures.api;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CheckVariousAPICalls {

    static Response response;
    static RequestSpecification requestSpecification;

    public static RequestSpecification setupBaseURI(String baseURI){
        requestSpecification = given().baseUri(baseURI);
        return requestSpecification;
    }

    public static Response performGETCallOnSpecifiedEndPoint(String endpoint){
        response = requestSpecification
                .when()
                .get(endpoint);
        return response;
    }

    public static Boolean checkResponseContainsExpectedUserDetails(List<Map<String,String>> mapOfData){
        boolean isDetailsFound;
        isDetailsFound = response.path("data.id").toString().equals(mapOfData.get(0).get("id"))
                && response.path("data.email").toString().equals(mapOfData.get(0).get("email"))
                && response.path("data.first_name").toString().equals(mapOfData.get(0).get("first_name"))
                && response.path("data.last_name").toString().equals(mapOfData.get(0).get("last_name"));
        return isDetailsFound;
    }
}
