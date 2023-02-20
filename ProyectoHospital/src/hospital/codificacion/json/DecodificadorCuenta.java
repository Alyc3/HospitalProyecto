package hospital.codificacion.json;

import hospital.modelo.Cuenta;
import hospital.modelo.Persona;
import hospital.utilidades.excepciones.IncompatibleTypeForJSONFieldException;
import org.json.simple.JSONObject;

public class DecodificadorCuenta implements DecodificadorEntiedad<Cuenta> {

    /**
     * Método para convertir un JSONObject a un objeto de tipo Cuenta
     * @param object
     * @return
     * @throws IncompatibleTypeForJSONFieldException 
     */
    @Override
    public Cuenta decodificar(JSONObject object) throws IncompatibleTypeForJSONFieldException {
        Cuenta cuenta = new Cuenta();

        cuenta.setId(LeectorArchivoJSON.getInteger(object, "id"));
        cuenta.setUsuario(LeectorArchivoJSON.getString(object, "usuario"));
        cuenta.setClave(LeectorArchivoJSON.getString(object, "clave"));

        Persona persona = new Persona();
        persona.setId(LeectorArchivoJSON.getInteger(object, "idPersona"));
        cuenta.setPersona(persona);

        return cuenta;
    }
}
