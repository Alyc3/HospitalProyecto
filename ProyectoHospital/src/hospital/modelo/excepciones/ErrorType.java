package hospital.modelo.excepciones;

/**
 * Contiene el listado de errores que pueden presentarse para mejor manejo de
 * los mismos
 *
 * @author Usuario iTC
 */
public enum ErrorType {

    ErrorDesconocido(false),
    ErrorAccesoDatos(false),
    ErrorDatosCorruptos(false),
    ErrorTimeOutExpired(false),
    ErrorValorNuloNoPermitido(false),
    ErrorOperacionNoValida(false),
    ErrorValorNoValido(true),
    ErrorAutenticacion(true),
    ErrorAccionNoPermitida(true),
    ErrorArchivoRequerido(true),
    ErrorValorNoPermitido(true),
    ErrorFormatoInvalido(true),
    ErrorObjetoNoEncontrado(true),
    ErrorRestriccionUnicidad(true);

    private final boolean errorUsuario;

    private ErrorType(boolean errorUsuario) {
        this.errorUsuario = errorUsuario;
    }

    // Indica si es o no, un error que es causado por el usuario, como lo
    // es ingresar datos inválidos dentro de un formulario.
    /*public boolean isUserError(){
		return userError;
	}

	// Indica si es o no, un error que es causado por un error 
	// , alguna inconsistencia dentro del sistema
	public boolean isInternalError(){
		return !userError;
	}*/
}
