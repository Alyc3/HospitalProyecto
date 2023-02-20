package hospital.controlador.json;

import hospital.modelo.ConsultaMedica;
import hospital.modelo.Cuenta;
import hospital.modelo.Diagnostico;
import hospital.modelo.Examen;
import hospital.modelo.HistorialMedico;
import hospital.modelo.Persona;
import hospital.modelo.excepciones.IncompatibleTypeForJSONFieldException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Contiene métodos estáticos para serializar y deserializar entidades
 *
 */
public class CodificacionEntidad {

    /**
     * Método para convertir una lista de objetos a un JSONArray.
     *
     * @param <T>
     * @param <E>
     * @param entidades
     * @param codificador
     * @return
     */
    private static <T, E extends CodificadorEntidad<T>> JSONArray codificarEntidades(T[] entidades, E codificador) {
        String jsonstr = "[";
        for (int i = 0; i < entidades.length; i++) {
            JSONObject object = codificador.codificar(entidades[i]);
            String str = object.toString();
            jsonstr += str;
            if (i < entidades.length - 1) {
                jsonstr += ",";
            }
        }
        jsonstr += "]";

        JSONParser parser = new JSONParser();
        JSONArray array = new JSONArray();
        try {
            array = (JSONArray) parser.parse(jsonstr);
        } catch (ParseException e) {
            //Esta Exception no debería ocurrir porque se parsea una cadena json válida
        }
        return array;
    }

    /**
     * Método para convertir un JSONArray a una lista de objetos
     *
     * @param <T>
     * @param <D>
     * @param arreglo
     * @param decodificador
     * @param salida
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    private static <T, D extends DecodificadorEntiedad<T>> T[] decodificarEntidades(JSONArray arreglo, D decodificador, T[] salida) throws IncompatibleTypeForJSONFieldException {
        for (int i = 0; i < arreglo.size() && i < salida.length; i++) {
            JSONObject object = (JSONObject) arreglo.get(i);
            salida[i] = decodificador.decodificar(object);
        }
        return salida;
    }

    /**
     * Método para codificar una persona
     *
     * @param persona
     * @return
     */
    public static JSONObject codificarPersona(Persona persona) {
        return (new CodificadorPersona()).codificar(persona);
    }

    /**
     * Método para codificar varias personas
     *
     * @param personas
     * @return
     */
    public static JSONArray codificarPersonas(Persona[] personas) {
        return codificarEntidades(personas, new CodificadorPersona());
    }

    /**
     * Método para decodificar una persona
     *
     * @param object
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    public static Persona decodificarPersona(JSONObject object) throws IncompatibleTypeForJSONFieldException {
        return (new DecodificadorPersona()).decodificar(object);
    }

    /**
     * Método para decodificar varias personas
     *
     * @param objects
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    public static Persona[] decodificarPersonas(JSONArray objects) throws IncompatibleTypeForJSONFieldException {
        return decodificarEntidades(objects, new DecodificadorPersona(), new Persona[objects.size()]);
    }

    /**
     * Método para codificar una cuenta
     *
     * @param cuenta
     * @return
     */
    public static JSONObject codificarCuenta(Cuenta cuenta) {
        return (new CodificadorCuenta()).codificar(cuenta);
    }

    /**
     * Método para codificar varias cuentas
     *
     * @param cuentas
     * @return
     */
    public static JSONArray codificarCuentas(Cuenta[] cuentas) {
        return codificarEntidades(cuentas, new CodificadorCuenta());
    }

    /**
     * Método para decodificar una cuenta
     *
     * @param object
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    public static Cuenta decodificarCuenta(JSONObject object) throws IncompatibleTypeForJSONFieldException {
        return (new DecodificadorCuenta()).decodificar(object);
    }

    /**
     * Método para decodificar varias cuentas
     *
     * @param objects
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    public static Cuenta[] decodificarCuentas(JSONArray objects) throws IncompatibleTypeForJSONFieldException {
        return decodificarEntidades(objects, new DecodificadorCuenta(), new Cuenta[objects.size()]);
    }

    /**
     * Método para codificar un historial medico
     *
     * @param historialMedico
     * @return
     */
    public static JSONObject codificarHistorialMedico(HistorialMedico historialMedico) {
        return (new CodificadorHistorialMedico()).codificar(historialMedico);
    }

