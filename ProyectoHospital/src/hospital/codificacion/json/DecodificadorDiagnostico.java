package hospital.codificacion.json;

import hospital.modelo.ConsultaMedica;
import hospital.modelo.Diagnostico;
import hospital.utilidades.excepciones.IncompatibleTypeForJSONFieldException;
import org.json.simple.JSONObject;

public class DecodificadorDiagnostico implements DecodificadorEntiedad<Diagnostico> {

    /**
     * Método para convertir un JSONObject a un objeto de tipo Diagnostico
     * @param objeto
     * @return
     * @throws IncompatibleTypeForJSONFieldException 
     */
    @Override
    public Diagnostico decodificar(JSONObject objeto) throws IncompatibleTypeForJSONFieldException {
        Diagnostico diagnostico = new Diagnostico();

        diagnostico.setId(LeectorArchivoJSON.getInteger(objeto, "id"));
        diagnostico.setRecomendacion(LeectorArchivoJSON.getString(objeto, "recomendacion"));

        ConsultaMedica consultaMedica = new ConsultaMedica();
        consultaMedica.setId(LeectorArchivoJSON.getInteger(objeto, "idConsultaMedica"));
        diagnostico.setConsultaMedica(consultaMedica);

        return diagnostico;
    }
}
