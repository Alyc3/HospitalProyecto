package hospital.vista.paneles;

import hospital.modelo.Cuenta;
import hospital.modelo.HistorialMedico;
import hospital.modelo.Medico;
import hospital.modelo.Persona;
import hospital.modelo.Rol;
import hospital.modelo.enumeradores.EncontrarTipoSanguineo;
import hospital.modelo.enumeradores.TipoSanguineo;
import hospital.modelo.excepciones.CampoNovalidoException;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.VariablesGlobales;
import hospital.modelo.utilities.Tiempo.UtilidadesTiempo;
import hospital.vista.FrmMain;
import hospital.vista.listeners.RegistrarPersona;
import hospital.vista.utilities.CrearFormularios;
import hospital.vista.utilities.ExtractorDeDatosDeForms;
import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;

public class PanelFormularioSignup extends JPanel {

    private FrmMain frmMain;

    private Rol rol;

    private JPanel panelMain;
    private JPanel panelTitulo;
    private JPanel panelDatos;
    private JPanel panelBotones;

    private JLabel labelTitulo;
    private JLabel labelUsuario;
    private JLabel labelClave;
    private JLabel labelCorreo;
    private JLabel labelTipoSanguineo;
    private JLabel labelNombre;
    private JLabel labelApellido;
    private JLabel labelCedula;
    private JLabel labelTelefono;
    private JLabel labelFechaNacimiento;
    private JLabel labelDia;
    private JLabel labelMes;
    private JLabel labelAño;
    private JLabel labelDireccion;
    private JLabel labelGenero;
    private JLabel labelEspecialidad;
    private JLabel labelErrorUsuario;
    private JLabel labelErrorClave;
    private JLabel labelErrorCorreo;
    private JLabel labelErrorTipoSanguineo;
    private JLabel labelErrorNombre;
    private JLabel labelErrorApellido;
    private JLabel labelErrorCedula;
    private JLabel labelErrorTelefono;
    private JLabel labelErrorFechaNacimiento;
    private JLabel labelErrorDia;
    private JLabel labelErrorMes;
    private JLabel labelErrorAño;
    private JLabel labelErrorDireccion;
    private JLabel labelErrorGenero;
    private JLabel labelErrorEspecialidad;

    private JTextField fieldUsuario;
    private JPasswordField fieldClave;
    private JTextField fieldCorreo;
    private JComboBox<String> fieldTipoSanguineo;
    private JTextField fieldNombre;
    private JTextField fieldApellido;
    private JTextField fieldCedula;
    private JTextField fieldTelefono;
    private JComboBox<String> fieldDia;
    private JComboBox<String> fieldMes;
    private JComboBox<String> fieldAño;
    private JTextField fieldDireccion;
    private JComboBox<String> fieldGenero;
    private JTextField fieldEspecialidad;

    private JButton buttonRegistrar;

    private JButton linkIniciarSesion;

    private String[] optionsTipoSanguineo = {"A+", "O+", "B+", "AB+", "A-", "O-", "B-", "AB-"};
    private String[] optionsGenero = {"M", "F"};
    private String[] optionsAño = UtilidadesTiempo.getAniosDesdeHasta(1900, 2023);
    private String[] optionsMes = UtilidadesTiempo.getMesesEspaniol();
    private String[] optionsDia = UtilidadesTiempo.getMaximoNumDiasMes();

    public PanelFormularioSignup(FrmMain frmMain, Rol rol) {
        this.frmMain = frmMain;
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

        labelTitulo = CrearFormularios.crearTituloLabel(rol.equals(VariablesGlobales.ROL_MEDICO) ? "REGISTRO MÉDICOS" : "REGISTRO PACIENTES");

        panelTitulo.add(labelTitulo);
    }

