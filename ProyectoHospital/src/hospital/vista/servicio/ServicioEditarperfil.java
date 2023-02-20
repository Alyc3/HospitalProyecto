package hospital.vista.servicio;

import hospital.modelo.Cuenta;
import hospital.modelo.Medico;
import hospital.modelo.Persona;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.modelo.global.VariablesGlobales;
import hospital.controlador.json.repositorio.RepositorioCuenta;
import hospital.controlador.json.repositorio.RepositorioPersona;

public class ServicioEditarperfil {

    private RepositorioCuenta repositorioCuenta;
    private RepositorioPersona repositorioPersona;

    public ServicioEditarperfil() {
        this.repositorioCuenta = ManagerComponentes.repositorioCuenta;
        this.repositorioPersona = ManagerComponentes.repositorioPersona;
    }

    /**
     * Método que permite actualizar la información de perfil de un usuario
     * 
     * @param cuenta
     * @param persona
     * @return
     * @throws ModeloException 
     */
    public Cuenta execute(Cuenta cuenta, Persona persona) throws ModeloException {
        if (cuenta == null) {
            throw new ModeloException("La cuenta no puede ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        if (persona == null) {
            throw new ModeloException("La persona no puede ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        if (persona.getId() == null) {
            throw new ModeloException("El id de la persona a modificar no puede ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        Cuenta cuentaOld = repositorioCuenta.encontrarPorId(cuenta.getId());
        if (cuentaOld == null) {
            throw new ModeloException("No se encontró una cuenta con el respectivo id", ErrorType.ErrorObjetoNoEncontrado);
        }
        Persona personaOld = repositorioPersona.encontrarPorId(persona.getId());
        if (personaOld == null) {
            throw new ModeloException("No se encontró una persona registrada con el respectivo id", ErrorType.ErrorObjetoNoEncontrado);
        }
        if (cuenta.getUsuario() != null && !cuenta.getUsuario().equals("")) {
            cuenta.validarUsuario();
        }
        if (cuenta.getClave() != null && !cuenta.getClave().equals("")) {
            cuenta.validarClave();
        }
        if (cuenta.getPersona() == null || cuenta.getPersona().getId() == null) {
            throw new ModeloException("No puede modificar el id de la persona asociada", ErrorType.ErrorOperacionNoValida);
        }
        if (cuenta.getPersona().getId() != cuentaOld.getPersona().getId()) {
            throw new ModeloException("No puede modificar el id de la persona asociada", ErrorType.ErrorOperacionNoValida);
        }
        if (persona.getRol() == null || !persona.getRol().equals(personaOld.getRol())) {
            throw new ModeloException("No puede modificar el rol", ErrorType.ErrorOperacionNoValida);
        }
        if (persona.getCedula() == null || !persona.getCedula().equals(personaOld.getCedula())) {
            throw new ModeloException("No puede modificar la cédula", ErrorType.ErrorOperacionNoValida);
        }
        if (persona.getGenero() == null || !persona.getGenero().equals(personaOld.getGenero())) {
            throw new ModeloException("No puede modificar el género", ErrorType.ErrorOperacionNoValida);
        }

        persona.validarNombre();
        persona.validarApellido();
        persona.validarDireccion();
        persona.validarEdad();
        persona.validarCorreo();
        persona.validarTelefono();
        if (persona.getRol().equals(VariablesGlobales.ROL_MEDICO)) {
            Medico medico = (Medico) persona;
            medico.validarEspecialidad();
        }
        if (cuenta.getUsuario() != null && !cuenta.getUsuario().equals("")) {
            cuentaOld.setUsuario(cuenta.getUsuario());
        }
        if (cuenta.getClave() != null && !cuenta.getClave().equals("")) {
        }
        repositorioPersona.actualizar(persona);
        return repositorioCuenta.actualizar(cuentaOld);
    }
}
