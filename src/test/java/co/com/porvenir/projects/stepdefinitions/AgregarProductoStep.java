package co.com.porvenir.projects.stepdefinitions;

import co.com.porvenir.projects.questions.web.DisplayProductsCategory;
import co.com.porvenir.projects.questions.web.ValidateCartPage;
import co.com.porvenir.projects.questions.web.ValidateProductListWidgetToCart;
import co.com.porvenir.projects.tasks.web.SelectCarButton;
import co.com.porvenir.projects.tasks.web.SelectCategory;
import co.com.porvenir.projects.tasks.web.SelectProductsAddToCart;
import co.com.porvenir.projects.tasks.web.SelectProductsAddToCartInDetail;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import org.hamcrest.Matchers;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class AgregarProductoStep {

    @Cuando("selecciono la categoría {string}")
    public void seleccionarCategoría(String category) {
        theActorInTheSpotlight().attemptsTo(
                SelectCategory.the(category)
        );
    }

    @Entonces("se visualizarán los productos para esta categoría")
    public void seVisualizaranProductosParaEstaCategoría(List<Map<String, String>> listProducts) {
        theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(
                        DisplayProductsCategory.isVisible(listProducts),
                        Matchers.equalTo(true)
                )
        );
    }

    @Cuando("selecciono cada producto en la categoría {string} doy click en Añadir al carrito")
    public void seleccionoCadaProductoEnLaCategoriaDoyClickEnAnadirAlCarrito(String category, List<Map<String, String>> listProducts) {
        theActorInTheSpotlight().attemptsTo(
                SelectCategory.the(category),
                SelectProductsAddToCart.thisCategory(listProducts)
        );
    }
    @Entonces("el producto se agregará al carrito de compras")
    public void elProductoSeAgregaráAlCarritoDeCompras() {
        theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(
                        ValidateCartPage.inProctsSelected(),
                        Matchers.equalTo(true)
                )
        );
    }

    @Cuando("he agregado los productos de la categoría {string} desde el detalle del produto")
    public void heAgregadoLosProductosDeLaCategoríaDesdeElDetalleDelProduto(String category, List<Map<String, String>> listProducts) {
        theActorInTheSpotlight().attemptsTo(
                SelectCategory.the(category),
                SelectProductsAddToCartInDetail.thisCategory(category,listProducts)
        );
    }
    @Cuando("doy clic en la opción “CARRO” en la parte superior derecha")
    public void doyClicEnLaOpciónCARROEnLaParteSuperiorDerecha() {
        theActorInTheSpotlight().attemptsTo(
                SelectCarButton.yourShoppingCart()
        );
    }
    @Entonces("se deberán visualizar los productos agregados")
    public void seDeberánVisualizarLosProductosAgregados() {
        theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(
                        ValidateProductListWidgetToCart.status(),
                        Matchers.equalTo(true)
                )
        );
    }

}