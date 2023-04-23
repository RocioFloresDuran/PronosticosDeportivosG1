package modelo;

public class CantidadDeCamposException extends Exception {
    public CantidadDeCamposException() {
    }

    @Override
    public String getMessage() {
        return "Error - cantidad de columnas incorrecto";
    }
}
