package hospital.vista.servicio;

import hospital.modelo.HistorialMedico;
import hospital.modelo.Persona;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.controlador.json.repositorio.RepositorioHistorialMedico;
import hospital.controlador.json.repositorio.RepositorioPersona;

public class ServicioVerHistorialMedico {

    private RepositorioPersona repositorioPersona;
    private RepositorioHistorialMedico repositorioHistorialMedico;

    public ServicioVerHistorialMedico() {
        this.repositorioPersona = ManagerComponentes.repositorioPersona;
        this.repositorioHistorialMedico = ManagerComponentes.repositorioHistorialmedico;
    }

    /**
     * Método que permite encontrar el historial médico asociado a una persona
     *
     * @param idPersona
     * @return
     * @throws ModeloException
     */
    public HistorialMedico execute(Integer idPersona) throws ModeloException {
        if (idPersona == null) {
            throw new ModeloException("El id de la persona no puede ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        Persona persona = repositorioPersona.encontrarPorId(idPersona);
        if (persona == null) {
            throw new ModeloException("No se encontró una persona registrada con ese id", ErrorType.ErrorObjetoNoEncontrado);
        }
        HistorialMedico historialMedico = repositorioHistorialMedico.encontrarPorIdPersona(idPersona);
        if (historialMedico == null) {
            throw new ModeloException("No se encontró un historial médico asociado a esa persona", ErrorType.ErrorObjetoNoEncontrado);
        }
        return historialMedico;
    }
}
