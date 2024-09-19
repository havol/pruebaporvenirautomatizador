package co.com.porvenir.projects.models.web;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParameterFloristeria {

    private String strPath = "DataDriven.xlsx";
    private String strTable = "FLOWER";

    public static final String COLUMN_PRODUCTS = "productos";
    public static final String SELECT_FROM_FLOWER = "SELECT * FROM FLOWER";

    private String strProductos;

    public ParameterFloristeria() {
        super();
    }

}
