package hospital.repositorio;

import hospital.controlador.lista.ListaEnlazada;
import hospital.modelo.Examen;
import hospital.modelo.excepciones.ModeloException;

public interface RepositorioExamen {

    /**
     * Método para crear un Examen
     * @param examen
     * @return
     * @throws ModeloException 
     */
    public Examen crear(Examen examen) throws ModeloException;

    /**
     * Método para eliminar un Examen
     * @param examen
     * @return
     * @throws ModeloException 
     */
    public boolean eliminar(Examen examen) throws ModeloException;

    /**
     * Método para encontrar todos los exámentes asociados a una consulta medica
     * @param idConsultaMedica
     * @return
     * @throws ModeloException 
     */
    public ListaEnlazada<Examen> encontrarTodoPorIdConsultaMedica(Integer idConsultaMedica) throws ModeloException;
}
