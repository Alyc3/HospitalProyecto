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

public class Servicios {

    private ServicioEditarperfil servicioEditarperfil;
    private ServicioVerInformacionPersona servicioVerInformacionPersona;
    private ServicioEditarHistorialMedico servicioEditarHistorialMedico;
    private ServicioVerHistorialMedico servicioVerHistorialMedico;
    private ServicioListarPacientesPendientes servicioListarPacientesPendientes;
    private ServicioModificarDiagnostico servicioModificarDiagnostico;

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

    public void setServicioListarPacientesPendientes(ServicioListarPacientesPendientes servicioListarPacientesPendientes) {
        this.servicioListarPacientesPendientes = servicioListarPacientesPendientes;
    }

    public void setServicioModificarDiagnostico(ServicioModificarDiagnostico servicioModificarDiagnostico) {
        this.servicioModificarDiagnostico = servicioModificarDiagnostico;
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

    public ListaEnlazada<Persona> listarPacientesPendientes() throws ModeloException {
        return servicioListarPacientesPendientes.execute();
    }

    public Diagnostico modificarDiagnostico(Integer idConsultaMedica, Diagnostico diagnostico) throws ModeloException {
        return servicioModificarDiagnostico.execute(idConsultaMedica, diagnostico);
    }

}
