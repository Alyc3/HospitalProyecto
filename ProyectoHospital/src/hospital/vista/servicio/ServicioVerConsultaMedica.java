package hospital.vista.servicio;

import hospital.modelo.ConsultaMedica;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.controlador.json.repositorio.RepositorioConsultaMedica;

public class ServicioVerConsultaMedica {

    private RepositorioConsultaMedica repositorioConsultaMedica;

    public ServicioVerConsultaMedica() {
        this.repositorioConsultaMedica = ManagerComponentes.repositorioConsultaMedica;
    }

    /**
     * Método que permite encontrar una consulta médica por su id
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
        return consultaMedica;
    }
}
