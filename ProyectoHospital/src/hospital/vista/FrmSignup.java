package hospital.vista;

import hospital.modelo.Rol;
import hospital.vista.paneles.PanelFormularioSignup;
import hospital.vista.paneles.PanelIzquierdoDefault;
import java.awt.*;
import javax.swing.*;

public class FrmSignup {

    private FrmMain window;

    private Rol rol;

    private JPanel panelMain;
    private JPanel panelLeft;
    private JPanel panelBody;
    private JPanel panelFormularioSignup;

    public FrmSignup(FrmMain window) {
        this.window = window;
    }

    public void mostrar(Rol rol) {
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
        panelMain.add(panelLeft, BorderLayout.WEST);
        panelMain.add(panelBody, BorderLayout.CENTER);
    }

    private void makePanelLeft() {
        panelLeft = new PanelIzquierdoDefault(window);
    }

    private void makePanelBody() {
        panelBody = new JPanel();
        panelBody.setLayout(new GridBagLayout());
        panelBody.setBackground(new Color(238, 242, 245));
        makePanelFormularioSignup();
        panelBody.add(panelFormularioSignup);
    }

    private void makePanelFormularioSignup() {
        panelFormularioSignup = new PanelFormularioSignup(window, rol);
    }
}
