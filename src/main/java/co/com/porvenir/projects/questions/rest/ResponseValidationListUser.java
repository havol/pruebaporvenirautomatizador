package co.com.porvenir.projects.questions.rest;

import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;


import static co.com.porvenir.projects.models.enums.Constants.INFORMATION_RESPONSE_REST;
import static co.com.porvenir.projects.utils.Constants.BODY_RESPONSE;
import static co.com.porvenir.projects.utils.Constants.PAGE;
import static co.com.porvenir.projects.utils.Constants.STATUS_IS;

public class ResponseValidationListUser implements Question<Boolean> {

    private final String srtCode;
    private final String srtpage;

    public ResponseValidationListUser(String srtpage, String srtCode) {
        this.srtCode = srtCode;
        this.srtpage = srtpage;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        Response response = actor.recall(INFORMATION_RESPONSE_REST.getValue());
        if (response == null) {
            return false;
        }
        String responseBody = response.asString();
        Serenity.recordReportData().withTitle(BODY_RESPONSE).andContents(responseBody);
        int codeResp = response.statusCode();
        Serenity.recordReportData().withTitle(STATUS_IS).andContents(String.valueOf(codeResp));
        int srtParthPage = response.path(PAGE);
        Serenity.recordReportData().withTitle(PAGE).andContents(String.valueOf(srtParthPage));

        return String.valueOf(srtParthPage).equals(srtpage) && String.valueOf(codeResp).equals(srtCode);
    }

    public static ResponseValidationListUser successfullyCode(String srtPage, String srtCode) {
        return new ResponseValidationListUser(srtPage, srtCode);
    }

}
