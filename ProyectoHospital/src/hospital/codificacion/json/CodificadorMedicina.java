package hospital.codificacion.json;

import hospital.codificacion.json.CodificadorEntidad;
import hospital.modelo.Medicina;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

public class CodificadorMedicina implements CodificadorEntidad<Medicina> {

    /**
     * Método para convertir un objeto de tipo Medicina a un JSONObject
     * @param medicina
     * @return 
     */
    @Override
    public JSONObject codificar(Medicina medicina) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("nombre", medicina.getNombre());
        map.put("unidad", medicina.getUnidad());
        map.put("pauta", medicina.getPauta());

        if (medicina.getConsultaMedica() == null) {
            map.put("idConsultaMedica", null);
        } else {
            map.put("idConsultaMedica", medicina.getConsultaMedica().getId());
        }

        return new JSONObject(map);
    }
}
