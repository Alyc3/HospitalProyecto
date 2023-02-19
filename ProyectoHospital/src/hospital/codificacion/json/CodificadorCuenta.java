package hospital.codificacion.json;

import hospital.modelo.Cuenta;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

public class CodificadorCuenta implements CodificadorEntidad<Cuenta> {

    /**
     * Método para convertir un objeto de tipo Cuenta a un JSONObject
     * @param cuenta
     * @return 
     */
    @Override
    public JSONObject codificar(Cuenta cuenta) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("id", cuenta.getId());
        map.put("usuario", cuenta.getUsuario());
        map.put("clave", cuenta.getClave());

        if (cuenta.getPersona() == null) {
            map.put("idPersona", null);
        } else {
            map.put("idPersona", cuenta.getPersona().getId());
        }

        return new JSONObject(map);
    }
}
