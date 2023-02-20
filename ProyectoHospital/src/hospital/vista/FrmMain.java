package hospital.vista;

import hospital.modelo.Rol;
import java.awt.Container;
import javax.swing.*;

public class FrmMain extends JFrame {

    private FrmHome frmHome;
    private FrmPacientesPendientes frmPacientesPendientes;
    private FrmVerPerfil frmVerPerfil;

    public FrmMain() {
        super("Administracion Hospital");

        frmHome = new FrmHome(this);
        frmVerPerfil = new FrmVerPerfil(this);
        frmPacientesPendientes = new FrmPacientesPendientes(this);

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

    public void loadFrmVerPerfil(Rol rol) {
        frmVerPerfil.show(rol);
    }

    public void loadFrmPacientesPendientes() {
        frmPacientesPendientes.show();
    }
}
