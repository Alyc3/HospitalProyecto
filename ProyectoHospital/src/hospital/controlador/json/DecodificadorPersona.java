package hospital.controlador.json;

import hospital.modelo.Medico;
import hospital.modelo.Persona;
import hospital.modelo.Rol;
import hospital.modelo.excepciones.IncompatibleTypeForJSONFieldException;
import hospital.modelo.global.VariablesGlobales;
import org.json.simple.JSONObject;

public class DecodificadorPersona implements DecodificadorEntiedad<Persona> {

    private Rol rolPaciente = VariablesGlobales.ROL_PACIENTE;
    private Rol rolMedico = VariablesGlobales.ROL_MEDICO;

    /**
     * Método de decodificacion de Persona, para convertir un JSONObject a un
     * objeto de tipo Persona
     *
     * @param object
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    @Override
    public Persona decodificar(JSONObject object) throws IncompatibleTypeForJSONFieldException {
        Persona persona;

        Integer idRol = LeectorArchivoJSON.getInteger(object, "idRol");
        Rol rol = new Rol(idRol, null);
        if (rol.getId() == rolMedico.getId()) {
            persona = new Medico();
            persona.setRol(VariablesGlobales.ROL_MEDICO);
        } else {
            persona = new Persona();
            persona.setRol(VariablesGlobales.ROL_PACIENTE);
        }

        persona.setId(LeectorArchivoJSON.getInteger(object, "id"));
        persona.setCedula(LeectorArchivoJSON.getString(object, "cedula"));
        persona.setNombre(LeectorArchivoJSON.getString(object, "nombre"));
        persona.setApellido(LeectorArchivoJSON.getString(object, "apellido"));
        persona.setDireccion(LeectorArchivoJSON.getString(object, "direccion"));
        persona.setEdad(LeectorArchivoJSON.getInteger(object, "edad"));
        persona.setGenero(LeectorArchivoJSON.getString(object, "genero"));
        persona.setCorreo(LeectorArchivoJSON.getString(object, "correo"));
        persona.setTelefono(LeectorArchivoJSON.getString(object, "telefono"));

        if (persona.getRol().equals(VariablesGlobales.ROL_MEDICO)) {
            ((Medico) persona).setEspecialidad(LeectorArchivoJSON.getString(object, "especialidad"));
        }

        return persona;
    }
}
