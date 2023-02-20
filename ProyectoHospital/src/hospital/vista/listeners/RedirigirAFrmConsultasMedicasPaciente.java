package hospital.vista.listeners;

import hospital.vista.FrmMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener para redirigir al usuario (medico) al Frame de consultas medicas
 * pendientes
 */
public class RedirigirAFrmConsultasMedicasPaciente implements ActionListener {

    private FrmMain frmMain;

    public RedirigirAFrmConsultasMedicasPaciente(FrmMain frmMain) {
        this.frmMain = frmMain;
    }

    public void actionPerformed(ActionEvent e) {
        frmMain.loalFrmConsultasMedicasPaciente();
    }
}
