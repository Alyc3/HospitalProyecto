package hospital.vista;

import hospital.modelo.Rol;
import hospital.modelo.global.VariablesGlobales;
import hospital.vista.listeners.CancelarConsultaMedica;
import hospital.vista.listeners.FinalizarConsultaMedica;
import hospital.vista.paneles.PanelConsultaMedica;
import hospital.vista.paneles.PanelDiagnostico;
import hospital.vista.paneles.PanelExamenes;
import hospital.vista.paneles.PanelIzquierdoMedico;
import hospital.vista.paneles.PanelIzquierdoPaciente;
import hospital.vista.utilities.CrearFormularios;
import java.awt.*;
import javax.swing.*;

public class FrmConsultaMedica {

    private FrmMain frmMain;

    private Rol rol;

    private Integer idPaciente;

    private Integer idConsultaMedica;

    private JPanel panelMain;
    private JPanel panelLeft;
    private JPanel panelBody;
    private JPanel panelConsultaMedica;
    private JPanel panelDiagnostico;
    private JPanel panelExamenes;
    private JPanel panelBotones;

    private JButton buttonCancelar;
    private JButton buttonFinalizar;

    public FrmConsultaMedica(FrmMain frmMain) {
        this.frmMain = frmMain;
    }

    /**
     * Inicializa los componentes de la vista y muestra los mismos en la ventana
     * Guarda el rol e id del paciente y el id de la consulta medica
     *
     * @param rol
     * @param idPaciente
     * @param idConsultaMedica
     */
    public void mostrar(Rol rol, Integer idPaciente, Integer idConsultaMedica) {
        frmMain.removeAll();
        this.rol = rol;
        this.idPaciente = idPaciente;
        this.idConsultaMedica = idConsultaMedica;
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
     * Crea el panel lateral izquierdo basado en el rol del usuario (Paciente o
     * médico)
     */
    private void makePanelLeft() {
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            panelLeft = new PanelIzquierdoMedico(frmMain);
        } else {
            panelLeft = new PanelIzquierdoPaciente(frmMain);
        }
    }

    /**
     * Crea el panel del cuerpo de la vista con todos los componentes
     * correspondientes
     */
    private void makePanelBody() {
        panelBody = new JPanel();
        panelBody.setLayout(new FlowLayout());
        JPanel panelInterno = new JPanel();
        panelInterno.setLayout(new BoxLayout(panelInterno, BoxLayout.Y_AXIS));
        panelInterno.setBackground(new Color(214, 234, 248));
        makePanelConsultaMedica();
        makePanelDiagnostico();
        makePanelExamenes();
        makePanelBotones();
        panelInterno.add(panelConsultaMedica);
        panelInterno.add(panelDiagnostico);
        panelInterno.add(panelExamenes);
        panelInterno.add(panelBotones);
        panelBody.add(panelInterno);
    }

    /**
     * Crea el panel para consultas médicas
     */
    private void makePanelConsultaMedica() {
        panelConsultaMedica = new JPanel();
        panelConsultaMedica.setLayout(new GridBagLayout());
        panelConsultaMedica.add(new PanelConsultaMedica(frmMain, rol, idPaciente, idConsultaMedica));
    }

    /**
     * Crea el panel para Diagnosticos
     */
    private void makePanelDiagnostico() {
        /*panelDiagnostico = new JPanel();
		panelDiagnostico.setLayout(new GridBagLayout());

		panelDiagnostico.add(new PanelDiagnostico(window, rol, idConsultaMedica));*/

        panelDiagnostico = new PanelDiagnostico(frmMain, rol, idConsultaMedica);
    }

    /**
     * Crea el panel para examenes
     */
    private void makePanelExamenes() {
        //	panelExamenes = new JPanel();
        //	panelExamenes.add(new JLabel("Examenes"));
        panelExamenes = new PanelExamenes(frmMain, rol, idPaciente, idConsultaMedica);

    }

    /**
     * Crea el panel contenedor de los botones presetados
     */
    private void makePanelBotones() {
        panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());

        buttonCancelar = CrearFormularios.crearButton("Cancelar consulta", 240, 40, new CancelarConsultaMedica(frmMain, rol, idPaciente, idConsultaMedica));
        buttonFinalizar = CrearFormularios.crearButton("Finalizar consulta", 240, 40, new FinalizarConsultaMedica(frmMain, rol, idPaciente, idConsultaMedica));

        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            panelBotones.add(buttonFinalizar);
        } else {
            panelBotones.add(buttonCancelar);
        }
    }
}
