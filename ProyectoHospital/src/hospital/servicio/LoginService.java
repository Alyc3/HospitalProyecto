package hospital.servicio;

import hospital.modelo.Cuenta;
import hospital.modelo.excepciones.ModeloException;
import hospital.repositorio.CuentaRepository;

public class LoginService {

    private CuentaRepository cuentaRepository;

    public LoginService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    /**
     * Método para validar el login por medio de la clave del usuario
     * @param usuario
     * @param clave
     * @return
     * @throws ModeloException 
     */
    public Cuenta execute(String usuario, String clave) throws ModeloException {
        Cuenta cuenta = cuentaRepository.encontrarPorUsuario(usuario); // Puede lanzar una ApplicationException.

        // [*] todavía no se estan comparando los hash.
        if (clave == cuenta.getClave()) {
            return cuenta;
        } else {
            throw new ModeloException("");
        }
    }
}
