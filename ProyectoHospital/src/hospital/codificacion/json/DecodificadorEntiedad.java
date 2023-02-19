package hospital.codificacion.json;

import hospital.utilidades.excepciones.IncompatibleTypeForJSONFieldException;
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
