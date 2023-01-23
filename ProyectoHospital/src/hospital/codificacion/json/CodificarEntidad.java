package hospital.codificacion.json;

import hospital.modelo.Cuenta;
import hospital.modelo.Persona;
import hospital.modelo.Rol;
import hospital.modelo.excepciones.ModeloException;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CodificarEntidad {
    
	/**
         * Método para codificar una Persona a formato Json
         * @param persona
         * @return 
         */
	public static String codificarPersona(Persona persona){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", persona.getId());
		map.put("cedula", persona.getCedula());
		map.put("nombre", persona.getNombre());
		map.put("apellido", persona.getApellido());
		map.put("direccion", persona.getDireccion());
		map.put("edad", persona.getEdad());
		map.put("genero", persona.getGenero());
		map.put("correo", persona.getCorreo());
		map.put("telefono", persona.getTelefono());
		map.put("idRol", persona.getRol().getId());

		JSONObject obj = new JSONObject(map);
		String json = obj.toString();
		return json;
	}

        /**
         * Método para decodificar una Persona desde formato Json
         * @param jsonstr
         * @return
         * @throws ModeloException 
         */
	public static Persona decodificarPersona(String jsonstr) throws ModeloException {
		Persona persona = new Persona();
		Rol rol = new Rol(null, null);
		JSONParser parser = new JSONParser();
		try{
			JSONObject o = (JSONObject)parser.parse(jsonstr);
			persona.setId(getInteger(o, "id"));
			persona.setCedula(getString(o, "cedula"));
			persona.setNombre(getString(o, "nombre"));
			persona.setApellido(getString(o, "apellido"));
			persona.setDireccion(getString(o, "direccion"));
			persona.setEdad(getInteger(o, "edad"));
			persona.setGenero(getString(o, "genero"));
			persona.setCorreo(getString(o, "correo"));
			persona.setTelefono(getString(o, "telefono"));
			rol.setId(getInteger(o, "idRol"));
			persona.setRol(rol);
			return persona;
		}
		catch(ParseException e){
			throw new ModeloException("");
		}
		catch(ClassCastException e){
			throw new ModeloException("");
		}
		catch(NumberFormatException e){
			throw new ModeloException("");
		}
	}

        /**
         * Método para codificar una Cuenta a formato Json
         * @param cuenta
         * @return 
         */
	public static String codificarCuenta(Cuenta cuenta){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", cuenta.getId());
		map.put("usuario", cuenta.getUsuario());
		map.put("clave", cuenta.getClave());
		map.put("idPersona", cuenta.getPersona().getId());

		JSONObject obj = new JSONObject(map);
		String json = obj.toString();
		return json;
	}

        /**
         * Método para decodificar una Cuenta desde formato Json
         * @param jsonstr
         * @throws ModeloException 
         */
	public static void decodificarCuenta(String jsonstr) throws ModeloException {
		Cuenta cuenta = new Cuenta();
		Persona persona = new Persona();
		JSONParser parser = new JSONParser();
		try{
			JSONObject o = (JSONObject)parser.parse(jsonstr);
			cuenta.setId(getInteger(o, "id"));
			cuenta.setUsuario(getString(o, "usuario"));
			cuenta.setClave(getString(o, "clave"));
			persona.setId(getInteger(o, "idPersona"));
			cuenta.setPersona(persona);			
		}
		catch(ParseException e){

		}
		catch(ClassCastException e){

		}
		catch(NumberFormatException e){

		}
	}

        /**
         * Método para comprobar si un objeto del archivo .json es un String
         * @param object
         * @param fieldName
         * @return
         * @throws ClassCastException 
         */
	public static String getString(JSONObject object, String fieldName) throws ClassCastException {
		// Si el valor retornado para el correspondiente campo no puede ser casteado a String, lanza ClassCastException.
		return (String)object.get(fieldName);
	}

        /**
         * Método para comprobar si un objeto del archivo .json es un Long
         * @param object
         * @param fieldName
         * @return
         * @throws ClassCastException 
         */
	public static Long getLong(JSONObject object, String fieldName) throws ClassCastException {
		// Si el valor retornado para el correspondiente campo no puede ser casteado a Long, lanza ClassCastException.
		return (Long)object.get(fieldName);
	}

        /**
         * Método para comprobar si un objeto del archivo.sjon es un Integer
         * @param object
         * @param fieldName
         * @return
         * @throws ClassCastException
         * @throws NumberFormatException 
         */
	public static Integer getInteger(JSONObject object, String fieldName) throws ClassCastException, NumberFormatException {
		Long longNumber = (Long)object.get(fieldName); // Si el valor retornado para el correspondiente campo no puede ser casteado a Long, lanza ClassCastException.
		if(longNumber == null){
			return null;
		}
		else{
			// Si número de tipo Long no puede ser representado por un entero, lanza NumberFormatException.
			return Integer.valueOf((String.valueOf(longNumber))); 
		}
	}
}