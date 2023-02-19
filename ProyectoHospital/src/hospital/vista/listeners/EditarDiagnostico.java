package hospital.vista.listeners;

import hospital.modelo.Diagnostico;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.vista.servicio.Servicios;
import hospital.vista.FrmMain;
import hospital.vista.paneles.PanelDiagnostico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EditarDiagnostico implements ActionListener {

    private FrmMain frmMain;
    private PanelDiagnostico panelDiagnostico;
    private Servicios servicios;

    public EditarDiagnostico(FrmMain frmMain, PanelDiagnostico panelDiagnostico) {
        this.frmMain = frmMain;
        this.panelDiagnostico = panelDiagnostico;
        this.servicios = ManagerComponentes.servicios;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Editar diagnóstico");

        Integer idConsultaMedica = panelDiagnostico.getIdConsultaMedica();
        Diagnostico diagnostico = panelDiagnostico.getDiagnostico();

        try {
            Diagnostico diagnosticoNew = servicios.modificarDiagnostico(idConsultaMedica, diagnostico);
            showResultOfEditarDiagnostico(true, "Ha actualizado el diagnóstico");

            //window.loadConsultaMedicaView();
            //	diagnostico = panelDiagnostico.getDiagnostico();

            /*HistorialMedico historialMedico2 = ComponentManager.services.editarHistorialMedico(historialMedico);

			showResultOfEditarHistorialMedico(true, "El historial médico ha sido modificado");

			Rol rol = panelHistorialMedico.getRol();
			Integer idPaciente = null;
			if(rol.equals(GlobalVariables.ROL_MEDICO)){
				idPaciente = panelHistorialMedico.getIdPaciente();
			}*/
            //window.loadHistorialMedicoView(rol, idPaciente);

            /*UserSession.cuenta = cuenta2;

			Persona p = services.verInformacionPersona(cuenta2.getPersona().getId());
			UserSession.persona = p;
			
			
			Rol rol = panelFormularioSignup.getRol();
			if(rol.equals(GlobalVariables.ROL_MEDICO)){
		 		window.loadPacientesPendientesView();
		 	}
		 	else{
		 		
		 	}*/
        } catch (ModeloException ae) {
            showResultOfEditarDiagnostico(false, ae.getMessage());
            return;
        }
    }

    public void showResultOfEditarDiagnostico(boolean successful, String message) {
        if (successful) {
            JOptionPane.showMessageDialog(frmMain, message, "Resultado de editar el diagnóstico", JOptionPane.PLAIN_MESSAGE, new ImageIcon("assets/img/successful.png"));
        } else {
            JOptionPane.showMessageDialog(frmMain, message, "Resultado de editar el diagnóstico", JOptionPane.ERROR_MESSAGE);
        }
    }
}
