package hospital.vista.listeners;

import hospital.modelo.Cuenta;
import hospital.modelo.HistorialMedico;
import hospital.modelo.Medico;
import hospital.modelo.Persona;
import hospital.modelo.Rol;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.modelo.global.SesionUsuario;
import hospital.modelo.global.VariablesGlobales;
import hospital.vista.servicio.Servicios;
import hospital.vista.FrmMain;
import hospital.vista.paneles.PanelFormularioSignup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class RegistrarPersona implements ActionListener {

    private FrmMain window;
    private PanelFormularioSignup panelFormularioSignup;
    private Servicios servicios;

    public RegistrarPersona(FrmMain window, PanelFormularioSignup panelFormularioSignup) {
        this.window = window;
        this.panelFormularioSignup = panelFormularioSignup;
        servicios = ManagerComponentes.servicios;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Registrarse");

        Cuenta cuenta;
        Persona persona;
        HistorialMedico historialMedico;

        try {
            cuenta = panelFormularioSignup.getCuenta();
            System.out.println("Usuario: " + cuenta.getUsuario());
            System.out.println("Clave: " + cuenta.getClave());
            System.out.println();

            persona = panelFormularioSignup.getPersona();
            System.out.println("Cedula: " + persona.getCedula());
            System.out.println("Nombre: " + persona.getNombre());
            System.out.println("Apellido: " + persona.getApellido());
            System.out.println("Correo: " + persona.getCorreo());
            System.out.println("Telefono: " + persona.getTelefono());
            System.out.println("Direccion: " + persona.getDireccion());
            System.out.println("Genero: " + persona.getGenero());
            System.out.println("Edad: " + persona.getEdad());
            System.out.println("Rol: " + persona.getRol().getNombre());
            if (Medico.class.isInstance(persona)) {
                System.out.println("Especialidad: " + ((Medico) persona).getEspecialidad());
            }
            System.out.println();

            historialMedico = panelFormularioSignup.getHistorialMedico();
            System.out.println("Fecha de nacimiento: " + historialMedico.getFechaNacimiento());
            System.out.println("Tipo sanguíneo: " + historialMedico.getTipoSanguineo());
            System.out.println();

            Cuenta cuenta2 = ManagerComponentes.servicios.registrarPersona(cuenta, persona, historialMedico);

            SesionUsuario.cuenta = cuenta2;

            Persona p = servicios.verInformacionPersona(cuenta2.getPersona().getId());
            SesionUsuario.persona = p;

            showResultOfRegistrar(true, "Se ha registrado correctamente");
            Rol rol = panelFormularioSignup.getRol();
            if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
                window.loadFrmPacientesPendientes();
            } else {
            }

        } catch (ModeloException ae) {
            showResultOfRegistrar(false, ae.getMessage());
            return;
        }
    }

    private void showResultOfRegistrar(boolean successful, String message) {
        if (successful) {
            JOptionPane.showMessageDialog(window, message, "Resultado del registro", JOptionPane.PLAIN_MESSAGE, new ImageIcon("assets/img/successful.png"));
        } else {
            JOptionPane.showMessageDialog(window, message, "Resultado del registro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
