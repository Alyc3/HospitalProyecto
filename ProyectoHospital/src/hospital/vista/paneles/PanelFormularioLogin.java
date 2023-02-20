package hospital.vista.paneles;

import hospital.modelo.Cuenta;
import hospital.modelo.Rol;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.VariablesGlobales;
import hospital.vista.FrmMain;
import hospital.vista.listeners.IniciarSesion;
import hospital.vista.listeners.RedirigirAFrmSignup;
import hospital.vista.utilities.CrearFormularios;
import java.awt.*;
import javax.swing.*;

/**
 * Sirve para generar la configuraciòn del panel del formulario del login,
 * dependiendo del rol que tiene el usuario
 */
public class PanelFormularioLogin extends JPanel {

    private FrmMain frmMain;

    private Rol rol;

    private JPanel panelMain;
    private JPanel panelTitulo;
    private JPanel panelDatos;
    private JPanel panelBotones;

    private JLabel labelTitulo;
    private JLabel labelUsuario;
    private JLabel labelClave;
    private JLabel labelErrorUsuario;
    private JLabel labelErrorClave;

    private JTextField fieldUsuario;
    private JPasswordField fieldClave;

    private JButton buttonIniciarSesion;

    private JButton linkRegistrar;

    public PanelFormularioLogin(FrmMain window, Rol rol) {
        this.frmMain = window;
        this.rol = rol;
        setLayout(new BorderLayout());
        makePanelMain();
        add(panelMain, BorderLayout.CENTER);
    }

    private void makePanelMain() {
        panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.setBackground(Color.WHITE);
        panelMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        makePanelTitulo();
        makePanelDatos();
        makePanelBotones();
        panelMain.add(panelTitulo);
        panelMain.add(panelDatos);
        panelMain.add(panelBotones);
    }

    private void makePanelTitulo() {
        panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout());
        panelTitulo.setBackground(new Color(0, 0, 0, 0));
        labelTitulo = CrearFormularios.crearTituloLabel(rol.equals(VariablesGlobales.ROL_MEDICO) ? "LOGIN MÉDICOS" : "LOGIN PACIENTES");
        panelTitulo.add(labelTitulo);
    }

    private void makePanelDatos() {
        panelDatos = new JPanel();
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
        panelDatos.setBackground(new Color(0, 0, 0, 0));
        panelDatos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        labelUsuario = CrearFormularios.crearEtiquetaDesdeCampo("Usuario");
        fieldUsuario = CrearFormularios.crearTextField(20);
        labelErrorUsuario = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelClave = CrearFormularios.crearEtiquetaDesdeCampo("Clave");
        fieldClave = CrearFormularios.crearPasswordFiled(20);
        labelErrorClave = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        JPanel panelUsuario = CrearFormularios.crearPanelCampo(labelUsuario, fieldUsuario, labelErrorUsuario);

        JPanel panelClave = CrearFormularios.crearPanelCampo(labelClave, fieldClave, labelErrorClave);

        panelDatos.add(panelUsuario);
        panelDatos.add(panelClave);
    }

    private void makePanelBotones() {
        panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setBackground(new Color(0, 0, 0, 0));

        JPanel panelButtonIniciarSesion = new JPanel();
        panelButtonIniciarSesion.setLayout(new FlowLayout());
        panelButtonIniciarSesion.setBackground(new Color(0, 0, 0, 0));
        makeButtonIniciarSesion();
        panelButtonIniciarSesion.add(buttonIniciarSesion);

        JPanel panelLinkRegistrar = new JPanel();
        panelLinkRegistrar.setLayout(new FlowLayout());
        panelLinkRegistrar.setBackground(Color.WHITE);
        makeLinkRegistrar();
        panelLinkRegistrar.add(linkRegistrar);

        panelBotones.add(panelButtonIniciarSesion);
        panelBotones.add(panelLinkRegistrar);
    }

    private void makeButtonIniciarSesion() {
        buttonIniciarSesion = CrearFormularios.crearButton("Iniciar Sesión", 160, 40, new IniciarSesion(frmMain, this));
    }

    private void makeLinkRegistrar() {
        linkRegistrar = CrearFormularios.crearLink("Registrarse", new RedirigirAFrmSignup(frmMain, rol));
    }

    public Cuenta getCuenta() throws ModeloException {
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario(fieldUsuario.getText());
        cuenta.setClave(new String(fieldClave.getPassword()));
        return cuenta;
    }

    public Rol getRol() {
        return rol;
    }
}
