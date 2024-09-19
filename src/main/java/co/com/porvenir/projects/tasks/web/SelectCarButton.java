package co.com.porvenir.projects.tasks.web;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.porvenir.projects.ui.HomeFlowerUI.BOX_WIGET_CAR;
import static co.com.porvenir.projects.ui.HomeFlowerUI.BUTTON_CAR;
import static co.com.porvenir.projects.utils.Constants.TIME_SHORT;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SelectCarButton implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BUTTON_CAR),
                WaitUntil.the(BOX_WIGET_CAR, isVisible())
                        .forNoMoreThan(TIME_SHORT).seconds()
        );
    }

    public static SelectCarButton yourShoppingCart() {
        return Tasks.instrumented(SelectCarButton.class);
    }
}