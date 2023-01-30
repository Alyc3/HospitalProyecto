package hospital.codificacion.json;

import hospital.codificacion.json.CodificadorEntidad;
import hospital.modelo.Examen;
import hospital.modelo.utilities.Conversores.Conversor;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

public class CodificadorExamen implements CodificadorEntidad<Examen> {

    /**
     * Método para convertir un objeto de tipo Examen a un JSONObject
     * @param examen
     * @return 
     */
    @Override
    public JSONObject codificar(Examen examen) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("tipoExamen", Conversor.toString(examen.getTipoExamen()));
        map.put("tiempoEntrega", examen.getTiempoEntrega());

        if (examen.getConsultaMedica() == null) {
            map.put("idConsultaMedica", null);
        } else {
            map.put("idConsultaMedica", examen.getConsultaMedica().getId());
        }

        return new JSONObject(map);
    }
}
