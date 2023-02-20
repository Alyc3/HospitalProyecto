package hospital.vista.listeners;

import hospital.modelo.Cuenta;
import hospital.modelo.Persona;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.modelo.global.SesionUsuario;
import hospital.modelo.global.VariablesGlobales;
import hospital.vista.servicio.Servicios;
import hospital.vista.FrmMain;
import hospital.vista.paneles.PanelFormularioLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class IniciarSesion implements ActionListener {

    private FrmMain frmMain;
    private PanelFormularioLogin panelFormularioLogin;
    private Servicios servicios;

    public IniciarSesion(FrmMain frmMain, PanelFormularioLogin panelFormularioLogin) {
        this.frmMain = frmMain;
        this.panelFormularioLogin = panelFormularioLogin;
        this.servicios = ManagerComponentes.servicios;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Iniciar sesión");

        Cuenta cuenta;
        try {
            cuenta = panelFormularioLogin.getCuenta();
            System.out.println(cuenta.getUsuario() + " " + cuenta.getClave());

            Cuenta c = servicios.login(panelFormularioLogin.getRol(), cuenta.getUsuario(), cuenta.getClave());
            SesionUsuario.cuenta = c;

            Persona p = servicios.verInformacionPersona(c.getPersona().getId());
            SesionUsuario.persona = p;

            showResultOfIniciarSesion(true, "Inicio sesión correctamente");
            if (panelFormularioLogin.getRol().equals(VariablesGlobales.ROL_MEDICO)) {
                frmMain.loadFrmPacientesPendientes();
            } else {
                frmMain.loalFrmConsultasMedicasPaciente();
            }

        } catch (ModeloException ae) {
            showResultOfIniciarSesion(false, ae.getMessage());
            return;
        }

        /*if(!cuenta.getUsuario().equals("") && !cuenta.getClave().equals("")){
			showResultOfIniciarSesion(true, "Inicio sesión correctamente (mensaje de prueba)");
			if(panelFormularioLogin.getRol().equals(GlobalVariables.ROL_MEDICO))
			window.loadPacientesPendientesView();
			else
			window.loadConsultasMedicasPacienteView();
		}
		else
		showResultOfIniciarSesion(false, "Falló al iniciar sesión (mensaje de prueba)");*/
    }

    /**
     * Abre un cuadro de diálogo que muestra el resultado del intento del inicio
     * de sesión
     *
     * @param successful
     * @param message
     */
    public void showResultOfIniciarSesion(boolean successful, String message) {
        if (successful) {
            JOptionPane.showMessageDialog(frmMain, message, "Resultado de iniciar sesión", JOptionPane.PLAIN_MESSAGE, new ImageIcon("assets/img/successful.png"));
        } else {
            JOptionPane.showMessageDialog(frmMain, message, "Resultado de iniciar sesión", JOptionPane.ERROR_MESSAGE);
        }
    }
}
