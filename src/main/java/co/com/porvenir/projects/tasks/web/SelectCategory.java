package co.com.porvenir.projects.tasks.web;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.porvenir.projects.ui.HomeFlowerUI.HEADING_ALL_CATEGORY;
import static co.com.porvenir.projects.ui.HomeFlowerUI.HEADING_TITLE_CATEGORY;
import static co.com.porvenir.projects.ui.HomeFlowerUI.LINK_ALL_CATEGORY;
import static co.com.porvenir.projects.utils.Constants.TIME_SHORT;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;


public class SelectCategory implements Task {

    private final String category;

    public SelectCategory(String category) {
        this.category = category;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(HEADING_ALL_CATEGORY, isVisible())
                        .forNoMoreThan(TIME_SHORT).seconds(),
                Click.on(LINK_ALL_CATEGORY.of(category)),
                WaitUntil.the(HEADING_TITLE_CATEGORY.of(category), isVisible())
                        .forNoMoreThan(TIME_SHORT).seconds()
        );
    }

    public static SelectCategory the(String category) {
        return Tasks.instrumented(SelectCategory.class, category);
    }

}