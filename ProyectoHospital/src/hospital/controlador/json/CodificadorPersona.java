package hospital.controlador.json;

import hospital.modelo.Medico;
import hospital.modelo.Persona;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

public class CodificadorPersona implements CodificadorEntidad<Persona> {

    @Override
    /**
     * Método codificar implementado para convertir un objeto de tipo Persona a
     * un JSONObject
     */
    public JSONObject codificar(Persona persona) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("id", persona.getId());
        map.put("cedula", persona.getCedula());
        map.put("nombre", persona.getNombre());
        map.put("apellido", persona.getApellido());
        map.put("direccion", persona.getDireccion());
        map.put("edad", persona.getEdad());
        map.put("genero", persona.getGenero());
        map.put("correo", persona.getCorreo());
        map.put("telefono", persona.getTelefono());

        if (persona.getRol() == null) {
            map.put("idRol", null);
        } else {
            map.put("idRol", persona.getRol().getId());
        }

        if (Medico.class.isInstance(persona)) {
            Medico medico = (Medico) persona;
            map.put("especialidad", medico.getEspecialidad());
        }

        return new JSONObject(map);
    }
}
