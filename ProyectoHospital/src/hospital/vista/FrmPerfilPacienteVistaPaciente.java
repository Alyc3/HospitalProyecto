package hospital.vista;

import hospital.vista.paneles.PanelIzquierdoPaciente;
import java.awt.*;
import javax.swing.*;

public class FrmPerfilPacienteVistaPaciente {

    private FrmMain window;

    private JPanel panelMain;
    private JPanel panelLeft;
    private JPanel panelBody;
    private JPanel panelPerfilPersona;

    public FrmPerfilPacienteVistaPaciente(FrmMain window) {
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
        panelLeft = new PanelIzquierdoPaciente(window);
    }

    private void makePanelBody() {
        panelBody = new JPanel();
        panelBody.setLayout(new GridBagLayout()); 
        makePanelPerfilPersona();
        panelBody.add(panelPerfilPersona);
    }

    private void makePanelPerfilPersona() {
        //panelPerfilPersona = new PanelPerfilPersona(window, GlobalVariables.ROL_PACIENTE);
        panelPerfilPersona = new JPanel();
        panelPerfilPersona.setLayout(new GridBagLayout());
        JLabel label = new JLabel("Perfil Paciente");
        panelPerfilPersona.add(label);
    }
}
