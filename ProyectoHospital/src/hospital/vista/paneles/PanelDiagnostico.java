package hospital.vista.paneles;

import hospital.modelo.Diagnostico;
import hospital.modelo.Rol;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.modelo.global.VariablesGlobales;
import hospital.vista.servicio.Servicios;
import hospital.vista.FrmMain;
import hospital.vista.listeners.EditarDiagnostico;
import hospital.vista.utilities.CrearFormularios;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.*;

/**
 * Sirve para crear el panel de Diagnostico y su configruación
 */
public class PanelDiagnostico extends JPanel {

    private FrmMain frmMain;

    private Rol rol;
    private Integer idConsultaMedica;

    private Servicios servicios;

    private JPanel panelMain;
    private JPanel panelDatos;
    private JPanel panelBotones;

    private JLabel labelRecomendacion;
    private JLabel labelErrorRecomendacion;

    private JTextArea fieldRecomendacion;

    private JButton buttonGuardar;

    private Diagnostico diagnostico;

    public PanelDiagnostico(FrmMain frmMain, Rol rol, Integer idConsultaMedica) {
        this.frmMain = frmMain;
        this.rol = rol;
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

        makePanelDatos();
        makePanelBotones();

        panelMain.add(panelDatos);
        panelMain.add(panelBotones);
    }

    private void makePanelDatos() {
        panelDatos = new JPanel();
        panelDatos.setLayout(new FlowLayout());
        panelDatos.setBackground(Color.WHITE);

        labelRecomendacion = CrearFormularios.crearEtiquetaDesdeCampo("Recomendación");
        fieldRecomendacion = CrearFormularios.crearTextArea(5, 30, false, rol.equals(VariablesGlobales.ROL_MEDICO) ? true : false);
        labelErrorRecomendacion = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");
        JScrollPane scrollFieldRecomendacion = CrearFormularios.crearScrollPane(fieldRecomendacion);

        JPanel panelRecomendacion = CrearFormularios.crearPanelCampo(labelRecomendacion, scrollFieldRecomendacion, labelErrorRecomendacion);

        panelDatos.add(panelRecomendacion);
    }

    private void makePanelBotones() {
        panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setBackground(new Color(0, 0, 0, 0));
        buttonGuardar = CrearFormularios.crearButton("Guardar", 160, 40, new EditarDiagnostico(frmMain, this));
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            panelBotones.add(buttonGuardar);
        }
    }

    public Integer getIdConsultaMedica() {
        return idConsultaMedica;
    }

    public Diagnostico getDiagnostico() {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setRecomendacion(fieldRecomendacion.getText());
        return diagnostico;
    }

    public void loadData() {
        try {
            diagnostico = servicios.verDiagnostico(idConsultaMedica);
            if (diagnostico != null && diagnostico.getRecomendacion() != null) {
                fieldRecomendacion.setText(diagnostico.getRecomendacion());
            }
        } catch (ModeloException e) {
            System.out.println("Error cargando los datos");
        }
    }
}
