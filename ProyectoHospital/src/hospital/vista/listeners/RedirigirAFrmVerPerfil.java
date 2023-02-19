package hospital.vista.listeners;

import hospital.modelo.Rol;
import hospital.vista.FrmMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedirigirAFrmVerPerfil implements ActionListener {

    private FrmMain frmMain;
    private Rol rol;

    public RedirigirAFrmVerPerfil(FrmMain frmMain, Rol rol) {
        this.frmMain = frmMain;
        this.rol = rol;
    }

    public void actionPerformed(ActionEvent e) {
        frmMain.loadFrmVerPerfil(rol);
    }
}
