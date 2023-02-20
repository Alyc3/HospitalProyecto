package hospital.vista;

import hospital.modelo.Rol;
import hospital.vista.paneles.PanelIzquierdoDefault;
import java.awt.*;
import javax.swing.*;

public class FrmSignup {

    private FrmMain frmMain;

    private Rol rol;

    private JPanel panelMain;
    private JPanel panelLeft;
    private JPanel panelBody;
    private JPanel panelFormularioSignup;

    public FrmSignup(FrmMain frmMain) {
        this.frmMain = frmMain;
    }

    public void mostrar(Rol rol) {
        frmMain.removeAll();
        this.rol = rol;
        Container cp = frmMain.getContentPane();
        cp.setLayout(new BorderLayout());
        makePanelMain();
        cp.add(panelMain, BorderLayout.CENTER);
        frmMain.repaint();
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
        panelLeft = new PanelIzquierdoDefault(frmMain);
    }

    private void makePanelBody() {
        panelBody = new JPanel();
        panelBody.setLayout(new GridBagLayout());
        panelBody.setBackground(new Color(238, 242, 245));
        makePanelFormularioSignup();
        panelBody.add(panelFormularioSignup);
    }

    private void makePanelFormularioSignup() {
    }
}
