package hospital.controlador.json;

import hospital.modelo.HistorialMedico;
import hospital.modelo.Persona;
import hospital.modelo.excepciones.IncompatibleTypeForJSONFieldException;
import org.json.simple.JSONObject;

public class DecodificadorHistorialMedico implements DecodificadorEntiedad<HistorialMedico> {

    /**
     * Método para convertir un JSONObject a un objeto de tipo HistorialMedico
     * @param object
     * @return
     * @throws IncompatibleTypeForJSONFieldException 
     */
    @Override
    public HistorialMedico decodificar(JSONObject object) throws IncompatibleTypeForJSONFieldException {
        HistorialMedico historialMedico = new HistorialMedico();

        historialMedico.setId(LeectorArchivoJSON.getInteger(object, "id"));
        historialMedico.setFechaNacimiento(LeectorArchivoJSON.getString(object, "fechaNacimiento"));
        historialMedico.setPeso(LeectorArchivoJSON.getFloat(object, "peso"));
        historialMedico.setEstatura(LeectorArchivoJSON.getFloat(object, "estatura"));
        historialMedico.setAntecedentes(LeectorArchivoJSON.getString(object, "antecedentes"));
        historialMedico.setAlergias(LeectorArchivoJSON.getString(object, "alergias"));
        historialMedico.setTratamientosVigentes(LeectorArchivoJSON.getString(object, "tratamientosVigentes"));
        historialMedico.setAntecedentesFamiliares(LeectorArchivoJSON.getString(object, "antecedentesFamiliares"));
        historialMedico.setTipoSanguineo(LeectorArchivoJSON.getTipoSanguineo(object, "tipoSanguineo"));

        Persona persona = new Persona();
        persona.setId(LeectorArchivoJSON.getInteger(object, "idPersona"));
        historialMedico.setPersona(persona);

        return historialMedico;
    }
}
