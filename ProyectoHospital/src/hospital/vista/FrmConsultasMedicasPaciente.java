package hospital.vista;

import hospital.vista.paneles.PanelConsultasMedicas;
import hospital.vista.paneles.PanelIzquierdoPaciente;
import java.awt.*;
import javax.swing.*;

public class FrmConsultasMedicasPaciente {

    private FrmMain frmMain;

    private JPanel panelMain;
    private JPanel panelIzquierdo;
    private JPanel panelCuerpo;
    private JPanel panelConsultasMedicas;

    public FrmConsultasMedicasPaciente(FrmMain frmMain) {
        this.frmMain = frmMain;
    }

    /**
     * Inicializa los componentes de la vista y muestra los mismos en la ventana
     *
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
        panelMain.add(panelIzquierdo, BorderLayout.WEST);
        panelMain.add(panelCuerpo, BorderLayout.CENTER);
    }

    /**
     * Crea el panel lateral izquierdo generado para pacientes
     */
    private void makePanelLeft() {
        panelIzquierdo = new PanelIzquierdoPaciente(frmMain);
    }

    /**
     * Crea el panel del cuerpo de la vista
     */
    private void makePanelBody() {
        panelCuerpo = new JPanel();
        panelCuerpo.setLayout(new GridBagLayout());
        panelCuerpo.setBackground(new Color(214, 234, 248));

        makePanelConsultasMedicas();

        panelCuerpo.add(panelConsultasMedicas);
    }

    /**
     * Crea el panel para consultas medicas con todos sus subcomponentes
     */
    private void makePanelConsultasMedicas() {
        panelConsultasMedicas = new PanelConsultasMedicas(frmMain);

    }

    /**
     * Recarga la vista
     */
    public void reload() {
        mostrar();
    }
}
