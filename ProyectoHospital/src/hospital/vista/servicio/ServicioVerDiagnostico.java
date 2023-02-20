package hospital.vista.servicio;

import hospital.modelo.ConsultaMedica;
import hospital.modelo.Diagnostico;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.controlador.json.repositorio.RepositorioConsultaMedica;
import hospital.controlador.json.repositorio.RepositorioDiagnostico;

// VerDiagnosticoService.
public class ServicioVerDiagnostico {

    private RepositorioConsultaMedica repositorioConsultaMedica;
    private RepositorioDiagnostico repositorioDiagnostico;

    public ServicioVerDiagnostico() {
        this.repositorioConsultaMedica = ManagerComponentes.repositorioConsultaMedica;
        this.repositorioDiagnostico = ManagerComponentes.repositorioDiagnostico;
    }

    /**
     * Método que obtiene los valores del Diagnostico para presentarlos Valida
     * que la id de la consulta no sea nula
     *
     * @param idConsultaMedica
     * @return
     * @throws ModeloException
     */
    public Diagnostico execute(Integer idConsultaMedica) throws ModeloException {
        if (idConsultaMedica == null) {
            throw new ModeloException("El id de la consulta médica no puede ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        ConsultaMedica consultaMedica = repositorioConsultaMedica.encontrarPorId(idConsultaMedica);
        if (consultaMedica == null) {
            throw new ModeloException("No se encontró una consulta médica con ese id", ErrorType.ErrorObjetoNoEncontrado);
        }
        Diagnostico diagnostico = repositorioDiagnostico.encontrarPorIdConsultaMedica(idConsultaMedica);
        if (diagnostico == null) {
            throw new ModeloException("No se encontró un diagnóstico asociado a esa consulta médica", ErrorType.ErrorObjetoNoEncontrado);
        }
        return diagnostico;
    }
}
