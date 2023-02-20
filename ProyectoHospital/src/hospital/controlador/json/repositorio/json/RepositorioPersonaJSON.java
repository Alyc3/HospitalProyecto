package hospital.controlador.json.repositorio.json;

import hospital.controlador.json.CodificacionEntidad;
import hospital.controlador.lista.ListaEnlazada;
import hospital.controlador.lista.Ordenamiento;
import hospital.modelo.Persona;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.IncompatibleTypeForJSONFieldException;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.Configuracion;
import hospital.modelo.utilities.Comparadores.CompararInteger;
import hospital.modelo.utilities.Comparadores.CompararPersonaPorId;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import hospital.controlador.json.repositorio.RepositorioPersona;

public class RepositorioPersonaJSON implements RepositorioPersona {

    private AlmacenamientoJSON almacenamiento;

    public RepositorioPersonaJSON() throws FileNotFoundException {
        almacenamiento = new AlmacenamientoJSON(Configuracion.rutaPersonaRepositoryFile);
    }

    /**
     *  Método que recibe una excepción y detalla cual fue el error ocurrido
     * @param e
     * @throws ModeloException 
     */
    private void detallarExcepcion(Exception e) throws ModeloException {
        if (IOException.class.isInstance(e)) {
            throw new ModeloException(
                    "Error al intentar escribir o acceder, a información o contenido de los "
                    + "archivos del repositorio PersonaJSONRepository",
                    e,
                    ErrorType.ErrorAccesoDatos
            );
        } else if (ParseException.class.isInstance(e)) {
            throw new ModeloException(
                    "No se pueden extraer datos del repositorio PersonaJSONRepository, "
                    + "porque el contenido de sus archivos no cumple correctamente con el "
                    + "formato json",
                    e,
                    ErrorType.ErrorDatosCorruptos
            );
        } else if (IncompatibleTypeForJSONFieldException.class.isInstance(e)) {
            throw new ModeloException(
                    "Error de incompatibilidad entre los tipos de datos guardados "
                    + "en PersonaJSONRepository y los definidos por la aplicación",
                    e,
                    ErrorType.ErrorDatosCorruptos
            );
        } else if (ModeloException.class.isInstance(e)) {
            throw (ModeloException) e;
        } else {
            throw new ModeloException(e.getMessage(), e, ErrorType.ErrorDesconocido);
        }
    }

    /**
     * Método para validar que la persona tenga id, cedula, correo y rol definidos
     * @param persona
     * @param validateId
     * @throws ModeloException 
     */
    private void validar(Persona persona, boolean validateId) throws ModeloException {
        if (validateId) {
            if (persona.getId() == null) {
                throw new ModeloException(
                        "El campo id de la entidad Persona no puede ser null",
                        ErrorType.ErrorValorNuloNoPermitido
                );
            }
        }
        if (persona.getCedula() == null || persona.getCedula().equals("")) {
            throw new ModeloException(
                    "El campo cedula de la entidad Persona no puede ser null, ni vacío",
                    ErrorType.ErrorValorNuloNoPermitido
            );
        }
        if (persona.getCorreo() == null || persona.getCorreo().equals("")) {
            throw new ModeloException(
                    "El campo correo de la entidad Persona no puede ser null, ni vacío",
                    ErrorType.ErrorValorNuloNoPermitido
            );
        }
        if (persona.getRol() == null) {
            throw new ModeloException(
                    "El campo rol de la entidad Persona no puede ser null",
                    ErrorType.ErrorValorNuloNoPermitido
            );
        }
    }

    /**
     * Método para crear una persona y comprobar que no exista una persona con los mismos datos
     * @param persona
     * @return
     * @throws ModeloException 
     */
    @Override
    public Persona crear(Persona persona) throws ModeloException {
        try {
            validar(persona, false);
            JSONArray objetos = almacenamiento.obtenerTodo();
            Persona[] personas = CodificacionEntidad.decodificarPersonas(objetos);

            Integer maxUsedId = 0;
            for (int i = 0; i < personas.length; i++) {
                maxUsedId = Math.max(maxUsedId, personas[i].getId());

                if (persona.getCedula().equals(personas[i].getCedula())) {
                    throw new ModeloException(
                            "Ya hay registrada una persona con la misma cedula",
                            ErrorType.ErrorRestriccionUnicidad
                    );
                }
            }

            persona.setId(maxUsedId + 1);
            JSONObject object = CodificacionEntidad.codificarPersona(persona);
            almacenamiento.guardarUno(object);
            return persona;
        } catch (Exception e) {
            detallarExcepcion(e);
            return null;
        }
    }

