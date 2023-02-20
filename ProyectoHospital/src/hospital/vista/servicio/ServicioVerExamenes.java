package hospital.vista.servicio;

import hospital.controlador.lista.ListaEnlazada;
import hospital.modelo.ConsultaMedica;
import hospital.modelo.Examen;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.controlador.json.repositorio.RepositorioConsultaMedica;
import hospital.controlador.json.repositorio.RepositorioExamen;

public class ServicioVerExamenes {

    private RepositorioConsultaMedica repositorioConsultaMedica;
    private RepositorioExamen repositorioExamen;

    public ServicioVerExamenes() {
        this.repositorioConsultaMedica = ManagerComponentes.repositorioConsultaMedica;
        this.repositorioExamen = ManagerComponentes.repositorioExamen;
    }

    /**
     * Método que obtiene una consulta medica para presentarla
     * Valida que la id de la consulta no sea nulo
     * 
     * @param idConsultaMedica
     * @return
     * @throws ModeloException 
     */
    public ListaEnlazada<Examen> execute(Integer idConsultaMedica) throws ModeloException {
        if (idConsultaMedica == null) {
            throw new ModeloException("El id de la consulta médica no puede ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        ConsultaMedica consultaMedica = repositorioConsultaMedica.encontrarPorId(idConsultaMedica);
        if (consultaMedica == null) {
            throw new ModeloException("No se encontró una consulta médica con ese id", ErrorType.ErrorObjetoNoEncontrado);
        }
        return repositorioExamen.encontrarTodoPorIdConsultaMedica(idConsultaMedica);
    }
}
