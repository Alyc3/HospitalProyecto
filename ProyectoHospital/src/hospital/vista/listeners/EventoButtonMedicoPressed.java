package hospital.vista.listeners;

import hospital.modelo.global.VariablesGlobales;
import hospital.vista.FrmHome;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoButtonMedicoPressed implements ActionListener {

    private FrmHome view;

    public EventoButtonMedicoPressed(FrmHome view) {
        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
//        view.loadLoginView(VariablesGlobales.ROL_MEDICO);
    }
}
