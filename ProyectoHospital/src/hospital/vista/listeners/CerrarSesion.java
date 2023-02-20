package hospital.vista.listeners;

import hospital.vista.FrmMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class CerrarSesion implements ActionListener {

    private FrmMain frmMain;

    public CerrarSesion(FrmMain frmMain) {
        this.frmMain = frmMain;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Cerrar sesión");
        showResultOfCerrarSesion(true, "Ha cerrado sesión correctamente");
        frmMain.loadFrmHome();
    }

    /**
     * Abre un cuadro de diálogo mostrando el resultado del intento de Logout
     *
     * @param successful
     * @param message
     */
    public void showResultOfCerrarSesion(boolean successful, String message) {
        if (successful) {
            JOptionPane.showMessageDialog(frmMain, message, "Resultado de cerrar sesión", JOptionPane.PLAIN_MESSAGE, new ImageIcon("assets/img/successful.png"));
        } else {
            JOptionPane.showMessageDialog(frmMain, message, "Resultado de cerrar sesión", JOptionPane.ERROR_MESSAGE);
        }
    }
}
