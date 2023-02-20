package hospital.vista.listeners;

import hospital.vista.FrmMain;
import hospital.vista.FrmNuevaConsulta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

/**
 * Listener para abrir un nuevo Frame de Consulta Medica
 */
public class AbrirNuevoFrmConsulta implements ActionListener {

    private FrmMain frmMain;

    public AbrirNuevoFrmConsulta(FrmMain frmMain) {
        this.frmMain = frmMain;
    }

    public void actionPerformed(ActionEvent e) {
        JDialog frmNuevaConsulta = new FrmNuevaConsulta(frmMain);
        frmNuevaConsulta.setVisible(true);
    }
}
