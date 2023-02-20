package hospital.controlador.json.repositorio;

import hospital.modelo.Cuenta;
import hospital.controlador.lista.ListaEnlazada;
import hospital.modelo.excepciones.ModeloException;

public interface RepositorioCuenta {

    /**
     * Método para crear una cuenta
     * @param cuenta
     * @return 
     */
    public Cuenta crear(Cuenta cuenta) throws ModeloException;

    /**
     * Método para actualizar una cuenta existente
     * @param cuenta
     * @return 
     */
    public Cuenta actualizar(Cuenta cuenta) throws ModeloException;

    /**
     * Método para encontrar una cuenta por medio del Id
     * @param id
     * @return 
     */
    public Cuenta encontrarPorId(Integer id) throws ModeloException;

    /**
     * Método para encontrar una cuenta por medio del usuario
     * @param usuario
     * @return 
     */
    public Cuenta encontrarPorUsuario(String usuario) throws ModeloException;
}
