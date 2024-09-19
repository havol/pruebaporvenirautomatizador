package co.com.porvenir.projects.questions.web;

import co.com.porvenir.projects.interactions.Scroll;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.List;
import java.util.Map;

import static co.com.porvenir.projects.ui.HomeFlowerUI.LINK_PAGINATION_NEXT_ARROW;
import static co.com.porvenir.projects.ui.HomeFlowerUI.LINK_PRODUCT_NAME_SELECT;
import static co.com.porvenir.projects.utils.Constants.PRODUCT_TABLE;
import static co.com.porvenir.projects.utils.Constants.TIME_SHORT;

public class DisplayProductsCategory implements Question<Boolean> {

    private final List<Map<String, String>> listProducts;

    public DisplayProductsCategory(List<Map<String, String>> listProducts) {
        this.listProducts = listProducts;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        boolean statusView = false;
        for (Map<String, String> product : listProducts) {
            String productName = product.get(PRODUCT_TABLE);
            if (isProductPresent(actor, LINK_PRODUCT_NAME_SELECT.of(productName))) {
                actor.attemptsTo(
                        net.serenitybdd.screenplay.actions.Scroll.to(LINK_PRODUCT_NAME_SELECT.of(productName)),
                        WaitUntil.the(LINK_PRODUCT_NAME_SELECT.of(productName), WebElementStateMatchers.isVisible())
                              .forNoMoreThan(TIME_SHORT).seconds(),
                        Scroll.numberOfTimes(-500)
                );
                statusView = true;
            } else
            {
                while (LINK_PAGINATION_NEXT_ARROW.isVisibleFor(actor)) {
                    actor.attemptsTo(
                            net.serenitybdd.screenplay.actions.Scroll.to(LINK_PAGINATION_NEXT_ARROW),
                            Scroll.numberOfTimes(-500),
                            WaitUntil.the(LINK_PAGINATION_NEXT_ARROW, WebElementStateMatchers.isVisible())
                                    .forNoMoreThan(TIME_SHORT).seconds(),
                            Click.on(LINK_PAGINATION_NEXT_ARROW)
                    );
                    if (isProductPresent(actor, LINK_PRODUCT_NAME_SELECT.of(productName))) {
                        actor.attemptsTo(
                                net.serenitybdd.screenplay.actions.Scroll.to(LINK_PRODUCT_NAME_SELECT.of(productName)),
                                Scroll.numberOfTimes(-500),
                                WaitUntil.the(LINK_PRODUCT_NAME_SELECT.of(productName), WebElementStateMatchers.isVisible())
                                        .forNoMoreThan(TIME_SHORT).seconds()
                        );
                        statusView = true;
                    }
                }
            }
        }
        return statusView;
    }

    public static DisplayProductsCategory isVisible(List<Map<String, String>> listProducts) {
        return new DisplayProductsCategory(listProducts);
    }

    private static boolean isProductPresent(Actor actor, Target productTarget) {
        return productTarget.isVisibleFor(actor);
    }

}