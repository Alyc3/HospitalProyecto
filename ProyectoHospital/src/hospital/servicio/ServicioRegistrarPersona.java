package hospital.servicio;

import hospital.modelo.Cuenta;
import hospital.modelo.HistorialMedico;
import hospital.modelo.Persona;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.repositorio.RepositorioCuenta;
import hospital.repositorio.RepositorioHistorialMedico;
import hospital.repositorio.RepositorioPersona;

public class ServicioRegistrarPersona {

    private RepositorioCuenta cuentaRepository;
    private RepositorioPersona personaRepository;
    private RepositorioHistorialMedico historialMedicoRepository;

    public ServicioRegistrarPersona(RepositorioCuenta cuentaRepository, RepositorioPersona personaRepository, RepositorioHistorialMedico historialMedicoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.personaRepository = personaRepository;
        this.historialMedicoRepository = historialMedicoRepository;
    }

    public Cuenta execute(Cuenta cuenta, Persona persona, HistorialMedico historialMedico) throws ModeloException {
        System.out.println("....Logueando");
        if (cuentaRepository.encontrarPorUsuario(cuenta.getUsuario()) != null) {
            throw new ModeloException(ErrorType.ErrorRestriccionUnicidad);
        }
        if (personaRepository.encontrarPorCedula(persona.getCedula()) != null) {
            throw new ModeloException(ErrorType.ErrorRestriccionUnicidad);
        }

        persona = personaRepository.crear(persona);
        cuenta.setPersona(persona);
        cuentaRepository.crear(cuenta);
        historialMedico.setPersona(persona);
        historialMedicoRepository.crear(historialMedico);

        return cuenta;
    }
}
