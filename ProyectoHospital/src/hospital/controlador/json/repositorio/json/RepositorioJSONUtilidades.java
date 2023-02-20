package hospital.controlador.json.repositorio.json;

import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.IncompatibleTypeForJSONFieldException;
import hospital.modelo.excepciones.ModeloException;
import java.io.IOException;
import java.text.ParseException;

public class RepositorioJSONUtilidades {

    /**
     * Recibe una excepcion y la envuelve para dar más detalles sobre el error
     * 
     * @param e
     * @param nombreRepositorio
     * @throws ModeloException 
     */
    public static void detallarExcepcion(Exception e, String nombreRepositorio) throws ModeloException {
        if (IOException.class.isInstance(e)) {
            throw new ModeloException(
                    "Error al intentar escribir o acceder a información o contenido de los "
                    + "archivos del repositorio " + nombreRepositorio,
                    e,
                    ErrorType.ErrorAccesoDatos
            );
        } else if (ParseException.class.isInstance(e)) {
            throw new ModeloException(
                    "No se pueden extraer datos del repositorio " + nombreRepositorio + ", "
                    + "porque el contenido de sus archivos no cumple correctamente con el "
                    + "formato json",
                    e,
                    ErrorType.ErrorDatosCorruptos
            );
        } else if (IncompatibleTypeForJSONFieldException.class.isInstance(e)) {
            throw new ModeloException(
                    "Error de incompatibilidad entre los tipos de datos guardados "
                    + "en " + nombreRepositorio + " y los definidos por la aplicación",
                    e,
                    ErrorType.ErrorDatosCorruptos
            );
        } else if (ModeloException.class.isInstance(e)) {
            throw (ModeloException) e;
        } else {
            throw new ModeloException(e.getMessage(), e, ErrorType.ErrorDesconocido);
        }
    }
}
