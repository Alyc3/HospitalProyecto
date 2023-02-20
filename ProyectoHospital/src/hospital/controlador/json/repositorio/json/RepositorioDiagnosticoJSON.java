package hospital.controlador.json.repositorio.json;

import hospital.controlador.json.CodificacionEntidad;
import hospital.modelo.Diagnostico;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.Configuracion;
import hospital.controlador.json.repositorio.RepositorioDiagnostico;
import java.io.FileNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RepositorioDiagnosticoJSON implements RepositorioDiagnostico {

    private AlmacenamientoJSON almacenamiento;

    public RepositorioDiagnosticoJSON() throws FileNotFoundException {
        almacenamiento = new AlmacenamientoJSON(Configuracion.rutaDiagnosticoRepositoryFile);
    }

    /**
     * Método para validar los campos que no deben ser nulos (Id de
     * ConsultaMedica)
     *
     * @param diagnostico
     * @param validateId
     * @throws ModeloException
     */
    private void validar(Diagnostico diagnostico, boolean validateId) throws ModeloException {
        if (validateId) {
            if (diagnostico.getId() == null) {
                throw new ModeloException(
                        "El campo id de la entidad Diagnostico no puede ser null",
                        ErrorType.ErrorValorNuloNoPermitido
                );
            }
        }
        if (diagnostico.getConsultaMedica() == null || diagnostico.getConsultaMedica().getId() == null) {
            throw new ModeloException(
                    "El campo idConsultaMedica de la entidad Diagnostico no puede ser null",
                    ErrorType.ErrorValorNuloNoPermitido
            );
        }
    }

    /**
     * Método que permite crear un nuevo Diagnostico Verifica que no exista otro
     * Diagnostico asociado a la misma Consulta Médica
     *
     * @param diagnostico
     * @return
     * @throws ModeloException
     */
    @Override
    public Diagnostico crear(Diagnostico diagnostico) throws ModeloException {
        try {
            validar(diagnostico, false);
            JSONArray objects = almacenamiento.obtenerTodo();
            Diagnostico[] diagnosticos = CodificacionEntidad.decodificarDiagnosticos(objects);
            Integer maxUsedId = 0;
            for (int i = 0; i < diagnosticos.length; i++) {
                maxUsedId = Math.max(maxUsedId, diagnosticos[i].getId());
                if (diagnostico.getConsultaMedica().getId() == diagnosticos[i].getConsultaMedica().getId()) {
                    throw new ModeloException(
                            "Ya existe otro diagnóstico asociado a la misma consulta médica",
                            ErrorType.ErrorRestriccionUnicidad
                    );
                }
            }
            diagnostico.setId(maxUsedId + 1);
            JSONObject object = CodificacionEntidad.codificarDiagnostico(diagnostico);
            almacenamiento.guardarUno(object);
            return diagnostico;
        } catch (Exception e) {
            RepositorioJSONUtilidades.detallarExcepcion(e, "RepositorioDiagnosticoJSON");
            return null;
        }
    }

    /**
     * Método que permite actualizar la información de un Diagnostico existente
     * por medio de su id, de no encontrarlo retorna null
     *
     * @param diagnostico
     * @return
     * @throws ModeloException
     */
    @Override
    public Diagnostico actualizar(Diagnostico diagnostico) throws ModeloException {
        try {
            validar(diagnostico, true);
            JSONArray objects = almacenamiento.obtenerTodo();
            Diagnostico[] diagnosticos = CodificacionEntidad.decodificarDiagnosticos(objects);
            for (int i = 0; i < diagnosticos.length; i++) {
                if (diagnostico.getId() == diagnosticos[i].getId()) {
                    if (diagnostico.getConsultaMedica().getId() != diagnosticos[i].getConsultaMedica().getId()) {
                        throw new ModeloException(
                                "La consulta médica asociada al diagnóstico no puede ser cambiada",
                                ErrorType.ErrorOperacionNoValida
                        );
                    }
                    diagnosticos[i] = diagnostico;
                    objects = CodificacionEntidad.codificarDiagnosticos(diagnosticos);
                    almacenamiento.limpiarYGuardarVarios(objects);
                    return diagnostico;
                }
            }
            return null;
        } catch (Exception e) {
            RepositorioJSONUtilidades.detallarExcepcion(e, "RepositorioDiagnosticoJSON");
            return null;
        }
    }

    /**
     * Método que permite encontrar un Diágnostico cuyo id de Consulta Media sea
     * igual al que se busca, si no lo encuentra retornará null
     *
     * @param idConsultaMedica
     * @return
     * @throws ModeloException
     */
    @Override
    public Diagnostico encontrarPorIdConsultaMedica(Integer idConsultaMedica) throws ModeloException {
        try {
            JSONArray objects = almacenamiento.obtenerTodo();
            Diagnostico[] diagnosticos = CodificacionEntidad.decodificarDiagnosticos(objects);
            for (int i = 0; i < diagnosticos.length; i++) {
                if (diagnosticos[i].getConsultaMedica() != null) {
                    if (idConsultaMedica == diagnosticos[i].getConsultaMedica().getId()) {
                        return diagnosticos[i];
                    }
                }
            }
            return null;
        } catch (Exception e) {
            RepositorioJSONUtilidades.detallarExcepcion(e, "RepositorioDiagnosticoJSON");
            return null;
        }
    }
}
