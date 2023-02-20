package hospital.vista.listeners;

import hospital.controlador.lista.ListaEnlazada;
import hospital.modelo.ConsultaMedica;
import hospital.modelo.Diagnostico;
import hospital.modelo.HistorialMedico;
import hospital.modelo.Persona;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.modelo.global.SesionUsuario;
import hospital.modelo.global.VariablesGlobales;
import hospital.controlador.json.repositorio.RepositorioConsultaMedica;
import hospital.controlador.json.repositorio.RepositorioDiagnostico;
import hospital.controlador.json.repositorio.RepositorioHistorialMedico;
import hospital.controlador.json.repositorio.RepositorioPersona;
import hospital.vista.servicio.Servicios;
import hospital.vista.FrmMain;
import hospital.vista.FrmNuevaConsulta;
import hospital.vista.paneles.PanelFormularioNuevaConsulta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class AgendarConsultaMedica implements ActionListener {

    private FrmNuevaConsulta frmNuevaConsulta;
    private PanelFormularioNuevaConsulta panelFormularioNuevaConsulta;
    private Servicios servicios;

    public AgendarConsultaMedica(FrmNuevaConsulta frmNuevaConsulta, PanelFormularioNuevaConsulta panelFormularioNuevaConsulta) {
        this.frmNuevaConsulta = frmNuevaConsulta;
        this.panelFormularioNuevaConsulta = panelFormularioNuevaConsulta;
        servicios = ManagerComponentes.servicios;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Creando consulta médica");

        ConsultaMedica consultaMedica;

        try {
            consultaMedica = panelFormularioNuevaConsulta.getConsultaMedica();
            System.out.println("Fecha: " + consultaMedica.getFecha());
            System.out.println("Hora: " + consultaMedica.getHora());
            System.out.println();
            System.out.println(SesionUsuario.persona.getCedula());

            ConsultaMedica consultaMedica2 = ManagerComponentes.servicios.agendarConsultaMedica(SesionUsuario.persona.getCedula(), consultaMedica);
            showResultOfAgendarNuevaConsulta(true, "Se ha registrado correctamente");

            FrmMain frmMain = (FrmMain) frmNuevaConsulta.getOwner();
            frmMain.loalFrmConsultasMedicasPaciente();
        } catch (ModeloException ae) {
            showResultOfAgendarNuevaConsulta(false, ae.getMessage());
            return;
        }
    }

    /**
     * Abre un cuadro de diálogo que indica cual fue el resultado del intneto
     * del agendado de una consulta medica
     *
     * @param successful
     * @param message
     */
    private void showResultOfAgendarNuevaConsulta(boolean successful, String message) {
        if (successful) {
            JOptionPane.showMessageDialog(frmNuevaConsulta, message, "Resultado de agendar consulta", JOptionPane.PLAIN_MESSAGE, new ImageIcon("assets/img/successful.png"));
        } else {
            JOptionPane.showMessageDialog(frmNuevaConsulta, message, "Resultado de agendar consulta", JOptionPane.ERROR_MESSAGE);
        }
    }
}
