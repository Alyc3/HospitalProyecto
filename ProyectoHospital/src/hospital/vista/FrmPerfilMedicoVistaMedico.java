package hospital.vista;

import hospital.vista.paneles.PanelIzquierdoMedico;
import java.awt.*;
import javax.swing.*;

public class FrmPerfilMedicoVistaMedico {

    private FrmMain window;

    private JPanel panelMain;
    private JPanel panelLeft;
    private JPanel panelBody;
    private JPanel panelPerfilMedico;

    public FrmPerfilMedicoVistaMedico(FrmMain window) {
        this.window = window;
    }

    public void mostrar() {
        window.removeAll();
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
        panelLeft = new PanelIzquierdoMedico(window);
    }

    private void makePanelBody() {
        panelBody = new JPanel();
        panelBody.setLayout(new GridBagLayout()); 
        makePanelPerfilMedico();
        panelBody.add(panelPerfilMedico);
    }

    private void makePanelPerfilMedico() {
        //panelPerfilMedico = new PanelPerfilMedico(window);
        panelPerfilMedico = new JPanel();
        panelPerfilMedico.setLayout(new GridBagLayout());
        JLabel label = new JLabel("Perfil Médico");
        panelPerfilMedico.add(label);
    }
}
