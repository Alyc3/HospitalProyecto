package hospital.vista.paneles;

import hospital.modelo.Cuenta;
import hospital.modelo.HistorialMedico;
import hospital.modelo.Medico;
import hospital.modelo.Persona;
import hospital.modelo.Rol;
import hospital.modelo.global.VariablesGlobales;
import hospital.modelo.utilities.Tiempo.UtilidadesTiempo;
import hospital.vista.FrmMain;
import hospital.vista.listeners.RegistrarPersona;
import java.awt.*;
import javax.swing.*;

public class PanelFormularioSignup extends JPanel {

    private FrmMain window;

    private Rol rol;

    private JPanel panelFormulario;

    private JLabel labelTituloFormulario;
    private JLabel labelUsuario;
    private JLabel labelClave;
    private JLabel labelCorreo;
    private JLabel labelTelefono;
    private JLabel labelCedula;
    private JLabel labelNombre;
    private JLabel labelApellido;
    private JLabel labelDireccion;
    private JLabel labelGenero;
    private JLabel labelDia;
    private JLabel labelMes;
    private JLabel labelAño;
    private JLabel labelEspecialidad;

    private JButton linkIniciarSesion;

    private JTextField fieldUsuario;
    private JPasswordField fieldClave;
    private JTextField fieldCorreo;
    private JTextField fieldTelefono;
    private JTextField fieldCedula;
    private JTextField fieldNombre;
    private JTextField fieldApellido;
    private JTextField fieldDireccion;
    private JComboBox<String> fieldGenero;
    private JComboBox<String> fieldDia;
    private JComboBox<String> fieldMes;
    private JComboBox<String> fieldAño;
    private JTextField fieldEspecialidad;

    private JButton buttonRegistrar;

    String[] optionsGenero = {"F", "M"};
    String[] optionsAño = UtilidadesTiempo.getAniosDesdeHasta(1900, 2023);
    String[] optionsMes = UtilidadesTiempo.getMesesEspaniol();
    String[] optionsDia = UtilidadesTiempo.getMaximoNumDiasMes();

    public PanelFormularioSignup(FrmMain window, Rol rol) {
        this.window = window;
        this.rol = rol;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        makePanelFormulario();
        add(panelFormulario);
    }

