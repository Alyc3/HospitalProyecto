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

    /**
     * Inicializa los componentes de la vista y muestra los mismos en la ventana
     */
    public void mostrar() {
        frmMain.removeAll();
        Container cp = frmMain.getContentPane();
        cp.setLayout(new BorderLayout());
        makePanelMain();
        cp.add(panelMain, BorderLayout.CENTER);
        frmMain.repaint();
    }

    /**
     * Método para crear y establecer la configuración del panel y añadir cada
     * uno de los componentes
     */
    private void makePanelMain() {
        panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());

        makePanelLeft();
        makePanelBody();

        panelMain.add(panelLeft, BorderLayout.WEST);
        panelMain.add(panelBody, BorderLayout.CENTER);
    }

    /**
     * Crea el panel lateral izquierdo presentado para medicos
     */
    private void makePanelLeft() {
        panelLeft = new PanelIzquierdoMedico(frmMain);
    }

    /**
     * Crea el panel del cuerpo junto con todos sus componentes
     */
    private void makePanelBody() {
        panelBody = new JPanel();
        panelBody.setLayout(new FlowLayout());
        panelBody.setBackground(new Color(214, 234, 248));
        makePanelPacientesPendientes();
        panelBody.add(panelPacientesPendientes);
    }

    /**
     * Crea el panel de Pacientes pendientes y sus subcomponentes
     */
    private void makePanelPacientesPendientes() {
        panelPacientesPendientes = new PanelPacientesPendientes(frmMain);
    }
}
