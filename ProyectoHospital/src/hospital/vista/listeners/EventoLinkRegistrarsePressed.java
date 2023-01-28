package hospital.vista.listeners;

import hospital.vista.FrmLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoLinkRegistrarsePressed implements ActionListener {

    private FrmLogin view;

    public EventoLinkRegistrarsePressed(FrmLogin view) {
        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Ir a la vista para registrarse");
        view.loadSignupView(view.getRol());
    }
}
