package co.com.porvenir.projects.tasks.web;

import co.com.porvenir.projects.models.enums.Constants;
import co.com.porvenir.projects.interactions.ReturnToCategory;
import co.com.porvenir.projects.interactions.Scroll;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.List;
import java.util.Map;

import static co.com.porvenir.projects.ui.HomeFlowerUI.BUTTON_ADD_TO_CART_DETAIL;
import static co.com.porvenir.projects.ui.HomeFlowerUI.LINK_PAGINATION_NEXT_ARROW;
import static co.com.porvenir.projects.ui.HomeFlowerUI.LINK_PRODUCT_NAME_SELECT;
import static co.com.porvenir.projects.ui.HomeFlowerUI.LINK_PRODUCT_SELECT_GRID;
import static co.com.porvenir.projects.utils.Constants.PRODUCT_TABLE;
import static co.com.porvenir.projects.utils.Constants.TIME_SHORT;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;


public class SelectProductsAddToCartInDetail implements Task {

    private final List<Map<String, String>> listProducts;
    private final String category;

    public SelectProductsAddToCartInDetail(String category, List<Map<String, String>> listProducts) {
        this.category = category;
        this.listProducts = listProducts;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.remember(Constants.INFORMATION_PRODUCTS.getValue(), listProducts);
        actor.remember(Constants.INFORMATION_CATEGORY.getValue(), category);
        for (Map<String, String> product : listProducts) {
            String productName = product.get(PRODUCT_TABLE);
            if (isProductPresent(actor, LINK_PRODUCT_NAME_SELECT.of(productName))) {
                actor.attemptsTo(
                        net.serenitybdd.screenplay.actions.Scroll.to(LINK_PRODUCT_NAME_SELECT.of(productName)),
                        WaitUntil.the(LINK_PRODUCT_NAME_SELECT.of(productName), isVisible())
                                .forNoMoreThan(TIME_SHORT).seconds(),
                        Scroll.numberOfTimes(-500)
                );
                actor.attemptsTo(
                        Click.on(LINK_PRODUCT_SELECT_GRID.of(productName)),
                        WaitUntil.the(BUTTON_ADD_TO_CART_DETAIL, isVisible())
                                .forNoMoreThan(TIME_SHORT).seconds(),
                        Click.on(BUTTON_ADD_TO_CART_DETAIL),
                        ReturnToCategory.gridForSelected(category)
                );
            } else
            {
                while (LINK_PAGINATION_NEXT_ARROW.isVisibleFor(actor)) {
                    actor.attemptsTo(
                            net.serenitybdd.screenplay.actions.Scroll.to(LINK_PAGINATION_NEXT_ARROW),
                            Scroll.numberOfTimes(-500),
                            WaitUntil.the(LINK_PAGINATION_NEXT_ARROW, isVisible())
                                    .forNoMoreThan(TIME_SHORT).seconds(),
                            Click.on(LINK_PAGINATION_NEXT_ARROW)
                    );
                    if (isProductPresent(actor, LINK_PRODUCT_NAME_SELECT.of(productName))) {
                        actor.attemptsTo(
                                net.serenitybdd.screenplay.actions.Scroll.to(LINK_PRODUCT_NAME_SELECT.of(productName)),
                                Scroll.numberOfTimes(-500),
                                WaitUntil.the(LINK_PRODUCT_NAME_SELECT.of(productName), isVisible())
                                        .forNoMoreThan(TIME_SHORT).seconds()
                        );
                        actor.attemptsTo(
                                Click.on(LINK_PRODUCT_SELECT_GRID.of(productName)),
                                WaitUntil.the(BUTTON_ADD_TO_CART_DETAIL, isVisible())
                                        .forNoMoreThan(TIME_SHORT).seconds(),
                                Click.on(BUTTON_ADD_TO_CART_DETAIL),
                                ReturnToCategory.gridForSelected(category)
                        );
                    }
                }
            }
        }
    }

    public static SelectProductsAddToCartInDetail thisCategory(String category, List<Map<String, String>> listProducts) {
        return Tasks.instrumented(SelectProductsAddToCartInDetail.class, category, listProducts);
    }

    private static boolean isProductPresent(Actor actor, Target productTarget) {
        return productTarget.isVisibleFor(actor);
    }

}