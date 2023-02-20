package hospital.vista.paneles;

import hospital.modelo.ConsultaMedica;
import hospital.modelo.Persona;
import hospital.modelo.Rol;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.modelo.global.SesionUsuario;
import hospital.modelo.global.VariablesGlobales;
import hospital.vista.servicio.Servicios;
import hospital.vista.FrmMain;
import hospital.vista.listeners.RedirigirAFrmConsultaMedica;
import hospital.vista.utilities.CrearFormularios;
import hospital.vista.utilities.VistaTabla;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.*;

/**
 * Sirve para generar la configuración del panel de consulta medica presentado a
 * los medicos
 */
public class PanelConsultaMedica extends JPanel {

    private FrmMain frmMain;

    private Rol rol;
    private Integer idPaciente;
    private Integer idConsultaMedica;

    private Servicios servicios;

    private JPanel panelMain;
    private JPanel panelTitulo;
    private JPanel panelNombreApellido;
    private JPanel panelTabla;

    private VistaTabla tabla;

    private JLabel labelTitulo;
    private JLabel labelNombreApellido;
    private JLabel labelId, labelFecha, labelHora, labelConsultorio, labelEstado;
    private JLabel labelValueId, labelValueFecha, labelValueHora, labelValueConsultorio, labelValueEstado;

    private JButton buttonBuscarConsultaMedica;

    private Persona persona;
    private ConsultaMedica consultaMedica;

    public PanelConsultaMedica(FrmMain frmMain, Rol rol, Integer idPaciente, Integer idConsultaMedica) {
        this.frmMain = frmMain;
        this.rol = rol;
        this.idPaciente = idPaciente;
        this.idConsultaMedica = idConsultaMedica;
        this.servicios = ManagerComponentes.servicios;
        setLayout(new GridBagLayout());
        makePanelMain();
        add(panelMain);
        loadData();
    }

    private void makePanelMain() {
        panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));

        makePanelTitulo();
        makePanelNombreApellido();
        makePanelTabla();

        panelMain.add(panelTitulo);
        panelMain.add(panelNombreApellido);
        panelMain.add(panelTabla);
    }

    private void makePanelTitulo() {
        panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout());
        panelTitulo.setBackground(new Color(0, 0, 0, 0));

        makeLabelTitulo();

        panelTitulo.add(labelTitulo);
    }

    private void makePanelNombreApellido() {
        panelNombreApellido = new JPanel();
        panelNombreApellido.setLayout(new BorderLayout());
        panelNombreApellido.setBackground(new Color(0, 0, 0, 0));
        makeLabelNombreApellido();
        panelNombreApellido.add(labelNombreApellido, BorderLayout.WEST);
    }

    private void makePanelTabla() {
        panelTabla = new JPanel();
        panelTabla.setLayout(new GridBagLayout());
        panelTabla.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK));

        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            tabla = new VistaTabla(2, 6);
        } else {
            tabla = new VistaTabla(2, 5);
        }

        labelId = new JLabel("ID");
        labelFecha = new JLabel("FECHA");
        labelHora = new JLabel("HORA");
        labelConsultorio = new JLabel("CONSULTORIO");
        labelEstado = new JLabel("ESTADO");
        labelValueId = new JLabel(" ");
        labelValueFecha = new JLabel(" ");
        labelValueHora = new JLabel(" ");
        labelValueConsultorio = new JLabel(" ");
        labelValueEstado = new JLabel(" ");
        buttonBuscarConsultaMedica = new JButton();

        labelId.setForeground(Color.WHITE);
        labelFecha.setForeground(Color.WHITE);
        labelHora.setForeground(Color.WHITE);
        labelConsultorio.setForeground(Color.WHITE);
        labelEstado.setForeground(Color.WHITE);

        tabla.setCellComponent(0, 0, labelId);
        tabla.setCellComponent(0, 1, labelFecha);
        tabla.setCellComponent(0, 2, labelHora);
        tabla.setCellComponent(0, 3, labelConsultorio);
        tabla.setCellComponent(0, 4, labelEstado);
        tabla.setCellComponent(1, 0, labelValueId);
        tabla.setCellComponent(1, 1, labelValueFecha);
        tabla.setCellComponent(1, 2, labelValueHora);
        tabla.setCellComponent(1, 3, labelValueConsultorio);
        tabla.setCellComponent(1, 4, labelValueEstado);
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            tabla.setCellComponent(1, 5, buttonBuscarConsultaMedica);
        }

        panelTabla.add(tabla);
    }

    private void makeLabelTitulo() {
        labelTitulo = CrearFormularios.crearSubtitulolabel("CONSULTA MÉDICA");
    }

    private void makeLabelNombreApellido() {
        labelNombreApellido = CrearFormularios.crearSubtitulolabel("Nombre y Apellido");
    }

    public void loadData() {

        Integer idPersona;
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            idPersona = idPaciente;
        } else {
            idPersona = SesionUsuario.persona.getId();
        }

        try {
            persona = servicios.verInformacionPersona(idPersona);
            labelNombreApellido.setText(persona.getNombre() + " " + persona.getApellido());

            if (idConsultaMedica != null) {
                consultaMedica = servicios.verConsultaMedica(idConsultaMedica);

                if (consultaMedica.getId() != null) {
                    labelValueId.setText(String.valueOf(consultaMedica.getId()));
                }
                if (consultaMedica.getFecha() != null) {
                    labelValueFecha.setText(consultaMedica.getFecha().toString());
                }
                if (consultaMedica.getHora() != null) {
                    labelValueHora.setText(consultaMedica.getHora().toString());
                }
                if (consultaMedica.getNumConsultorio() != null) {
                    labelValueConsultorio.setText(String.valueOf(consultaMedica.getNumConsultorio()));
                }
                if (consultaMedica.getEstado() != null) {
                    labelValueEstado.setText(consultaMedica.getEstado());
                    labelValueEstado.setForeground(new Color(255, 128, 0));
                }

                if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
                    buttonBuscarConsultaMedica.setIcon(new ImageIcon("assets/img/loupe.png"));
                    buttonBuscarConsultaMedica.setContentAreaFilled(false);
                    buttonBuscarConsultaMedica.setBorderPainted(false);
                    buttonBuscarConsultaMedica.setFocusPainted(false);
                    buttonBuscarConsultaMedica.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    buttonBuscarConsultaMedica.addActionListener(new RedirigirAFrmConsultaMedica(frmMain, rol, idPaciente, idConsultaMedica));
                }
            }
        } catch (ModeloException e) {
            System.out.println("Error cargando los datos");
        }
    }
}
