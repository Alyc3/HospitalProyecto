package hospital.controlador.json;

import hospital.controlador.json.CodificadorEntidad;
import hospital.modelo.Diagnostico;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

public class CodificadorDiagnostico implements CodificadorEntidad<Diagnostico> {

    /**
     * Método para convertir un objeto de tipo Diagnostico a un JSONObject
     * @param diagnostico
     * @return 
     */
    @Override
    public JSONObject codificar(Diagnostico diagnostico) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("id", diagnostico.getId());
        map.put("recomendacion", diagnostico.getRecomendacion());

        if (diagnostico.getConsultaMedica() == null) {
            map.put("idConsultaMedica", null);
        } else {
            map.put("idConsultaMedica", diagnostico.getConsultaMedica().getId());
        }

        return new JSONObject(map);
    }
}
