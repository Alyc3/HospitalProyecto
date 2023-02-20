package hospital.vista.paneles;

import hospital.controlador.lista.ListaEnlazada;
import hospital.modelo.Examen;
import hospital.modelo.Rol;
import hospital.modelo.enumeradores.EncontrarTipoExamen;
import hospital.modelo.enumeradores.TipoExamen;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.modelo.global.VariablesGlobales;
import hospital.vista.servicio.Servicios;
import hospital.vista.FrmMain;
import hospital.vista.listeners.AgregarExamen;
import hospital.vista.utilities.CrearFormularios;
import hospital.vista.utilities.VistaTabla;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.*;

/**
 * Sirve para genera el panel de examenes y su configuración
 */
public class PanelExamenes extends JPanel {

    private FrmMain frmMain;

    private Rol rol;
    private Integer idConsultaMedica;
    private Integer idPaciente;

    private Servicios servicios;

    private JPanel panelMain;
    private JPanel panelSubtitulo;
    private JPanel panelBotones;
    private JPanel panelTabla;
    private JPanel panelDatos;

    private JLabel labelSubtitulo;
    private JLabel labelTipoExamen;
    private JLabel labelTiempoEntrega;
    private JLabel labelErrorTipoExamen;
    private JLabel labelErrorTiempoEntrega;

    private JComboBox fieldTipoExamen;
    private JTextField fieldTiempoEntrega;

    private VistaTabla tabla;

    private JButton buttonAgregar;

    private Examen[] examenes;

    private String[] optionsTipoExamen = {TipoExamen.Electrolitos.toString(), TipoExamen.Heces.toString(), TipoExamen.Orina.toString(), TipoExamen.Hematologia.toString()};

    public PanelExamenes(FrmMain frmMain, Rol rol, Integer idPaciente, Integer idConsultaMedica) {
        this.frmMain = frmMain;
        this.rol = rol;
        this.idPaciente = idPaciente;
        this.idConsultaMedica = idConsultaMedica;
        this.servicios = ManagerComponentes.servicios;
        loadData();
        setLayout(new GridBagLayout());
        makePanelMain();
        add(panelMain);
    }

    private void makePanelMain() {
        panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));

        makePanelSubtitulo();
        makePanelTabla();
        makePanelDatos();
        makePanelBotones();

        panelMain.add(panelSubtitulo);
        panelMain.add(panelTabla);
        panelMain.add(panelDatos);
        panelMain.add(panelBotones);
    }

    private void makePanelSubtitulo() {
        panelSubtitulo = new JPanel();
        panelSubtitulo.setLayout(new FlowLayout());
        panelSubtitulo.setBackground(new Color(0, 0, 0, 0));

        makeLabelSubtitulo();

        panelSubtitulo.add(labelSubtitulo);
    }

    private void makePanelTabla() {
        panelTabla = new JPanel();
        panelTabla.setLayout(new GridBagLayout());
        panelTabla.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK));

        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            tabla = new VistaTabla(examenes.length + 1, 3);
        } else {
            tabla = new VistaTabla(examenes.length + 1, 3);
        }

        JLabel labelNo = new JLabel("No.");
        JLabel labelTipoExamen = new JLabel("EXAMEN");
        JLabel labelTiempoEntrega = new JLabel("TIEMPO DE ENTREGA");

        labelNo.setForeground(Color.WHITE);
        labelTipoExamen.setForeground(Color.WHITE);
        labelTiempoEntrega.setForeground(Color.WHITE);

        tabla.setCellComponent(0, 0, labelNo);
        tabla.setCellComponent(0, 1, labelTipoExamen);
        tabla.setCellComponent(0, 2, labelTiempoEntrega);

        for (int i = 0; i < examenes.length; i++) {
            JLabel labelValueNo = new JLabel(String.valueOf(i + 1));
            JLabel labelValueTipoExamen = new JLabel(" ");
            JLabel labelValueTiempoEntrega = new JLabel(" ");

            if (examenes[i].getTipoExamen() != null) {
                labelValueTipoExamen.setText(examenes[i].getTipoExamen().toString());
            }
            if (examenes[i].getTiempoEntrega() != null) {
                labelValueTiempoEntrega.setText(examenes[i].getTiempoEntrega());
            }

            tabla.setCellComponent(i + 1, 0, labelValueNo);
            tabla.setCellComponent(i + 1, 1, labelValueTipoExamen);
            tabla.setCellComponent(i + 1, 2, labelValueTiempoEntrega);
        }

        panelTabla.add(tabla);
    }

    private void makePanelDatos() {
        panelDatos = new JPanel();
        panelDatos.setLayout(new FlowLayout());
        panelDatos.setBackground(Color.WHITE);
        labelTipoExamen = CrearFormularios.crearEtiquetaDesdeCampo("Tipo de examen");
        fieldTipoExamen = CrearFormularios.crearComboBox(150, 30, optionsTipoExamen, rol.equals(VariablesGlobales.ROL_MEDICO) ? true : false);
        labelErrorTipoExamen = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");
        labelTiempoEntrega = CrearFormularios.crearEtiquetaDesdeCampo("Tiempo de entrega");
        fieldTiempoEntrega = CrearFormularios.crearTextField(15, rol.equals(VariablesGlobales.ROL_MEDICO) ? true : false);
        labelErrorTiempoEntrega = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");
        JPanel panelTipoExamenTiempoEntrega = CrearFormularios.crearPanelMultiCampo();
        panelTipoExamenTiempoEntrega.add(CrearFormularios.crearPanelCampo(labelTipoExamen, fieldTipoExamen, labelErrorTipoExamen));
        panelTipoExamenTiempoEntrega.add(CrearFormularios.crearPanelCampo(labelTiempoEntrega, fieldTiempoEntrega, labelErrorTiempoEntrega));
        panelDatos.add(panelTipoExamenTiempoEntrega);
    }

    private void makePanelBotones() {
        panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setBackground(new Color(0, 0, 0, 0));
        buttonAgregar = CrearFormularios.crearButton("+", 50, 50, new AgregarExamen(frmMain, this, rol, idPaciente));
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            panelBotones.add(buttonAgregar);
        }
    }

    private void makeLabelSubtitulo() {
        labelSubtitulo = CrearFormularios.crearTituloLabel("EXAMENES");
    }

    public Integer getIdConsultaMedica() {
        return idConsultaMedica;
    }

    public Examen getExamen() {
        Examen examen = new Examen();

        examen.setTipoExamen(EncontrarTipoExamen.encontrar(fieldTipoExamen.getSelectedItem().toString()));
        examen.setTiempoEntrega(fieldTiempoEntrega.getText());

        return examen;
    }

    public void loadData() {
        examenes = new Examen[0];

        try {
            ListaEnlazada<Examen> listExamenes = servicios.verExamenes(idConsultaMedica);
            examenes = new Examen[listExamenes.size()];
            listExamenes.toArray(examenes);
        } catch (ModeloException e) {
            System.out.println("Error cargando los datos");
        }
    }
}
