package hospital.vista.listeners;

import hospital.modelo.Rol;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.vista.servicio.Servicios;
import hospital.vista.FrmMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FinalizarConsultaMedica implements ActionListener {

    private FrmMain frmMain;
    private Rol rol;
    private Integer idPaciente;
    private Integer idConsultaMedica;
    private Servicios servicios;

    public FinalizarConsultaMedica(FrmMain frmMain, Rol rol, Integer idPaciente, Integer idConsultaMedica) {
        this.frmMain = frmMain;
        this.rol = rol;
        this.idPaciente = idPaciente;
        this.idConsultaMedica = idConsultaMedica;
        this.servicios = ManagerComponentes.servicios;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            servicios.finalizarConsultaMedica(idConsultaMedica);
            showResultOfFinalizarConsultaMedica(true, "La consulta médica ha sido finalizada");
            frmMain.loadFrmConsultaMedica(rol, idPaciente, idConsultaMedica);
        } catch (ModeloException ae) {
            showResultOfFinalizarConsultaMedica(false, ae.getMessage());
            return;
        }
    }

    /**
     * Muestra un cuadro de diálogo con la informacion del resultado del intento
     * de finalización de la consulta medica
     *
     * @param successful
     * @param message
     */
    public void showResultOfFinalizarConsultaMedica(boolean successful, String message) {
        if (successful) {
            JOptionPane.showMessageDialog(frmMain, message, "Resultado de finalizar consulta médica", JOptionPane.PLAIN_MESSAGE, new ImageIcon("assets/img/successful.png"));
        } else {
            JOptionPane.showMessageDialog(frmMain, message, "Resultado de finalizar consulta médica", JOptionPane.ERROR_MESSAGE);
        }
    }
}
