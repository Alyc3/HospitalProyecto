package hospital.vista;

import hospital.modelo.Rol;
import java.awt.Container;
import javax.swing.*;

/**
 * Frame principal del sistema desde donde se cargarán todas las demás vistas
 * para que puedan ser usadas
 */
public class FrmMain extends JFrame {

    private FrmHome frmHome;
    private FrmLogin frmLogin;
    private FrmSignup frmSignup;
    private FrmConsultasMedicasPaciente frmConsultasMedicasPaciente;
    private FrmPacientesPendientes frmPacientesPendientes;
    private FrmConsultaMedica frmConsultaMedica;
    private FrmHistorialMedico frmHistorialMedico;
    private FrmVerPerfil frmVerPerfil;

    public FrmMain() {
        super("Administracion Hospital");

        frmHome = new FrmHome(this);
        frmLogin = new FrmLogin(this);
        frmSignup = new FrmSignup(this);
        frmVerPerfil = new FrmVerPerfil(this);
        frmHistorialMedico = new FrmHistorialMedico(this);
        frmConsultasMedicasPaciente = new FrmConsultasMedicasPaciente(this);
        frmPacientesPendientes = new FrmPacientesPendientes(this);
        frmConsultaMedica = new FrmConsultaMedica(this);

        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(this);
        frmHome.mostrar();
    }

    public void removeAll() {
        Container cp = getContentPane();
        cp.removeAll();
    }

    public void repaint() {
        Container cp = getContentPane();
        cp.validate();
        cp.repaint();
    }

    public void loadFrmHome() {
        frmHome.mostrar();
    }

    public void loadFrmLogin(Rol rol) {
        frmLogin.mostrar(rol);
    }

    public void loadFrmSignup(Rol rol) {
        frmSignup.mostrar(rol);
    }

    public void loadFrmVerPerfil(Rol rol) {
        frmVerPerfil.mostrar(rol);
    }

    public void loalFrmConsultasMedicasPaciente() {
        frmConsultasMedicasPaciente.mostrar();
    }

    public void loalFrmHistorialMedico(Rol rol, Integer idPaciente) {
        frmHistorialMedico.mostrar(rol, idPaciente);
    }

    public void loadFrmPacientesPendientes() {
        frmPacientesPendientes.mostrar();
    }

    public void loadFrmConsultaMedica(Rol rol, Integer idPaciente, Integer idConsultaMedica) {
        frmConsultaMedica.mostrar(rol, idPaciente, idConsultaMedica);
    }
}
