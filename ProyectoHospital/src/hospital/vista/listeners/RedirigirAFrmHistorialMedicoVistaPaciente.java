package hospital.vista.listeners;

import hospital.vista.FrmMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedirigirAFrmHistorialMedicoVistaPaciente implements ActionListener {

    private FrmMain frmMain;

    public RedirigirAFrmHistorialMedicoVistaPaciente(FrmMain frmMain) {
        this.frmMain = frmMain;
    }

    public void actionPerformed(ActionEvent e) {
        frmMain.loadHistorialMedicoPacienteView();
    }
}
