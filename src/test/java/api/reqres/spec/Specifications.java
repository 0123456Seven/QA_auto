package api.reqres.spec;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {
    public static RequestSpecification requestSpecification(String url){
        return new RequestSpecBuilder().setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }
    public static ResponseSpecification responseSpecificationOK200(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
    public static ResponseSpecification  responseSpecificationNotFound400(){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }
    public static void installSpecification(RequestSpecification requestSpecification, ResponseSpecification responseSpecification){
        RestAssured.requestSpecification  = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }
    public static ResponseSpecification responseSpecificationUnique(int code){
        return new ResponseSpecBuilder()
                .expectStatusCode(code)
                .build();
    }
    public static ResponseSpecification responseSpecification204(){
        return new ResponseSpecBuilder()
                .expectStatusCode(204)
                .build();
    }
}
