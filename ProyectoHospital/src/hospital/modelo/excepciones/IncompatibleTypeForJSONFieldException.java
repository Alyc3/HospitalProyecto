package hospital.modelo.excepciones;

/**
 * Esta excepcion se lanza cuando se intenta obtener el valor de un campo de un
 * objeto json y guardarlo en una variable con un tipo de dato incompatible
 *
 */
public class IncompatibleTypeForJSONFieldException extends Exception {

    private String fieldName;
    private String incompatibleType;

    public IncompatibleTypeForJSONFieldException(String fieldName, String incompatibleType) {
        super("field name (" + fieldName + "), incompatible type (" + incompatibleType + ")");
        this.fieldName = fieldName;
        this.incompatibleType = incompatibleType;
    }

    public IncompatibleTypeForJSONFieldException(Throwable cause, String fieldName, String incompatibleType) {
        super("field name (" + fieldName + "), incompatible type (" + incompatibleType + ")", cause);
        this.fieldName = fieldName;
        this.incompatibleType = incompatibleType;
    }

    /**
     * Método para retornar el nombre del campo al que se intento acceder
     *
     * @return
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Método para retornar el tipo de dato al que se intento convertir el campo
     * del objeto json
     *
     * @return
     */
    public String getIncompatibleType() {
        return incompatibleType;
    }
}
