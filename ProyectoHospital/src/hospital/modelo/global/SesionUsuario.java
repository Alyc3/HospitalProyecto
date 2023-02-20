package hospital.modelo.global;

import hospital.modelo.Cuenta;
import hospital.modelo.Persona;

/**
 * Contiene variables que deben inicializarse al momento en que el usuario
 * inicia sesión, y deben ser accesibles desde varios componentes del sistema
 */
public class SesionUsuario {

    public static Cuenta cuenta;
    public static Persona persona;
}
