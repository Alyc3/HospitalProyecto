package hospital.vista.servicio;

import hospital.controlador.lista.ListaEnlazada;
import hospital.modelo.ConsultaMedica;
import hospital.modelo.Cuenta;
import hospital.modelo.Diagnostico;
import hospital.modelo.Examen;
import hospital.modelo.HistorialMedico;
import hospital.modelo.Persona;
import hospital.modelo.Rol;
import hospital.modelo.excepciones.ModeloException;

/**
 * Se utiliza para controlar e inicializar los servicios que se requieren
 */
public class Servicios {

    private ServicioLogin servicioLogin;
    private ServicioRegistrarPersona servicioRegistrarPersona;
    private ServicioEditarperfil servicioEditarperfil;
    private ServicioVerInformacionPersona servicioVerInformacionPersona;
    private ServicioEditarHistorialMedico servicioEditarHistorialMedico;
    private ServicioVerHistorialMedico servicioVerHistorialMedico;
    private ServicioAgendarConsultaMedica servicioAgendarConsultaMedica;
    private ServicioVerConsultaMedica servicioVerConsultaMedica;
    private ServicioCancelarConsultaMedica servicioCancelarConsultaMedica;
    private ServicioFinalizarConsultaMedica servicioFinalizarConsultaMedica;
    private ServicioListarConsultasMedicasPorPaciente servicioListarConsultasMedicasPorPaciente;
    private ServicioListarPacientesPendientes servicioListarPacientesPendientes;
    private ServicioEncontrarConsultaMedicaPendientePaciente servicioEncontrarConsultaMedicaPendientePaciente;
    private ServicioModificarDiagnostico servicioModificarDiagnostico;
    private ServicioVerDiagnostico servicioVerDiagnostico;
    private ServicioAgregarExamen servicioAgregarExamen;
    private ServicioVerExamenes servicioVerExamenes;

    public void setServicioLogin(ServicioLogin servicioLogin) {
        this.servicioLogin = servicioLogin;
    }

    public void setServicioRegistrarPersona(ServicioRegistrarPersona servicioRegistrarPersona) {
        this.servicioRegistrarPersona = servicioRegistrarPersona;
    }

    public void setServicioEditarperfil(ServicioEditarperfil servicioEditarperfil) {
        this.servicioEditarperfil = servicioEditarperfil;
    }

    public void setServicioVerInformacionPersona(ServicioVerInformacionPersona servicioVerInformacionPersona) {
        this.servicioVerInformacionPersona = servicioVerInformacionPersona;
    }

    public void setServicioEditarHistorialMedico(ServicioEditarHistorialMedico servicioEditarHistorialMedico) {
        this.servicioEditarHistorialMedico = servicioEditarHistorialMedico;
    }

    public void setServicioVerHistorialMedico(ServicioVerHistorialMedico servicioVerHistorialMedico) {
        this.servicioVerHistorialMedico = servicioVerHistorialMedico;
    }

    public void setServicioAgendarConsultaMedica(ServicioAgendarConsultaMedica servicioAgendarConsultaMedica) {
        this.servicioAgendarConsultaMedica = servicioAgendarConsultaMedica;
    }

    public void setServicioVerConsultaMedica(ServicioVerConsultaMedica servicioVerConsultaMedica) {
        this.servicioVerConsultaMedica = servicioVerConsultaMedica;
    }

    public void setServicioCancelarConsultaMedica(ServicioCancelarConsultaMedica servicioCancelarConsultaMedica) {
        this.servicioCancelarConsultaMedica = servicioCancelarConsultaMedica;
    }

    public void setServicioFinalizarConsultaMedica(ServicioFinalizarConsultaMedica servicioFinalizarConsultaMedica) {
        this.servicioFinalizarConsultaMedica = servicioFinalizarConsultaMedica;
    }

    public void setServicioListarConsultasMedicasPorPaciente(ServicioListarConsultasMedicasPorPaciente servicioListarConsultasMedicasPorPaciente) {
        this.servicioListarConsultasMedicasPorPaciente = servicioListarConsultasMedicasPorPaciente;
    }

    public void setServicioListarPacientesPendientes(ServicioListarPacientesPendientes servicioListarPacientesPendientes) {
        this.servicioListarPacientesPendientes = servicioListarPacientesPendientes;
    }