    private void makePanelDatos() {
        panelDatos = new JPanel();
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
        panelDatos.setBackground(new Color(0, 0, 0, 0));
        panelDatos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        labelUsuario = CrearFormularios.crearEtiquetaDesdeCampo("Usuario");
        fieldUsuario = CrearFormularios.crearTextField(13);
        labelErrorUsuario = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelClave = CrearFormularios.crearEtiquetaDesdeCampo("Clave");
        fieldClave = CrearFormularios.crearPasswordFiled(12);
        labelErrorClave = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelCorreo = CrearFormularios.crearEtiquetaDesdeCampo("Correo");
        fieldCorreo = CrearFormularios.crearTextField(18);
        labelErrorCorreo = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelTipoSanguineo = CrearFormularios.crearEtiquetaDesdeCampo("Tipo sanguíneo");
        fieldTipoSanguineo = CrearFormularios.crearComboBox(94, 34, optionsTipoSanguineo);
        labelErrorTipoSanguineo = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelNombre = CrearFormularios.crearEtiquetaDesdeCampo("Nombre");
        fieldNombre = CrearFormularios.crearTextField(10);
        labelErrorNombre = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelApellido = CrearFormularios.crearEtiquetaDesdeCampo("Apellido");
        fieldApellido = CrearFormularios.crearTextField(15);
        labelErrorApellido = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelCedula = CrearFormularios.crearEtiquetaDesdeCampo("Cédula");
        fieldCedula = CrearFormularios.crearTextField(10);
        labelErrorCedula = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelTelefono = CrearFormularios.crearEtiquetaDesdeCampo("Teléfono");
        fieldTelefono = CrearFormularios.crearTextField(15);
        labelErrorTelefono = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelFechaNacimiento = CrearFormularios.crearEtiquetaDesdeCampo("Fecha de nacimiento");
        labelErrorFechaNacimiento = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelDia = CrearFormularios.crearEtiquetaDesdeCampo("Dia");
        fieldDia = CrearFormularios.crearComboBox(60, 34, optionsDia);
        labelErrorDia = CrearFormularios.crearEtiquetaErrDesdeCampo("");

        labelMes = CrearFormularios.crearEtiquetaDesdeCampo("Mes");
        fieldMes = CrearFormularios.crearComboBox(140, 34, optionsMes);
        labelErrorMes = CrearFormularios.crearEtiquetaErrDesdeCampo("");

        labelAño = CrearFormularios.crearEtiquetaDesdeCampo("Año");
        fieldAño = CrearFormularios.crearComboBox(100, 34, optionsAño);
        labelErrorAño = CrearFormularios.crearEtiquetaErrDesdeCampo("");

        labelDireccion = CrearFormularios.crearEtiquetaDesdeCampo("Dirección");
        fieldDireccion = CrearFormularios.crearTextField(21);
        labelErrorDireccion = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelGenero = CrearFormularios.crearEtiquetaDesdeCampo("Género");
        fieldGenero = CrearFormularios.crearComboBox(58, 34, optionsGenero);
        labelErrorGenero = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelEspecialidad = CrearFormularios.crearEtiquetaDesdeCampo("Especialidad");
        fieldEspecialidad = CrearFormularios.crearTextField(26);
        labelErrorEspecialidad = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        JPanel panelUsuarioClave = CrearFormularios.crearPanelMultiCampo();
        panelUsuarioClave.add(CrearFormularios.crearPanelCampo(labelUsuario, fieldUsuario, labelErrorUsuario));
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            panelUsuarioClave.add(CrearFormularios.crearPanelCampo(labelClave, fieldClave, labelErrorClave));
        }

        JPanel panelCorreoTipoSanguineo = CrearFormularios.crearPanelMultiCampo();
        panelCorreoTipoSanguineo.add(CrearFormularios.crearPanelCampo(labelCorreo, fieldCorreo, labelErrorCorreo));
        if (rol.equals(VariablesGlobales.ROL_PACIENTE)) {
            panelCorreoTipoSanguineo.add(CrearFormularios.crearPanelCampo(labelTipoSanguineo, fieldTipoSanguineo, labelErrorTipoSanguineo));
        }

        JPanel panelNombreApellido = CrearFormularios.crearPanelMultiCampo();
        panelNombreApellido.add(CrearFormularios.crearPanelCampo(labelNombre, fieldNombre, labelErrorNombre));
        panelNombreApellido.add(CrearFormularios.crearPanelCampo(labelApellido, fieldApellido, labelErrorApellido));

        JPanel panelCedulaTelefono = CrearFormularios.crearPanelMultiCampo();
        panelCedulaTelefono.add(CrearFormularios.crearPanelCampo(labelCedula, fieldCedula, labelErrorCedula));
        panelCedulaTelefono.add(CrearFormularios.crearPanelCampo(labelTelefono, fieldTelefono, labelErrorTelefono));

        JPanel panelInternoFechaNacimiento = CrearFormularios.crearPanelMultiCampo();
        panelInternoFechaNacimiento.add(CrearFormularios.crearPanelCampo(labelDia, fieldDia, labelErrorDia));
        panelInternoFechaNacimiento.add(CrearFormularios.crearPanelCampo(labelMes, fieldMes, labelErrorMes));
        panelInternoFechaNacimiento.add(CrearFormularios.crearPanelCampo(labelAño, fieldAño, labelErrorAño));
        JPanel panelFechaNacimiento = CrearFormularios.crearPanelCampo(labelFechaNacimiento, panelInternoFechaNacimiento, labelErrorFechaNacimiento);

