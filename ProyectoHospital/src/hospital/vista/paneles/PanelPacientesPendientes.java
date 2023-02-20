package hospital.vista.paneles;

import hospital.controlador.lista.ListaEnlazada;
import hospital.modelo.Persona;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.modelo.global.SesionUsuario;
import hospital.modelo.global.VariablesGlobales;
import hospital.vista.servicio.Servicios;
import hospital.vista.FrmMain;
import hospital.vista.listeners.RedirigirAFrmHistorialMedico;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.time.*;

/**
 * Sirve para crear la configuración de un panel de pacientes pendientes junto
 * con todos sus subcomponentes
 */
public class PanelPacientesPendientes extends JPanel {

    private FrmMain frmMain;

    private Servicios servicios;

    private JPanel panelMain;
    private JPanel panelTitulo;
    private JPanel panelTabla;

    private JLabel labelTitulo;

    private JButton[] buttonsHistorialesMedicos;
    private JButton button;

    private Persona[] pacientesPendientes;

    public PanelPacientesPendientes(FrmMain frmMain) {
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

        panelMain.add(panelTitulo);
        panelMain.add(panelTabla);
    }

    private void makePanelTitulo() {
        panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout());
        panelTitulo.setBackground(new Color(0, 0, 0, 0));

        makeLabelTitulo();

        panelTitulo.add(labelTitulo);
    }

    private void makePanelTabla() {
        buttonsHistorialesMedicos = new JButton[pacientesPendientes.length];
        for (int i = 0; i < buttonsHistorialesMedicos.length; i++) {
            buttonsHistorialesMedicos[i] = new JButton();
            buttonsHistorialesMedicos[i].setIcon(new ImageIcon("assets/img/loupe.png"));
            buttonsHistorialesMedicos[i].addActionListener(new RedirigirAFrmHistorialMedico(frmMain, VariablesGlobales.ROL_MEDICO, pacientesPendientes[i].getId()));
        }

        panelTabla = new JPanel();
        panelTabla.setLayout(new GridBagLayout());
        panelTabla.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK));

        setTableHeaderCell(0, "No.");
        setTableHeaderCell(1, "Nombre");
        setTableHeaderCell(2, "Cedula");
        setTableHeaderCell(3, "");

        JPanel panelPacientesPendientes = new JPanel();
        panelPacientesPendientes.setBackground(new Color(179, 179, 179));
        panelPacientesPendientes.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
        panelTabla.add(panelPacientesPendientes, makeContraintsForPanelForCell(0, 3));

        for (int i = 0; i < pacientesPendientes.length; i++) {
            setTableCell(i + 1, 0, String.valueOf(i + 1));
            setTableCell(i + 1, 1, pacientesPendientes[i].getNombre());
            setTableCell(i + 1, 2, pacientesPendientes[i].getCedula());

            JPanel panel = makePanelForCell("Buscar");
            panel = new JPanel();
            panel.add(buttonsHistorialesMedicos[i]);
            panel.setBackground(Color.WHITE);
            panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
            panelTabla.add(panel, makeContraintsForPanelForCell(i + 1, 3));
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

    private void makeLabelTitulo() {
        labelTitulo = new JLabel("PACIENTES PENDIENTES");
    }

    public void loadData() {
        Integer idPersona = SesionUsuario.persona.getId();
        try {
            ListaEnlazada<Persona> listPacientesPendientes = servicios.listarPacientesPendientes();
            pacientesPendientes = new Persona[listPacientesPendientes.size()];
            listPacientesPendientes.toArray(pacientesPendientes);
        } catch (ModeloException e) {
            System.out.println("Error cargando los datos");
            System.out.println(e);
        }
    }
}
