package co.com.porvenir.projects.tasks.rest;

import co.com.porvenir.projects.models.enums.Constants;
import co.com.porvenir.projects.models.rest.SharedState;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static co.com.porvenir.projects.utils.Constants.ID_USUARIO;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CaptureExistingUser implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaptureExistingUser.class);

    @Override
    public <T extends Actor> void performAs(T actor) {
        int idUser = SharedState.getUserId();
        LOGGER.info(" El ID Usuario es : " +  idUser);
        Serenity.recordReportData().withTitle(ID_USUARIO).andContents(String.valueOf(idUser));

    }

    public static CaptureExistingUser id(){
        return instrumented(CaptureExistingUser.class);
    }

}