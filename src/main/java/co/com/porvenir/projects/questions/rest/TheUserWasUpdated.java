package co.com.porvenir.projects.questions.rest;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.com.porvenir.projects.utils.Constants.STATUS_IS;
import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class TheUserWasUpdated implements Question<Boolean> {

    private final String srtCode;

    public TheUserWasUpdated(String srtCode) {
        this.srtCode = srtCode;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        Integer numberCode = lastResponse().statusCode();
        Serenity.recordReportData().withTitle(STATUS_IS).andContents(String.valueOf(numberCode));
        return  srtCode.equals(String.valueOf(numberCode));
    }

    public static TheUserWasUpdated successfullyCode(String srtCode) {
        return new TheUserWasUpdated(srtCode);
    }

}