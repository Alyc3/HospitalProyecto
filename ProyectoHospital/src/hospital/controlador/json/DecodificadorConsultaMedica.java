package hospital.controlador.json;

import hospital.controlador.json.DecodificadorEntiedad;
import hospital.modelo.ConsultaMedica;
import hospital.modelo.HistorialMedico;
import hospital.modelo.excepciones.IncompatibleTypeForJSONFieldException;
import java.time.LocalDate;
import java.time.LocalTime;
import org.json.simple.JSONObject;

public class DecodificadorConsultaMedica implements DecodificadorEntiedad<ConsultaMedica> {

    /**
     * Método para convertir un JSONObject a un objeto de tipo ConsultaMedica
     * @param object
     * @return
     * @throws IncompatibleTypeForJSONFieldException 
     */
    @Override
    public ConsultaMedica decodificar(JSONObject object) throws IncompatibleTypeForJSONFieldException {
        ConsultaMedica consultaMedica = new ConsultaMedica();

        consultaMedica.setId(LeectorArchivoJSON.getInteger(object, "id"));
        consultaMedica.setFecha(LeectorArchivoJSON.getLocalDate(object, "fecha"));
        consultaMedica.setHora(LeectorArchivoJSON.getLocalTime(object, "hora"));
        consultaMedica.setEstado(LeectorArchivoJSON.getString(object, "estado"));
        consultaMedica.setNumConsultorio(LeectorArchivoJSON.getInteger(object, "numConsultorio"));

        HistorialMedico historialMedico = new HistorialMedico();
        historialMedico.setId(LeectorArchivoJSON.getInteger(object, "idHistorialMedico"));
        consultaMedica.setHistorialMedico(historialMedico);

        return consultaMedica;
    }
}
