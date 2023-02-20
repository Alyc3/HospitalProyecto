package hospital.modelo.global;

import hospital.modelo.utilities.Contrasenias.HashContrasenias;
import hospital.controlador.json.repositorio.json.RepositorioCuentaJSON;
import hospital.controlador.json.repositorio.json.RepositorioHistorialMedicoJSON;
import hospital.controlador.json.repositorio.json.RepositorioPersonaJSON;
import hospital.vista.servicio.ServicioLogin;
import hospital.vista.servicio.ServicioRegistrarPersona;
import hospital.vista.servicio.Servicios;
import java.io.FileNotFoundException;
import hospital.controlador.json.repositorio.RepositorioConsultaMedica;
import hospital.controlador.json.repositorio.RepositorioCuenta;
import hospital.controlador.json.repositorio.RepositorioDiagnostico;
import hospital.controlador.json.repositorio.RepositorioExamen;
import hospital.controlador.json.repositorio.RepositorioHistorialMedico;
import hospital.controlador.json.repositorio.RepositorioPersona;
import hospital.controlador.json.repositorio.json.RepositorioConsultaMedicaJSON;
import hospital.controlador.json.repositorio.json.RepositorioDiagnosticoJSON;
import hospital.controlador.json.repositorio.json.RepositorioExamenJSON;
import hospital.vista.servicio.ServicioAgendarConsultaMedica;
import hospital.vista.servicio.ServicioAgregarExamen;
import hospital.vista.servicio.ServicioCancelarConsultaMedica;
import hospital.vista.servicio.ServicioEditarHistorialMedico;
import hospital.vista.servicio.ServicioEditarperfil;
import hospital.vista.servicio.ServicioEncontrarConsultaMedicaPendientePaciente;
import hospital.vista.servicio.ServicioFinalizarConsultaMedica;
import hospital.vista.servicio.ServicioListarConsultasMedicasPorPaciente;
import hospital.vista.servicio.ServicioListarPacientesPendientes;
import hospital.vista.servicio.ServicioModificarDiagnostico;
import hospital.vista.servicio.ServicioVerConsultaMedica;
import hospital.vista.servicio.ServicioVerDiagnostico;
import hospital.vista.servicio.ServicioVerExamenes;
import hospital.vista.servicio.ServicioVerHistorialMedico;
import hospital.vista.servicio.ServicioVerInformacionPersona;

/**
 * Permite la inicialización y manejo de todos los servicios proporcionados
 */
public class ManagerComponentes {

    public static HashContrasenias hashContrasenias;

    // Repositorios
    public static RepositorioCuenta repositorioCuenta;
    public static RepositorioPersona repositorioPersona;
    public static RepositorioHistorialMedico repositorioHistorialmedico;
    public static RepositorioConsultaMedica repositorioConsultaMedica;
    public static RepositorioDiagnostico repositorioDiagnostico;
    public static RepositorioExamen repositorioExamen;

    // Servicios
    public static ServicioRegistrarPersona servicioRegistrarPersona;
    public static ServicioLogin servicioLogin;
    public static ServicioEditarperfil servicioEditarPerfil;
    public static ServicioVerInformacionPersona servicioVerInformacionPersona;
    public static ServicioEditarHistorialMedico servicioEditarHistorialMedico;
    public static ServicioVerHistorialMedico servicioVerHistorialMedico;
    public static ServicioAgendarConsultaMedica servicioAgendarConsultaMedica;
    public static ServicioVerConsultaMedica servicioVerConsultaMedica;
    public static ServicioCancelarConsultaMedica servicioCancelarConsultaMedica;
    public static ServicioFinalizarConsultaMedica servicioFinalizarConsultaMedica;
    public static ServicioListarConsultasMedicasPorPaciente servicioListarConsultasMedicasPorPaciente;
    public static ServicioListarPacientesPendientes servicioListarPacientesPendientes;
    public static ServicioEncontrarConsultaMedicaPendientePaciente servicioEncontrarConsultaMedicaPendientePaciente;
    public static ServicioModificarDiagnostico servicioModificarDiagnostico;
    public static ServicioVerDiagnostico servicioVerDiagnostico;
    public static ServicioAgregarExamen servicioAgregarExamen;
    public static ServicioVerExamenes servicioVerExamenes;
    public static Servicios servicios;

    public static void init() throws FileNotFoundException {
        hashContrasenias = new HashContrasenias();

        repositorioCuenta = new RepositorioCuentaJSON();
        repositorioPersona = new RepositorioPersonaJSON();
        repositorioHistorialmedico = new RepositorioHistorialMedicoJSON();
        repositorioConsultaMedica = new RepositorioConsultaMedicaJSON();
        repositorioDiagnostico = new RepositorioDiagnosticoJSON();
        repositorioExamen = new RepositorioExamenJSON();

        servicioLogin = new ServicioLogin();
        servicioRegistrarPersona = new ServicioRegistrarPersona();
        servicioEditarPerfil = new ServicioEditarperfil();
        servicioVerInformacionPersona = new ServicioVerInformacionPersona();
        servicioEditarHistorialMedico = new ServicioEditarHistorialMedico();
        servicioVerHistorialMedico = new ServicioVerHistorialMedico();
        servicioAgendarConsultaMedica = new ServicioAgendarConsultaMedica();
        servicioVerConsultaMedica = new ServicioVerConsultaMedica();
        servicioCancelarConsultaMedica = new ServicioCancelarConsultaMedica();
        servicioFinalizarConsultaMedica = new ServicioFinalizarConsultaMedica();
        servicioListarConsultasMedicasPorPaciente = new ServicioListarConsultasMedicasPorPaciente();
        servicioListarPacientesPendientes = new ServicioListarPacientesPendientes();
        servicioEncontrarConsultaMedicaPendientePaciente = new ServicioEncontrarConsultaMedicaPendientePaciente();
        servicioModificarDiagnostico = new ServicioModificarDiagnostico();
        servicioVerDiagnostico = new ServicioVerDiagnostico();
        servicioAgregarExamen = new ServicioAgregarExamen();
        servicioVerExamenes = new ServicioVerExamenes();

        servicios = new Servicios();
        servicios.setServicioLogin(servicioLogin);
        servicios.setServicioRegistrarPersona(servicioRegistrarPersona);
        servicios.setServicioEditarperfil(servicioEditarPerfil);
        servicios.setServicioVerInformacionPersona(servicioVerInformacionPersona);
        servicios.setServicioEditarHistorialMedico(servicioEditarHistorialMedico);
        servicios.setServicioVerHistorialMedico(servicioVerHistorialMedico);
        servicios.setServicioAgendarConsultaMedica(servicioAgendarConsultaMedica);
        servicios.setServicioVerConsultaMedica(servicioVerConsultaMedica);
        servicios.setServicioCancelarConsultaMedica(servicioCancelarConsultaMedica);
        servicios.setServicioFinalizarConsultaMedica(servicioFinalizarConsultaMedica);
        servicios.setServicioListarConsultasMedicasPorPaciente(servicioListarConsultasMedicasPorPaciente);
        servicios.setServicioListarPacientesPendientes(servicioListarPacientesPendientes);
        servicios.setServicioEncontrarConsultaMedicaPendientePaciente(servicioEncontrarConsultaMedicaPendientePaciente);
        servicios.setServicioModificarDiagnostico(servicioModificarDiagnostico);
        servicios.setServicioVerDiagnostico(servicioVerDiagnostico);
        servicios.setServicioAgregarExamen(servicioAgregarExamen);
        servicios.setServicioVerExamenes(servicioVerExamenes);
    }
}
