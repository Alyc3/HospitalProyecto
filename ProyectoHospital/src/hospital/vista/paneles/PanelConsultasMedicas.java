package hospital.vista.paneles;

import hospital.controlador.lista.ListaEnlazada;
import hospital.modelo.ConsultaMedica;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.modelo.global.SesionUsuario;
import hospital.modelo.global.VariablesGlobales;
import hospital.vista.servicio.Servicios;
import hospital.vista.FrmMain;
import hospital.vista.listeners.AbrirNuevoFrmConsulta;
import hospital.vista.listeners.RedirigirAFrmConsultaMedica;
import hospital.vista.utilities.CrearFormularios;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.*;

/**
 * Sirve para generar el panel de consultas medicas y su configuración
 * presentado a los pacientes
 */
public class PanelConsultasMedicas extends JPanel {

    private FrmMain frmMain;

    private Servicios servicios;

    private JPanel panelMain;
    private JPanel panelTitulo;
    private JPanel panelTabla;
    private JPanel panelBotones;

    private JLabel labelTitulo;

    private JButton[] buttonsConsultasMedicas;
    private JButton buttonNuevaConsulta;

    private ConsultaMedica[] consultasMedicas;

    public PanelConsultasMedicas(FrmMain frmMain) {
        this.frmMain = frmMain;
        this.servicios = ManagerComponentes.servicios;
        loadData();
        setLayout(new GridBagLayout());
        makePanelMain();
        add(panelMain);
    }

    private void makePanelMain() {
        panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));

        makePanelTitulo();
        makePanelTabla();
        makePanelBotones();

        panelMain.add(panelTitulo);
        panelMain.add(panelTabla);
        panelMain.add(panelBotones);
    }

    private void makePanelTitulo() {
        panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout());
        panelTitulo.setBackground(new Color(0, 0, 0, 0));

        makeLabelTitulo();

        panelTitulo.add(labelTitulo);
    }

    private void makePanelTabla() {
        buttonsConsultasMedicas = new JButton[consultasMedicas.length];
        for (int i = 0; i < buttonsConsultasMedicas.length; i++) {
            buttonsConsultasMedicas[i] = new JButton();
            buttonsConsultasMedicas[i].setIcon(new ImageIcon("assets/img/loupe.png"));
            buttonsConsultasMedicas[i].addActionListener(new RedirigirAFrmConsultaMedica(frmMain, VariablesGlobales.ROL_PACIENTE, null, consultasMedicas[i].getId()));
        }

        panelTabla = new JPanel();
        panelTabla.setLayout(new GridBagLayout());
        panelTabla.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK));

        setTableHeaderCell(0, "No.");
        setTableHeaderCell(1, "Id");
        setTableHeaderCell(2, "Fecha");
        setTableHeaderCell(3, "Hora");
        setTableHeaderCell(4, "Consultorio");
        setTableHeaderCell(5, "Estado");
        setTableHeaderCell(6, "");

        JPanel panelConsultar = new JPanel();
        panelConsultar.setBackground(new Color(179, 179, 179));
        panelConsultar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
        panelTabla.add(panelConsultar, makeContraintsForPanelForCell(0, 6));

        for (int i = 0; i < consultasMedicas.length; i++) {
            setTableCell(i + 1, 0, String.valueOf(i + 1));
            setTableCell(i + 1, 1, String.valueOf(consultasMedicas[i].getId()));
            setTableCell(i + 1, 2, consultasMedicas[i].getFecha().toString());
            setTableCell(i + 1, 3, consultasMedicas[i].getHora().toString());
            setTableCell(i + 1, 4, String.valueOf(consultasMedicas[i].getNumConsultorio()));

            if (consultasMedicas[i].getEstado().equals("PENDIENTE")) {
                setTableCell(i + 1, 5, consultasMedicas[i].getEstado(), new Color(255, 128, 0));
            } else if (consultasMedicas[i].getEstado().equals("CANCELADA")) {
                setTableCell(i + 1, 5, consultasMedicas[i].getEstado(), new Color(231, 76, 60));
            } else if (consultasMedicas[i].getEstado().equals("FINALIZADA")) {
                setTableCell(i + 1, 5, consultasMedicas[i].getEstado(), new Color(39, 174, 96));
            } else {
                setTableCell(i + 1, 5, consultasMedicas[i].getEstado());
            }

            JPanel panel = makePanelForCell("Buscar");
            panel = new JPanel();
            panel.add(buttonsConsultasMedicas[i]);
            panel.setBackground(Color.WHITE);
            panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
            panelTabla.add(panel, makeContraintsForPanelForCell(i + 1, 6));
        }
    }

    private void setTableHeaderCell(int col, String name) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(name);
        panel.add(label);
        panel.setPreferredSize(new Dimension(100, 20));
        panel.setBackground(new Color(179, 179, 179));
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
        panelTabla.add(panel, makeContraintsForPanelForCell(0, col));
    }

    private void setTableCell(int row, int col, String text, Color color) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = col;
        constraints.gridy = row;
        constraints.fill = GridBagConstraints.BOTH;

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
        JLabel label = new JLabel(text);
        label.setForeground(color);
        panel.add(label, BorderLayout.CENTER);

        panelTabla.add(panel, constraints);
    }

    private void setTableCell(int row, int col, String text) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = col;
        constraints.gridy = row;
        constraints.fill = GridBagConstraints.BOTH;

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
        JLabel label = new JLabel(text);
        panel.add(label, BorderLayout.CENTER);

        panelTabla.add(panel, constraints);
    }

    private GridBagConstraints makeContraintsForPanelForCell(int row, int col) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = col;
        constraints.gridy = row;
        constraints.fill = GridBagConstraints.BOTH;
        return constraints;
    }

    private JPanel makePanelForCell(String text) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
        JLabel label = new JLabel(text);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private void makePanelBotones() {
        panelBotones = new JPanel();
        panelBotones.setLayout(new BorderLayout());

        makeButtonNuevaConsulta();

        panelBotones.add(buttonNuevaConsulta, BorderLayout.EAST);
    }

    private void makeButtonNuevaConsulta() {
        buttonNuevaConsulta = CrearFormularios.crearButton("Nueva consulta", 160, 40, new AbrirNuevoFrmConsulta(frmMain));
    }

    private void makeLabelTitulo() {
        labelTitulo = new JLabel("Últimas consultas médicas");
    }

    public void loadData() {
        Integer idPersona = SesionUsuario.persona.getId();
        try {
            ListaEnlazada<ConsultaMedica> listConsultasMedicas = servicios.listarConsultasMedicasPorPaciente(idPersona);
            consultasMedicas = new ConsultaMedica[listConsultasMedicas.size()];
            listConsultasMedicas.toArray(consultasMedicas);
            System.out.println(consultasMedicas.length);
        } catch (ModeloException e) {
            System.out.println("Error cargando los datos");
        }
    }
}
