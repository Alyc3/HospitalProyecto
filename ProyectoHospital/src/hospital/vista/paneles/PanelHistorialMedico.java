package hospital.vista.paneles;

import hospital.modelo.Rol;
import hospital.vista.FrmMain;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelHistorialMedico extends JPanel {

    private FrmMain frmMain;

    private Rol rol;

    private JPanel panelMain;
    private JPanel panelTitulo;
    private JPanel panelNombreApellido;
    private JPanel panelDatos;
    private JPanel panelDatosLeft;
    private JPanel panelDatosRight;
    private JPanel panelBotones;

    private JLabel labelTitulo;
    private JLabel labelNombreApellido;
    private JLabel labelPeso;
    private JLabel labelEstatura;
    private JLabel labelTipoSanguineo;
    private JLabel labelEdad;
    private JLabel labelDia;
    private JLabel labelMes;
    private JLabel labelAño;
    private JLabel labelAntecedentes;
    private JLabel labelAntecedentesFamiliares;
    private JLabel labelAlergias;
    private JLabel labelTratamientosVigentes;

    private JTextField fieldPeso;
    private JTextField fieldEstatura;
    private JTextField fieldTipoSanguineo;
    private JTextField fieldEdad;
    private JComboBox fieldDia;
    private JComboBox fieldMes;
    private JComboBox fieldAño;
    private JTextArea fieldAntecedentes;
    private JTextArea fieldAntecedentesFamiliares;
    private JTextArea fieldAlergias;
    private JTextArea fieldTratamientosVigentes;

    private JButton buttonGuardar;

    public PanelHistorialMedico(FrmMain frmMain, Rol rol) {
        this.frmMain = frmMain;
        this.rol = rol;
        setLayout(new BorderLayout());
        setBackground(new Color(38, 53, 72));
        makePanelMain();
        add(panelMain);
    }

    private void makePanelMain() {
        panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.setBackground(new Color(0, 0, 0, 0));
        makePanelTitulo();
        makePanelNombreApellido();
        makePanelDatos();
        makePanelBotones();
        panelMain.add(panelTitulo);
        panelMain.add(panelNombreApellido);
        panelMain.add(panelDatos);
        panelMain.add(panelBotones);
    }

    private void makePanelTitulo() {
        panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout());
        makeLabelTitulo();
        panelTitulo.add(labelTitulo);
    }

    private void makePanelNombreApellido() {
        panelNombreApellido = new JPanel();
        panelNombreApellido.setLayout(new BorderLayout());
        makeLabelNombreApellido();
        panelNombreApellido.add(labelNombreApellido, BorderLayout.WEST);
    }

    private void makePanelDatos() {
        panelDatos = new JPanel();
        panelDatos.setLayout(new BorderLayout());
        makePanelDatosLeft();
        makePanelDatosRight();
        panelDatos.add(panelDatosLeft, BorderLayout.WEST);
        panelDatos.add(panelDatosRight, BorderLayout.EAST);
    }

    private void makePanelBotones() {
        panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        makeButtonGuardar();
        panelBotones.add(buttonGuardar);
    }

    private void makePanelDatosLeft() {
        panelDatosLeft = new JPanel();
        panelDatosLeft.setLayout(new BoxLayout(panelDatosLeft, BoxLayout.Y_AXIS));

        JPanel panelAntecedentes = new JPanel();
        panelAntecedentes.setLayout(new BorderLayout());
        labelAntecedentes = makeLabelForFormulario("Antecedentes");
        fieldAntecedentes = makeTextAreaForFormulario(5, 20);
        JScrollPane scrollFieldAntecedentes = new JScrollPane(fieldAntecedentes);
        panelAntecedentes.add(labelAntecedentes, BorderLayout.NORTH);
        panelAntecedentes.add(scrollFieldAntecedentes, BorderLayout.SOUTH);
        panelAntecedentes.setBackground(new Color(0, 0, 0, 0));
        panelAntecedentes.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panelAntecedentesFamiliares = new JPanel();
        panelAntecedentesFamiliares.setLayout(new BorderLayout());
        labelAntecedentesFamiliares = makeLabelForFormulario("Antecedentes familiares");
        fieldAntecedentesFamiliares = makeTextAreaForFormulario(5, 20);
        JScrollPane scrollFieldAntecedentesFamiliares = new JScrollPane(fieldAntecedentesFamiliares);
        panelAntecedentesFamiliares.add(labelAntecedentesFamiliares, BorderLayout.NORTH);
        panelAntecedentesFamiliares.add(scrollFieldAntecedentesFamiliares, BorderLayout.SOUTH);
        panelAntecedentesFamiliares.setBackground(new Color(0, 0, 0, 0));
        panelAntecedentesFamiliares.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        String[] empty = {};
        JPanel panelFecha = new JPanel();
        panelFecha.setLayout(new BoxLayout(panelFecha, BoxLayout.X_AXIS));

        JPanel panelDia = new JPanel();
        panelDia.setLayout(new BorderLayout());
        labelDia = makeLabelForFormulario("Dia");
        fieldDia = makeComboBoxForFormulario(50, 25, empty);
        panelDia.add(labelDia, BorderLayout.NORTH);
        panelDia.add(fieldDia, BorderLayout.SOUTH);
        panelDia.setBackground(new Color(0, 0, 0, 0));
        panelDia.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        JPanel panelMes = new JPanel();
        panelMes.setLayout(new BorderLayout());
        labelMes = makeLabelForFormulario("Mes");
        fieldMes = makeComboBoxForFormulario(150, 25, empty);
        panelMes.add(labelMes, BorderLayout.NORTH);
        panelMes.add(fieldMes, BorderLayout.SOUTH);
        panelMes.setBackground(new Color(0, 0, 0, 0));
        panelMes.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        JPanel panelAño = new JPanel();
        panelAño.setLayout(new BorderLayout());
        labelAño = makeLabelForFormulario("Año");
        fieldAño = makeComboBoxForFormulario(100, 25, empty);
        panelAño.add(labelAño, BorderLayout.NORTH);
        panelAño.add(fieldAño, BorderLayout.SOUTH);
        panelAño.setBackground(new Color(0, 0, 0, 0));
        panelAño.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        panelFecha.add(panelDia);
        panelFecha.add(panelMes);
        panelFecha.add(panelAño);
        panelFecha.setBackground(new Color(0, 0, 0, 0));
        panelFecha.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        panelDatosLeft.add(panelFecha);
        panelDatosLeft.add(panelAntecedentes);
        panelDatosLeft.add(panelAntecedentesFamiliares);
    }

    private void makePanelDatosRight() {
        panelDatosRight = new JPanel();
        panelDatosRight.setLayout(new BoxLayout(panelDatosRight, BoxLayout.Y_AXIS));

        JPanel panelAlergias = new JPanel();
        panelAlergias.setLayout(new BorderLayout());
        labelAlergias = makeLabelForFormulario("Alergias");
        fieldAlergias = makeTextAreaForFormulario(5, 20);
        JScrollPane scrollFieldAlergias = new JScrollPane(fieldAlergias);
        panelAlergias.add(labelAlergias, BorderLayout.NORTH);
        panelAlergias.add(scrollFieldAlergias, BorderLayout.SOUTH);
        panelAlergias.setBackground(new Color(0, 0, 0, 0));
        panelAlergias.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panelTratamientosVigentes = new JPanel();
        panelTratamientosVigentes.setLayout(new BorderLayout());
        labelTratamientosVigentes = makeLabelForFormulario("TratamientosVigentes");
        fieldTratamientosVigentes = makeTextAreaForFormulario(5, 20);
        JScrollPane scrollFieldTratamientosVigentes = new JScrollPane(fieldTratamientosVigentes);
        panelTratamientosVigentes.add(labelTratamientosVigentes, BorderLayout.NORTH);
        panelTratamientosVigentes.add(scrollFieldTratamientosVigentes, BorderLayout.SOUTH);
        panelTratamientosVigentes.setBackground(new Color(0, 0, 0, 0));
        panelTratamientosVigentes.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panelDatosRight.add(panelAlergias);
        panelDatosRight.add(panelTratamientosVigentes);
    }

    private void makeLabelTitulo() {
        labelTitulo = new JLabel("Historial médico");
    }

    private void makeLabelNombreApellido() {
        labelNombreApellido = new JLabel("Nombre y apellido");
    }

    private void makeButtonGuardar() {
        buttonGuardar = new JButton("Guardar");
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

    private JTextArea makeTextAreaForFormulario(int r, int c) {
        JTextArea textArea = new JTextArea(r, c);
        //textArea.setPreferredSize(new Dimension(width,height));
        //textArea.setMargin(new Insets(0, 10, 0, 10));
        return textArea;
    }

    private JComboBox<String> makeComboBoxForFormulario(int width, int height, String[] data) {
        JComboBox<String> comboBox = new JComboBox<String>(data);
        comboBox.setPreferredSize(new Dimension(width, height));
        return comboBox;
    }
}
