package hospital.repositorio;

import hospital.modelo.ConsultaMedica;
import hospital.modelo.excepciones.ModeloException;
import hospital.controlador.lista.ListaEnlazada;

public interface RepositorioConsultaMedica {

    /**
     * Método para crear una Consulta Médica
     * @param consultaMedica
     * @return
     * @throws ModeloException 
     */
    public ConsultaMedica crear(ConsultaMedica consultaMedica) throws ModeloException;

    /**
     * Método para actualizar una consulta médica
     * @param consultaMedica
     * @return
     * @throws ModeloException 
     */
    public ConsultaMedica actualizar(ConsultaMedica consultaMedica) throws ModeloException;

    /**
     * Método para encontrar todas las consultas medcias asociadas a dterminado
     * Historial Medico
     * @param idHistorialMedico
     * @return
     * @throws ModeloException 
     */
    public ListaEnlazada<ConsultaMedica> encontrarTodosPorIdHistorialMedico(Integer idHistorialMedico) throws ModeloException;

    /**
     * Método para encontrar todas las consulta médicas que se encuentren en
     * estado pendiente
     * @return
     * @throws ModeloException 
     */
    public ListaEnlazada<ConsultaMedica> encontrarTodosConEstadoPendiente() throws ModeloException;

    /**
     * Método para encontrar todas las consultas médicas asociadas a un historial
     * medico cuyo estado sea pendiente
     * @param idHistorialMedico
     * @return
     * @throws ModeloException 
     */
    public ConsultaMedica encontrarPorIdHistorialMedicoConEstadoPendiente(Integer idHistorialMedico) throws ModeloException;
}
