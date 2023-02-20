package hospital.vista.servicio;

import hospital.modelo.ConsultaMedica;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.controlador.json.repositorio.RepositorioConsultaMedica;

public class ServicioCancelarConsultaMedica {

    private RepositorioConsultaMedica repositorioConsultaMedica;

    public ServicioCancelarConsultaMedica() {
        this.repositorioConsultaMedica = ManagerComponentes.repositorioConsultaMedica;
    }

    /**
     * Método que permite cancelar una consulta médica, siento posible hacerlo
     * solo cuando la consulta médica está en estado PENDIENTE
     *
     * @param idConsultaMedica
     * @return
     * @throws ModeloException
     */
    public ConsultaMedica execute(Integer idConsultaMedica) throws ModeloException {
        if (idConsultaMedica == null) {
            throw new ModeloException("El id de la consulta médica no debe ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        ConsultaMedica consultaMedica = repositorioConsultaMedica.encontrarPorId(idConsultaMedica);
        if (consultaMedica == null) {
            throw new ModeloException("La consulta médica no fue encontrada", ErrorType.ErrorObjetoNoEncontrado);
        }
        if (consultaMedica.getEstado().equals("PENDIENTE")) {
            consultaMedica.setEstado("CANCELADA");
        } else {
            throw new ModeloException("Solo las consultas médicas en estado PENDIENTE pueden ser canceladas", ErrorType.ErrorAccionNoPermitida);
        }
        consultaMedica = repositorioConsultaMedica.actualizar(consultaMedica);
        if (consultaMedica == null) {
            throw new ModeloException("La consulta médica no fue encontrada", ErrorType.ErrorObjetoNoEncontrado);
        }
        return consultaMedica;
    }
}