    public void setServicioEncontrarConsultaMedicaPendientePaciente(ServicioEncontrarConsultaMedicaPendientePaciente servicioEncontrarConsultaMedicaPendientePaciente) {
        this.servicioEncontrarConsultaMedicaPendientePaciente = servicioEncontrarConsultaMedicaPendientePaciente;
    }

    public void setServicioModificarDiagnostico(ServicioModificarDiagnostico servicioModificarDiagnostico) {
        this.servicioModificarDiagnostico = servicioModificarDiagnostico;
    }

    public void setServicioVerDiagnostico(ServicioVerDiagnostico servicioVerDiagnostico) {
        this.servicioVerDiagnostico = servicioVerDiagnostico;
    }

    public void setServicioAgregarExamen(ServicioAgregarExamen servicioAgregarExamen) {
        this.servicioAgregarExamen = servicioAgregarExamen;
    }

    public void setServicioVerExamenes(ServicioVerExamenes servicioVerExamenes) {
        this.servicioVerExamenes = servicioVerExamenes;
    }

    public Cuenta login(Rol rol, String usuario, String clave) throws ModeloException {
        return servicioLogin.execute(rol, usuario, clave);
    }

    public Cuenta registrarPersona(Cuenta cuenta, Persona persona, HistorialMedico historialMedico) throws ModeloException {
        return servicioRegistrarPersona.execute(cuenta, persona, historialMedico);
    }

    public Cuenta servicioEditarPerfil(Cuenta cuenta, Persona persona) throws ModeloException {
        return servicioEditarperfil.execute(cuenta, persona);
    }

    public Persona verInformacionPersona(Integer idPersona) throws ModeloException {
        return servicioVerInformacionPersona.execute(idPersona);
    }

    public HistorialMedico editarHistorialMedico(HistorialMedico historialMedico) throws ModeloException {
        return servicioEditarHistorialMedico.execute(historialMedico);
    }

    public HistorialMedico verHistorialMedico(Integer idPersona) throws ModeloException {
        return servicioVerHistorialMedico.execute(idPersona);
    }

    public ConsultaMedica agendarConsultaMedica(String cedula, ConsultaMedica consultaMedica) throws ModeloException {
        return servicioAgendarConsultaMedica.execute(cedula, consultaMedica);
    }

    public ConsultaMedica verConsultaMedica(Integer idConsultaMedica) throws ModeloException {
        return servicioVerConsultaMedica.execute(idConsultaMedica);
    }

    public ConsultaMedica cancelarConsultaMedica(Integer idConsultaMedica) throws ModeloException {
        return servicioCancelarConsultaMedica.execute(idConsultaMedica);
    }

    public ConsultaMedica finalizarConsultaMedica(Integer idConsultaMedica) throws ModeloException {
        return servicioFinalizarConsultaMedica.execute(idConsultaMedica);
    }

    public ListaEnlazada<ConsultaMedica> listarConsultasMedicasPorPaciente(Integer idPaciente) throws ModeloException {
        return servicioListarConsultasMedicasPorPaciente.execute(idPaciente);
    }

    public ListaEnlazada<Persona> listarPacientesPendientes() throws ModeloException {
        return servicioListarPacientesPendientes.execute();
    }

    public ConsultaMedica encontrarConsultaMedicaPendienteDePaciente(Integer idPaciente) throws ModeloException {
        return servicioEncontrarConsultaMedicaPendientePaciente.execute(idPaciente);
    }

    public Diagnostico modificarDiagnostico(Integer idConsultaMedica, Diagnostico diagnostico) throws ModeloException {
        return servicioModificarDiagnostico.execute(idConsultaMedica, diagnostico);
    }

    public Diagnostico verDiagnostico(Integer idConsultaMedica) throws ModeloException {
        return servicioVerDiagnostico.execute(idConsultaMedica);
    }

    public Examen agregarExamen(Integer idConsultaMedica, Examen examen) throws ModeloException {
        return servicioAgregarExamen.execute(idConsultaMedica, examen);
    }

    public ListaEnlazada<Examen> verExamenes(Integer idConsultaMedica) throws ModeloException {
        return servicioVerExamenes.execute(idConsultaMedica);
    }
}
