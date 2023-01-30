package hospital.vista.listeners;

import hospital.vista.FrmMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CerrarSesion implements ActionListener {

    private FrmMain frmMain;

    public CerrarSesion(FrmMain frmMain) {
        this.frmMain = frmMain;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Cerrar sesión");
        frmMain.loadHomeView();
    }
}
