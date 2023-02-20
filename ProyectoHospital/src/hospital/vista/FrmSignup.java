package hospital.vista;

import hospital.modelo.Rol;
import hospital.vista.paneles.PanelFormularioSignup;
import hospital.vista.paneles.PanelIzquierdoDefault;
import java.awt.*;
import javax.swing.*;

public class FrmSignup {

    private FrmMain frmMain;

    private Rol rol;

    private JPanel panelMain;
    private JPanel panelLeft;
    private JPanel panelBody;
    private JPanel panelFormularioSignup;

    public FrmSignup(FrmMain frmMain) {
        this.frmMain = frmMain;
    }

    /**
     * Inicializa los componentes de la vista y muestra los mismos en la ventana
     * Obtiene el rol para dterminar para que tipo de usuario será el Signup
     *
     * @param rol
     */
    public void mostrar(Rol rol) {
        frmMain.removeAll();
        this.rol = rol;
        Container cp = frmMain.getContentPane();
        cp.setLayout(new BorderLayout());
        makePanelMain();
        cp.add(panelMain, BorderLayout.CENTER);
        frmMain.repaint();
    }

    /**
     * Método para crear y establecer la configuración del panel y añadir cada
     * uno de los componentes
     */
    private void makePanelMain() {
        panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());
        makePanelLeft();
        makePanelBody();
        panelMain.add(panelLeft, BorderLayout.WEST);
        panelMain.add(panelBody, BorderLayout.CENTER);
    }

    /**
     * Crea el panel lateral izquierdo por defecto
     */
    private void makePanelLeft() {
        panelLeft = new PanelIzquierdoDefault(frmMain);
    }

    /**
     * Crea el panel principal con todos sus componentes
     */
    private void makePanelBody() {
        panelBody = new JPanel();
        panelBody.setLayout(new GridBagLayout());
        panelBody.setBackground(new Color(238, 242, 245));
        makePanelFormularioSignup();
        panelBody.add(panelFormularioSignup);
    }

    /**
     * Crea el panel del formulario con sus subcomponentes
     */
    private void makePanelFormularioSignup() {
        panelFormularioSignup = new PanelFormularioSignup(frmMain, rol);
    }
}
