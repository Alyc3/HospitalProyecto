package hospital.modelo.global;

import hospital.controlador.json.repositorio.json.RepositorioCuentaJSON;
import hospital.controlador.json.repositorio.json.RepositorioHistorialMedicoJSON;
import hospital.controlador.json.repositorio.json.RepositorioPersonaJSON;
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
import hospital.vista.servicio.ServicioEditarHistorialMedico;
import hospital.vista.servicio.ServicioEditarperfil;
import hospital.vista.servicio.ServicioListarPacientesPendientes;
import hospital.vista.servicio.ServicioModificarDiagnostico;
import hospital.vista.servicio.ServicioVerHistorialMedico;
import hospital.vista.servicio.ServicioVerInformacionPersona;

public class ManagerComponentes {


    // Repositorios
    public static RepositorioCuenta repositorioCuenta;
    public static RepositorioPersona repositorioPersona;
    public static RepositorioHistorialMedico repositorioHistorialmedico;
    public static RepositorioConsultaMedica repositorioConsultaMedica;
    public static RepositorioDiagnostico repositorioDiagnostico;
    public static RepositorioExamen repositorioExamen;

    // Servicios
    public static ServicioEditarperfil servicioEditarPerfil;
    public static ServicioVerInformacionPersona servicioVerInformacionPersona;
    public static ServicioEditarHistorialMedico servicioEditarHistorialMedico;
    public static ServicioVerHistorialMedico servicioVerHistorialMedico;
    public static ServicioListarPacientesPendientes servicioListarPacientesPendientes;
    public static ServicioModificarDiagnostico servicioModificarDiagnostico;
    public static Servicios servicios;

    public static void init() throws FileNotFoundException {

        repositorioCuenta = new RepositorioCuentaJSON();
        repositorioPersona = new RepositorioPersonaJSON();
        repositorioHistorialmedico = new RepositorioHistorialMedicoJSON();
        repositorioConsultaMedica = new RepositorioConsultaMedicaJSON();
        repositorioDiagnostico = new RepositorioDiagnosticoJSON();
        repositorioExamen = new RepositorioExamenJSON();

        servicioEditarPerfil = new ServicioEditarperfil();
        servicioVerInformacionPersona = new ServicioVerInformacionPersona();
        servicioEditarHistorialMedico = new ServicioEditarHistorialMedico();
        servicioVerHistorialMedico = new ServicioVerHistorialMedico();
        servicioListarPacientesPendientes = new ServicioListarPacientesPendientes();
        servicioModificarDiagnostico = new ServicioModificarDiagnostico();

        servicios = new Servicios();
        servicios.setServicioEditarperfil(servicioEditarPerfil);
        servicios.setServicioVerInformacionPersona(servicioVerInformacionPersona);
        servicios.setServicioEditarHistorialMedico(servicioEditarHistorialMedico);
        servicios.setServicioVerHistorialMedico(servicioVerHistorialMedico);
        servicios.setServicioListarPacientesPendientes(servicioListarPacientesPendientes);
        servicios.setServicioModificarDiagnostico(servicioModificarDiagnostico);

    }
}