    private void makePanelFormulario() {
        panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));

        JPanel panelTituloFormulario = new JPanel();
        panelTituloFormulario.setLayout(new FlowLayout());
        makeLabelTituloFormulario();
        panelTituloFormulario.setBackground(new Color(0, 0, 0, 0));
        panelTituloFormulario.add(labelTituloFormulario);

        JPanel panelUsuario = new JPanel();
        panelUsuario.setLayout(new BorderLayout());
        labelUsuario = makeLabelForFormulario("Usuario");
        fieldUsuario = makeTextFieldForFormulario(300, 25);
        panelUsuario.add(labelUsuario, BorderLayout.NORTH);
        panelUsuario.add(fieldUsuario, BorderLayout.SOUTH);
        panelUsuario.setBackground(new Color(0, 0, 0, 0));
        panelUsuario.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panelClave = new JPanel();
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            System.out.println("...");
            panelClave.setLayout(new BorderLayout());
            labelClave = makeLabelForFormulario("Clave");
            fieldClave = makePasswordFieldForFormulario(300, 25);
            panelClave.add(labelClave, BorderLayout.NORTH);
            panelClave.add(fieldClave, BorderLayout.SOUTH);
            panelClave.setBackground(new Color(0, 0, 0, 0));
            panelClave.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }

        JPanel panelCorreo = new JPanel();
        panelCorreo.setLayout(new BorderLayout());
        labelCorreo = makeLabelForFormulario("Correo");
        fieldCorreo = makeTextFieldForFormulario(300, 25);
        panelCorreo.add(labelCorreo, BorderLayout.NORTH);
        panelCorreo.add(fieldCorreo, BorderLayout.SOUTH);
        panelCorreo.setBackground(new Color(0, 0, 0, 0));
        panelCorreo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panelCedulaTelefono = new JPanel();
        panelCedulaTelefono.setLayout(new BoxLayout(panelCedulaTelefono, BoxLayout.X_AXIS));

        JPanel panelCedula = new JPanel();
        panelCedula.setLayout(new BorderLayout());
        labelCedula = makeLabelForFormulario("Cedula");
        fieldCedula = makeTextFieldForFormulario(120, 25);
        panelCedula.add(labelCedula, BorderLayout.NORTH);
        panelCedula.add(fieldCedula, BorderLayout.SOUTH);
        panelCedula.setBackground(new Color(0, 0, 0, 0));
        panelCedula.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        JPanel panelTelefono = new JPanel();
        panelTelefono.setLayout(new BorderLayout());
        labelTelefono = makeLabelForFormulario("Telefono");
        fieldTelefono = makeTextFieldForFormulario(180, 25);
        panelTelefono.add(labelTelefono, BorderLayout.NORTH);
        panelTelefono.add(fieldTelefono, BorderLayout.SOUTH);
        panelTelefono.setBackground(new Color(0, 0, 0, 0));
        panelTelefono.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        panelCedulaTelefono.add(panelCedula);
        panelCedulaTelefono.add(panelTelefono);
        panelCedulaTelefono.setBackground(new Color(0, 0, 0, 0));
        panelCedulaTelefono.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JPanel panelNombreApellido = new JPanel();
        panelNombreApellido.setLayout(new BoxLayout(panelNombreApellido, BoxLayout.X_AXIS));

        JPanel panelNombre = new JPanel();
        panelNombre.setLayout(new BorderLayout());
        labelNombre = makeLabelForFormulario("Nombre");
        fieldNombre = makeTextFieldForFormulario(120, 25);
        panelNombre.add(labelNombre, BorderLayout.NORTH);
        panelNombre.add(fieldNombre, BorderLayout.SOUTH);
        panelNombre.setBackground(new Color(0, 0, 0, 0));
        panelNombre.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        JPanel panelApellido = new JPanel();
        panelApellido.setLayout(new BorderLayout());
        labelApellido = makeLabelForFormulario("Apellido");
        fieldApellido = makeTextFieldForFormulario(180, 25);
        panelApellido.add(labelApellido, BorderLayout.NORTH);
        panelApellido.add(fieldApellido, BorderLayout.SOUTH);
        panelApellido.setBackground(new Color(0, 0, 0, 0));
        panelApellido.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        panelNombreApellido.add(panelNombre);
        panelNombreApellido.add(panelApellido);
        panelNombreApellido.setBackground(new Color(0, 0, 0, 0));
        panelNombreApellido.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JPanel panelDireccionGenero = new JPanel();
        panelDireccionGenero.setLayout(new BoxLayout(panelDireccionGenero, BoxLayout.X_AXIS));

        JPanel panelDireccion = new JPanel();
        panelDireccion.setLayout(new BorderLayout());
        labelDireccion = makeLabelForFormulario("Direccion");
        fieldDireccion = makeTextFieldForFormulario(250, 25);
        panelDireccion.add(labelDireccion, BorderLayout.NORTH);
        panelDireccion.add(fieldDireccion, BorderLayout.SOUTH);
        panelDireccion.setBackground(new Color(0, 0, 0, 0));
        panelDireccion.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        JPanel panelGenero = new JPanel();
        panelGenero.setLayout(new BorderLayout());
        labelGenero = makeLabelForFormulario("Genero");
        String[] data = {"M", "F"};
        fieldGenero = makeComboBoxForFormulario(50, 25, data);
        panelGenero.add(labelGenero, BorderLayout.NORTH);
        panelGenero.add(fieldGenero, BorderLayout.SOUTH);
        panelGenero.setBackground(new Color(0, 0, 0, 0));
        panelGenero.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        panelDireccionGenero.add(panelDireccion);
        panelDireccionGenero.add(panelGenero);
        panelDireccionGenero.setBackground(new Color(0, 0, 0, 0));
        panelDireccionGenero.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JPanel panelFecha = new JPanel();
        panelFecha.setLayout(new BoxLayout(panelFecha, BoxLayout.X_AXIS));

        JPanel panelDia = new JPanel();
        panelDia.setLayout(new BorderLayout());
        labelDia = makeLabelForFormulario("Dia");
        fieldDia = makeComboBoxForFormulario(50, 25, optionsDia);
        panelDia.add(labelDia, BorderLayout.NORTH);
        panelDia.add(fieldDia, BorderLayout.SOUTH);
        panelDia.setBackground(new Color(0, 0, 0, 0));
        panelDia.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        JPanel panelMes = new JPanel();
        panelMes.setLayout(new BorderLayout());
        labelMes = makeLabelForFormulario("Mes");
        fieldMes = makeComboBoxForFormulario(150, 25, optionsMes);
        panelMes.add(labelMes, BorderLayout.NORTH);
        panelMes.add(fieldMes, BorderLayout.SOUTH);
        panelMes.setBackground(new Color(0, 0, 0, 0));
        panelMes.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        JPanel panelAño = new JPanel();
        panelAño.setLayout(new BorderLayout());
        labelAño = makeLabelForFormulario("Año");
        fieldAño = makeComboBoxForFormulario(100, 25, optionsAño);
        panelAño.add(labelAño, BorderLayout.NORTH);
        panelAño.add(fieldAño, BorderLayout.SOUTH);
        panelAño.setBackground(new Color(0, 0, 0, 0));
        panelAño.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        panelFecha.add(panelDia);
        panelFecha.add(panelMes);
        panelFecha.add(panelAño);
        panelFecha.setBackground(new Color(0, 0, 0, 0));
        panelFecha.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JPanel panelEspecialidad = new JPanel();
        panelEspecialidad.setLayout(new BorderLayout());
        labelEspecialidad = makeLabelForFormulario("Especialidad");
        fieldEspecialidad = makeTextFieldForFormulario(300, 25);
        panelEspecialidad.add(labelEspecialidad, BorderLayout.NORTH);
        panelEspecialidad.add(fieldEspecialidad, BorderLayout.SOUTH);
        panelEspecialidad.setBackground(new Color(0, 0, 0, 0));
        panelEspecialidad.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panelButtonRegistrar = new JPanel();
        panelButtonRegistrar.setLayout(new FlowLayout());
        makeButtonRegistrar();
        panelButtonRegistrar.add(buttonRegistrar);
        panelButtonRegistrar.setBackground(new Color(0, 0, 0, 0));

        JPanel panelLinkIniciarSesion = new JPanel();
        panelLinkIniciarSesion.setLayout(new FlowLayout());
        makeLinkIniciarSesion();
        panelLinkIniciarSesion.add(linkIniciarSesion);
        panelLinkIniciarSesion.setBackground(new Color(0, 0, 0, 0));

        panelFormulario.add(panelTituloFormulario);
        panelFormulario.add(panelUsuario);
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            panelFormulario.add(panelClave);
        }
        panelFormulario.add(panelCorreo);
        panelFormulario.add(panelCedulaTelefono);
        panelFormulario.add(panelNombreApellido);
        panelFormulario.add(panelDireccionGenero);
        panelFormulario.add(panelFecha);
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            panelFormulario.add(panelEspecialidad);
        }
        panelFormulario.add(panelButtonRegistrar);
        panelFormulario.add(panelLinkIniciarSesion);
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

    private JComboBox<String> makeComboBoxForFormulario(int width, int height, String[] data) {
        JComboBox<String> comboBox = new JComboBox<String>(data);
        comboBox.setPreferredSize(new Dimension(width, height));
        return comboBox;
    }

    private void makeButtonRegistrar() {
        buttonRegistrar = new JButton("Registrar");
        buttonRegistrar.setPreferredSize(new Dimension(100, 40));
        buttonRegistrar.setBackground(new Color(0, 76, 153));
        buttonRegistrar.setForeground(Color.WHITE);
        buttonRegistrar.setFocusable(false);
        buttonRegistrar.addActionListener(new RegistrarPersona(window, this));
    }

    private void makeLinkIniciarSesion() {
        linkIniciarSesion = new JButton("Iniciar Sesión");
        linkIniciarSesion.setBackground(new Color(255, 255, 255, 0));
        linkIniciarSesion.setForeground(new Color(0, 76, 153));
        linkIniciarSesion.setFocusable(false);
        linkIniciarSesion.setContentAreaFilled(false);
        linkIniciarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        linkIniciarSesion.setBorder(BorderFactory.createEmptyBorder());
    }

    private void makeLabelTituloFormulario() {
        if (rol.equals(VariablesGlobales.ROL_PACIENTE)) {
            labelTituloFormulario = new JLabel("REGISTRO PACIENTES");
        } else {
            labelTituloFormulario = new JLabel("REGISTRO MÉDICOS");
        }
        labelTituloFormulario.setFont(new Font("Helvetica", Font.BOLD, 24));
        labelTituloFormulario.setForeground(new Color(0, 76, 153));
    }

    public Cuenta getCuenta() {
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario(fieldUsuario.getText());
        if (rol == VariablesGlobales.ROL_MEDICO) {
            cuenta.setClave(new String(fieldClave.getPassword()));
            // falta limpiar las variables donde se guardo la clave.
        }
        return cuenta;
    }

    public Persona getPersona() {
        Persona persona = new Persona();
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            persona = new Medico();
        }

        persona.setCedula(fieldCedula.getText());
        persona.setNombre(fieldNombre.getText());
        persona.setApellido(fieldApellido.getText());
        persona.setTelefono(fieldTelefono.getText());
        persona.setCorreo(fieldCorreo.getText());
        persona.setDireccion(fieldDireccion.getText());
        persona.setGenero(fieldGenero.getSelectedItem().toString());
        persona.setRol(VariablesGlobales.ROL_MEDICO);
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            Medico medico = (Medico) persona;
            medico.setEspecialidad(fieldEspecialidad.getText());
        }

        return persona;
    }

    public HistorialMedico getHistorialMedico() {
        HistorialMedico historialMedico = new HistorialMedico();
        String año = fieldAño.getSelectedItem().toString();

        String mes = fieldMes.getSelectedItem().toString();
        mes = String.valueOf(UtilidadesTiempo.posicionMes(mes));
        if (mes.length() == 1) {
            mes = "0" + mes;
        }

        String dia = fieldDia.getSelectedItem().toString();
        if (dia.length() == 1) {
            dia = "0" + dia;
        }

        String fecha = año + "-" + mes + "-" + dia;
        historialMedico.setFechaNacimiento(fecha);
        return historialMedico;
    }

    public void showDialog() {
        JOptionPane.showMessageDialog(window, "Fallo el inicio de sesión", "Resultado del registro", JOptionPane.ERROR_MESSAGE);
    }

    public void showResultadoRegistro(boolean successful, String message) {
        if (successful) {
            JOptionPane.showMessageDialog(window, message, "Resultado del registro", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(window, message, "Resultado del registro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Rol getRol() {
        return rol;
    }
}
