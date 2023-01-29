package hospital.vista;

import hospital.vista.paneles.PanelIzquierdoMedico;
import java.awt.*;
import javax.swing.*;

public class FrmPacientesPendientes {

    private FrmMain window;

    private JPanel panelMain;
    private JPanel panelLeft;
    private JPanel panelBody;
    private JPanel panelPacientesPendientes;

    public FrmPacientesPendientes(FrmMain window) {
        this.window = window;
    }

    public void show() {
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
        makePanelPacientesPendientes();
        panelBody.add(panelPacientesPendientes);
    }

    private void makePanelPacientesPendientes() {
        //panelPacientesPendientes = new PanelPacientesPendientes(window);
        panelPacientesPendientes = new JPanel();
        panelPacientesPendientes.setLayout(new GridBagLayout());
        JLabel label = new JLabel("Pacientes pendientes");
        panelPacientesPendientes.add(label);
    }
}
