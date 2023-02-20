package hospital.controlador.json.repositorio.json;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Es la clase encargada de leer e insertar objetos json en un archivo
 *
 */
public class AlmacenamientoJSON {

    private String filename;
    private RandomAccessFile rwfile;
    private int tamanioMaxArchivo = 256 * 1024 * 1024;
    private int tamanioLectura = 10;
    
    public AlmacenamientoJSON(String filename) throws FileNotFoundException {
        this.filename = filename;

        rwfile = new RandomAccessFile(filename, "rws");
    }

    /**
     * Método para insertar un objeto json al final del archivo El objeto no
     * puede ser null
     *
     * @param object
     * @throws IOException
     */
    public void guardarUno(JSONObject object) throws IOException {
        long s = rwfile.length();
        if (s > tamanioMaxArchivo) {
            throw new IOException(
                    "El tamaño del archivo (" + s + ") "
                    + "es mayor al soportado (" + tamanioMaxArchivo + ")"
            );
        }
        int tamanio = (int) s;

        String jsonstr = object.toString();

        byte[] bytes = jsonstr.getBytes();

        if (tamanio == 0 || tamanio == 2) {
            if (bytes.length + 2 > tamanioMaxArchivo) {
                throw new IOException(
                        "No se puede agregar este objeto al archivo, "
                        + "ya que el nuevo tamaño del archivo ("
                        + (bytes.length + 2) + ") superaria el tamaño "
                        + "permitido (" + tamanioMaxArchivo + ")"
                );
            }

            rwfile.setLength(0);
            rwfile.write('[');
            rwfile.write(bytes);
            rwfile.write(']');
        } else {
            if (tamanio + bytes.length + 1 > tamanioMaxArchivo) {
                throw new IOException(
                        "No se puede agregar este objeto al archivo, "
                        + "ya que el nuevo tamaño del archivo ("
                        + (tamanio + bytes.length + 1) + ") superaria el tamaño "
                        + "permitido (" + tamanioMaxArchivo + ")"
                );
            }
            rwfile.seek(tamanio - 1);
            rwfile.write(',');
            rwfile.write(bytes);
            rwfile.write(']');
        }
    }

    /**
     * Método para inserrtar varios objetos json al final del archivo
     *
     * @param objects
     * @throws IOException
     */
    public void guardarVarios(JSONArray objects) throws IOException {
        long s = rwfile.length();
        if (s > tamanioMaxArchivo) {
            throw new IOException(
                    "El tamaño del archivo (" + s + ") "
                    + "es mayor al soportado (" + tamanioMaxArchivo + ")"
            );
        }
        int tamanio = (int) s;

        if (objects.size() == 0) {
            if (tamanio == 0) {
                limpiarYGuardarVarios(objects);
            }
        } else if (tamanio == 0 || tamanio == 2) {
            limpiarYGuardarVarios(objects);
        } else {
            String jsonstr = objects.toString();
            jsonstr = jsonstr.substring(1, jsonstr.length());
            byte[] bytes = jsonstr.getBytes();
            if (tamanio + bytes.length > tamanioMaxArchivo) {
                throw new IOException(
                        "No se pueden agregar estos objetos al archivo, "
                        + "ya que el nuevo tamaño del archivo ("
                        + (tamanio + bytes.length) + ") superaria el tamaño "
                        + "permitido (" + tamanioMaxArchivo + ")"
                );
            }
            rwfile.seek(tamanio - 1);
            rwfile.write(',');
            rwfile.write(bytes);
        }
    }

    /**
     * Método para insertar nuevos objetos json y borrar todos los anteriores
     *
     * @param objetos
     * @throws IOException
     */
    public void limpiarYGuardarVarios(JSONArray objetos) throws IOException {
        String jsonstr = objetos.toString();
        byte[] bytes = jsonstr.getBytes();
        if (bytes.length > tamanioMaxArchivo) {
            throw new IOException(
                    "No se pueden agregar estos objetos al archivo, "
                    + "ya que el nuevo tamaño del archivo ("
                    + (bytes.length) + ") superaria el tamaño "
                    + "permitido (" + tamanioMaxArchivo + ")"
            );
        }
        rwfile.setLength(0);
        rwfile.write(bytes);
    }

    /**
     * Método que retorna un JSSONArray conteniendo todos los objetos json
     * almacenados en el archivo
     *
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public JSONArray obtenerTodo() throws IOException, ParseException {
        String jsonstr = leerArchivo();
        if (jsonstr.length() == 0) {
            return new JSONArray();
        }
        JSONParser parser = new JSONParser();
        return (JSONArray) parser.parse(jsonstr);
    }

    /**
     * Método para leer el archivo completo y retornar su contenido en forma de
     * String
     *
     * @return
     * @throws IOException
     */
    private String leerArchivo() throws IOException {
        try {
            rwfile.seek(0);
            long s = rwfile.length();
            if (s > tamanioMaxArchivo) {
                throw new IOException(
                        "El tamaño del archivo (" + s + ") "
                        + "es mayor al soportado (" + tamanioMaxArchivo + ")"
                );
            }
            int tamanio = (int) s;
            int accu = 0;

            byte[] bytes = new byte[tamanio];
            for (int r = 0; r != -1 && accu < tamanio; accu += r) {
                r = rwfile.read(bytes, accu, Math.min(tamanioLectura, tamanio - accu));
            }

            return new String(bytes);
        } catch (IOException e) {
            throw e;
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }
}
