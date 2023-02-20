package hospital.vista;

import hospital.modelo.Rol;
import hospital.modelo.global.VariablesGlobales;
import hospital.vista.paneles.PanelFormularioVerPerfil;
import hospital.vista.paneles.PanelIzquierdoMedico;
import hospital.vista.paneles.PanelIzquierdoPaciente;
import java.awt.*;
import javax.swing.*;

public class FrmVerPerfil {

    private FrmMain frmMain;

    private Rol rol;

    private JPanel panelMain;
    private JPanel panelLeft;
    private JPanel panelBody;
    private JPanel panelFormularioVerPerfil;

    public FrmVerPerfil(FrmMain frmMain) {
        this.frmMain = frmMain;
    }

    public void show(Rol rol) {
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
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            panelLeft = new PanelIzquierdoMedico(frmMain);
        } else {
            panelLeft = new PanelIzquierdoPaciente(frmMain);
        }
    }

    private void makePanelBody() {
        panelBody = new JPanel();
        panelBody.setLayout(new GridBagLayout());
        panelBody.setBackground(new Color(214, 234, 248));

        makePanelFormularioVerPerfil();
        panelBody.add(panelFormularioVerPerfil);
    }

    private void makePanelFormularioVerPerfil() {
        panelFormularioVerPerfil = new PanelFormularioVerPerfil(frmMain, rol);
    }
}
