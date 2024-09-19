package co.com.porvenir.projects.tasks.rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static co.com.porvenir.projects.models.enums.Constants.INFORMATION_RESPONSE_REST;
import static co.com.porvenir.projects.utils.Constants.URL_REQRES;
import static co.com.porvenir.projects.utils.RestService.LIST_USERS;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ListUser implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        RestAssured.baseURI = URL_REQRES;
        Response response = RestAssured
                .given().log().all()
                .when().get(LIST_USERS.toString())
                .then().log().all().assertThat().statusCode(200).extract().response();

        actor.remember(INFORMATION_RESPONSE_REST.getValue(), response);
    }

    public static ListUser sendGetReqresList(){
        return instrumented(ListUser.class);
    }

}