package co.com.porvenir.projects.questions.web;

import co.com.porvenir.projects.models.enums.Constants;
import co.com.porvenir.projects.ui.HomeFlowerUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.Map;

import static co.com.porvenir.projects.utils.Constants.PRODUCT_TABLE;
import static java.lang.Boolean.FALSE;


public class ValidateProductListWidgetToCart implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        boolean statusProductudList = FALSE;
        List<Map<String, String>> listProducts = actor.recall(Constants.INFORMATION_PRODUCTS.getValue());

        for (Map<String, String> product : listProducts) {
            String productName = product.get(PRODUCT_TABLE);
            statusProductudList = HomeFlowerUI.isVisibleCarBoxWiget(productName);
        }
        return statusProductudList;
    }

    public static ValidateProductListWidgetToCart status() {
        return new ValidateProductListWidgetToCart();
    }

}