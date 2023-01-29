package hospital.vista;

import hospital.modelo.Rol;
import java.awt.Container;
import javax.swing.*;

public class FrmMain extends JFrame {

    private FrmHome frmHome;
    private FrmLogin frmLogin;
    private FrmSignup frmSignup;
    private FrmPerfilPacienteVistaPaciente frmPerfilPacienteVistaPaciente;
    private FrmHistorialMedicoVistaPaciente frmHistorialMedicoVistaPaciente;
    private FrmConsultasMedicasVistaPaciente frmConsultasMedicasVistaPaciente;
    private FrmPerfilMedicoVistaMedico frmPerfilMedicoVistaMedico;
    private FrmPacientesPendientes frmPacientesPendientes;

    public FrmMain() {
        super("Administracion Hospital");

        frmHome = new FrmHome(this);
        frmLogin = new FrmLogin(this);
        frmSignup = new FrmSignup(this);
        frmPerfilPacienteVistaPaciente = new FrmPerfilPacienteVistaPaciente(this);
        frmHistorialMedicoVistaPaciente = new FrmHistorialMedicoVistaPaciente(this);
        frmConsultasMedicasVistaPaciente = new FrmConsultasMedicasVistaPaciente(this);
        frmPerfilMedicoVistaMedico = new FrmPerfilMedicoVistaMedico(this);
        frmPacientesPendientes = new FrmPacientesPendientes(this);

        setSize(1200, 600);
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

    public void loadHomeView() {
        frmHome.mostrar();
    }

    public void loadLoginView(Rol rol) {
        frmLogin.show(rol);
    }

    public void loadSignupView(Rol rol) {
        frmSignup.mostrar(rol);
    }

    public void loadConsultasMedicasPacienteView() {
        frmConsultasMedicasVistaPaciente.mostrar();
    }

    public void loadPerfilPacientePacienteView() {
        frmPerfilPacienteVistaPaciente.mostrar();
    }

    public void loadHistorialMedicoPacienteView() {
        frmHistorialMedicoVistaPaciente.mostrar();
    }

    public void loadPerfilMedicoMedicoView() {
        frmPerfilMedicoVistaMedico.mostrar();
    }

    public void loadPacientesPendientesView() {
        frmPacientesPendientes.show();
    }
}
