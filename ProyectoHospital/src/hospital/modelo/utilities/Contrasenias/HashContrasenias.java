package hospital.modelo.utilities.Contrasenias;

import de.mkammerer.argon2.*;

/**
 * Utilización de cifrador de contraseñas Argon2 para la encriptación de las
 * contraseñas de los usuarios
 */
public class HashContrasenias {

    private static final Argon2Factory.Argon2Types algoritmo = Argon2Factory.Argon2Types.ARGON2id;
    private static final int salidaPredefinida = 16; // Salida de 16 bytes 
    private static final int hashPredefinido = 32; // Hash generado: 32 bytes
    private static final int iteraciones = 10; 	// Número de iteraciones.
    private static final int memoria = 1 << 16; 	// Cantidad de memoria usada por el algoritmo.
    private static final int paralelismo = 1;	// Número de hilos usados por el algoritmo.
    private static Argon2 argon2;

    public HashContrasenias() {
        argon2 = Argon2Factory.create(algoritmo, salidaPredefinida, hashPredefinido);
    }

    /**
     * Método para generar el hash de una contraseña
     *
     * @param password
     * @return
     */
    public String hash(char[] password) {
        return argon2.hash(iteraciones, memoria, paralelismo, password);
    }

    /**
     * Método que verifica si una contraseña es correcta comparandolo con el
     * hash de la contraseña real
     *
     * @param hash
     * @param contrasenia
     * @return
     */
    public boolean verificar(String hash, char[] contrasenia) {
        return argon2.verify(hash, contrasenia);
    }
}
