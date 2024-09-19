package co.com.porvenir.projects.stepdefinitions;

import co.com.porvenir.projects.questions.rest.IsUserCreated;
import co.com.porvenir.projects.questions.rest.IsUserDelete;
import co.com.porvenir.projects.questions.rest.ResponseValidationListUser;
import co.com.porvenir.projects.questions.rest.TheUserWasUpdated;
import co.com.porvenir.projects.tasks.rest.CaptureExistingUser;
import co.com.porvenir.projects.tasks.rest.CreateUser;
import co.com.porvenir.projects.tasks.rest.DeleteUser;
import co.com.porvenir.projects.tasks.rest.ListUser;
import co.com.porvenir.projects.tasks.rest.UpdateUser;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ValidarServiciosWebReqresStep {

    @Cuando("realizo una solicitud GET al endpoint de {string}")
    public void realizoUnaSolicitudGETAlEndpointDe(String string) {
        theActorInTheSpotlight().attemptsTo(
                ListUser.sendGetReqresList()
        );
    }

    @Entonces("la respuesta debe contener la lista de usuarios en la página {string} y el estado del endpoint Listar usuarios debe ser {string}.")
    public void laRespuestaDebeContenerLaListaDeUsuariosEnLaPágina(String page, String status) {
        theActorInTheSpotlight().should(
                seeThat(
                        ResponseValidationListUser.successfullyCode(page, status),
                        Matchers.equalTo(true)
                )
        );

    }

    @Cuando("realizo una solicitud POST al endpoint Crear Usuario con los datos del usuario")
    public void realizoUnaSolicitudPOSTAlEndpointConLosDatosDelUsuario() {
        theActorInTheSpotlight().attemptsTo(
                CreateUser.sendPostReqresUser()
        );
    }

    @Entonces("la respuesta debe indicar que el usuario fue creado exitosamente con un estado de {string} Y debe devolver el ID del usuario creado.")
    public void laRespuestaDebeIndicarQueElUsuarioFueCreadoExitosamenteConUnEstadoDe(String status) {
        theActorInTheSpotlight().should(
                seeThat(
                        IsUserCreated.successfullyCode(status),
                        Matchers.equalTo(true)
                )
        );
    }

    @Dado("que tengo un ID de usuario existente")
    public void queTengoUnIDDeUsuarioExistente() {
        theActorInTheSpotlight().attemptsTo(
                CaptureExistingUser.id()
        );
    }

    @Cuando("realizo una solicitud PUT al endpoint Actualizar Usuario con datos actualizados")
    public void realizoUnaSolicitudPUTAlEndpointConDatosActualizados() {
        theActorInTheSpotlight().attemptsTo(
                UpdateUser.theUserWithId()
        );
    }

    @Entonces("la respuesta debe indicar que el usuario fue actualizado exitosamente con un estado de {string} y debe devolver los datos actualizados.")
    public void laRespuestaDebeIndicarQueElUsuarioFueActualizadoExitosamenteConUnEstadoDe(String codeStatus) {
        theActorInTheSpotlight().should(
                seeThat(
                        TheUserWasUpdated.successfullyCode(codeStatus),
                        Matchers.equalTo(true)
                )
        );
    }

    @Cuando("realizo una solicitud DELETE al endpoint Eliminar Usuario")
    public void realizoUnaSolicitudDELETEAlEndpoint() {
        theActorInTheSpotlight().attemptsTo(
                DeleteUser.sendDeleteReqres()
        );
    }

    @Entonces("la respuesta debe devolver un estado de {string}, indicando que el usuario fue eliminado exitosamente.")
    public void laRespuestaDebeDevolverUnEstadoDeIndicandoQueElUsuarioFueEliminadoExitosamente(String status) {
        theActorInTheSpotlight().should(
                seeThat(
                        IsUserDelete.successfullyCode(status),
                        Matchers.equalTo(true)
                )
        );
    }

}