package co.com.porvenir.projects.interactions;

import co.com.porvenir.projects.exceptions.IncorrectDataDrivenValues;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import org.openqa.selenium.interactions.Actions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Scroll implements Interaction {

    private final int pixelsToScroll;

    public Scroll(int pixelsToScroll) {
        this.pixelsToScroll = pixelsToScroll;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            Actions actions = new Actions(BrowseTheWeb.as(OnStage.theActorInTheSpotlight()).getDriver());
            actions.scrollByAmount(0, pixelsToScroll).perform();
        } catch (Exception e) {
            throw new IncorrectDataDrivenValues(IncorrectDataDrivenValues.MESSAGE_FAILED_LIST, e);
        }
    }

    public static Performable numberOfTimes(int pixelsToScroll) {
        return instrumented(Scroll.class, pixelsToScroll);
    }

}