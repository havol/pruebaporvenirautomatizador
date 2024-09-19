package co.com.porvenir.projects.tasks.rest;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

import static co.com.porvenir.projects.utils.Constants.URL_REQRES;


public class CallApiReqres implements Task {

    private EnvironmentVariables environmentVariables;
    private final String webUrl;

    public CallApiReqres(String webUrl) {
        this.webUrl = webUrl;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String pathWebApi = environmentVariables.optionalProperty(webUrl)
                .orElse(URL_REQRES);
        actor.whoCan(CallAnApi.at(pathWebApi));

    }

    public static Performable withWebUrl(String webUrl) {
        return new CallApiReqres(webUrl);
    }

}