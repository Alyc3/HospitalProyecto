package hospital.repositorio;

import hospital.modelo.Persona;
import hospital.controlador.lista.ListaEnlazada;

public interface RepositorioPersona {

    /**
     * Método para crear una persona
     * @param persona
     * @return 
     */
    public Persona crear(Persona persona);

    /**
     * Método para actualizar una persona existente
     * @param persona
     * @return 
     */
    public Persona actualizar(Persona persona);

    /**
     * Método para encontrar a una persona por medio del Id
     * @param id
     * @return 
     */
    public Persona encontrarPorId(Integer id);

    /**
     * Método para encontrar a varias personas por medio del Id
     * @param ids
     * @return 
     */
    public ListaEnlazada<Persona> encontrarMuchosPorId(ListaEnlazada<Integer> ids);
}