    /**
     * Método para codificar varios historiales medicos
     *
     * @param historialesMedicos
     * @return
     */
    public static JSONArray codificarHistorialesMedicos(HistorialMedico[] historialesMedicos) {
        return codificarEntidades(historialesMedicos, new CodificadorHistorialMedico());
    }

    /**
     * Método para decodificar un historial medico
     *
     * @param object
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    public static HistorialMedico decodificarHistorialMedico(JSONObject object) throws IncompatibleTypeForJSONFieldException {
        return (new DecodificadorHistorialMedico()).decodificar(object);
    }

    /**
     * Método para decodificar varios historiales medicos
     *
     * @param objects
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    public static HistorialMedico[] decodificarHistorialesMedicos(JSONArray objects) throws IncompatibleTypeForJSONFieldException {
        return decodificarEntidades(objects, new DecodificadorHistorialMedico(), new HistorialMedico[objects.size()]);
    }

    /**
     * Método para codificar una consulta medica
     *
     * @param consultaMedica
     * @return
     */
    public static JSONObject codificarConsultaMedica(ConsultaMedica consultaMedica) {
        return (new CodificadorConsultaMedica()).codificar(consultaMedica);
    }

    /**
     * Método para codificar varias consultas medicas
     *
     * @param consultasMedicas
     * @return
     */
    public static JSONArray codificarConsultasMedicas(ConsultaMedica[] consultasMedicas) {
        return codificarEntidades(consultasMedicas, new CodificadorConsultaMedica());
    }

    /**
     * Método para decodificar una consulta medica
     *
     * @param object
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    public static ConsultaMedica decodificarConsultaMedica(JSONObject object) throws IncompatibleTypeForJSONFieldException {
        return (new DecodificadorConsultaMedica()).decodificar(object);
    }

    /**
     * Método para decodificar varias consultas medicas
     *
     * @param objects
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    public static ConsultaMedica[] decodificarConsultasMedicas(JSONArray objects) throws IncompatibleTypeForJSONFieldException {
        return decodificarEntidades(objects, new DecodificadorConsultaMedica(), new ConsultaMedica[objects.size()]);
    }

    /**
     * Método para codificar un Diagnostico
     *
     * @param diagnostico
     * @return
     */
    public static JSONObject codificarDiagnostico(Diagnostico diagnostico) {
        return (new CodificadorDiagnostico()).codificar(diagnostico);
    }

    /**
     * Método para codificar varios Diagnosticos
     *
     * @param diagnosticos
     * @return
     */
    public static JSONArray codificarDiagnosticos(Diagnostico[] diagnosticos) {
        return codificarEntidades(diagnosticos, new CodificadorDiagnostico());
    }

    /**
     * Método para decodificar un Diagnostico
     *
     * @param object
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    public static Diagnostico decodificarDiagnostico(JSONObject object) throws IncompatibleTypeForJSONFieldException {
        return (new DecodificadorDiagnostico()).decodificar(object);
    }

    /**
     * Método para decodificar varios Diagnosticos
     *
     * @param objects
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    public static Diagnostico[] decodificarDiagnosticos(JSONArray objects) throws IncompatibleTypeForJSONFieldException {
        return decodificarEntidades(objects, new DecodificadorDiagnostico(), new Diagnostico[objects.size()]);
    }

    /**
     * Método para codificar un Examen
     *
     * @param examen
     * @return
     */
    public static JSONObject codificarExamen(Examen examen) {
        return (new CodificadorExamen()).codificar(examen);
    }

    /**
     * Método para codificar varios Examenes
     *
     * @param examenes
     * @return
     */
    public static JSONArray codificarExamenes(Examen[] examenes) {
        return codificarEntidades(examenes, new CodificadorExamen());
    }

    /**
     * Método para decodificar un Examen
     *
     * @param object
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    public static Examen decodificarExamen(JSONObject object) throws IncompatibleTypeForJSONFieldException {
        return (new DecodificadorExamen()).decodificar(object);
    }

    /**
     * Método para decodificar vaiors Examenes
     *
     * @param objects
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    public static Examen[] decodificarExamenes(JSONArray objects) throws IncompatibleTypeForJSONFieldException {
        return decodificarEntidades(objects, new DecodificadorExamen(), new Examen[objects.size()]);
    }

}
