package hospital.vista.listeners;

import hospital.vista.FrmMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedirigirAFrmPerfilMedicoVistaMedico implements ActionListener {

    private FrmMain frmMain;

    public RedirigirAFrmPerfilMedicoVistaMedico(FrmMain frmMain) {
        this.frmMain = frmMain;
    }

    public void actionPerformed(ActionEvent e) {
        frmMain.loadPerfilMedicoMedicoView();
    }
}
