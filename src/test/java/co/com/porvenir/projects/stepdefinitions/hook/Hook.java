package co.com.porvenir.projects.stepdefinitions.hook;

import co.com.porvenir.projects.tasks.rest.CallApiReqres;
import co.com.porvenir.projects.tasks.rest.GenerateDataForUser;
import co.com.porvenir.projects.tasks.web.OpenWeb;
import co.com.porvenir.projects.tasks.ReadExcel;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import org.hamcrest.Matchers;

import static co.com.porvenir.projects.utils.Constants.ACTOR;
import static co.com.porvenir.projects.utils.Constants.TIME_SHORT;
import static co.com.porvenir.projects.utils.Constants.TITLE_WEBSITE_FLOWER;
import static co.com.porvenir.projects.utils.Constants.ENVIRONMENTS_API_REST_BASE_URL;
import static co.com.porvenir.projects.utils.Constants.WEB_URL_FLOWER_SHOP;
import static co.com.porvenir.projects.utils.Time.waiting;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class Hook {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("que {string} está en la página principal de la tienda de Floristeria Mundo Flor - Medellin")
    public void abreElSitioWebFloristeriaMundoFlor(String actor) {
        OnStage.theActorCalled(actor).attemptsTo(
                OpenWeb.browserURL(WEB_URL_FLOWER_SHOP),
                ReadExcel.withoutFilters()
        );
        waiting(TIME_SHORT);
        theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(
                        TheWebPage.title(),
                        Matchers.containsString(TITLE_WEBSITE_FLOWER)
                )
        );
    }

    @Given("que tengo los datos para crear un nuevo usuario")
    public void queTengoLosDatosParaCrearUnNuevoUsuario() {
        OnStage.theActorCalled(ACTOR).attemptsTo(
                GenerateDataForUser.withThisInformation()
        );
    }

    @Given("que accedo al servicio de ReqRes")
    public void queAccedoAlServicioDeReqRes() {
        OnStage.theActorCalled(ACTOR).attemptsTo(
                CallApiReqres.withWebUrl(ENVIRONMENTS_API_REST_BASE_URL)
        );
    }

}