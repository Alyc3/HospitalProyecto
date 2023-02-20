package hospital.vista.servicio;

import hospital.modelo.Cuenta;
import hospital.modelo.Persona;
import hospital.modelo.Rol;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.modelo.utilities.Contrasenias.HashContrasenias;
import hospital.controlador.json.repositorio.RepositorioCuenta;
import hospital.controlador.json.repositorio.RepositorioPersona;

public class ServicioLogin {

    private HashContrasenias hashContrasenias;
    private RepositorioCuenta repositorioCuenta;
    private RepositorioPersona repositorioPersona;

    public ServicioLogin() {
        this.hashContrasenias = ManagerComponentes.hashContrasenias;
        this.repositorioCuenta = ManagerComponentes.repositorioCuenta;
        this.repositorioPersona = ManagerComponentes.repositorioPersona;
    }

    /**
     * Método para validar que el rol, usuario y clave no sean nulos
     *
     * @param rol
     * @param usuario
     * @param clave
     * @return
     * @throws ApplicationException
     */
    public Cuenta execute(Rol rol, String usuario, String clave) throws ModeloException {
        if (rol == null) {
            throw new ModeloException("Debe ingresar el rol", ErrorType.ErrorArchivoRequerido);
        }
        if (usuario == null || usuario.equals("")) {
            throw new ModeloException("Debe ingresar el usuario", ErrorType.ErrorArchivoRequerido);
        }
        if (clave == null || clave.equals("")) {
            throw new ModeloException("Debe ingresar la clave", ErrorType.ErrorArchivoRequerido);
        }
        Cuenta cuenta = repositorioCuenta.encontrarPorUsuario(usuario);
        if (cuenta == null) {
            throw new ModeloException("Los datos ingresados son incorrectos", ErrorType.ErrorAutenticacion);
        }
        Persona persona = repositorioPersona.encontrarPorId(cuenta.getPersona().getId());
        if (!rol.equals(persona.getRol())) {
            throw new ModeloException("Los datos ingresados son incorrectos", ErrorType.ErrorAutenticacion);
        }
        String hash = cuenta.getClave();
        if (hashContrasenias.verificar(hash, clave.toCharArray())) {
            cuenta.setClave(null);
            return cuenta;
        } else {
            throw new ModeloException("Los datos ingresados son incorrectos", ErrorType.ErrorAutenticacion);
        }
    }
}
