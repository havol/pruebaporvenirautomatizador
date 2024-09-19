package co.com.porvenir.projects.questions.web;

import co.com.porvenir.projects.models.enums.Constants;
import co.com.porvenir.projects.ui.HomeFlowerUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.OpenUrl;

import java.util.List;
import java.util.Map;

import static co.com.porvenir.projects.utils.Constants.PRODUCT_TABLE;
import static co.com.porvenir.projects.utils.Constants.URL_CAR_SHOP;
import static java.lang.Boolean.FALSE;

public class ValidateCartPage implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        boolean statusProductudList = FALSE;
        List<Map<String, String>> listProducts = actor.recall(Constants.INFORMATION_PRODUCTS.getValue());

        actor.attemptsTo(new OpenUrl(URL_CAR_SHOP));

        for (Map<String, String> product : listProducts) {
            String productName = product.get(PRODUCT_TABLE);
            statusProductudList = HomeFlowerUI.isProdutoPresentCartPage(productName);
        }
        return statusProductudList;
    }

    public static ValidateCartPage inProctsSelected() {
        return new ValidateCartPage();
    }

}