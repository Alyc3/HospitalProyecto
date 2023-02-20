package hospital.vista;

import java.awt.*;
import javax.swing.*;

public class FrmNuevaConsulta extends JDialog {

    private NuevaConsultaVista nuevaConsultaVista;

    public FrmNuevaConsulta(JFrame duenio) {
        super(duenio, "Nueva consulta médica", true);

        nuevaConsultaVista = new NuevaConsultaVista(this);

        setSize(400, 300);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(this);

        loadNuevaConsultaView();
    }

    public void removeAll() {
        Container cp = getContentPane();
        cp.removeAll();
    }

    public void repaint() {
        Container cp = getContentPane();
        cp.validate();
        cp.repaint();
    }

    public void loadNuevaConsultaView() {
        nuevaConsultaVista.mostrar();
    }
}
