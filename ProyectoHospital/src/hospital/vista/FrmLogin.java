package hospital.vista;

import hospital.modelo.Rol;
import hospital.vista.paneles.PanelFormularioLogin;
import hospital.vista.paneles.PanelIzquierdoDefault;
import java.awt.*;
import javax.swing.*;

public class FrmLogin {

    private FrmMain window;

    private Rol rol;

    private JPanel panelMain;
    private JPanel panelIzquierdo;
    private JPanel panelCuerpo;
    private JPanel panelFormularioLogin;

    public FrmLogin(FrmMain window) {
        this.window = window;
    }

    public void show(Rol rol) {
        window.removeAll();
        this.rol = rol;
        Container cp = window.getContentPane();
        cp.setLayout(new BorderLayout());
        makePanelMain();
        cp.add(panelMain, BorderLayout.CENTER);
        window.repaint();
    }

    private void makePanelMain() {
        panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());
        makePanelLeft();
        makePanelBody();
        panelMain.add(panelIzquierdo, BorderLayout.WEST);
        panelMain.add(panelCuerpo, BorderLayout.CENTER);
    }

    private void makePanelLeft() {
        panelIzquierdo = new PanelIzquierdoDefault(window);
    }

    private void makePanelBody() {
        panelCuerpo = new JPanel();
        panelCuerpo.setLayout(new GridBagLayout());
        makePanelFormularioLogin();
        panelCuerpo.add(panelFormularioLogin);
    }

    private void makePanelFormularioLogin() {
        panelFormularioLogin = new PanelFormularioLogin(window, rol);
    }
}
