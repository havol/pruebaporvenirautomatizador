package co.com.porvenir.projects.interactions;

import co.com.porvenir.projects.models.enums.Constants;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.MoveMouse;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static co.com.porvenir.projects.ui.HomeFlowerUI.BUTTON_ADD_TO_CART_DETAIL;
import static co.com.porvenir.projects.ui.HomeFlowerUI.BUTTON_CONTINUE_SHOPPING;
import static co.com.porvenir.projects.ui.HomeFlowerUI.BUTTON_LOCALIZE_ADD_TO_CART_OUT_PRODUCT;
import static co.com.porvenir.projects.ui.HomeFlowerUI.LINK_PAGINATION_NEXT_ARROW;
import static co.com.porvenir.projects.ui.HomeFlowerUI.LINK_PRODUCT_NAME_SELECT;
import static co.com.porvenir.projects.ui.HomeFlowerUI.LINK_PRODUCT_SELECT_GRID;
import static co.com.porvenir.projects.utils.Constants.PRODUCT_TABLE;
import static co.com.porvenir.projects.utils.Constants.TIME_SHORT;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class PaginationForEachProduct implements Interaction {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaginationForEachProduct.class);

    private final List<Map<String, String>> listProducts;
    private final String option;
    private final Boolean status;

    public PaginationForEachProduct(List<Map<String, String>> listProducts, String option, Boolean status) {
        this.listProducts = listProducts;
        this.option = option;
        this.status = status;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        for (Map<String, String> product : listProducts) {
            String productName = product.get(PRODUCT_TABLE);
            if (isProductPresent(actor, LINK_PRODUCT_NAME_SELECT.of(productName))) {
                actor.attemptsTo(
                        net.serenitybdd.screenplay.actions.Scroll.to(LINK_PRODUCT_NAME_SELECT.of(productName)),
                        WaitUntil.the(LINK_PRODUCT_NAME_SELECT.of(productName), isVisible())
                                .forNoMoreThan(TIME_SHORT).seconds(),
                        Scroll.numberOfTimes(-500)
                );
                LOGGER.info(" *** Encontró el eleneto  " + productName);
                procesingOption(option, actor, productName, status);

            } else
            {
                while (LINK_PAGINATION_NEXT_ARROW.isVisibleFor(actor)) {
                    LOGGER.info(" *** Entro aqui debe hacer scroll ");
                    actor.attemptsTo(
                            net.serenitybdd.screenplay.actions.Scroll.to(LINK_PAGINATION_NEXT_ARROW),
                            Scroll.numberOfTimes(-500),
                            WaitUntil.the(LINK_PAGINATION_NEXT_ARROW, isVisible())
                                    .forNoMoreThan(TIME_SHORT).seconds(),
                            Click.on(LINK_PAGINATION_NEXT_ARROW)
                    );
                    LOGGER.info(" *** hizo clic en siguiente. ");
                    if (isProductPresent(actor, LINK_PRODUCT_NAME_SELECT.of(productName))) {
                        actor.attemptsTo(
                                net.serenitybdd.screenplay.actions.Scroll.to(LINK_PRODUCT_NAME_SELECT.of(productName)),
                                Scroll.numberOfTimes(-500),
                                WaitUntil.the(LINK_PRODUCT_NAME_SELECT.of(productName), isVisible())
                                        .forNoMoreThan(TIME_SHORT).seconds()
                        );
                        LOGGER.info(" *** Encontró el eleneto  " + productName);
                        procesingOption(option, actor, productName, status);
                    }
                }
            }
        }
    }

    public static Performable paginationForEachProduct(List<Map<String, String>> listProducts, String option, Boolean status) {
        return instrumented(PaginationForEachProduct.class, listProducts, option, status);
    }

    public static Performable opcionA(List<Map<String, String>> listProducts, String option, Boolean status) {
        return instrumented(PaginationForEachProduct.class, listProducts, option, status);
    }

    private static boolean isProductPresent(Actor actor, Target productTarget) {
        return productTarget.isVisibleFor(actor);
    }

    private static boolean validateViewProdutInTheGrid() {
        return true;
    }

    private static void clicToAddToCartInProductudOut(Actor actor, String productName) {
        LOGGER.info(" *** Encontró el eleneto  " + productName);
        actor.attemptsTo(
                MoveMouse.to(BUTTON_LOCALIZE_ADD_TO_CART_OUT_PRODUCT.of(productName)),
                Scroll.numberOfTimes(300),
                AddProductToCart.forProduct(productName),
                WaitUntil.the(BUTTON_CONTINUE_SHOPPING, isVisible()),
                Click.on(BUTTON_CONTINUE_SHOPPING)
        );
    }

    private static void clicToAddToCartInProductudDeaitl(Actor actor, String productName) {
        String srtCategory = actor.recall(Constants.INFORMATION_CATEGORY.getValue());
        actor.attemptsTo(
                Click.on(LINK_PRODUCT_SELECT_GRID.of(productName)),
                WaitUntil.the(BUTTON_ADD_TO_CART_DETAIL, isVisible())
                        .forNoMoreThan(TIME_SHORT).seconds(),
                Click.on(BUTTON_ADD_TO_CART_DETAIL),
                ReturnToCategory.gridForSelected(srtCategory)
        );
    }

    private static void procesingOption(String option, Actor actor, String productName, Boolean status) {
        switch (option) {
            case "VALIDAR_PRODUCTOS":
                status = true;
                break;
            case "ACCION_ADD_TO_CART_DESDE_IMAGEN_PRODUCTO":
                clicToAddToCartInProductudOut(actor, productName);
                break;
            case "ACCION_ADD_TO_CART_DESDE_DETALLE_PRODUCTO":
                clicToAddToCartInProductudDeaitl(actor, productName);
                break;
            default:
                LOGGER.info(" NO HAY NADA QUE HACER EN LA PAGINACION...  ");
                break;
        }
    }

}