package hospital.vista.listeners;

import hospital.modelo.Rol;
import hospital.vista.FrmMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener para redirigir al usuario (paciente) al Frame de Consulta Medica
 */
public class RedirigirAFrmConsultaMedica implements ActionListener {

    private FrmMain frmMain;
    private Rol rol;
    private Integer idPaciente;
    private Integer idConsultaMedica;

    public RedirigirAFrmConsultaMedica(FrmMain frmMain, Rol rol, Integer idPaciente, Integer idConsultaMedica) {
        this.frmMain = frmMain;
        this.rol = rol;
        this.idPaciente = idPaciente;
        this.idConsultaMedica = idConsultaMedica;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("redirigir a consulta médica");
        frmMain.loadFrmConsultaMedica(rol, idPaciente, idConsultaMedica);
    }
}
