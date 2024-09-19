package co.com.porvenir.projects.tasks.rest;

import co.com.porvenir.projects.models.rest.SharedState;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static co.com.porvenir.projects.models.enums.Constants.INFORMATION_RESPONSE_REST;
import static co.com.porvenir.projects.utils.Constants.ID;
import static co.com.porvenir.projects.utils.Constants.URL_REQRES;
import static co.com.porvenir.projects.utils.RestService.DELETE_USER;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteUser implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        int idUser = SharedState.getUserId();
        RestAssured.baseURI = URL_REQRES;
        Response response = RestAssured
                .given().log().all()
                    .pathParam(ID, idUser)
                .when()
                    .delete(DELETE_USER.toString())
                .then().log().all()
                .assertThat().statusCode(204).extract().response();

        actor.remember(INFORMATION_RESPONSE_REST.getValue(), response);
    }

    public static DeleteUser sendDeleteReqres(){
        return instrumented(DeleteUser.class);
    }

}