package hospital.vista;

import hospital.modelo.Rol;
import hospital.vista.listeners.EventoButtonMedicoPressed;
import hospital.vista.listeners.EventoButtonPacientePressed;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;

public class FrmHome {

    private FrmMain window;

    public FrmHome(FrmMain window) {
        this.window = window;
    }

    public void show() {
        Container cp = window.getContentPane();
        cp.setLayout(new FlowLayout());

        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new FlowLayout());

        JButton medicosButton = new JButton("Medicos");
        JButton pacientesButton = new JButton("Pacientes");
        medicosButton.addActionListener(new EventoButtonMedicoPressed(this));
        medicosButton.setBackground(new Color(0, 76, 153));
        medicosButton.setForeground(Color.WHITE);
        medicosButton.setPreferredSize(new Dimension(200, 80));
        //pacientesButton.addActionListener(new EventButtonPressedLoadMedicoMainView(this));
        pacientesButton.setBackground(new Color(0, 76, 153));
        pacientesButton.setForeground(Color.WHITE);
        pacientesButton.setPreferredSize(new Dimension(200, 80));
	pacientesButton.addActionListener(new EventoButtonPacientePressed(this));

        cp.add(panelHeader);
        cp.add(medicosButton, BorderLayout.CENTER);
        cp.add(pacientesButton, BorderLayout.CENTER);
    }

    public void loadLoginView(Rol rol) {
        window.loadLoginView(rol);
    }
}
