package co.com.porvenir.projects.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.MoveMouse;

import static co.com.porvenir.projects.ui.HomeFlowerUI.ADD_TO_CART_BUTTON;
import static co.com.porvenir.projects.ui.HomeFlowerUI.PRODUCT_TITLE;


public class AddProductToCart implements Interaction {

    private final String productTitle;

    public AddProductToCart(String productTitle) {
        this.productTitle = productTitle;
    }

    public static AddProductToCart forProduct(String productTitle) {
        return Tasks.instrumented(AddProductToCart.class, productTitle);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                MoveMouse.to(PRODUCT_TITLE.of(productTitle)),
                Click.on(ADD_TO_CART_BUTTON.of(productTitle))
                );
    }

}