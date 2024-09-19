package co.com.porvenir.projects.ui;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomeFlowerUI extends PageObject {

    public static final Target HEADING_ALL_CATEGORY = Target.the("Tirulo Todas las Categorias")
            .locatedBy("//h2[normalize-space()='Todas las categorías']");

    public static final Target LINK_ALL_CATEGORY = Target.the("Enlace de la categoria {0}")
            .locatedBy("//a[normalize-space()='{0}']");

    public static final Target HEADING_TITLE_CATEGORY = Target.the("Titulo de Categoria {0}")
            .locatedBy("//h1[normalize-space()='{0}']");

    //Decidi no crear otra clase UI de Agradecimiento ya que es dinamico con el nombre del producto a loalizar
    public static final Target LINK_PRODUCT_NAME_SELECT = Target.the("producto {0}")
            .locatedBy("//h3[@class='name']//a[contains(text(),'{0}')]");

    public static final Target LINK_PAGINATION_NEXT_ARROW = Target.the("ir a paginación siguiente")
            .located(By.cssSelector(".next.page-numbers"));

    public static final Target BUTTON_LOCALIZE_ADD_TO_CART_OUT_PRODUCT = Target.the("Localiza Añadir Carrito en {0}")
            .locatedBy("//span[contains(@data-gtm4wp_product_name,'{0}')]//ancestor::div[@class='product-block grid']//div[contains(@class,'add-cart tbay-tooltip')]//span");

    public static final Target BUTTON_TOOLTIP_ADD_TO_CART_OUT_PRODUCT = Target.the("Click en Añadir Carrito")
            .locatedBy("//a[contains(text(),'Añadir al carrito')]//ancestor::div[@class='product-block grid']//div[contains(@class,'tooltip fade top in')]");

    public static void clickAddToCartProdutOut() {
        WebDriver driver = BrowseTheWeb.as(OnStage.theActorInTheSpotlight()).getDriver();
        WebElement addToart = driver.findElement(By.xpath("//a[contains(text(), 'Añadir al carrito')]//ancestor::div[@class='product-block grid']//div[contains(@class,'tooltip fade top in')]"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(addToart));
        try {
            addToart.click();
        } catch (ElementClickInterceptedException e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", addToart);
        }
    }

    public static void clickAddToCartProdutOutO() {
        WebDriver driver = BrowseTheWeb.as(OnStage.theActorInTheSpotlight()).getDriver();
        WebElement addToart = driver.findElement(By.xpath("//a[contains(text(), 'Añadir al carrito')]//ancestor::div[@class='product-block grid']//div[contains(@class,'tooltip fade top in')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", addToart);
        addToart.click();
    }

    public static final Target PRODUCT_TITLE = Target.the("Product title")
            .locatedBy("//a[@title='{0}']");

    public static final Target ADD_TO_CART_BUTTON = Target.the("Añadir al carrito button")
            .locatedBy("//a[@title='{0}']/following::a[contains(@class, 'add_to_cart_button')]");

    public static final Target BUTTON_CONTINUE_SHOPPING = Target.the("Click en Seguir comprando")
            .located(By.xpath("//a[normalize-space()='Seguir comprando']"));


    public static final Target LINK_HOME = Target.the("ir a Inicio")
            .located(By.xpath("//ul[@id='primary-menu']//a[normalize-space()='Inicio']"));

    public static final Target LIST_CAR_PRODUCT = Target.the("Localiza Carrito en {0}")
            .locatedBy("//td[@class='product-name']//a[contains(text(),'{0}')]");

    public static boolean isProdutoPresentCartPage(String productName) {
        String xpath = String.format("//td[@class='product-name']//a[contains(text(),'%s')]", productName);
        return !BrowseTheWeb.as(OnStage.theActorInTheSpotlight()).getDriver().findElements(By.xpath(xpath)).isEmpty();
    }

    public static final Target LINK_PRODUCT_SELECT_GRID = Target.the("Enlace del Produto {0}")
            .locatedBy("//h3[@class='name']//a[contains(text(),'{0}')]");

    public static final Target BUTTON_ADD_TO_CART_DETAIL = Target.the("Click en Boton añadir al carrito")
            .located(By.xpath("//button[normalize-space()='Añadir al carrito']"));

    public static final Target BUTTON_CAR = Target.the("Click en Boton CARRO")
            .located(By.xpath("//div[@class='tbay-topcart']//div[@id='cart']"));

    public static final Target BOX_WIGET_CAR = Target.the("Cuadro Lista para Ver Mi CARRO")
            .located(By.xpath("//div[@id='cart']//ul[@class='cart_list product_list_widget ']"));

    public static boolean isVisibleCarBoxWiget(String productName) {
        String xpath = String.format("//div[@id='cart']//a[@class='product-name'][normalize-space()='%s']", productName);
        return !BrowseTheWeb.as(OnStage.theActorInTheSpotlight()).getDriver().findElements(By.xpath(xpath)).isEmpty();
    }

}