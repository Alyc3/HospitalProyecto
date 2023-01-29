package hospital.vista.paneles;

import hospital.modelo.Cuenta;
import hospital.modelo.Rol;
import hospital.modelo.global.VariablesGlobales;
import hospital.vista.FrmMain;
import hospital.vista.listeners.IniciarSesion;
import hospital.vista.listeners.RedirigirAFrmSignup;
import java.awt.*;
import javax.swing.*;

public class PanelFormularioLogin extends JPanel {

    private FrmMain window;

    private Rol rol;

    private JLabel labelTitulo;
    private JLabel labelUsuario;
    private JLabel labelClave;

    private JButton buttonInicio;

    private JButton linkRegistrarse;

    private JTextField fieldUsuario;
    private JPasswordField fieldClave;

    private JButton buttonIniciarSesion;

    public PanelFormularioLogin(FrmMain window, Rol rol) {
        this.window = window;
        this.rol = rol;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel panelUsuario = new JPanel();
        panelUsuario.setLayout(new BorderLayout());
        labelUsuario = makeLabelForFormulario("Usuario");
        fieldUsuario = makeTextFieldForFormulario(200, 30);
        panelUsuario.add(labelUsuario, BorderLayout.NORTH);
        panelUsuario.add(fieldUsuario, BorderLayout.SOUTH);
        panelUsuario.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panelClave = new JPanel();
        panelClave.setLayout(new BorderLayout());
        labelClave = makeLabelForFormulario("Clave");
        fieldClave = makePasswordFieldForFormulario(200, 30);
        panelClave.add(labelClave, BorderLayout.NORTH);
        panelClave.add(fieldClave, BorderLayout.SOUTH);
        panelClave.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panelButtonIniciarSesion = new JPanel();
        panelButtonIniciarSesion.setLayout(new FlowLayout());
        makeButtonIniciarSesion();
        panelButtonIniciarSesion.add(buttonIniciarSesion);

        makeLinkRegistrarse();

        add(panelUsuario);
        add(panelClave);
        add(panelButtonIniciarSesion);
        add(linkRegistrarse);
    }

    private JLabel makeLabelForFormulario(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Helvetica", Font.BOLD, 11));
        label.setForeground(new Color(89, 103, 128));
        return label;
    }

    private JTextField makeTextFieldForFormulario(int width, int height) {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(width, height));
        textField.setMargin(new Insets(0, 10, 0, 10));
        return textField;
    }

    private JPasswordField makePasswordFieldForFormulario(int width, int height) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(width, height));
        passwordField.setMargin(new Insets(0, 10, 0, 10));
        return passwordField;
    }

    private void makeButtonIniciarSesion() {
        buttonIniciarSesion = new JButton("Iniciar Sesión");
        buttonIniciarSesion.setBackground(new Color(0, 76, 153));
        buttonIniciarSesion.setForeground(Color.WHITE);
        buttonIniciarSesion.setPreferredSize(new Dimension(160, 40));
        buttonIniciarSesion.setFocusable(false);
        buttonIniciarSesion.addActionListener(new IniciarSesion(window, this));
        buttonIniciarSesion.setFont(new Font("Helvetica", Font.BOLD, 12));
    }

    private void makeLinkRegistrarse() {
        linkRegistrarse = new JButton("Registrarse");
        linkRegistrarse.setBackground(new Color(255, 255, 255, 0));
        linkRegistrarse.setForeground(Color.BLUE);
        linkRegistrarse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        System.out.println(this);
        if (rol == VariablesGlobales.ROL_MEDICO) {
            linkRegistrarse.addActionListener(new RedirigirAFrmSignup(window, VariablesGlobales.ROL_MEDICO));
        } else {
            linkRegistrarse.addActionListener(new RedirigirAFrmSignup(window, VariablesGlobales.ROL_PACIENTE));
        }
        linkRegistrarse.setFocusable(false);
        linkRegistrarse.setContentAreaFilled(false);
    }

    public Cuenta getCuenta() {
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario(fieldUsuario.getText());
        cuenta.setClave(new String(fieldClave.getPassword()));
        return cuenta;
    }

    public Rol getRol() {
        return rol;
    }
}
