package hospital.vista;

import hospital.modelo.Cuenta;
import hospital.modelo.Rol;
import hospital.vista.listeners.EventoButtonIniciarSesionPressed;
import hospital.vista.listeners.EventoLinkRegistrarsePressed;
import java.awt.*;
import javax.swing.*;

public class FrmLogin {

    private FrmMain window;

    private Rol rol;

    // Paneles
    private JPanel panelHeader;
    private JPanel panelBody;
    private JPanel panelFormulario;

    // Etiquetas
    private JLabel labelTituloHeader;
    private JLabel labelUsuario;
    private JLabel labelClave;

    // Enlaces
    private JButton linkRegistrarse;

    // Cajas de texto
    private JTextField fieldUsuario;
    private JPasswordField fieldClave;

    // Botones
    private JButton buttonInciarSesion;

    public FrmLogin(FrmMain window) {
        this.window = window;
    }

    public Rol getRol() {
        return rol;
    }

    public void show(Rol rol) {
        window.clear();
        this.rol = rol;
        System.out.println(rol.getNombre());

        Container cp = window.getContentPane();

        cp.setLayout(new BorderLayout());

        makePanelHeader();
        makePanelBody();

        cp.setBackground(Color.ORANGE);

        cp.add(panelHeader, BorderLayout.NORTH);
        cp.add(panelBody, BorderLayout.CENTER);

        cp.validate();
        cp.repaint();
    }

    private void makePanelHeader() {
        panelHeader = new JPanel();
        panelHeader.setLayout(new FlowLayout());
        panelHeader.setBackground(Color.RED);

        makeLabelTituloHeader();
        panelHeader.add(labelTituloHeader);
    }

    private void makePanelBody() {
        panelBody = new JPanel();
        panelBody.setLayout(new FlowLayout());
        panelHeader.setBackground(Color.BLUE);

        makePanelFormulario();
        panelBody.add(panelFormulario);
    }

    private void makePanelFormulario() {
        panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(6, 1));
        panelHeader.setBackground(Color.BLUE);

        makeLabelUsuario();
        makeLabelClave();
        makeLinkRegistrarse();

        makeFieldUsuario();
        makeFieldClave();

        makeButtonIniciarSesion();

        panelFormulario.add(labelUsuario);
        panelFormulario.add(fieldUsuario);
        panelFormulario.add(labelClave);
        panelFormulario.add(fieldClave);
        panelFormulario.add(buttonInciarSesion);
        panelFormulario.add(linkRegistrarse);
    }

    private void makeLabelTituloHeader() {
        labelTituloHeader = new JLabel("LOGIN");
    }

    private void makeLabelUsuario() {
        labelUsuario = new JLabel("Usuario: ");
        labelUsuario.setFont(new Font("Helvetica", Font.BOLD, 10));
    }

    private void makeLabelClave() {
        labelClave = new JLabel("Clave: ");
    }

    private void makeLinkRegistrarse() {
        linkRegistrarse = new JButton("Registrarse");
        linkRegistrarse.setBackground(new Color(255, 255, 255, 0));
        linkRegistrarse.setForeground(Color.BLUE);
        linkRegistrarse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        linkRegistrarse.addActionListener(new EventoLinkRegistrarsePressed(this));
        linkRegistrarse.setFocusable(false);
        //linkRegistrarse.setFocusPainted(false);
        linkRegistrarse.setContentAreaFilled(false);
    }

    private void makeFieldUsuario() {
        fieldUsuario = new JTextField();
        fieldUsuario.setPreferredSize(new Dimension(350, 30));
    }

    private void makeFieldClave() {
        fieldClave = new JPasswordField();
    }

    private void makeButtonIniciarSesion() {
        buttonInciarSesion = new JButton("Iniciar Sesión");
        buttonInciarSesion.setBackground(new Color(0, 76, 153));
        buttonInciarSesion.setForeground(Color.WHITE);
        //	buttonInciarSesion.setPreferredSize(new Dimension(200, 80));
        buttonInciarSesion.addActionListener(new EventoButtonIniciarSesionPressed(this));
    }

    public Cuenta getCuenta() {
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario(fieldUsuario.getText());
        cuenta.setClave(new String(fieldClave.getPassword()));
        // falta limpiar las variables donde se guardo la clave.

        return cuenta;
    }

    public void loadSignupView(Rol rol) {
        window.loadSignupView(rol);
    }

}
