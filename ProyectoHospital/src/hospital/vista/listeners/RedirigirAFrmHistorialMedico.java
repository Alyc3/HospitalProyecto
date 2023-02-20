package hospital.vista.listeners;

import hospital.modelo.Rol;
import hospital.vista.FrmMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener para redirigir al usuario al frame del historial medico
 */
public class RedirigirAFrmHistorialMedico implements ActionListener {

    private FrmMain frmMain;
    private Rol rol;
    private Integer idPaciente;

    public RedirigirAFrmHistorialMedico(FrmMain frmMain, Rol rol, Integer idPaciente) {
        this.frmMain = frmMain;
        this.rol = rol;
        this.idPaciente = idPaciente;
    }

    public void actionPerformed(ActionEvent e) {
        frmMain.loalFrmHistorialMedico(rol, idPaciente);
    }
}
