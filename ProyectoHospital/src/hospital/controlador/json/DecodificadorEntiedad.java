package hospital.controlador.json;

import hospital.modelo.excepciones.IncompatibleTypeForJSONFieldException;
import org.json.simple.JSONObject;

public interface DecodificadorEntiedad<T> {

    /**
     * Convierte un JSONObject en un objeto
     * Puede lanzar excepciones de tipo IncompatibleTypeForJsonField
     * @param object
     * @return
     * @throws IncompatibleTypeForJSONFieldException 
     */
    public T decodificar(JSONObject object) throws IncompatibleTypeForJSONFieldException;
}
