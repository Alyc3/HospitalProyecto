package hospital.codificacion.json;

import hospital.codificacion.json.DecodificadorEntiedad;
import hospital.modelo.ConsultaMedica;
import hospital.modelo.Medicina;
import hospital.utilidades.excepciones.IncompatibleTypeForJSONFieldException;
import org.json.simple.JSONObject;

public class DecodificadorMedicina implements DecodificadorEntiedad<Medicina> {

    /**
     * Método para convertir un JSONObjetc a un objeto de tipo Medicina
     * @param object
     * @return
     * @throws IncompatibleTypeForJSONFieldException 
     */
    @Override
    public Medicina decodificar(JSONObject object) throws IncompatibleTypeForJSONFieldException {
        Medicina medicina = new Medicina();

        medicina.setNombre(LeectorArchivoJSON.getString(object, "nombre"));
        medicina.setUnidad(LeectorArchivoJSON.getInteger(object, "unidad"));
        medicina.setPauta(LeectorArchivoJSON.getString(object, "pauta"));

        ConsultaMedica consultaMedica = new ConsultaMedica();
        consultaMedica.setId(LeectorArchivoJSON.getInteger(object, "idConsultaMedica"));
        medicina.setConsultaMedica(consultaMedica);

        return medicina;
    }
}
