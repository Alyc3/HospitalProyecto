package hospital.vista;

import hospital.modelo.global.VariablesGlobales;
import hospital.vista.listeners.RedirigirAFrmLogin;
import hospital.vista.paneles.PanelIzquierdoDefault;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FrmHome {

    private FrmMain frmMain;

    private JPanel panelMain;
    private JPanel panelIzquierdo;
    private JPanel panelCuerpo;
    private JPanel panelSeleccionRol;

    private JButton buttonMedicos;
    private JButton buttonPacientes;

    public FrmHome(FrmMain frmMain) {
        this.frmMain = frmMain;
    }

    /**
     * Inicializa los componentes de la vista y muestra los mismos en la ventana
     */
    public void mostrar() {
        frmMain.removeAll();
        Container cp = frmMain.getContentPane();
        cp.setLayout(new BorderLayout());
        makePanelMain();
        cp.add(panelMain, BorderLayout.CENTER);
        frmMain.repaint();
    }

    /**
     * Método para crear y establecer la configuración del panel y añadir cada uno de los componentes
     */
    private void makePanelMain() {
        panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());
        makePanelLeft();
        makePanelBody();
        panelMain.add(panelIzquierdo, BorderLayout.WEST);
        panelMain.add(panelCuerpo, BorderLayout.CENTER);
    }

    /**
     * Crea el panel lateral izquierdo generado por defecto
     */
    private void makePanelLeft() {
        panelIzquierdo = new PanelIzquierdoDefault(frmMain);
    }

    /**
     * Crea el panel del cuerpo de la vista con todos los componentes
     */
    private void makePanelBody() {
        panelCuerpo = new JPanel();
        panelCuerpo.setLayout(new GridBagLayout());
        panelCuerpo.setBackground(new Color(214, 234, 248));
        makePanelSeleccionRol();
        panelCuerpo.add(panelSeleccionRol);
    }

    /**
     * Crea el panel contenedor de sselección de roles (Paciente o Médico)
     */
    private void makePanelSeleccionRol() {
        panelSeleccionRol = new JPanel();
        panelSeleccionRol.setLayout(new FlowLayout());
        panelSeleccionRol.setBackground(new Color(0,0,0,0));
        JPanel panelButtonPacientes = createPanelForButtonFromPanelSeleccionRol();
        buttonPacientes = makeButtonForSeleccionarRol("Pacientes", new RedirigirAFrmLogin(frmMain, VariablesGlobales.ROL_PACIENTE));
        panelButtonPacientes.add(buttonPacientes);
        JPanel panelButtonMedicos = createPanelForButtonFromPanelSeleccionRol();
        buttonMedicos = makeButtonForSeleccionarRol("Médicos", new RedirigirAFrmLogin(frmMain, VariablesGlobales.ROL_MEDICO));
        panelButtonMedicos.add(buttonMedicos);
        panelSeleccionRol.add(panelButtonPacientes);
        panelSeleccionRol.add(panelButtonMedicos);
    }

    /**
     * Crea la configuración para los botones presentados
     * @param name
     * @param listener
     * @return 
     */
    public JButton makeButtonForSeleccionarRol(String name, ActionListener listener) {
        JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(200, 80));
        button.setBackground(new Color(0, 76, 153));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        button.addActionListener(listener);
        return button;
    }

    /**
     * Crear un nuevo botón con las preferencias definidas
     * @return 
     */
    public JPanel createPanelForButtonFromPanelSeleccionRol() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0,0,0,0));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return panel;
    }
}
