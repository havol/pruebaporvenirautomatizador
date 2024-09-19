package co.com.porvenir.projects.tasks.rest;

import co.com.porvenir.projects.models.rest.SharedState;
import co.com.porvenir.projects.models.rest.Users;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static co.com.porvenir.projects.utils.Constants.ID;
import static co.com.porvenir.projects.utils.ConstantsHeaders.APPPLICATION_JSON;
import static co.com.porvenir.projects.utils.ConstantsHeaders.CONTENT_TYPE;
import static co.com.porvenir.projects.utils.RestService.UPDATE_USER;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UpdateUser implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        int idUser = SharedState.getUserId();
        Users user = Users.generateRandomUser();
        actor.attemptsTo(
                Put.to(UPDATE_USER.toString())
                        .with(request -> request.header(CONTENT_TYPE, APPPLICATION_JSON)
                                .pathParam(ID, idUser)
                                        .body(user)
                        )
        );
    }

    public static UpdateUser theUserWithId() {
        return instrumented(UpdateUser.class);
    }

}