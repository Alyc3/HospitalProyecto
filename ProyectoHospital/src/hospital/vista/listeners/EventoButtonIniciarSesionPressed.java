package hospital.vista.listeners;

import hospital.modelo.Cuenta;
import hospital.vista.FrmLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoButtonIniciarSesionPressed implements ActionListener {

    private FrmLogin view;

    public EventoButtonIniciarSesionPressed(FrmLogin view) {
        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Iniciar sesión");
        Cuenta cuenta = view.getCuenta();
        System.out.println(cuenta.getUsuario() + " " + cuenta.getClave());
        //	view.get

        //	Rol rol = new Rol(1, "Rol-Medico");
        //	view.loadLoginView(rol);
    }
}
