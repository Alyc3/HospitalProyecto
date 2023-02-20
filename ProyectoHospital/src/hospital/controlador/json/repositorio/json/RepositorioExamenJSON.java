package hospital.controlador.json.repositorio.json;

import hospital.controlador.json.CodificacionEntidad;
import hospital.controlador.lista.ListaEnlazada;
import hospital.modelo.Examen;
import hospital.modelo.enumeradores.TipoExamen;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.Configuracion;
import hospital.controlador.json.repositorio.RepositorioExamen;
import hospital.controlador.json.repositorio.json.AlmacenamientoJSON;
import hospital.controlador.json.repositorio.json.RepositorioJSONUtilidades;
import java.io.FileNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RepositorioExamenJSON implements RepositorioExamen {

    private AlmacenamientoJSON almacenamiento;

    public RepositorioExamenJSON() throws FileNotFoundException {
        almacenamiento = new AlmacenamientoJSON(Configuracion.rutaExamenRepositoryFile);
    }

    /**
     * Método para validar los campos que no deben ser nulos (tipoExamen e
     * idConsultaMedica)
     *
     * @param examen
     * @throws ModeloException
     */
    private void validar(Examen examen) throws ModeloException {
        if (examen.getTipoExamen() == null) {
            throw new ModeloException(
                    "El campo tipoExamen de la entidad Examen no puede ser null",
                    ErrorType.ErrorValorNuloNoPermitido
            );
        }
        if (examen.getConsultaMedica() == null || examen.getConsultaMedica().getId() == null) {
            throw new ModeloException(
                    "El campo idConsultaMedica de la entidad Examen no puede ser null",
                    ErrorType.ErrorValorNuloNoPermitido
            );
        }
    }

    @Override
    public Examen crear(Examen examen) throws ModeloException {
        try {
            validar(examen);
            JSONArray objects = almacenamiento.obtenerTodo();
            Examen[] examenes = CodificacionEntidad.decodificarExamenes(objects);
            for (int i = 0; i < examenes.length; i++) {
                if (examen.getConsultaMedica().getId() == examenes[i].getConsultaMedica().getId()) {
                    if (examen.getTipoExamen().equals(examenes[i].getTipoExamen())) {
                        throw new ModeloException(
                                "Ya existe otro examen con el mismo tipo de examen y asociado a la misma consulta médica",
                                ErrorType.ErrorRestriccionUnicidad
                        );
                    }
                }
            }

            JSONObject object = CodificacionEntidad.codificarExamen(examen);
            almacenamiento.guardarUno(object);
            return examen;
        } catch (Exception e) {
            RepositorioJSONUtilidades.detallarExcepcion(e, "RepositorioExamenJSON");
            return null;
        }
    }

    @Override
    public void eliminar(Integer idConsultaMedica, TipoExamen tipoExamen) throws ModeloException {
        try {
            JSONArray objects = almacenamiento.obtenerTodo();
            Examen[] examenes = CodificacionEntidad.decodificarExamenes(objects);
            int posToRemove = -1;
            for (int i = 0; i < examenes.length && posToRemove == -1; i++) {
                if (idConsultaMedica == examenes[i].getConsultaMedica().getId()) {
                    if (tipoExamen.equals(examenes[i].getTipoExamen())) {
                        posToRemove = i;
                    }
                }
            }
            if (posToRemove == -1) {
                throw new ModeloException("No existe un examen asociado a esa consulta y que tenga ese tipo de examen", ErrorType.ErrorObjetoNoEncontrado);
            }

            Examen[] examenesActuales = new Examen[examenes.length - 1];
            for (int i = 0, j = 0; i < examenes.length; i++) {
                if (i != posToRemove) {
                    examenesActuales[j++] = examenes[i];
                }
            }

            objects = CodificacionEntidad.codificarExamenes(examenesActuales);
            almacenamiento.limpiarYGuardarVarios(objects);
        } catch (Exception e) {
            RepositorioJSONUtilidades.detallarExcepcion(e, "RepositorioExamenJSON");
        }
    }

    @Override
    public ListaEnlazada<Examen> encontrarTodoPorIdConsultaMedica(Integer idConsultaMedica) throws ModeloException {
        try {
            JSONArray objects = almacenamiento.obtenerTodo();
            Examen[] examenes = CodificacionEntidad.decodificarExamenes(objects);
            ListaEnlazada<Examen> listExamenes = new ListaEnlazada<Examen>();
            for (int i = 0; i < examenes.length; i++) {
                if (examenes[i].getConsultaMedica() != null) {
                    if (idConsultaMedica == examenes[i].getConsultaMedica().getId()) {
                        listExamenes.insertarFinal(examenes[i]);
                    }
                }
            }
            return listExamenes;
        } catch (Exception e) {
            RepositorioJSONUtilidades.detallarExcepcion(e, "RepositorioExamenJSON");
            return null;
        }
    }
}
