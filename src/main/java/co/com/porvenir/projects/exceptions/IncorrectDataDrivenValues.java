package co.com.porvenir.projects.exceptions;

import net.serenitybdd.core.exceptions.SerenityManagedException;

public class IncorrectDataDrivenValues extends SerenityManagedException {

    public static final String MESSAGE_FAILED_LIST = "No se pudo generar lista del recordSet en el archivo Excel.";
    public static final String MESSAGE_FAILED_SCROLL = " *** Error al desplazar la página hacia arriba: ";

    public IncorrectDataDrivenValues(String message, Throwable testErrorException) {
        super(message, testErrorException);
    }

}
