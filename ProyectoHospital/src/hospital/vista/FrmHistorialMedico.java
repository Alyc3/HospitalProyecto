package hospital.vista;

import hospital.modelo.ConsultaMedica;
import hospital.modelo.Rol;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.modelo.global.VariablesGlobales;
import hospital.vista.servicio.Servicios;
import hospital.vista.paneles.PanelHistorialMedico;
import hospital.vista.paneles.PanelIzquierdoMedico;
import hospital.vista.paneles.PanelIzquierdoPaciente;
import java.awt.*;
import javax.swing.*;

public class FrmHistorialMedico {

    private FrmMain frmMain;

    private Rol rol;

    private Integer idPaciente;

    private Servicios servicios;

    private JPanel panelMain;
    private JPanel panelLeft;
    private JPanel panelBody;
    private JPanel panelHistorialMedico;
    private JPanel panelConsultaMedica;

    public FrmHistorialMedico(FrmMain frmMain) {
        this.frmMain = frmMain;
    }

    public void show(Rol rol, Integer idPaciente) {
        frmMain.removeAll();
        this.rol = rol;
        this.idPaciente = idPaciente;
        this.servicios = ManagerComponentes.servicios;
        Container cp = frmMain.getContentPane();
        cp.setLayout(new BorderLayout());
        makePanelMain();
        cp.add(panelMain, BorderLayout.CENTER);
        frmMain.repaint();
    }

    private void makePanelMain() {
        panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());
        makePanelLeft();
        makePanelBody();
        panelMain.add(panelLeft, BorderLayout.WEST);
        panelMain.add(panelBody, BorderLayout.CENTER);
    }

    private void makePanelLeft() {
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            panelLeft = new PanelIzquierdoMedico(frmMain);
        } else {
            panelLeft = new PanelIzquierdoPaciente(frmMain);
        }
    }

    private void makePanelBody() {
        panelBody = new JPanel();
        panelBody.setLayout(new FlowLayout()); // Centrará horizontalmente sus subcomponentes.
        panelBody.setBackground(new Color(214, 234, 248));
        makePanelHistorialMedico();
        makePanelConsultaMedica();
        panelBody.add(panelHistorialMedico);
        panelBody.add(panelConsultaMedica);
    }

    private void makePanelHistorialMedico() {
        panelHistorialMedico = new PanelHistorialMedico(frmMain, rol, idPaciente);
    }

    private void makePanelConsultaMedica() {
        panelConsultaMedica = new JPanel();
        panelConsultaMedica.setLayout(new GridBagLayout());
        try {
            System.out.println("... " + idPaciente);
            Integer idConsultaMedica = null;
        } catch (Exception e) {
            System.out.println("Error obteniendo datos de la consulta");
        }
    }
}
