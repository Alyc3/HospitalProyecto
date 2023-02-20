package hospital.controlador.json.repositorio;

import hospital.modelo.Persona;
import hospital.controlador.lista.ListaEnlazada;
import hospital.modelo.excepciones.ModeloException;

public interface RepositorioPersona {

    /**
     * Método para crear una persona
     * @param persona
     * @return 
     */
    public Persona crear(Persona persona) throws ModeloException;

    /**
     * Método para actualizar una persona existente
     * @param persona
     * @return 
     */
    public Persona actualizar(Persona persona) throws ModeloException;

    /**
     * Método para encontrar a una persona por medio del Id
     * @param id
     * @return 
     */
    public Persona encontrarPorId(Integer id) throws ModeloException;

    /**
     * Método para encontrar a una persona por medio de su Cedula
     * @param cedula
     * @return
     * @throws ModeloException 
     */
    public Persona encontrarPorCedula(String cedula) throws ModeloException;
    
    /**
     * Método para encontrar a varias personas por medio del Id
     * @param ids
     * @return 
     */
    public ListaEnlazada<Persona> encontrarMuchosPorId(ListaEnlazada<Integer> ids) throws ModeloException;
}
