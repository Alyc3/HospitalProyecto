package hospital.vista.paneles;

import hospital.modelo.Medico;
import hospital.modelo.Persona;
import hospital.modelo.Rol;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.modelo.global.SesionUsuario;
import hospital.modelo.global.VariablesGlobales;
import hospital.vista.servicio.Servicios;
import hospital.vista.FrmMain;
import hospital.vista.utilities.CrearFormularios;
import java.awt.*;
import javax.swing.*;

/**
 * Sirve para crear la configuración base del panel presentado para ver el
 * perfil de usuario
 */
public class PanelFormularioVerPerfil extends JPanel {

    private FrmMain frmMain;

    private Rol rol;

    private Servicios servicios;

    private JPanel panelMain;
    private JPanel panelTitulo;
    private JPanel panelDatos;

    private JLabel labelTitulo;
    private JLabel labelCorreo;
    private JLabel labelEdad;
    private JLabel labelNombre;
    private JLabel labelApellido;
    private JLabel labelCedula;
    private JLabel labelTelefono;
    private JLabel labelDireccion;
    private JLabel labelGenero;
    private JLabel labelEspecialidad;
    private JLabel labelErrorCorreo;
    private JLabel labelErrorEdad;
    private JLabel labelErrorNombre;
    private JLabel labelErrorApellido;
    private JLabel labelErrorCedula;
    private JLabel labelErrorTelefono;
    private JLabel labelErrorDireccion;
    private JLabel labelErrorGenero;
    private JLabel labelErrorEspecialidad;

    private JTextField fieldCorreo;
    private JTextField fieldEdad;
    private JTextField fieldNombre;
    private JTextField fieldApellido;
    private JTextField fieldCedula;
    private JTextField fieldTelefono;
    private JTextField fieldDireccion;
    private JComboBox fieldGenero;
    private JTextField fieldEspecialidad;

    private String[] optionsGenero = {"M", "F"};

    public PanelFormularioVerPerfil(FrmMain frmMain, Rol rol) {
        this.frmMain = frmMain;
        this.rol = rol;
        this.servicios = ManagerComponentes.servicios;
        setLayout(new BorderLayout());
        makePanelMain();
        add(panelMain, BorderLayout.CENTER);
        loadData();
    }

    private void makePanelMain() {
        panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.setBackground(Color.WHITE);
        panelMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        makePanelTitulo();
        makePanelDatos();

        panelMain.add(panelTitulo);
        panelMain.add(panelDatos);
    }

    private void makePanelTitulo() {
        panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout());
        panelTitulo.setBackground(new Color(0, 0, 0, 0));

        labelTitulo = CrearFormularios.crearEtiquetaDesdeCampo(rol.equals(VariablesGlobales.ROL_MEDICO) ? "PERFIL DEL MÉDICO" : "PERFIL DEL PACIENTE");

