package api.reqres;

import api.reqres.colors.ColorsData;
import api.reqres.registration.Register;
import api.reqres.registration.SuccessReg;
import api.reqres.registration.UnSuccessReg;
import api.reqres.spec.Specifications;
import api.reqres.users.UserData;
import api.reqres.users.UserTime;
import api.reqres.users.UserTimeResponse;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresTest {
    private final static String URL = "https://reqres.in/";

    @Test
    public void checkAvatarAndIdTest(){
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecificationOK200());
        List<UserData> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.stream().forEach(element -> Assert.assertTrue(element.getAvatar().contains(element.getId().toString())));//произошел перебор по каждому пользователю и мы сравнили айди содержащимся в нашем аватаре
        Assert.assertTrue(users.stream().allMatch(element->element.getEmail().endsWith("@reqres.in")));
        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(element -> element.getId().toString()).collect(Collectors.toList());

        for(int i = 0; i<avatars.size();i++){
            Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
        }
    }
    @Test
    public void successRegTest(){
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecificationOK200());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register user = new Register("eve.holt@reqres.in", "pistol");
        SuccessReg successReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessReg.class);
        Assert.assertEquals(id, successReg.getId());
        Assert.assertEquals(token, successReg.getToken());

    }
    @Test
    public void unSuccessRegTest(){
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecificationNotFound400());
        String errorMessage = "Missing password";
        Register user = new Register("sydney@fife", "");
        UnSuccessReg unSuccessReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(UnSuccessReg.class);
        Assert.assertEquals(errorMessage, unSuccessReg.getError());
    }
    @Test
    public void sortedYearsTest(){
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecificationOK200());
        List<ColorsData> colors = given()
                .when()
                .get("/api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data",ColorsData.class);
        List<Integer> years = colors.stream().map(element -> element.getYear()).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(sortedYears, years);
        System.out.println("====================");
        System.out.println(years);
        System.out.println(sortedYears);

    }
    @Test
    public void deleteUser(){
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecificationUnique(204));
        given()
                .when()
                .delete("api/user/2")
                .then().log().all();

    }
    @Test
    public void timeTest(){
        Specifications.installSpecification(Specifications.requestSpecification(URL), Specifications.responseSpecificationUnique(200));
        UserTime user = new UserTime("morpheus", "zion resident");
        UserTimeResponse response = given()
                .body(user)
                .when()
                .put("api/users/2")
                .then()
                .log().all()
                .extract().as(UserTimeResponse.class);

        String regex = "(.{5})$";
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex, "");
        String responseTime = response.getUpdatedAt().replaceAll(regex, "");
        System.out.println(currentTime);
        System.out.println(responseTime);
        Assert.assertEquals(currentTime, responseTime);
        int i = 0;

    }

}
