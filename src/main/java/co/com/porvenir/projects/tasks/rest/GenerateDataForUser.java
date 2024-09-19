package co.com.porvenir.projects.tasks.rest;

import co.com.porvenir.projects.models.enums.Constants;
import co.com.porvenir.projects.models.rest.Users;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static co.com.porvenir.projects.utils.Constants.DATOS_DEL_USUARIO;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GenerateDataForUser implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateDataForUser.class);

    @Override
    public <T extends Actor> void performAs(T actor) {
        Users user = Users.generateRandomUser();
        actor.remember(Constants.INFORMATION_USER.getValue(), user);
        Serenity.recordReportData().withTitle(DATOS_DEL_USUARIO).andContents(String.valueOf(user));
        LOGGER.info(" *** JSON Users  " + user);
    }

    public static Performable withThisInformation() {
        return instrumented(GenerateDataForUser.class);
    }

}