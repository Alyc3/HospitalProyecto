package hospital.vista;

import hospital.modelo.global.VariablesGlobales;
import hospital.vista.listeners.RedirigirAFrmLogin;
import hospital.vista.paneles.PanelIzquierdoDefault;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FrmHome {

    private FrmMain window;

    private JPanel panelMain;
    private JPanel panelIzquierdo;
    private JPanel panelCuerpo;
    private JPanel panelSeleccionRol;

    private JButton buttonMedicos;
    private JButton buttonPacientes;

    public FrmHome(FrmMain window) {
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
        panelIzquierdo = new PanelIzquierdoDefault(window);
    }

    private void makePanelBody() {
        panelCuerpo = new JPanel();
        panelCuerpo.setLayout(new GridBagLayout());
        makePanelSeleccionRol();
        panelCuerpo.add(panelSeleccionRol);
    }

    private void makePanelSeleccionRol() {
        panelSeleccionRol = new JPanel();
        panelSeleccionRol.setLayout(new FlowLayout());
        JPanel panelButtonPacientes = createPanelForButtonFromPanelSeleccionRol();
        buttonPacientes = makeButtonForSeleccionarRol("Pacientes", new RedirigirAFrmLogin(window, VariablesGlobales.ROL_PACIENTE));
        panelButtonPacientes.add(buttonPacientes);
        JPanel panelButtonMedicos = createPanelForButtonFromPanelSeleccionRol();
        buttonMedicos = makeButtonForSeleccionarRol("Médicos", new RedirigirAFrmLogin(window, VariablesGlobales.ROL_MEDICO));
        panelButtonMedicos.add(buttonMedicos);
        panelSeleccionRol.add(panelButtonPacientes);
        panelSeleccionRol.add(panelButtonMedicos);
    }

    public JButton makeButtonForSeleccionarRol(String name, ActionListener listener) {
        JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(200, 80));
        button.setBackground(new Color(0, 76, 153));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        button.addActionListener(listener);
        return button;
    }

    public JPanel createPanelForButtonFromPanelSeleccionRol() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return panel;
    }
}
