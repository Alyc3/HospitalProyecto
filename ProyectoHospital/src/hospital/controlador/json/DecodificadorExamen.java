package hospital.controlador.json;

import hospital.controlador.json.DecodificadorEntiedad;
import hospital.modelo.ConsultaMedica;
import hospital.modelo.Examen;
import hospital.modelo.excepciones.IncompatibleTypeForJSONFieldException;
import org.json.simple.JSONObject;

public class DecodificadorExamen implements DecodificadorEntiedad<Examen> {

    /**
     * Método para convertir un JSONObject a un objeto de tipo Examen
     * @param object
     * @return
     * @throws IncompatibleTypeForJSONFieldException 
     */
    @Override
    public Examen decodificar(JSONObject object) throws IncompatibleTypeForJSONFieldException {
        Examen examen = new Examen();

        examen.setTipoExamen(LeectorArchivoJSON.getTipoExamen(object, "tipoExamen"));
        examen.setTiempoEntrega(LeectorArchivoJSON.getString(object, "tiempoEntrega"));

        ConsultaMedica consultaMedica = new ConsultaMedica();
        consultaMedica.setId(LeectorArchivoJSON.getInteger(object, "idConsultaMedica"));
        examen.setConsultaMedica(consultaMedica);

        return examen;
    }
}
