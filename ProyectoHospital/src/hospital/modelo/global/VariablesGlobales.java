package hospital.modelo.global;

import hospital.modelo.Rol;

/**
 * Contiene variables que deben ser accesibles por varios componentes del sistema
 * 
 */
public class VariablesGlobales {

	// Roles de los usuarios.
	public static final Rol ROL_PACIENTE = new Rol(1, "ROL-PACIENTE");
	public static final Rol ROL_MEDICO = new Rol(2, "ROL-MEDICO");
}