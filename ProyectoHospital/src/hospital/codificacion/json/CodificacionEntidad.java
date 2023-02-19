package hospital.codificacion.json;

import hospital.modelo.Cuenta;
import hospital.modelo.Persona;
import hospital.modelo.Rol;
import hospital.modelo.excepciones.ModeloException;
import hospital.utilidades.excepciones.IncompatibleTypeForJSONFieldException;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
}