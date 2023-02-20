package hospital.vista.paneles;

import hospital.vista.FrmMain;
import hospital.vista.listeners.CerrarSesion;
import hospital.vista.listeners.RedirigirAFrmPacientesPendientes;
import hospital.vista.listeners.RedirigirAFrmPerfilMedicoVistaMedico;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelIzquierdoMedico extends JPanel {

    private FrmMain frmMain;

    private JPanel panelMain;
    private JPanel panelHeader;
    private JPanel panelMenu;

    private JLabel labelBrand;

    private JButton buttonHome;

    public PanelIzquierdoMedico(FrmMain frmMain) {
        this.frmMain = frmMain;
        setBackground(new Color(38, 53, 72));
        makePanelMain();
        add(panelMain);
    }

    private void makePanelMain() {
        panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.setBackground(new Color(0, 0, 0, 0));
        makePanelHeader();
        makePanelMenu();
        panelMain.add(panelHeader);
        panelMain.add(panelMenu);
    }

    private void makePanelHeader() {
        panelHeader = new JPanel();
        panelHeader.setBackground(new Color(0, 0, 0, 0)); // Color de fondo transparente.
        panelHeader.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        makeLabelBrand();
        panelHeader.add(labelBrand);
    }

    private void makePanelMenu() {
        panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(4, 1));
        JButton buttonPerfil = makeButtonForFormulario("Ver perfil", new RedirigirAFrmPerfilMedicoVistaMedico(frmMain));
        JButton buttonPacientesPendientes = makeButtonForFormulario("Pacientes pendientes", new RedirigirAFrmPacientesPendientes(frmMain));
        JButton buttonLogout = makeButtonForFormulario("Cerrar sesión", new CerrarSesion(frmMain));
        panelMenu.add(buttonPerfil);
        panelMenu.add(buttonPacientesPendientes);
        panelMenu.add(buttonLogout);
    }

    private JButton makeButtonForFormulario(String name, ActionListener listener) {
        JButton button = new JButton();
        button = new JButton(name);
        button.setBackground(new Color(38, 53, 72));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Helvetica", Font.BOLD, 16));
        button.addActionListener(listener);
        return button;
    }

    private void makeLabelBrand() {
        labelBrand = new JLabel("HOSPITAL");
        labelBrand.setFont(new Font("Helvetica", Font.BOLD, 32));
        labelBrand.setForeground(Color.WHITE);
        labelBrand.setIcon(new ImageIcon("assets/img/hospital.png"));
    }
}