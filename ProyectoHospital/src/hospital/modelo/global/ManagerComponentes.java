package hospital.modelo.global;

import hospital.repositorio.json.RepositorioCuentaJSON;
import hospital.repositorio.json.RepositorioHistorialMedicoJSON;
import hospital.repositorio.json.RepositorioPersonaJSON;
import hospital.servicio.ServicioLogin;
import hospital.servicio.ServicioRegistrarPersona;
import hospital.servicio.Servicios;
import java.io.FileNotFoundException;
import hospital.repositorio.RepositorioConsultaMedica;
import hospital.repositorio.RepositorioCuenta;
import hospital.repositorio.RepositorioHistorialMedico;
import hospital.repositorio.RepositorioPersona;

public class ManagerComponentes {
    // Repositorios

    public static RepositorioCuenta cuentaRepository;
    public static RepositorioPersona personaRepository;
    public static RepositorioHistorialMedico historialMedicoRepository;
    public static RepositorioConsultaMedica consultaMedicaRepository;
    //public static DiagnosticoRepository diagnosticoRepository;
    //public static MedicinaRepository medicinaRepository;
    //public static ExamenRepository examenRepository;

    // Servicios
    public static ServicioRegistrarPersona registrarPersonaService;
    public static ServicioLogin loginService;
    //public static EditarPerfilService;
    //public static VerPerfilPersonaService;
    //public static VerInformacionMedicaService;
    //public static EditarInformacionMedicaService;
    //public static GetConsultasMedicasService
    //public static GetListOfPacientesPendientesService
    //public static EncontrarConsultaPendientePacienteService
    //public static CrearConsultaMedicaService
    //public static CancelarConsultaMedicaService
    //public static VerConsultaMedicaService
    //public static ModificarRecomenedacionConsultaService
    //public static AgregarExamenService
    //public static EliminarExamenService
    //public static VerExamenesService
    //public static AgregarMedicinaService
    //public static EliminarMedicinaService
    //public static VerMedicinasService
    //public static FinalizarConsultaMedica
    public static Servicios services;

    public static void init() throws FileNotFoundException {
        cuentaRepository = new RepositorioCuentaJSON();
        personaRepository = new RepositorioPersonaJSON();
        historialMedicoRepository = new RepositorioHistorialMedicoJSON();

        loginService = new ServicioLogin(cuentaRepository);
        registrarPersonaService = new ServicioRegistrarPersona(cuentaRepository, personaRepository, historialMedicoRepository);

        services = new Servicios();
        services.setLoginService(loginService);
        services.setRegistrarPersonaService(registrarPersonaService);
    }
}
