package hospital.controlador.json.repositorio;

import hospital.controlador.lista.ListaEnlazada;
import hospital.modelo.HistorialMedico;
import hospital.modelo.excepciones.ModeloException;

public interface RepositorioHistorialMedico {

    /**
     * Método para crear un Historial Médico
     * @param historialMedico
     * @return
     * @throws ModeloException 
     */
    public HistorialMedico crear(HistorialMedico historialMedico) throws ModeloException;

    /**
     * Método para actualizar un Historial Médico
     * @param historialMedico
     * @return
     * @throws ModeloException 
     */
    public HistorialMedico actualizar(HistorialMedico historialMedico) throws ModeloException;

    /**
     * Método para encontrar un Historial Médico por medio de su id
     * @param id
     * @return
     * @throws ModeloException 
     */
    public HistorialMedico encontrarPorId(Integer id) throws ModeloException;

    /**
     * Método para encontrar un Historial Médico por medio del id de la persona
     * a la que se encuentra asociado
     * @param idPersona
     * @return
     * @throws ModeloException 
     */
    public HistorialMedico encontrarPorIdPersona(Integer idPersona) throws ModeloException;
    
    /**
     * Método para encontrar varios Historiales Medicos por medio de su ID
     * @param ids
     * @return
     * @throws ModeloException 
     */
    public ListaEnlazada<HistorialMedico> encontrarMuchosPorId(ListaEnlazada<Integer> ids) throws ModeloException;
}
