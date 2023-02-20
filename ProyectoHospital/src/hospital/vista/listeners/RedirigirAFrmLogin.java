package hospital.vista.listeners;

import hospital.modelo.Rol;
import hospital.vista.FrmMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener utilizado para redirigir al usuario hacia el frame del login
 */
public class RedirigirAFrmLogin implements ActionListener {

    private FrmMain frmMain;
    private Rol rol;

    public RedirigirAFrmLogin(FrmMain frmMain, Rol rol) {
        this.frmMain = frmMain;
        this.rol = rol;
    }

    public void actionPerformed(ActionEvent e) {
        frmMain.loadFrmLogin(rol);
    }
}
