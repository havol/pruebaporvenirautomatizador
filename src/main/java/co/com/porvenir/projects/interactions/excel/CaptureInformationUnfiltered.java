package co.com.porvenir.projects.interactions.excel;

import co.com.porvenir.projects.models.enums.Constants;
import co.com.porvenir.projects.exceptions.IncorrectDataDrivenValues;
import co.com.porvenir.projects.models.web.ParameterFloristeria;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static co.com.porvenir.projects.models.web.ParameterFloristeria.COLUMN_PRODUCTS;
import static co.com.porvenir.projects.utils.Constants.HEADER_TITLE_EXCEL;
import static co.com.porvenir.projects.utils.Constants.INFORMATION_IN_EXCEL;
import static co.com.porvenir.projects.utils.Constants.PRODUCT_TABLE;

public class CaptureInformationUnfiltered implements Interaction {
    private static final Logger LOGGER = LoggerFactory.getLogger(CaptureInformationUnfiltered.class);
    static final String STRBASEPATH = System.getProperty("user.dir") + "\\src\\test\\resources\\data\\";

    @Override
    public <T extends Actor> void performAs(T actor) {
        ParameterFloristeria parameter = new ParameterFloristeria();
        List<Map<String, String>> paramDataList = new ArrayList<>();
        StringBuilder msg1 = new StringBuilder();

        try{
            Fillo filloConnection = new Fillo();
            Connection connection = filloConnection.getConnection(STRBASEPATH + parameter.getStrPath());
            Recordset recordSet = connection.executeQuery(ParameterFloristeria.SELECT_FROM_FLOWER);
            ParameterFloristeria dataProduct = null;
            recordSet.moveFirst();
            dataProduct = new ParameterFloristeria();
            LOGGER.info(HEADER_TITLE_EXCEL);
            for (int i = 0; i < recordSet.getCount(); i++) {
                dataProduct.setStrProductos(recordSet.getField(COLUMN_PRODUCTS));

                Map<String, String> productoMap = new HashMap<>();
                productoMap.put(PRODUCT_TABLE, dataProduct.getStrProductos());

                paramDataList.add(productoMap);

                String msg = " | " + COLUMN_PRODUCTS + " = " + dataProduct.getStrProductos() + " | ";
                LOGGER.info(msg);
                msg1.append('\n').append(i + 1).append(") ").append(msg);
                recordSet.next();
            }
            Serenity.recordReportData().withTitle(INFORMATION_IN_EXCEL).andContents(msg1.toString());
            actor.remember(Constants.INFORMATION_EXCEL.getValue(), paramDataList);

            recordSet.close();
            connection.close();
        } catch (FilloException e) {
            throw new IncorrectDataDrivenValues(IncorrectDataDrivenValues.MESSAGE_FAILED_LIST, e);
        }
    }

    public static CaptureInformationUnfiltered inExcel( ) {
        return Tasks.instrumented(CaptureInformationUnfiltered.class);
    }

}