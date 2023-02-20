package hospital.vista;

import hospital.vista.paneles.PanelFormularioNuevaConsulta;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NuevaConsultaVista {

    private FrmNuevaConsulta frmNuevaConsulta;

    private JPanel panelMain;
    private JPanel panelFormularioNuevaConsulta;

    public NuevaConsultaVista(FrmNuevaConsulta frmNuevaConsulta) {
        this.frmNuevaConsulta = frmNuevaConsulta;
    }

    /**
     * Inicializa los componentes de la vista y muestra los mismos en la ventana
     */
    public void mostrar() {
        frmNuevaConsulta.removeAll();
        Container cp = frmNuevaConsulta.getContentPane();
        cp.setLayout(new BorderLayout());
        makePanelMain();
        cp.add(panelMain, BorderLayout.CENTER);
        frmNuevaConsulta.repaint();
    }

    /**
     * Método para crear y establecer la configuración del panel y añadir cada
     * uno de los componentes
     */
    private void makePanelMain() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridBagLayout());
        panelFormularioNuevaConsulta = new PanelFormularioNuevaConsulta(frmNuevaConsulta);
        panelMain.add(panelFormularioNuevaConsulta);
    }
}
