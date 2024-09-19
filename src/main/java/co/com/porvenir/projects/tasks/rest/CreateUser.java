package co.com.porvenir.projects.tasks.rest;

import co.com.porvenir.projects.models.enums.Constants;
import co.com.porvenir.projects.models.rest.Users;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static co.com.porvenir.projects.utils.ConstantsHeaders.APPPLICATION_JSON;
import static co.com.porvenir.projects.utils.ConstantsHeaders.CONTENT_TYPE;
import static co.com.porvenir.projects.utils.RestService.CREATE_USER;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateUser implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        Users user = actor.recall(Constants.INFORMATION_USER.getValue());
        actor.attemptsTo(
                Post.to(CREATE_USER.toString())
                        .with(request -> request.header(CONTENT_TYPE, APPPLICATION_JSON)
                                .body(user)
                                /*  .body(ConverterJson.getJson(user))
                                La funcion getJson es para implementar la estructura del JSON siempre cuando no se utilice
                                @Override, lo que significa que está reemplazando la implementación predeterminada
                                del metodo toString() heredado de la clase base.
                                En este caso la Clase Users si lo está implementando ya que es corto.
                                 */
                        )
        );
    }

    public static CreateUser sendPostReqresUser(){
        return instrumented(CreateUser.class);
    }

}