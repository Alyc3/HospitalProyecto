package hospital.controlador.json;

import hospital.controlador.json.CodificadorEntidad;
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
        Map<String, Object> mapa = new HashMap<String, Object>();

        mapa.put("tipoExamen", Conversor.toString(examen.getTipoExamen()));
        mapa.put("tiempoEntrega", examen.getTiempoEntrega());

        if (examen.getConsultaMedica() == null) {
            mapa.put("idConsultaMedica", null);
        } else {
            mapa.put("idConsultaMedica", examen.getConsultaMedica().getId());
        }

        return new JSONObject(mapa);
    }
}
