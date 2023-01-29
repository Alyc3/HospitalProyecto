package hospital.vista;

import hospital.vista.paneles.PanelConsultasMedicas;
import hospital.vista.paneles.PanelIzquierdoPaciente;
import java.awt.*;
import javax.swing.*;

public class FrmConsultasMedicasVistaPaciente {

    private FrmMain window;

    private JPanel panelMain;
    private JPanel panelIzquierdo;
    private JPanel panelCuerpo;
    private JPanel panelConsultasMedicas;

    public FrmConsultasMedicasVistaPaciente(FrmMain window) {
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
        panelMain.add(panelIzquierdo, BorderLayout.WEST);
        panelMain.add(panelCuerpo, BorderLayout.CENTER);
    }

    private void makePanelLeft() {
        panelIzquierdo = new PanelIzquierdoPaciente(window);
    }

    private void makePanelBody() {
        panelCuerpo = new JPanel();
        panelCuerpo.setLayout(new GridBagLayout());

        makePanelConsultasMedicas();

        panelCuerpo.add(panelConsultasMedicas);
    }

    private void makePanelConsultasMedicas() {
        panelConsultasMedicas = new PanelConsultasMedicas(window);
        
    }
}