        JPanel panelDireccionGenero = CrearFormularios.crearPanelMultiCampo();
        panelDireccionGenero.add(CrearFormularios.crearPanelCampo(labelDireccion, fieldDireccion, labelErrorDireccion));
        panelDireccionGenero.add(CrearFormularios.crearPanelCampo(labelGenero, fieldGenero, labelErrorGenero));

        JPanel panelEspecialidad = CrearFormularios.crearPanelCampo(labelEspecialidad, fieldEspecialidad, labelErrorEspecialidad);

        panelDatos.add(panelUsuarioClave);
        panelDatos.add(panelCorreoTipoSanguineo);
        panelDatos.add(panelNombreApellido);
        panelDatos.add(panelCedulaTelefono);
        panelDatos.add(panelFechaNacimiento);
        panelDatos.add(panelDireccionGenero);
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            panelDatos.add(panelEspecialidad);
        }
    }

    private void makePanelBotones() {
        panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setBackground(new Color(0, 0, 0, 0));

        JPanel panelButtonRegistrar = new JPanel();
        panelButtonRegistrar.setLayout(new FlowLayout());
        panelButtonRegistrar.setBackground(new Color(0, 0, 0, 0));
        makeButtonRegistrar();
        panelButtonRegistrar.add(buttonRegistrar);

        JPanel panelLinkIniciarSesion = new JPanel();
        panelLinkIniciarSesion.setLayout(new FlowLayout());
        panelLinkIniciarSesion.setBackground(Color.WHITE);
        makeLinkIniciarSesion();
        panelLinkIniciarSesion.add(linkIniciarSesion);

        panelBotones.add(panelButtonRegistrar);
        panelBotones.add(panelLinkIniciarSesion);
    }

    private void makeButtonRegistrar() {
        buttonRegistrar = CrearFormularios.crearButton("Registrar", 160, 40, new RegistrarPersona(frmMain, this));
    }

    private void makeLinkIniciarSesion() {
    }

    public Cuenta getCuenta() throws ModeloException {
        Cuenta cuenta = new Cuenta();
        cuenta.setUsuario(fieldUsuario.getText());
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            cuenta.setClave(new String(fieldClave.getPassword()));
        }
        return cuenta;
    }

    public Persona getPersona() throws ModeloException {
        Persona persona;
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            persona = new Medico();
        } else {
            persona = new Persona();
        }

        persona.setRol(rol);

        try {
            persona.setCedula(fieldCedula.getText());
            persona.setNombre(fieldNombre.getText());
            persona.setApellido(fieldApellido.getText());
            persona.setTelefono(fieldTelefono.getText());
            persona.setCorreo(fieldCorreo.getText());
            persona.setDireccion(fieldDireccion.getText());
            persona.setGenero(fieldGenero.getSelectedItem().toString());

            LocalDate fechaNacimiento = ExtractorDeDatosDeForms.getLocalDateFrom("Fecha de Nacimiento", fieldAño, fieldMes, fieldDia);
            LocalDate fechaAhora = LocalDate.now();
            persona.setEdad(UtilidadesTiempo.aniosTranscurridos(fechaNacimiento, fechaAhora));

            if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
                Medico medico = (Medico) persona;
                medico.setEspecialidad(fieldEspecialidad.getText());
            }
        } catch (CampoNovalidoException e) {
            throw new ModeloException(
                    "El valor del campo " + e.getFieldName() + " es inválido",
                    e,
                    ErrorType.ErrorValorNoValido
            );
        }

        return persona;
    }

    public HistorialMedico getHistorialMedico() throws ModeloException {
        HistorialMedico historialMedico = new HistorialMedico();

        try {
            LocalDate fechaNacimiento = ExtractorDeDatosDeForms.getLocalDateFrom("Fecha de Nacimiento", fieldAño, fieldMes, fieldDia);
            historialMedico.setFechaNacimiento(fechaNacimiento.toString());

            if (rol.equals(VariablesGlobales.ROL_PACIENTE)) {
                String tipoSanguineoStr = fieldTipoSanguineo.getSelectedItem().toString();
                TipoSanguineo tipoSanguineo = EncontrarTipoSanguineo.encontrar(tipoSanguineoStr);
                historialMedico.setTipoSanguineo(tipoSanguineo);
            }
        } catch (CampoNovalidoException e) {
            throw new ModeloException(
                    "El valor del campo de " + e.getFieldName() + " es inválido",
                    e,
                    ErrorType.ErrorValorNoValido
            );
        }
        return historialMedico;
    }

    public Rol getRol() {
        return rol;
    }
}
