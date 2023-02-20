package hospital.vista.listeners;

import hospital.modelo.HistorialMedico;
import hospital.modelo.Rol;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.modelo.global.VariablesGlobales;
import hospital.vista.FrmMain;
import hospital.vista.paneles.PanelHistorialMedico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EditarHistorialMedico implements ActionListener {

    private FrmMain frmMain;
    private PanelHistorialMedico panelHistorialMedico;

    public EditarHistorialMedico(FrmMain frmMain, PanelHistorialMedico panelHistorialMedico) {
        this.frmMain = frmMain;
        this.panelHistorialMedico = panelHistorialMedico;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Editar historial médico");

        HistorialMedico historialMedico;

        try {
            historialMedico = panelHistorialMedico.getHistorialMedico();

            HistorialMedico historialMedico2 = ManagerComponentes.servicios.editarHistorialMedico(historialMedico);

            showResultOfEditarHistorialMedico(true, "El historial médico ha sido modificado");

            Rol rol = panelHistorialMedico.getRol();
            Integer idPaciente = null;
            if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
                idPaciente = panelHistorialMedico.getIdPaciente();
            }

            frmMain.loalFrmHistorialMedico(rol, idPaciente);

            /*UserSession.cuenta = cuenta2;

			Persona p = services.verInformacionPersona(cuenta2.getPersona().getId());
			UserSession.persona = p;
			
			
			Rol rol = panelFormularioSignup.getRol();
			if(rol.equals(GlobalVariables.ROL_MEDICO)){
		 		window.loadPacientesPendientesView();
		 	}
		 	else{
		 		window.loadConsultasMedicasPacienteView();
		 	}*/
        } catch (ModeloException ae) {
            showResultOfEditarHistorialMedico(false, ae.getMessage());
            return;
        }
    }

    /**
     * Abre un cuadro de diálogo que muestra el resultado del intento de edición
     * del historial medico
     *
     * @param successful
     * @param message
     */
    public void showResultOfEditarHistorialMedico(boolean successful, String message) {
        if (successful) {
            JOptionPane.showMessageDialog(frmMain, message, "Resultado de editar historial médico", JOptionPane.PLAIN_MESSAGE, new ImageIcon("assets/img/successful.png"));
        } else {
            JOptionPane.showMessageDialog(frmMain, message, "Resultado de editar historial médico", JOptionPane.ERROR_MESSAGE);
        }
    }
}
