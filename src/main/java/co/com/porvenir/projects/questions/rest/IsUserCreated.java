package co.com.porvenir.projects.questions.rest;

import co.com.porvenir.projects.models.enums.Constants;
import co.com.porvenir.projects.models.rest.SharedState;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.com.porvenir.projects.utils.Constants.ID;
import static co.com.porvenir.projects.utils.Constants.ID_USUARIO;
import static co.com.porvenir.projects.utils.Constants.STATUS_IS;
import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class IsUserCreated implements Question<Boolean> {

    private final String srtCode;

    public IsUserCreated(String srtCode) {
        this.srtCode = srtCode;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        Integer numberCode = lastResponse().statusCode();
        Serenity.recordReportData().withTitle(STATUS_IS).andContents(String.valueOf(numberCode));
        String idUser = SerenityRest.lastResponse().body().jsonPath().getString(ID);
        Serenity.recordReportData().withTitle(ID_USUARIO).andContents(String.valueOf(idUser));
        actor.remember(Constants.INFORMATION_USER_ID.getValue(), idUser);
        SharedState.setUserId(Integer.parseInt(idUser));
        return srtCode.equals(String.valueOf(numberCode));
    }

    public static IsUserCreated successfullyCode(String srtCode) {
        return new IsUserCreated(srtCode);
    }

}