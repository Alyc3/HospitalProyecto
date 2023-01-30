package hospital.vista.listeners;

import hospital.modelo.Cuenta;
import hospital.vista.FrmMain;
import hospital.vista.paneles.PanelFormularioLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IniciarSesion implements ActionListener {

    private FrmMain frmMain;
    private PanelFormularioLogin panelFormularioLogin;

    public IniciarSesion(FrmMain mainWindow, PanelFormularioLogin panelFormularioLogin) {
        this.frmMain = mainWindow;
        this.panelFormularioLogin = panelFormularioLogin;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Iniciar sesión");
        Cuenta cuenta = panelFormularioLogin.getCuenta();
        System.out.println(cuenta.getUsuario() + " " + cuenta.getClave());
    }
}
