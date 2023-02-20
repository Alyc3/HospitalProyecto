package hospital.repositorio;

import hospital.modelo.Cuenta;
import hospital.controlador.lista.ListaEnlazada;

public interface RepositorioCuenta {

    /**
     * Método para crear una cuenta
     * @param cuenta
     * @return 
     */
    public Cuenta crear(Cuenta cuenta);

    /**
     * Método para actualizar una cuenta existente
     * @param cuenta
     * @return 
     */
    public Cuenta actualizar(Cuenta cuenta);

    /**
     * Método para encontrar una cuenta por medio del Id
     * @param id
     * @return 
     */
    public Cuenta encontrarPorId(Integer id);

    /**
     * Método para encontrar una cuenta por medio del usuario
     * @param usuario
     * @return 
     */
    public Cuenta encontrarPorUsuario(String usuario);
}