        panelTitulo.add(labelTitulo);
    }

    private void makePanelDatos() {
        panelDatos = new JPanel();
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
        panelDatos.setBackground(new Color(0, 0, 0, 0));
        panelDatos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        labelCorreo = CrearFormularios.crearEtiquetaDesdeCampo("Correo");
        fieldCorreo = CrearFormularios.crearTextField(18, false);
        labelErrorCorreo = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelEdad = CrearFormularios.crearEtiquetaDesdeCampo("Edad");
        fieldEdad = CrearFormularios.crearTextField(7, false);
        labelErrorEdad = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelNombre = CrearFormularios.crearEtiquetaDesdeCampo("Nombre");
        fieldNombre = CrearFormularios.crearTextField(10, false);
        labelErrorNombre = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelApellido = CrearFormularios.crearEtiquetaDesdeCampo("Apellido");
        fieldApellido = CrearFormularios.crearTextField(15, false);
        labelErrorApellido = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelCedula = CrearFormularios.crearEtiquetaDesdeCampo("Cédula");
        fieldCedula = CrearFormularios.crearTextField(10, false);
        labelErrorCedula = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelTelefono = CrearFormularios.crearEtiquetaDesdeCampo("Teléfono");
        fieldTelefono = CrearFormularios.crearTextField(15, false);
        labelErrorTelefono = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelDireccion = CrearFormularios.crearEtiquetaDesdeCampo("Dirección");
        fieldDireccion = CrearFormularios.crearTextField(21, false);
        labelErrorDireccion = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelGenero = CrearFormularios.crearEtiquetaDesdeCampo("Género");
        fieldGenero = CrearFormularios.crearComboBox(58, 34, optionsGenero, false);
        labelErrorGenero = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelEspecialidad = CrearFormularios.crearEtiquetaDesdeCampo("Especialidad");
        fieldEspecialidad = CrearFormularios.crearTextField(26, false);
        labelErrorEspecialidad = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        JPanel panelCorreoEdad = CrearFormularios.crearPanelMultiCampo();
        panelCorreoEdad.add(CrearFormularios.crearPanelCampo(labelCorreo, fieldCorreo, labelErrorCorreo));
        panelCorreoEdad.add(CrearFormularios.crearPanelCampo(labelEdad, fieldEdad, labelErrorEdad));

        JPanel panelNombreApellido = CrearFormularios.crearPanelMultiCampo();
        panelNombreApellido.add(CrearFormularios.crearPanelCampo(labelNombre, fieldNombre, labelErrorNombre));
        panelNombreApellido.add(CrearFormularios.crearPanelCampo(labelApellido, fieldApellido, labelErrorApellido));

        JPanel panelCedulaTelefono = CrearFormularios.crearPanelMultiCampo();
        panelCedulaTelefono.add(CrearFormularios.crearPanelCampo(labelCedula, fieldCedula, labelErrorCedula));
        panelCedulaTelefono.add(CrearFormularios.crearPanelCampo(labelTelefono, fieldTelefono, labelErrorTelefono));

        JPanel panelDireccionGenero = CrearFormularios.crearPanelMultiCampo();
        panelDireccionGenero.add(CrearFormularios.crearPanelCampo(labelDireccion, fieldDireccion, labelErrorDireccion));
        panelDireccionGenero.add(CrearFormularios.crearPanelCampo(labelGenero, fieldGenero, labelErrorGenero));

        JPanel panelEspecialidad = CrearFormularios.crearPanelCampo(labelEspecialidad, fieldEspecialidad, labelErrorEspecialidad);
        panelDatos.add(panelCorreoEdad);
        panelDatos.add(panelNombreApellido);
        panelDatos.add(panelCedulaTelefono);
        panelDatos.add(panelDireccionGenero);
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            panelDatos.add(panelEspecialidad);
        }
    }

    public Persona getPersona() throws ModeloException {
        Persona persona;
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            persona = new Medico();
        } else {
            persona = new Persona();
        }
        persona.setRol(rol);
        persona.setCedula(fieldCedula.getText());
        persona.setNombre(fieldNombre.getText());
        persona.setApellido(fieldApellido.getText());
        persona.setTelefono(fieldTelefono.getText());
        persona.setCorreo(fieldCorreo.getText());
        persona.setDireccion(fieldDireccion.getText());
        persona.setGenero(fieldGenero.getSelectedItem().toString());
        persona.setEdad(Integer.valueOf(fieldEdad.getText()));

        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            Medico medico = (Medico) persona;
            medico.setEspecialidad(fieldEspecialidad.getText());
        }

        return persona;
    }

    public Rol getRol() {
        return rol;
    }

    public void setCorreo(String correo) {
        fieldCorreo.setText(correo);
    }

    public void setNombre(String nombre) {
        fieldNombre.setText(nombre);
    }

    public void setApellido(String apellido) {
        fieldApellido.setText(apellido);
    }

    public void setCedula(String cedula) {
        fieldCedula.setText(cedula);
    }

    public void setTelefono(String telefono) {
        fieldTelefono.setText(telefono);
    }

    public void setDireccion(String direccion) {
        fieldDireccion.setText(direccion);
    }

    public void setGenero(String genero) {
        fieldGenero.setSelectedItem(genero);
    }

    public void setEdad(int edad) {
        fieldEdad.setText(String.valueOf(edad));
    }

    public void setEspecialidad(String especialidad) {
        fieldEspecialidad.setText(especialidad);
    }

    public void loadData() {
        Integer idPersona = SesionUsuario.persona.getId();
        try {

            Persona persona = servicios.verInformacionPersona(idPersona);
            System.out.println("Cedula: " + persona.getCedula());
            System.out.println("Nombre: " + persona.getNombre());
            System.out.println("Apellido: " + persona.getApellido());
            System.out.println("Correo: " + persona.getCorreo());
            System.out.println("Telefono: " + persona.getTelefono());
            System.out.println("Direccion: " + persona.getDireccion());
            System.out.println("Genero: " + persona.getGenero());
            System.out.println("Edad: " + persona.getEdad());
            System.out.println("Rol: " + persona.getRol().getId() + " " + persona.getRol().getNombre());
            if (Medico.class.isInstance(persona)) {
                System.out.println("Especialidad: " + ((Medico) persona).getEspecialidad());
            }
            System.out.println();

            if (persona.getCedula() != null) {
                setCedula(persona.getCedula());
            }
            if (persona.getNombre() != null) {
                setNombre(persona.getNombre());
            }
            if (persona.getApellido() != null) {
                setApellido(persona.getApellido());
            }
            if (persona.getCorreo() != null) {
                setCorreo(persona.getCorreo());
            }
            if (persona.getTelefono() != null) {
                setTelefono(persona.getTelefono());
            }
            if (persona.getDireccion() != null) {
                setDireccion(persona.getDireccion());
            }
            if (persona.getGenero() != null) {
                setGenero(persona.getGenero());
            }
            if (persona.getEdad() != null) {
                setEdad(persona.getEdad());
            }
            if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
                Medico medico = (Medico) persona;
                if (medico.getEspecialidad() != null) {
                    setEspecialidad(medico.getEspecialidad());
                }
            }
        } catch (ModeloException e) {
            System.out.println("Error cargando los datos");
        }
    }
}
