package hospital.vista.paneles;

import hospital.modelo.ConsultaMedica;
import hospital.modelo.excepciones.CampoNovalidoException;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.utilities.Tiempo.UtilidadesTiempo;
import hospital.vista.FrmNuevaConsulta;
import hospital.vista.listeners.AgendarConsultaMedica;
import hospital.vista.utilities.CrearFormularios;
import hospital.vista.utilities.ExtractorDeDatosDeForms;
import java.awt.*;
import java.time.*;
import javax.swing.*;

/**
 * Sirve para crear la configuración del panel del formulario para una nueva
 * consulta medica que se presenta a los pacientes
 */
public class PanelFormularioNuevaConsulta extends JPanel {

    private FrmNuevaConsulta frmNuevaConsulta;

    private JPanel panelMain;
    private JPanel panelTitulo;
    private JPanel panelDatos;
    private JPanel panelBotones;

    private JLabel labelTitulo;
    private JLabel labelFecha;
    private JLabel labelDia;
    private JLabel labelMes;
    private JLabel labelAño;
    private JLabel labelHora;
    private JLabel labelFechaError;
    private JLabel labelErrorDia;
    private JLabel labelErrorMes;
    private JLabel labelErrorAño;
    private JLabel labelErrorHora;

    private JComboBox<String> fieldDia;
    private JComboBox<String> fieldMes;
    private JComboBox<String> fieldAño;
    private JComboBox<String> fieldHora;

    private JButton buttonAgendarConsulta;

    private String[] optionsHora
            = {
                "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
                "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30"
            };
    private String[] optionsAño = UtilidadesTiempo.getAniosDesdeHasta(2023, 2023);
    private String[] optionsMes = UtilidadesTiempo.getMesesEspaniol();
    private String[] optionsDia = UtilidadesTiempo.getMaximoNumDiasMes();

    public PanelFormularioNuevaConsulta(FrmNuevaConsulta frmNuevaConsulta) {
        this.frmNuevaConsulta = frmNuevaConsulta;
        setLayout(new BorderLayout());
        makePanelMain();
        add(panelMain, BorderLayout.CENTER);
    }

    private void makePanelMain() {
        panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        makePanelTitulo();
        makePanelDatos();
        makePanelBotones();
        panelMain.add(panelTitulo);
        panelMain.add(panelDatos);
        panelMain.add(panelBotones);
    }

    private void makePanelTitulo() {
        panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout());
        panelTitulo.setBackground(new Color(0, 0, 0, 0));
        labelTitulo = CrearFormularios.crearTituloLabel("NUEVA CONSULTA MÉDICA");
        panelTitulo.add(labelTitulo);
    }

    private void makePanelDatos() {
        panelDatos = new JPanel();
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
        panelDatos.setBackground(new Color(0, 0, 0, 0));
        panelDatos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labelDia = CrearFormularios.crearEtiquetaDesdeCampo("Dia");
        fieldDia = CrearFormularios.crearComboBox(60, 34, optionsDia);
        labelErrorDia = CrearFormularios.crearEtiquetaErrDesdeCampo("");
        labelMes = CrearFormularios.crearEtiquetaDesdeCampo("Mes");
        fieldMes = CrearFormularios.crearComboBox(140, 34, optionsMes);
        labelErrorMes = CrearFormularios.crearEtiquetaErrDesdeCampo("");
        labelAño = CrearFormularios.crearEtiquetaDesdeCampo("Año");
        fieldAño = CrearFormularios.crearComboBox(100, 34, optionsAño);
        labelErrorAño = CrearFormularios.crearEtiquetaErrDesdeCampo("");
        labelHora = CrearFormularios.crearEtiquetaDesdeCampo("Hora");
        fieldHora = CrearFormularios.crearComboBox(100, 34, optionsHora);
        labelErrorHora = CrearFormularios.crearEtiquetaErrDesdeCampo("");
        JPanel panelFecha = CrearFormularios.crearPanelMultiCampo();
        panelFecha.add(CrearFormularios.crearPanelCampo(labelDia, fieldDia, labelErrorDia));
        panelFecha.add(CrearFormularios.crearPanelCampo(labelMes, fieldMes, labelErrorMes));
        panelFecha.add(CrearFormularios.crearPanelCampo(labelAño, fieldAño, labelErrorAño));
        JPanel panelHora = CrearFormularios.crearPanelCampo(labelHora, fieldHora, labelErrorHora);
        panelDatos.add(panelFecha);
        panelDatos.add(panelHora);
    }

    private void makePanelBotones() {
        panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setBackground(new Color(0, 0, 0, 0));
        JPanel panelButtonAgendarConsulta = new JPanel();
        panelButtonAgendarConsulta.setLayout(new FlowLayout());
        panelButtonAgendarConsulta.setBackground(new Color(0, 0, 0, 0));
        makeButtonAgendarConsulta();
        panelButtonAgendarConsulta.add(buttonAgendarConsulta);
        panelBotones.add(panelButtonAgendarConsulta);
    }

    private void makeButtonAgendarConsulta() {
        buttonAgendarConsulta = CrearFormularios.crearButton("Agendar consulta", 200, 40, new AgendarConsultaMedica(frmNuevaConsulta, this));
    }

    public ConsultaMedica getConsultaMedica() throws ModeloException {
        ConsultaMedica consultaMedica = new ConsultaMedica();

        try {
            LocalDate fecha = ExtractorDeDatosDeForms.getLocalDateFrom("Fecha", fieldAño, fieldMes, fieldDia);
            consultaMedica.setFecha(fecha);
        } catch (CampoNovalidoException e) {
            throw new ModeloException(
                    "El valor del campo de " + e.getFieldName() + " es inválido",
                    e,
                    ErrorType.ErrorValorNoValido
            );
        }

        LocalTime hora = LocalTime.parse(fieldHora.getSelectedItem().toString());
        consultaMedica.setHora(hora);

        return consultaMedica;
    }
}
