package hospital.vista.servicio;

import hospital.modelo.Cuenta;
import hospital.modelo.HistorialMedico;
import hospital.modelo.Medico;
import hospital.modelo.Persona;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.modelo.global.VariablesGlobales;
import hospital.modelo.utilities.Contrasenias.HashContrasenias;
import hospital.controlador.json.repositorio.RepositorioCuenta;
import hospital.controlador.json.repositorio.RepositorioHistorialMedico;
import hospital.controlador.json.repositorio.RepositorioPersona;

public class ServicioRegistrarPersona {

    private HashContrasenias hashContrasenias;
    private RepositorioCuenta repositorioCuenta;
    private RepositorioPersona repositorioPersona;
    private RepositorioHistorialMedico repositorioHistorialMedico;

    public ServicioRegistrarPersona() {
        this.hashContrasenias = ManagerComponentes.hashContrasenias;
        this.repositorioCuenta = ManagerComponentes.repositorioCuenta;
        this.repositorioPersona = ManagerComponentes.repositorioPersona;
        this.repositorioHistorialMedico = ManagerComponentes.repositorioHistorialmedico;
    }

    /**
     * Método para validar que la cuenta, persona e historial médico no sean
     * nulos al registrar una nueva persona
     *
     * @param cuenta
     * @param persona
     * @param historialMedico
     * @return
     * @throws ModeloException
     */
    public Cuenta execute(Cuenta cuenta, Persona persona, HistorialMedico historialMedico) throws ModeloException {
        if (cuenta == null) {
            throw new ModeloException("La cuenta no puede ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        if (persona == null) {
            throw new ModeloException("La persona no puede ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        if (historialMedico == null) {
            throw new ModeloException("El historial médico no puede ser null", ErrorType.ErrorValorNuloNoPermitido);
        }

        persona.validarRol();
        persona.validarCedula();
        persona.validarNombre();
        persona.validarApellido();
        persona.validarDireccion();
        persona.validarEdad();
        persona.validarGenero();
        persona.validarCorreo();
        persona.validarTelefono();

        if (Medico.class.isInstance(persona)) {
            Medico medico = (Medico) persona;
            medico.validarEspecialidad();
        }

        cuenta.validarUsuario();
        if (persona.getRol().equals(VariablesGlobales.ROL_MEDICO)) {
            cuenta.validarClave();
        }

        historialMedico.validarFechaNacimiento();
        if (persona.getRol().equals(VariablesGlobales.ROL_MEDICO)) {
            if (historialMedico.getTipoSanguineo() != null) {
                throw new ModeloException("El tipo sanguíneo no debe ser establecido", ErrorType.ErrorOperacionNoValida);
            }
        } else {
            historialMedico.validarTipoSanguineo();
        }
        if (historialMedico.getPeso() != null) {
            throw new ModeloException("El peso no debe ser establecido", ErrorType.ErrorOperacionNoValida);
        }
        if (historialMedico.getEstatura() != null) {
            throw new ModeloException("La estatura no debe ser establecida", ErrorType.ErrorOperacionNoValida);
        }
        if (historialMedico.getAntecedentes() != null && !historialMedico.getAntecedentes().equals("")) {
            throw new ModeloException("Los antecedentes no deben ser establecidos", ErrorType.ErrorOperacionNoValida);
        }
        if (historialMedico.getAlergias() != null && !historialMedico.getAlergias().equals("")) {
            throw new ModeloException("Las alergías no deben ser establecidas", ErrorType.ErrorOperacionNoValida);
        }
        if (historialMedico.getTratamientosVigentes() != null && !historialMedico.getTratamientosVigentes().equals("")) {
            throw new ModeloException("Los tratamientos vigentes no deben ser establecidos", ErrorType.ErrorOperacionNoValida);
        }
        if (historialMedico.getAntecedentesFamiliares() != null && !historialMedico.getAntecedentesFamiliares().equals("")) {
            throw new ModeloException("Los antecedentes familiares no deben ser establecidos", ErrorType.ErrorOperacionNoValida);
        }
        if (persona.getRol().equals(VariablesGlobales.ROL_PACIENTE)) {
            cuenta.setClave(persona.getCedula().substring(persona.getCedula().length() - 5));
        }
        if (repositorioCuenta.encontrarPorUsuario(cuenta.getUsuario()) != null) {
            throw new ModeloException("Ya existe una cuenta con ese usuario", ErrorType.ErrorRestriccionUnicidad);
        }
        if (repositorioPersona.encontrarPorCedula(persona.getCedula()) != null) {
            throw new ModeloException("Ya hay una persona registrada con esa cédula", ErrorType.ErrorRestriccionUnicidad);
        }
        String hash = hashContrasenias.hash(cuenta.getClave().toCharArray());
        cuenta.setClave(hash);
        Persona personaNueva = repositorioPersona.crear(persona);
        historialMedico.setPersona(personaNueva);
        repositorioHistorialMedico.crear(historialMedico);
        cuenta.setPersona(personaNueva);
        Cuenta cuentaNueva = repositorioCuenta.crear(cuenta);
        cuentaNueva.setClave(null);
        return cuentaNueva;
    }
}
