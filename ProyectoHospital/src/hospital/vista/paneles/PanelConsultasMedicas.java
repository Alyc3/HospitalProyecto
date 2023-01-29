package hospital.vista.paneles;

import hospital.modelo.ConsultaMedica;
import hospital.vista.FrmMain;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.*;

public class PanelConsultasMedicas extends JPanel {

    private FrmMain mainWindow;

    private JPanel panelMain;
    private JPanel panelTitulo;
    private JPanel panelTabla;
    private JPanel panelBotones;

    private JLabel labelTitulo;

    private JButton buttonNuevaConsulta;

    public PanelConsultasMedicas(FrmMain mainWindow) {
        this.mainWindow = mainWindow;
        setLayout(new BorderLayout());
        setBackground(new Color(38, 53, 72));
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

        makeLabelTitulo();

        panelTitulo.add(labelTitulo);
    }

    private void makePanelTabla() {
        ConsultaMedica cm1 = new ConsultaMedica();
        cm1.setId(1);
        cm1.setFecha(LocalDate.parse("2022-01-01"));
        cm1.setHora(LocalTime.parse("09:15"));
        cm1.setNumConsultorio(314);
        cm1.setEstado("FINALIZADA");

        ConsultaMedica[] consultasMedicas = {cm1, cm1, cm1, cm1, cm1};

        panelTabla = new JPanel();
        panelTabla.setLayout(new GridBagLayout());

        JPanel panelNumero = new JPanel();
        JLabel labelNumero = new JLabel("No.");
        panelNumero.add(labelNumero);
        panelTabla.add(panelNumero, makeContraintsForPanelForCell(0, 0));

        JPanel panelId = new JPanel();
        JLabel labelId = new JLabel("Id");
        panelId.add(labelId);
        panelTabla.add(panelId, makeContraintsForPanelForCell(0, 1));

        JPanel panelFecha = new JPanel();
        JLabel labelFecha = new JLabel("Fecha");
        panelFecha.add(labelFecha);
        panelTabla.add(panelFecha, makeContraintsForPanelForCell(0, 2));

        JPanel panelHora = new JPanel();
        JLabel labelHora = new JLabel("Hora");
        panelHora.add(labelHora);
        panelTabla.add(panelHora, makeContraintsForPanelForCell(0, 3));

        JPanel panelConsultorio = new JPanel();
        JLabel labelConsultorio = new JLabel("Consultorio");
        panelConsultorio.add(labelConsultorio);
        panelTabla.add(panelConsultorio, makeContraintsForPanelForCell(0, 4));

        JPanel panelEstado = new JPanel();
        JLabel labelEstado = new JLabel("Estado");
        panelEstado.add(labelEstado);
        panelTabla.add(panelEstado, makeContraintsForPanelForCell(0, 5));

        for (int i = 0; i < consultasMedicas.length; i++) {
            JPanel panel = makePanelForCell(String.valueOf(i + 1));
            panelTabla.add(panel, makeContraintsForPanelForCell(i + 1, 0));

            panel = makePanelForCell(String.valueOf(consultasMedicas[i].getId()));
            panelTabla.add(panel, makeContraintsForPanelForCell(i + 1, 1));

            panel = makePanelForCell(consultasMedicas[i].getFecha().toString());
            panelTabla.add(panel, makeContraintsForPanelForCell(i + 1, 2));

            panel = makePanelForCell(consultasMedicas[i].getHora().toString());
            panelTabla.add(panel, makeContraintsForPanelForCell(i + 1, 3));

            panel = makePanelForCell(String.valueOf(consultasMedicas[i].getNumConsultorio()));
            panelTabla.add(panel, makeContraintsForPanelForCell(i + 1, 4));

            panel = makePanelForCell(consultasMedicas[i].getEstado());
            panelTabla.add(panel, makeContraintsForPanelForCell(i + 1, 5));

            panel = makePanelForCell("Buscar");
            panelTabla.add(panel, makeContraintsForPanelForCell(i + 1, 6));
        }
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
        panel.setBackground(Color.GRAY);
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
        buttonNuevaConsulta = new JButton("Nueva consulta");
    }

    private void makeLabelTitulo() {
        labelTitulo = new JLabel("Últimas consultas médicas");
    }
}
