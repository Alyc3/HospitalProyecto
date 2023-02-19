package hospital.vista;

import hospital.vista.paneles.PanelIzquierdoMedico;
import hospital.vista.paneles.PanelPacientesPendientes;
import java.awt.*;
import javax.swing.*;

public class FrmPacientesPendientes {

    private FrmMain frmMain;

    private JPanel panelMain;
    private JPanel panelLeft;
    private JPanel panelBody;
    private JPanel panelPacientesPendientes;

    public FrmPacientesPendientes(FrmMain frmMain) {
        this.frmMain = frmMain;
    }

    public void show() {
        frmMain.removeAll();
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
        panelLeft = new PanelIzquierdoMedico(frmMain);
    }

    private void makePanelBody() {
        panelBody = new JPanel();
        panelBody.setLayout(new FlowLayout()); 
        panelBody.setBackground(new Color(214, 234, 248));
        makePanelPacientesPendientes();
        panelBody.add(panelPacientesPendientes);
    }

    private void makePanelPacientesPendientes() {
        panelPacientesPendientes = new PanelPacientesPendientes(frmMain);
    }
}
