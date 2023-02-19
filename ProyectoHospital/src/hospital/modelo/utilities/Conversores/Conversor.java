package hospital.modelo.utilities.Conversores;

public class Conversor {

    /**
     * Método toString para cualquier objeto
     * @param object
     * @return 
     */
    public static String toString(Object object) {
        if (object == null) {
            return null;
        }
        return object.toString();
    }
}
