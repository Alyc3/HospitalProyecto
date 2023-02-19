package hospital.modelo.excepciones;

public class ModeloException extends Exception {

    private Exception e;

    /**
     * Retorna una excepción del Modelo
     * @param message 
     */
    public ModeloException(String message) {
        super(message);
    }
}
