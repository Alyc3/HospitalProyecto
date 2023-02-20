package hospital.controlador.json;

import hospital.modelo.ConsultaMedica;
import hospital.modelo.utilities.Conversores.Conversor;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

public class CodificadorConsultaMedica implements CodificadorEntidad<ConsultaMedica> {

    /**
     * Método para convertir un objeto de tipo de ConsultaMedica a un JSONObject
     * @param consultaMedica
     * @return 
     */
    @Override
    public JSONObject codificar(ConsultaMedica consultaMedica) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("id", consultaMedica.getId());
        map.put("fecha", Conversor.toString(consultaMedica.getFecha()));
        map.put("hora", Conversor.toString(consultaMedica.getHora()));
        map.put("estado", consultaMedica.getEstado());
        map.put("numConsultorio", consultaMedica.getNumConsultorio());

        if (consultaMedica.getHistorialMedico() == null) {
            map.put("idHistorialMedico", null);
        } else {
            map.put("idHistorialMedico", consultaMedica.getHistorialMedico().getId());
        }

        return new JSONObject(map);
    }
}
