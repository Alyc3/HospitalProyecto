package hospital.vista.listeners;

import hospital.modelo.Examen;
import hospital.modelo.Rol;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.vista.servicio.Servicios;
import hospital.vista.FrmMain;
import hospital.vista.paneles.PanelExamenes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AgregarExamen implements ActionListener {

    private FrmMain frmMain;
    private PanelExamenes panelExamenes;
    private Servicios servicios;
    private Rol rol;
    private Integer idPaciente;

    public AgregarExamen(FrmMain frmMain, PanelExamenes panelExamenes, Rol rol, Integer idPaciente) {
        this.frmMain = frmMain;
        this.panelExamenes = panelExamenes;
        this.rol = rol;
        this.idPaciente = idPaciente;
        this.servicios = ManagerComponentes.servicios;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Agregar examen");

        try {
            Integer idConsultaMedica = panelExamenes.getIdConsultaMedica();
            Examen examen = panelExamenes.getExamen();
            servicios.agregarExamen(idConsultaMedica, examen);
            showResultOfAgregarExamen(true, "Ha agregado un nuevo examen");
            frmMain.loadFrmConsultaMedica(rol, idPaciente, idConsultaMedica);
        } catch (ModeloException ae) {
            showResultOfAgregarExamen(false, ae.getMessage());
        }

        /*	try{
			services.cancelarConsultaMedica(idConsultaMedica);
			showResultOfCancelarConsultaMedica(true, "La consulta médica ha sido cancelada");
		}
		catch(ApplicationException ae){
			showResultOfCancelarConsultaMedica(false, ae.getMessage());
			return;
		}*/
    }

    /**
     * Abre un cuadro de dialogo mostrando cual fue el resultado del intento de
     * agrega el examen
     *
     * @param successful
     * @param message
     */
    public void showResultOfAgregarExamen(boolean successful, String message) {
        if (successful) {
            JOptionPane.showMessageDialog(frmMain, message, "Resultado de agregar examen", JOptionPane.PLAIN_MESSAGE, new ImageIcon("assets/img/successful.png"));
        } else {
            JOptionPane.showMessageDialog(frmMain, message, "Resultado de agregar examen", JOptionPane.ERROR_MESSAGE);
        }
    }
}
