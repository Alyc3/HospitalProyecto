package hospital.vista.servicio;

import hospital.modelo.ConsultaMedica;
import hospital.modelo.HistorialMedico;
import hospital.modelo.Persona;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.modelo.global.VariablesGlobales;
import hospital.controlador.json.repositorio.RepositorioConsultaMedica;
import hospital.controlador.json.repositorio.RepositorioHistorialMedico;
import hospital.controlador.json.repositorio.RepositorioPersona;

public class ServicioEncontrarConsultaMedicaPendientePaciente {

    private RepositorioPersona repositorioPersona;
    private RepositorioHistorialMedico repositorioHistorialMedico;
    private RepositorioConsultaMedica repositorioConsultaMedica;

    public ServicioEncontrarConsultaMedicaPendientePaciente() {
        this.repositorioPersona = ManagerComponentes.repositorioPersona;
        this.repositorioHistorialMedico = ManagerComponentes.repositorioHistorialmedico;
        this.repositorioConsultaMedica = ManagerComponentes.repositorioConsultaMedica;
    }

    /**
     * Método que permite encontrar la consulta médica pendiente de un paciente
     *
     * @param idPaciente
     * @return
     * @throws ModeloException
     */
    public ConsultaMedica execute(Integer idPaciente) throws ModeloException {
        if (idPaciente == null) {
            throw new ModeloException("El id del paciente no debe ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        Persona persona = repositorioPersona.encontrarPorId(idPaciente);
        if (persona == null) {
            throw new ModeloException("No hay una persona registrada con ese id", ErrorType.ErrorObjetoNoEncontrado);
        }
        if (!persona.getRol().equals(VariablesGlobales.ROL_PACIENTE)) {
            throw new ModeloException("El id no corresponde al de un paciente", ErrorType.ErrorAccionNoPermitida);
        }
        HistorialMedico historialMedico = repositorioHistorialMedico.encontrarPorIdPersona(persona.getId());
        ConsultaMedica consultaMedica = repositorioConsultaMedica.encontrarPorIdHistorialMedicoConEstadoPendiente(historialMedico.getId());
        return consultaMedica;
    }
}
