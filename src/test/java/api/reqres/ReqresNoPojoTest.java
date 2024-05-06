package api.reqres;

import api.reqres.spec.Specifications;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ReqresNoPojoTest {

    private final static String URL = "https://reqres.in/";
    @Test
    public void checkAvatarsNoPojoTest(){
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecificationOK200());
        Response response = given()
                .when()
                .get("api/users?page=2")
                .then()
                .log().all()
                .body("page", equalTo(2))
                .body("data.id", notNullValue())
                .body("data.email", notNullValue())
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        List<String> emails = jsonPath.get("data.email");
        List<Integer> ids = jsonPath.get("data.id");
        List<String> avatars = jsonPath.get("data.avatar");

        for(int i = 0; i<avatars.size();i++){
            Assert.assertTrue(avatars.get(i).contains(ids.get(i).toString()));
        }
        Assert.assertTrue(emails.stream().allMatch(element -> element.endsWith("@reqres.in")));

    }
    @Test
    public void successUserRegTestNoPojo(){
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecificationOK200());
        Map<String, String> user = new HashMap<>();
        user.put("email", "eve.holt@reqres.in");
        user.put("password", "pistol");
        Response response =
        given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("id");
        String token = jsonPath.get("token");
        Assert.assertEquals(4, id);//сначала ожидаемый, а потом актуальный
        Assert.assertEquals("QpwL5tke4Pnpja7X4", token);


    }
    @Test
    public void unSuccessRegTestNoPojo(){
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecificationNotFound400());
        String error = "Missing password";
        String email = "sydney@fife";
        Map<String, String> user = new HashMap<>();
        user.put("email", email);
        user.put("password", "");
        Response response =
                given()
                        .body(user)
                        .when()
                        .post("api/register")
                        .then().log().all()
                        .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String responseMessage = jsonPath.get("error");
        Assert.assertEquals(error, responseMessage);

    }
}
