package hospital.vista;

import hospital.modelo.Rol;
import hospital.vista.paneles.PanelFormularioLogin;
import hospital.vista.paneles.PanelIzquierdoDefault;
import java.awt.*;
import javax.swing.*;

public class FrmLogin {

    private FrmMain frmMain;

    private Rol rol;

    private JPanel panelMain;
    private JPanel panelIzquierdo;
    private JPanel panelCuerpo;
    private JPanel panelFormularioLogin;

    public FrmLogin(FrmMain window) {
        this.frmMain = window;
    }

    /**
     * Inicializa los componentes de la vista y muestra los mismos en la ventana
     * Obtiene el rol para identificar para que tipo de usuario será el login
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
        panelMain.add(panelIzquierdo, BorderLayout.WEST);
        panelMain.add(panelCuerpo, BorderLayout.CENTER);
    }

    /**
     * Crea el panel lateral izquierdo por defecto
     */
    private void makePanelLeft() {
        panelIzquierdo = new PanelIzquierdoDefault(frmMain);
    }

    /**
     * Crea el panel del cuerpo de la vista con todos sus subcomponentes
     */
    private void makePanelBody() {
        panelCuerpo = new JPanel();
        panelCuerpo.setLayout(new GridBagLayout());
        panelCuerpo.setBackground(new Color(214, 234, 248));
        makePanelFormularioLogin();
        panelCuerpo.add(panelFormularioLogin);
    }

    /**
     * Crear el panel contenedor del formulario con todos los componentes del
     * mismo
     */
    private void makePanelFormularioLogin() {
        panelFormularioLogin = new PanelFormularioLogin(frmMain, rol);
    }
}
