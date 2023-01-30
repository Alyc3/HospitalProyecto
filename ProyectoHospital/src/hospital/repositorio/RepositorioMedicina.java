package hospital.repositorio;

import hospital.controlador.lista.ListaEnlazada;
import hospital.modelo.Medicina;
import hospital.modelo.excepciones.ModeloException;

public interface RepositorioMedicina {

    /**
     * Método para crear Medicina
     * @param medicina
     * @return
     * @throws ModeloException 
     */
    public Medicina crear(Medicina medicina) throws ModeloException;

    /**
     * Método para eliminar una Medicina
     * @param medicina
     * @return
     * @throws ModeloException 
     */
    public boolean eliminar(Medicina medicina) throws ModeloException;

    /**
     * Método para encontrar todas las medicinas asociadas a una consulta medica
     * @param idConsultaMedica
     * @return
     * @throws ModeloException 
     */
    public ListaEnlazada<Medicina> encontrarTodoPorIdConsultaMedica(Integer idConsultaMedica) throws ModeloException;
}
