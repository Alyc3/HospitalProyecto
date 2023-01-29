package hospital.vista.listeners;

import hospital.vista.FrmMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedirigirAFrmHome implements ActionListener {

    private FrmMain frmMain;

    public RedirigirAFrmHome(FrmMain frmMain) {
        this.frmMain = frmMain;
    }

    public void actionPerformed(ActionEvent e) {
        frmMain.loadHomeView();
    }
}
