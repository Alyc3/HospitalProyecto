package hospital.modelo.excepciones;

public class ModeloException extends Exception {

    private ErrorType errorType;

    public ModeloException(ErrorType errorType) {
        super();
        this.errorType = errorType;
    }

    public ModeloException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    public ModeloException(String message, Throwable causa, ErrorType errorType) {
        super(message, causa);
        this.errorType = errorType;
    }

    public ModeloException(Throwable causa, ErrorType errorType) {
        super(causa);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
