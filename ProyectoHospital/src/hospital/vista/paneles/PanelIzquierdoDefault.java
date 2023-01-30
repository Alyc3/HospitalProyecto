package hospital.vista.paneles;

import hospital.vista.FrmMain;
import hospital.vista.listeners.RedirigirAFrmHome;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelIzquierdoDefault extends JPanel {

    private FrmMain fraMain;

    private JPanel panelMain;
    private JPanel panelHeader;
    private JPanel panelMenu;

    private JLabel labelBrand;

    private JButton buttonHome;

    public PanelIzquierdoDefault(FrmMain frmMain) {
        this.fraMain = frmMain;
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
        panelHeader.setBackground(new Color(0, 0, 0, 0));
        panelHeader.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        makeLabelBrand();
        panelHeader.add(labelBrand);
    }

    private void makePanelMenu() {
        panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(1, 1));
        buttonHome = makeButtonForFormulario("Home", new RedirigirAFrmHome(fraMain));
        buttonHome.setIcon(new ImageIcon("assets/img/home.png"));
        panelMenu.add(buttonHome);
    }

    private JButton makeButtonForFormulario(String name, ActionListener listener) {
        JButton button = new JButton();
        button = new JButton(name);
        button.setBackground(new Color(38, 53, 72));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Helvetica", Font.BOLD, 20));
        button.addActionListener(listener);
        return button;
    }

    private void makeLabelBrand() {
        labelBrand = new JLabel("HOSPITAL");
        labelBrand.setFont(new Font("Helvetica", Font.BOLD, 32));
        labelBrand.setForeground(Color.WHITE);
        labelBrand.setIcon(new ImageIcon("assets/img/hospital.png"));
    }
}
