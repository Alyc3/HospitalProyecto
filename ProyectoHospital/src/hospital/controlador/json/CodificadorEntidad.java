package hospital.controlador.json;

import org.json.simple.JSONObject;

public interface CodificadorEntidad<T> {

    /**
     * Método que convierte un objeto a un JSONObject
     * Si la entidad contiene a más entidades, se insertan únicamente los 
     * identificadores de esas entidades.
     * @param entity
     * @return 
     */
    public JSONObject codificar(T entity);
}
