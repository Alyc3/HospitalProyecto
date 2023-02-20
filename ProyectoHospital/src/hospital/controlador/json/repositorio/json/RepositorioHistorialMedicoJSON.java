package hospital.controlador.json.repositorio.json;

import hospital.controlador.json.CodificacionEntidad;
import hospital.controlador.lista.ListaEnlazada;
import hospital.controlador.lista.Ordenamiento;
import hospital.modelo.HistorialMedico;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.IncompatibleTypeForJSONFieldException;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.Configuracion;
import hospital.modelo.utilities.Comparadores.CompararHistorialMedicoPorID;
import java.text.ParseException;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import hospital.controlador.json.repositorio.RepositorioHistorialMedico;
import hospital.modelo.utilities.Comparadores.CompararInteger;

public class RepositorioHistorialMedicoJSON implements RepositorioHistorialMedico {

    private AlmacenamientoJSON almacenamiento;

    public RepositorioHistorialMedicoJSON() throws FileNotFoundException {
        almacenamiento = new AlmacenamientoJSON(Configuracion.rutaHistorialMedicoRepositoryFile);
    }

    /**
     * Método que recibe una excepción y detalla cual fue el error ocurrido
     *
     * @param e
     * @throws ModeloException
     */
    private void detallarExcepcion(Exception e) throws ModeloException {
        if (IOException.class.isInstance(e)) {
            throw new ModeloException(
                    "Error al intentar escribir o acceder, a información o contenido de los "
                    + "archivos del repositorio HistorialMedicoJSONRepository",
                    e,
                    ErrorType.ErrorAccesoDatos
            );
        } else if (ParseException.class.isInstance(e)) {
            throw new ModeloException(
                    "No se pueden extraer datos del repositorio HistorialMedicoJSONRepository, "
                    + "porque el contenido de sus archivos no cumple correctamente con el "
                    + "formato json",
                    e,
                    ErrorType.ErrorDatosCorruptos
            );
        } else if (IncompatibleTypeForJSONFieldException.class.isInstance(e)) {
            throw new ModeloException(
                    "Error de incompatibilidad entre los tipos de datos guardados "
                    + "en HistorialMedicoJSONRepository y los definidos por la aplicación",
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
     * Método que valida que el historial medico tenga id y fecha de nacimiento
     * definidos
     *
     * @param historialMedico
     * @param validateId
     * @throws ModeloException
     */
    private void validar(HistorialMedico historialMedico, boolean validateId) throws ModeloException {
        if (validateId) {
            if (historialMedico.getId() == null) {
                throw new ModeloException(
                        "El campo id de la entidad HistorialMedico no puede ser null",
                        ErrorType.ErrorValorNuloNoPermitido
                );
            }
        }
        if (historialMedico.getFechaNacimiento() == null || historialMedico.getFechaNacimiento().equals("")) {
            throw new ModeloException(
                    "El campo getFechaNacimiento de la entidad HistorialMedico no puede ser null, ni vacío",
                    ErrorType.ErrorValorNuloNoPermitido
            );
        }
        if (historialMedico.getPersona() == null || historialMedico.getPersona().getId() == null) {
            throw new ModeloException(
                    "El campo idPersona de la entidad HistorialMedico no puede ser null, ni vacío",
                    ErrorType.ErrorValorNuloNoPermitido
            );
        }
    }

    @Override
    public HistorialMedico crear(HistorialMedico historialMedico) throws ModeloException {
        try {
            validar(historialMedico, false);
            JSONArray objects = almacenamiento.obtenerTodo();
            HistorialMedico[] historialesMedicos = CodificacionEntidad.decodificarHistorialesMedicos(objects);
            Integer maxUsedId = 0;
            for (int i = 0; i < historialesMedicos.length; i++) {
                maxUsedId = Math.max(maxUsedId, historialesMedicos[i].getId());

                if (historialMedico.getPersona().getId().equals(historialesMedicos[i].getPersona().getId())) {
                    throw new ModeloException(
                            "Ya existe otro historial medico relacionado a la "
                            + "misma persona",
                            ErrorType.ErrorRestriccionUnicidad
                    );
                }
            }
            historialMedico.setId(maxUsedId + 1);
            JSONObject object = CodificacionEntidad.codificarHistorialMedico(historialMedico);
            almacenamiento.guardarUno(object);
            return historialMedico;
        } catch (Exception e) {
            detallarExcepcion(e);
            return null;
        }
    }

    @Override
    public HistorialMedico actualizar(HistorialMedico historialMedico) throws ModeloException {
        try {
            validar(historialMedico, true);
            JSONArray objects = almacenamiento.obtenerTodo();
            HistorialMedico[] historialesMedicos = CodificacionEntidad.decodificarHistorialesMedicos(objects);
            for (int i = 0; i < historialesMedicos.length; i++) {
                if (historialMedico.getId() == historialesMedicos[i].getId()) {
                    if (!historialMedico.getFechaNacimiento().equals(historialesMedicos[i].getFechaNacimiento())) {
                        throw new ModeloException(
                                "El campo fechaNacimiento de la entidad HistorialMedico "
                                + "puede ser modificado",
                                ErrorType.ErrorOperacionNoValida
                        );
                    }
                    if (!historialMedico.getPersona().getId().equals(historialesMedicos[i].getPersona().getId())) {
                        throw new ModeloException(
                                "La persona asociada al historial médico no puede ser modificado",
                                ErrorType.ErrorOperacionNoValida
                        );
                    }
                    historialesMedicos[i] = historialMedico;
                    objects = CodificacionEntidad.codificarHistorialesMedicos(historialesMedicos);
                    almacenamiento.limpiarYGuardarVarios(objects);

                    return historialMedico;
                }
            }
            throw new ModeloException("El historial médico no existe", ErrorType.ErrorObjetoNoEncontrado);
        } catch (Exception e) {
            detallarExcepcion(e);
            return null;
        }
    }

    @Override
    public HistorialMedico encontrarPorId(Integer id) throws ModeloException {
        try {
            JSONArray objects = almacenamiento.obtenerTodo();
            HistorialMedico[] historialesMedicos = CodificacionEntidad.decodificarHistorialesMedicos(objects);
            for (int i = 0; i < historialesMedicos.length; i++) {
                if (id == historialesMedicos[i].getId()) {
                    return historialesMedicos[i];
                }
            }
            return null;
        } catch (Exception e) {
            detallarExcepcion(e);
            return null;
        }
    }

    @Override
    public HistorialMedico encontrarPorIdPersona(Integer idPersona) throws ModeloException {
        try {
            JSONArray objects = almacenamiento.obtenerTodo();
            HistorialMedico[] historialesMedicos = CodificacionEntidad.decodificarHistorialesMedicos(objects);
            for (int i = 0; i < historialesMedicos.length; i++) {
                if (idPersona == historialesMedicos[i].getPersona().getId()) {
                    return historialesMedicos[i];
                }
            }
            return null;
        } catch (Exception e) {
            detallarExcepcion(e);
            return null;
        }
    }

    @Override
    public ListaEnlazada<HistorialMedico> encontrarMuchosPorId(ListaEnlazada<Integer> ids) throws ModeloException {
        try {
            JSONArray objects = almacenamiento.obtenerTodo();
            HistorialMedico[] historialesMedicos = CodificacionEntidad.decodificarHistorialesMedicos(objects);
            Ordenamiento.mergeSort(historialesMedicos, new CompararHistorialMedicoPorID());
            Integer[] arrayIds = new Integer[ids.size()];
            ids.toArray(arrayIds);
            Ordenamiento.mergeSort(arrayIds, new CompararInteger());

            ListaEnlazada<HistorialMedico> listHistorialesMedicos = new ListaEnlazada<HistorialMedico>();
            for (int i = 0, j = 0; i < historialesMedicos.length && j < arrayIds.length;) {
                if (historialesMedicos[i].getId() == arrayIds[j]) {
                    listHistorialesMedicos.insertarFinal(historialesMedicos[i]);
                    i++;
                    j++;
                } else if (historialesMedicos[i].getId() < arrayIds[j]) {
                    i++;
                } else {
                    j++;
                }
            }
            return listHistorialesMedicos;
        } catch (Exception e) {
            detallarExcepcion(e);
            return null;
        }
    }
}
