package hospital.vista.paneles;

import hospital.modelo.global.SesionUsuario;
import hospital.modelo.global.VariablesGlobales;
import hospital.vista.FrmMain;
import hospital.vista.listeners.CerrarSesion;
import hospital.vista.listeners.RedirigirAFrmPacientesPendientes;
import hospital.vista.listeners.RedirigirAFrmVerPerfil;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Sirve para crear la configuración del panel lateral izquierdo presentado a
 * los medicos
 */
public class PanelIzquierdoMedico extends JPanel {

    private FrmMain frmMain;

    private JPanel panelMain;
    private JPanel panelHeader;
    private JPanel panelPerfil;
    private JPanel panelMenu;

    private JLabel labelBrand;
    private JLabel labelUsuario;

    private JLabel imageAvatar;

    private JButton buttonPerfil;
    private JButton buttonPacientesPendientes;
    private JButton buttonLogout;

    public PanelIzquierdoMedico(FrmMain frmMain) {
        this.frmMain = frmMain;
        setLayout(new FlowLayout());
        setBackground(new Color(38, 53, 72));
        makePanelMain();
        add(panelMain);
    }

    private void makePanelMain() {
        panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.setBackground(new Color(0, 0, 0, 0));

        makePanelHeader();
        makePanelPerfil();
        makePanelMenu();

        panelMain.add(panelHeader);
        panelMain.add(panelPerfil);
        panelMain.add(panelMenu);
    }

    private void makePanelHeader() {
        panelHeader = new JPanel();
        panelHeader.setLayout(new FlowLayout());
        panelHeader.setBackground(new Color(0, 0, 0, 0));
        panelHeader.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        makeLabelBrand();

        panelHeader.add(labelBrand);
    }

    private void makePanelPerfil() {
        panelPerfil = new JPanel();
        panelPerfil.setLayout(new BoxLayout(panelPerfil, BoxLayout.Y_AXIS));
        panelPerfil.setBackground(new Color(0, 0, 0, 0));
        panelPerfil.setBorder(BorderFactory.createEmptyBorder(20, 0, 50, 0));

        JPanel panelAvatar = new JPanel();
        panelAvatar.setLayout(new FlowLayout());
        panelAvatar.setBackground(new Color(0, 0, 0, 0));
        panelAvatar.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        makeImageAvatar();
        panelAvatar.add(imageAvatar);

        JPanel panelUsuario = new JPanel();
        panelUsuario.setLayout(new FlowLayout());
        panelUsuario.setBackground(new Color(0, 0, 0, 0));
        makeLabelUsuario();
        panelUsuario.add(labelUsuario);

        panelPerfil.add(panelAvatar);
        panelPerfil.add(panelUsuario);
    }

    private void makePanelMenu() {
        panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(3, 1));

        buttonPerfil = makeButtonForMenu("Ver perfil", new RedirigirAFrmVerPerfil(frmMain, VariablesGlobales.ROL_MEDICO));
        buttonPacientesPendientes = makeButtonForMenu("Pacientes pendientes", new RedirigirAFrmPacientesPendientes(frmMain));
        buttonLogout = makeButtonForMenu("Cerrar sesión", new CerrarSesion(frmMain));

        panelMenu.add(buttonPerfil);
        panelMenu.add(buttonPacientesPendientes);
        panelMenu.add(buttonLogout);
    }

    private void makeLabelBrand() {
        labelBrand = new JLabel("HOSPITAL");
        labelBrand.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        labelBrand.setForeground(Color.WHITE);
        labelBrand.setIcon(new ImageIcon("assets/img/hospital.png"));
    }

    private void makeImageAvatar() {
        imageAvatar = new JLabel();
        String genero = null;
        if (SesionUsuario.persona != null) {
            genero = SesionUsuario.persona.getGenero();
        }
        if (genero != null && genero.equals("F")) {
            imageAvatar.setIcon(new ImageIcon("assets/img/avatar-doctora.png"));
        } else {
            imageAvatar.setIcon(new ImageIcon("assets/img/avatar-doctor.png"));
        }
    }

    private void makeLabelUsuario() {
        String usuario = "";
        if (SesionUsuario.cuenta != null && SesionUsuario.cuenta.getUsuario() != null) {
            usuario = SesionUsuario.cuenta.getUsuario();
        }
        labelUsuario = new JLabel(usuario);
        double width = labelUsuario.getPreferredSize().getWidth();
        if (width > 120) {
            labelUsuario.setPreferredSize(new Dimension(200, 35));
        }
        labelUsuario.setForeground(Color.WHITE);
        labelUsuario.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
    }

    private JButton makeButtonForMenu(String name, ActionListener listener) {
        JButton button = new JButton(name);
        button.setBackground(new Color(38, 53, 72));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.addActionListener(listener);
        return button;
    }
}
