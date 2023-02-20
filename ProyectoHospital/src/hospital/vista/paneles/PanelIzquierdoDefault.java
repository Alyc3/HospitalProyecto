package hospital.vista.paneles;

import hospital.vista.FrmMain;
import hospital.vista.listeners.RedirigirAFrmHome;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Sirve para crear la configuración del panel lateral izquierdo generado por
 * defecto
 */
public class PanelIzquierdoDefault extends JPanel {

    private FrmMain frmMain;

    private JPanel panelMain;
    private JPanel panelHeader;
    private JPanel panelMenu;

    private JLabel labelBrand;

    private JButton buttonHome;

    public PanelIzquierdoDefault(FrmMain frmMain) {
        this.frmMain = frmMain;
        setLayout(new FlowLayout());
        setBackground(new Color(38, 53, 72));
        makePanelMain();
        add(panelMain);
    }

    private void makePanelMain() {
        panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.setBackground(new Color(0, 0, 0, 0));
        makePanelHeader();
        makePanelMenu();
        panelMain.add(panelHeader);
        panelMain.add(panelMenu);
    }

    private void makePanelHeader() {
        panelHeader = new JPanel();
        panelHeader.setLayout(new FlowLayout());
        panelHeader.setBackground(new Color(0, 0, 0, 0));
        panelHeader.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        makeLabelBrand();
        panelHeader.add(labelBrand);
    }

    private void makePanelMenu() {
        panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(1, 1));
        buttonHome = makeButtonForMenu("Home", new RedirigirAFrmHome(frmMain));
        panelMenu.add(buttonHome);
    }

    private void makeLabelBrand() {
        labelBrand = new JLabel("HOSPITAL");
        labelBrand.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        labelBrand.setForeground(Color.WHITE);
        labelBrand.setIcon(new ImageIcon("assets/img/hospital.png"));
    }

    private JButton makeButtonForMenu(String name, ActionListener listener) {
        JButton button = new JButton(name);
        button.setBackground(new Color(38, 53, 72));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.addActionListener(listener);
        return button;
    }
}
