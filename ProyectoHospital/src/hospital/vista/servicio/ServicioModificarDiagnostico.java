package hospital.vista.servicio;

import hospital.modelo.ConsultaMedica;
import hospital.modelo.Diagnostico;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.controlador.json.repositorio.RepositorioConsultaMedica;
import hospital.controlador.json.repositorio.RepositorioDiagnostico;

public class ServicioModificarDiagnostico {

    private RepositorioConsultaMedica repositorioConsultaMedica;
    private RepositorioDiagnostico repositorioDiagnostico;

    public ServicioModificarDiagnostico() {
        this.repositorioConsultaMedica = ManagerComponentes.repositorioConsultaMedica;
        this.repositorioDiagnostico = ManagerComponentes.repositorioDiagnostico;
    }

    /**
     * Método que permite Modificar un Diagnostico
     * valida que la consulta y el diagnostico no sean nulos
     * 
     * @param idConsultaMedica
     * @param diagnostico
     * @return
     * @throws ModeloException 
     */
    public Diagnostico execute(Integer idConsultaMedica, Diagnostico diagnostico) throws ModeloException {
        if (idConsultaMedica == null) {
            throw new ModeloException("El id de la consulta médica no puede ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        if (diagnostico == null) {
            throw new ModeloException("El diagnóstico no puede ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        ConsultaMedica consultaMedica = repositorioConsultaMedica.encontrarPorId(idConsultaMedica);
        if (consultaMedica == null) {
            throw new ModeloException("No existe una consulta médica con ese id", ErrorType.ErrorObjetoNoEncontrado);
        }
        diagnostico.validarRecomendacion();
        Diagnostico diagnosticoOld = repositorioDiagnostico.encontrarPorIdConsultaMedica(idConsultaMedica);
        if (diagnostico == null) {
            throw new ModeloException("Esa consulta médica no tiene asociado un diagnóstico", ErrorType.ErrorObjetoNoEncontrado);
        }
        diagnostico.setId(diagnosticoOld.getId());
        diagnostico.setConsultaMedica(consultaMedica);
        return repositorioDiagnostico.actualizar(diagnostico);
    }
}
