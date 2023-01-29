package hospital.vista.listeners;

import hospital.modelo.Rol;
import hospital.vista.FrmMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedirigirAFrmLogin implements ActionListener {

    private FrmMain frmMain;
    private Rol rol;

    public RedirigirAFrmLogin(FrmMain frmMain, Rol rol) {
        this.frmMain = frmMain;
        this.rol = rol;
    }

    public void actionPerformed(ActionEvent e) {
        frmMain.loadLoginView(rol);
    }
}
