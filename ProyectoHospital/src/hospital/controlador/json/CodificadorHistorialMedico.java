package hospital.controlador.json;

import hospital.modelo.HistorialMedico;
import hospital.modelo.utilities.Conversores.Conversor;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

public class CodificadorHistorialMedico implements CodificadorEntidad<HistorialMedico> {

    /**
     * Método para convertir un objeto de tipo HistorialMedico a un JSONObject
     * @param historialMedico
     * @return 
     */
    @Override
    public JSONObject codificar(HistorialMedico historialMedico) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("id", historialMedico.getId());
        map.put("fechaNacimiento", historialMedico.getFechaNacimiento());
        map.put("peso", historialMedico.getPeso());
        map.put("estatura", historialMedico.getEstatura());
        map.put("antecedentes", historialMedico.getAntecedentes());
        map.put("alergias", historialMedico.getAlergias());
        map.put("tratamientosVigentes", historialMedico.getTratamientosVigentes());
        map.put("antecedentesFamiliares", historialMedico.getAntecedentesFamiliares());
        map.put("tipoSanguineo", Conversor.toString(historialMedico.getTipoSanguineo()));

        if (historialMedico.getPersona() == null) {
            map.put("idPersona", null);
        } else {
            map.put("idPersona", historialMedico.getPersona().getId());
        }

        return new JSONObject(map);
    }
}
