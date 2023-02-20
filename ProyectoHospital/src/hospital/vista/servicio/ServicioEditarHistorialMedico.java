package hospital.vista.servicio;

import hospital.modelo.HistorialMedico;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.controlador.json.repositorio.RepositorioHistorialMedico;

public class ServicioEditarHistorialMedico {

    private RepositorioHistorialMedico repositorioHistorialMedico;

    public ServicioEditarHistorialMedico() {
        this.repositorioHistorialMedico = ManagerComponentes.repositorioHistorialmedico;
    }

    /**
     * Método que permite modificar la información de un historial médico.
     *
     * @param historialMedico
     * @return
     * @throws ModeloException
     */
    public HistorialMedico execute(HistorialMedico historialMedico) throws ModeloException {
        if (historialMedico == null) {
            throw new ModeloException("El historial médico no puede ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        if (historialMedico.getId() == null) {
            throw new ModeloException("El id del historial médico a modificar no puede ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        HistorialMedico old = repositorioHistorialMedico.encontrarPorId(historialMedico.getId());
        if (old == null) {
            throw new ModeloException("No se encontró ese historial médico", ErrorType.ErrorObjetoNoEncontrado);
        }
        if (historialMedico.getFechaNacimiento() == null || !historialMedico.getFechaNacimiento().equals(old.getFechaNacimiento())) {
            throw new ModeloException("No puede modificar la fecha de nacimiento", ErrorType.ErrorOperacionNoValida);
        }
        if (historialMedico.getTipoSanguineo() == null || !historialMedico.getTipoSanguineo().equals(old.getTipoSanguineo())) {
            throw new ModeloException("No puede modificar el tipo sanguíneo", ErrorType.ErrorOperacionNoValida);
        }
        if (historialMedico.getPersona() == null || historialMedico.getPersona().getId() == null) {
            throw new ModeloException("No puede modificar el id de la persona asociada", ErrorType.ErrorOperacionNoValida);
        }
        if (historialMedico.getPersona().getId() != old.getPersona().getId()) {
            throw new ModeloException("No puede modificar el id de la persona asociada", ErrorType.ErrorOperacionNoValida);
        }

        historialMedico.validarPeso();
        historialMedico.validarEstatura();
        historialMedico.validarAntecedentes();
        historialMedico.validarAntecedentesFamiliares();
        historialMedico.validarAlergias();
        historialMedico.validarTratamientosVigentes();
        return repositorioHistorialMedico.actualizar(historialMedico);
    }
}
