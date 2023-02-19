package hospital.codificacion.json;

//import hospital.modelo.enumeradores.EncontrarTipoSanguineo;
//import hospital.modelo.enumeradores.TipoExamen;
//import hospital.modelo.enumeradores.EncontrarTipoExamen;
//import hospital.modelo.enumeradores.TipoSanguineo;
import hospital.utilidades.excepciones.IncompatibleTypeForJSONFieldException;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import org.json.simple.JSONObject;

public class LeectorArchivoJSON {

    /**
     * Método para retornar un String con el valor del campo fieldName dentro
     * del JSONObject al que se hace referencia
     *
     * @param object
     * @param fieldName
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    public static String getString(JSONObject object, String fieldName) throws IncompatibleTypeForJSONFieldException {
        try {
            return (String) object.get(fieldName);
        } catch (ClassCastException e) {
            throw new IncompatibleTypeForJSONFieldException(e, fieldName, String.class.getName());
        }
    }

    /**
     * Método para retornar un Integer con el valor del campo fieldName dentro
     * del JSONObject al que se hace referencia
     *
     * @param object
     * @param fieldName
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    public static Integer getInteger(JSONObject object, String fieldName) throws IncompatibleTypeForJSONFieldException {
        try {
            Object o = object.get(fieldName);
            if (o == null) {
                return null;
            } else if (Integer.class.isInstance(o)) {
                return (Integer) o;
            } else {
                Long longNumber = (Long) o;
                return Integer.valueOf((String.valueOf(longNumber)));
            }
        } catch (ClassCastException e) {
            throw new IncompatibleTypeForJSONFieldException(e, fieldName, Integer.class.getName());
        } catch (NumberFormatException e) {
            throw new IncompatibleTypeForJSONFieldException(e, fieldName, Integer.class.getName());
        }
    }

    /**
     * Método para retornar un Long con el valor del campo fieldname dentro del
     * JSONObject al que se hace referencia
     *
     * @param object
     * @param fieldName
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    public static Long getLong(JSONObject object, String fieldName) throws IncompatibleTypeForJSONFieldException {
        try {
            Object o = object.get(fieldName);
            if (o == null) {
                return null;
            } else if (Long.class.isInstance(o)) {
                return (Long) object.get(fieldName);
            } else {
                Integer integerNumber = (Integer) o;
                return Long.valueOf((String.valueOf(integerNumber)));
            }
        } catch (ClassCastException e) {
            throw new IncompatibleTypeForJSONFieldException(e, fieldName, Long.class.getName());
        }
    }

    /**
     * Método para retornar un Float con el valor del campo fieldName dentro del
     * JSONObject al que se hace referencia
     *
     * @param object
     * @param fieldName
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    public static Float getFloat(JSONObject object, String fieldName) throws IncompatibleTypeForJSONFieldException {
        try {
            Object o = object.get(fieldName);
            if (o == null) {
                return null;
            } else if (Float.class.isInstance(o)) {
                return (Float) o;
            } else {
                Double doubleNumber = (Double) o;
                return Float.valueOf((String.valueOf(doubleNumber)));
            }
        } catch (ClassCastException e) {
            throw new IncompatibleTypeForJSONFieldException(e, fieldName, Float.class.getName());
        } catch (NumberFormatException e) {
            throw new IncompatibleTypeForJSONFieldException(e, fieldName, Float.class.getName());
        }
    }

    /**
     * Método para retornar un Double con el valor del campo fieldname dentro
     * del JSONObject al que se hace referencia
     *
     * @param object
     * @param fieldName
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    public static Double getDouble(JSONObject object, String fieldName) throws IncompatibleTypeForJSONFieldException {
        try {
            Object o = object.get(fieldName);
            if (o == null) {
                return null;
            } else if (Double.class.isInstance(o)) {
                return (Double) o;
            } else {
                Float floatNumber = (Float) o;
                return Double.valueOf((String.valueOf(floatNumber)));
            }
        } catch (ClassCastException e) {
            throw new IncompatibleTypeForJSONFieldException(e, fieldName, Double.class.getName());
        } catch (NumberFormatException e) {
            throw new IncompatibleTypeForJSONFieldException(e, fieldName, Double.class.getName());
        }
    }

    /**
     * Método para retornar un LocalDate con el valor del campo fieldName dentro
     * del JSONObject al que se hace referencia
     *
     * @param object
     * @param fieldName
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    public static LocalDate getLocalDate(JSONObject object, String fieldName) throws IncompatibleTypeForJSONFieldException {
        try {
            String str = getString(object, fieldName);
            if (str == null) {
                return null;
            }
            return LocalDate.parse(str);
        } catch (IncompatibleTypeForJSONFieldException e) {
            throw new IncompatibleTypeForJSONFieldException(e, fieldName, LocalDate.class.getName());
        } catch (DateTimeParseException e) {
            throw new IncompatibleTypeForJSONFieldException(e, fieldName, LocalDate.class.getName());
        }
    }

    /**
     * Método para retornar un LocalTime con el valor del campo fieldName dentro
     * del JSONObject al que se hace referencia
     *
     * @param object
     * @param fieldName
     * @return
     * @throws IncompatibleTypeForJSONFieldException
     */
    public static LocalTime getLocalTime(JSONObject object, String fieldName) throws IncompatibleTypeForJSONFieldException {
        try {
            String str = getString(object, fieldName);
            if (str == null) {
                return null;
            }
            return LocalTime.parse(str);
        } catch (IncompatibleTypeForJSONFieldException e) {
            throw new IncompatibleTypeForJSONFieldException(e, fieldName, LocalTime.class.getName());
        } catch (DateTimeParseException e) {
            throw new IncompatibleTypeForJSONFieldException(e, fieldName, LocalTime.class.getName());
        }
    }

//    /**
//     * Método para retornar un TipoSAnquineo con el valor del campo fieldName
//     * dentro del JSONObject al que se hace referencia
//     *
//     * @param object
//     * @param fieldName
//     * @return
//     * @throws IncompatibleTypeForJSONFieldException
//     */
//    public static TipoSanguineo getTipoSanguineo(JSONObject object, String fieldName) throws IncompatibleTypeForJSONFieldException {
//        try {
//            String tipoSanguineoStr = getString(object, fieldName);
//            if (tipoSanguineoStr == null) {
//                return null;
//            }
//            TipoSanguineo tipoSanguineo = EncontrarTipoSanguineo.encontrar(tipoSanguineoStr);
//            if (tipoSanguineo != null) {
//                return tipoSanguineo;
//            }
//        } catch (IncompatibleTypeForJSONFieldException e) {
//            throw new IncompatibleTypeForJSONFieldException(e, fieldName, TipoSanguineo.class.getName());
//        }
//        //Ocurre cuando no se encuentra un TipoSanguineo que coincida.
//        throw new IncompatibleTypeForJSONFieldException(fieldName, TipoSanguineo.class.getName());
//    }
//
//    /**
//     * Método para retornar un TipoSanguineo con el valor del campo fieldName
//     * dento del JSONObject al que se hace referencia
//     *
//     * @param object
//     * @param fieldName
//     * @return
//     * @throws IncompatibleTypeForJSONFieldException
//     */
//    public static TipoExamen getTipoExamen(JSONObject object, String fieldName) throws IncompatibleTypeForJSONFieldException {
//        try {
//            String tipoExamenStr = getString(object, fieldName);
//            if (tipoExamenStr == null) {
//                return null;
//            }
//
//            TipoExamen tipoExamen = EncontrarTipoExamen.encontrar(tipoExamenStr);
//            if (tipoExamen != null) {
//                return tipoExamen;
//            }
//        } catch (IncompatibleTypeForJSONFieldException e) {
//            throw new IncompatibleTypeForJSONFieldException(e, fieldName, TipoExamen.class.getName());
//        }
//
//        // Ocurre cuando no se encuentra un TipoExamen que coincida
//        throw new IncompatibleTypeForJSONFieldException(fieldName, TipoExamen.class.getName());
//    }
}
