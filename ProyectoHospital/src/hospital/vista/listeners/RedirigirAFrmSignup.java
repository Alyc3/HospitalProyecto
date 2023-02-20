package hospital.vista.listeners;

import hospital.modelo.Rol;
import hospital.vista.FrmMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listern utilizado para redirigir al usuario hacia el frame del signup
 */
public class RedirigirAFrmSignup implements ActionListener {

    private FrmMain frmMain;
    private Rol rol;

    public RedirigirAFrmSignup(FrmMain mainWindow, Rol rol) {
        this.frmMain = mainWindow;
        this.rol = rol;
    }

    public void actionPerformed(ActionEvent e) {
        frmMain.loadFrmSignup(rol);
    }
}
