package co.com.porvenir.projects.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/web/agregar_productos_al_carrito.feature",
        glue = "co.com.porvenir.projects.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = {"pretty", "html:target/cucumber-reports"},
        tags = "" /*@AddToCart*/
)

public class AgregarProductosRunner {
}
