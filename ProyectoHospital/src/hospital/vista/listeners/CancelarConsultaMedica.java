package hospital.vista.listeners;

import hospital.modelo.Rol;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.vista.servicio.Servicios;
import hospital.vista.FrmMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CancelarConsultaMedica implements ActionListener {

    private FrmMain frmMain;
    private Rol rol;
    private Integer idPaciente;
    private Integer idConsultaMedica;
    private Servicios servicios;

    public CancelarConsultaMedica(FrmMain frmMain, Rol rol, Integer idPaciente, Integer idConsultaMedica) {
        this.frmMain = frmMain;
        this.rol = rol;
        this.idPaciente = idPaciente;
        this.idConsultaMedica = idConsultaMedica;
        this.servicios = ManagerComponentes.servicios;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Cancela consulta");

        try {
            servicios.cancelarConsultaMedica(idConsultaMedica);
            showResultOfCancelarConsultaMedica(true, "La consulta médica ha sido cancelada");
            frmMain.loadFrmConsultaMedica(rol, idPaciente, idConsultaMedica);
        } catch (ModeloException ae) {
            showResultOfCancelarConsultaMedica(false, ae.getMessage());
            return;
        }
    }

    /**
     * Abre un cuadro de diálogo mostrando el resultado del intento de la
     * cancelación de una consulta medica
     *
     * @param successful
     * @param message
     */
    public void showResultOfCancelarConsultaMedica(boolean successful, String message) {
        if (successful) {
            JOptionPane.showMessageDialog(frmMain, message, "Resultado de cancelar consulta médica", JOptionPane.PLAIN_MESSAGE, new ImageIcon("assets/img/successful.png"));
        } else {
            JOptionPane.showMessageDialog(frmMain, message, "Resultado de cancelar consulta médica", JOptionPane.ERROR_MESSAGE);
        }
    }
}
