package hospital.modelo.excepciones;

public class CampoNovalidoException extends Exception {

    private String fieldName;
    private String invalidValue;

    public CampoNovalidoException(String fieldName, String invalidValue) {
        super("field name (" + fieldName + "), invalid value (" + invalidValue + ")");
        this.fieldName = fieldName;
        this.invalidValue = invalidValue;
    }

    public CampoNovalidoException(Throwable cause, String fieldName, String invalidValue) {
        super("field name (" + fieldName + "), invalid value (" + invalidValue + ")", cause);
        this.fieldName = fieldName;
        this.invalidValue = invalidValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getInvalidValue() {
        return invalidValue;
    }
}
