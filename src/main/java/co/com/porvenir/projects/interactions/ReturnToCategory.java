package co.com.porvenir.projects.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.porvenir.projects.ui.HomeFlowerUI.HEADING_ALL_CATEGORY;
import static co.com.porvenir.projects.ui.HomeFlowerUI.HEADING_TITLE_CATEGORY;
import static co.com.porvenir.projects.ui.HomeFlowerUI.LINK_ALL_CATEGORY;
import static co.com.porvenir.projects.ui.HomeFlowerUI.LINK_HOME;
import static co.com.porvenir.projects.utils.Constants.TIME_SHORT;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ReturnToCategory implements Interaction {

    private final String category;

    public ReturnToCategory(String category) {
        this.category = category;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(LINK_HOME),
                WaitUntil.the(HEADING_ALL_CATEGORY, isVisible())
                        .forNoMoreThan(TIME_SHORT).seconds(),
                Click.on(LINK_ALL_CATEGORY.of(category)),
                WaitUntil.the(HEADING_TITLE_CATEGORY.of(category), isVisible())
                        .forNoMoreThan(TIME_SHORT).seconds()
        );
    }

    public static Performable gridForSelected(String category) {
        return instrumented(ReturnToCategory.class, category);
    }

}