package hospital.vista;

import hospital.modelo.global.VariablesGlobales;
import hospital.vista.paneles.PanelHistorialMedico;
import hospital.vista.paneles.PanelIzquierdoPaciente;
import java.awt.*;
import javax.swing.*;

public class FrmHistorialMedicoVistaPaciente {

    private FrmMain window;

    private JPanel panelMain;
    private JPanel panelLeft;
    private JPanel panelBody;
    private JPanel panelHistorialMedico;

    public FrmHistorialMedicoVistaPaciente(FrmMain window) {
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
        makePanelHistorialMedico();
        panelBody.add(panelHistorialMedico);
    }

    private void makePanelHistorialMedico() {
        panelHistorialMedico = new PanelHistorialMedico(window, VariablesGlobales.ROL_PACIENTE);
        /*panelHistorialMedico = new JPanel();
		panelHistorialMedico.setLayout(new GridBagLayout());
		JLabel label = new JLabel("Historial Médico");

		panelHistorialMedico.add(label);*/
    }
}
