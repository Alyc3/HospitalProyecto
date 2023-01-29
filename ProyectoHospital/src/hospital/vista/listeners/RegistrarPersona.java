package hospital.vista.listeners;

import hospital.modelo.Cuenta;
import hospital.modelo.HistorialMedico;
import hospital.modelo.Medico;
import hospital.modelo.Persona;
import hospital.modelo.Rol;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.modelo.global.VariablesGlobales;
import hospital.vista.FrmMain;
import hospital.vista.paneles.PanelFormularioSignup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarPersona implements ActionListener {

    private FrmMain window;
    private PanelFormularioSignup panelFormularioSignup;

    public RegistrarPersona(FrmMain window, PanelFormularioSignup panelFormularioSignup) {
        this.window = window;
        this.panelFormularioSignup = panelFormularioSignup;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Registrarse");

        Cuenta cuenta = panelFormularioSignup.getCuenta();
        System.out.println(cuenta.getUsuario() + " " + cuenta.getClave());

        Persona persona = panelFormularioSignup.getPersona();
        System.out.println("Cedula: " + persona.getCedula());
        System.out.println("Nombre: " + persona.getNombre());
        System.out.println("Apellido: " + persona.getApellido());
        System.out.println("Correo: " + persona.getCorreo());
        System.out.println("Telefono: " + persona.getTelefono());
        System.out.println("Direccion: " + persona.getDireccion());
        System.out.println("Genero: " + persona.getGenero());
        System.out.println("Rol: " + persona.getRol().getNombre());

        if (Medico.class.isInstance(persona)) {
            System.out.println("Especialidad: " + ((Medico) persona).getEspecialidad());
        }

        HistorialMedico hs = panelFormularioSignup.getHistorialMedico();
        System.out.println(hs.getFechaNacimiento());

        try {
            Cuenta cuenta2 = ManagerComponentes.services.registrarPersona(cuenta, persona, hs);
            panelFormularioSignup.showResultadoRegistro(true, "El registro ");
            Rol rol = panelFormularioSignup.getRol();
            if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
                window.loadPacientesPendientesView();
            } else {
                window.loadConsultasMedicasPacienteView();
            }
        } catch (ModeloException exc) {
            panelFormularioSignup.showResultadoRegistro(false, exc.getMessage());
            Rol rol = panelFormularioSignup.getRol();
            if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
                window.loadPacientesPendientesView();
            } else {
                window.loadConsultasMedicasPacienteView();
            }
        }

    }
}
