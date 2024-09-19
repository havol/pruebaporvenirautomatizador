package co.com.porvenir.projects.models.enums;

public enum Constants {

    INFORMATION_EXCEL("Informacion"),
    INFORMATION_PRODUCTS("InformacionPrdocut"),
    INFORMATION_CATEGORY("InformacionCategoria"),
    INFORMATION_USER("InformacionUSuario"),
    INFORMATION_USER_ID("UsuarioID"),
    INFORMATION_RESPONSE_REST("response");

    final String constant;

    Constants(String menssage) {
        this.constant = menssage;
    }

    public String getValue() {
        return constant;
    }

}