    /**
     * Método para actualizar la información de una persona, buscandola en el archivo .json por medio de su id
     * @param persona
     * @return
     * @throws ModeloException 
     */
    @Override
    public Persona actualizar(Persona persona) throws ModeloException {
        try {
            validar(persona, true);

            JSONArray objetos = almacenamiento.obtenerTodo();
            Persona[] personas = CodificacionEntidad.decodificarPersonas(objetos);

            for (int i = 0; i < personas.length; i++) {
                if (persona.getId() == personas[i].getId()) {
                    if (!persona.getCedula().equals(personas[i].getCedula())) {
                        throw new ModeloException(
                                "La cedula no puede ser modificada",
                                ErrorType.ErrorOperacionNoValida
                        );
                    }
                    if (!persona.getRol().equals(personas[i].getRol())) {
                        throw new ModeloException(
                                "El rol no puede ser modificada",
                                ErrorType.ErrorOperacionNoValida
                        );
                    }

                    personas[i] = persona;

                    objetos = CodificacionEntidad.codificarPersonas(personas);
                    almacenamiento.limpiarYGuardarVarios(objetos);

                    return persona;
                }
            }
            return null;
        } catch (Exception e) {
            detallarExcepcion(e);
            return null;
        }
    }

    /**
     * Método que permite buscar a una persona por medio de su id, si no la encuentra retonar null
     * @param id
     * @return
     * @throws ModeloException 
     */
    @Override
    public Persona encontrarPorId(Integer id) throws ModeloException {
        try {
            JSONArray objetos = almacenamiento.obtenerTodo();
            Persona[] personas = CodificacionEntidad.decodificarPersonas(objetos);

            for (int i = 0; i < personas.length; i++) {
                if (id == personas[i].getId()) {
                    return personas[i];
                }
            }
            return null;
        } catch (Exception e) {
            detallarExcepcion(e);

            return null;
        }
    }

    /**
     * Método que permite buscar a una persona por medio de su cedula, si no la encuentra retorna null
     * @param cedula
     * @return
     * @throws ModeloException 
     */
    @Override
    public Persona encontrarPorCedula(String cedula) throws ModeloException {
        try {
            JSONArray objects = almacenamiento.obtenerTodo();
            Persona[] personas = CodificacionEntidad.decodificarPersonas(objects);

            for (int i = 0; i < personas.length; i++) {
                if (cedula.equals(personas[i].getCedula())) {
                    return personas[i];
                }
            }
            return null;
        } catch (Exception e) {
            detallarExcepcion(e);

            return null;
        }
    }

    /**
     * Método que permite buscar a varias personas por medio de sus id's, realizando primero un ordenamiento ascendente de las mismas
     * @param ids
     * @return
     * @throws ModeloException 
     */
    @Override
    public ListaEnlazada<Persona> encontrarMuchosPorId(ListaEnlazada<Integer> ids) throws ModeloException {
        try {
            JSONArray objetos = almacenamiento.obtenerTodo();
            Persona[] personas = CodificacionEntidad.decodificarPersonas(objetos);

            Ordenamiento.mergeSort(personas, new CompararPersonaPorId());

            Integer[] arrayIds = new Integer[ids.size()];
            ids.toArray(arrayIds);
            Ordenamiento.mergeSort(arrayIds, new CompararInteger());

            ListaEnlazada<Persona> listaEnlazadaPersonas = new ListaEnlazada<Persona>();
            for (int i = 0, j = 0; i < personas.length && j < arrayIds.length;) {
                if (personas[i].getId() == arrayIds[j]) {
                    listaEnlazadaPersonas.insertarFinal(personas[i]);
                    i++;
                    j++;
                } else if (personas[i].getId() < arrayIds[j]) {
                    i++;
                } else {
                    j++;
                }
            }
            return listaEnlazadaPersonas;
        } catch (Exception e) {
            detallarExcepcion(e);
            return null;
        }
    }
}
