package hospital.vista.paneles;

import hospital.modelo.HistorialMedico;
import hospital.modelo.Persona;
import hospital.modelo.Rol;
import hospital.modelo.enumeradores.EncontrarTipoExamen;
import hospital.modelo.enumeradores.EncontrarTipoSanguineo;
import hospital.modelo.enumeradores.TipoSanguineo;
import hospital.modelo.excepciones.CampoNovalidoException;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.modelo.global.SesionUsuario;
import hospital.modelo.global.VariablesGlobales;
import hospital.modelo.utilities.Tiempo.UtilidadesTiempo;
import hospital.vista.servicio.Servicios;
import hospital.vista.FrmMain;
import hospital.vista.listeners.EditarHistorialMedico;
import hospital.vista.utilities.CrearFormularios;
import hospital.vista.utilities.ExtractorDeDatosDeForms;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import javax.swing.*;

/**
 * Sirve para crear la configuración del panel de historial médico junto con sus
 * subcomponentes
 */
public class PanelHistorialMedico extends JPanel {

    private FrmMain frmMain;

    private Rol rol;

    private Integer idPaciente;

    private Servicios servicios;

    private JPanel panelMain;
    private JPanel panelTitulo;
    private JPanel panelNombreApellido;
    private JPanel panelDatos;
    private JPanel panelDatosLeft;
    private JPanel panelDatosRight;
    private JPanel panelBotones;

    private JLabel labelTitulo;
    private JLabel labelNombreApellido;
    private JLabel labelPeso;
    private JLabel labelEstatura;
    private JLabel labelTipoSanguineo;
    private JLabel labelGenero;
    private JLabel labelEdad;
    private JLabel labelFechaNacimiento;
    private JLabel labelDia;
    private JLabel labelMes;
    private JLabel labelAño;
    private JLabel labelAntecedentes;
    private JLabel labelAntecedentesFamiliares;
    private JLabel labelAlergias;
    private JLabel labelTratamientosVigentes;
    private JLabel labelErrorPeso;
    private JLabel labelErrorEstatura;
    private JLabel labelErrorTipoSanguineo;
    private JLabel labelErrorGenero;
    private JLabel labelErrorDia;
    private JLabel labelErrorMes;
    private JLabel labelErrorAño;
    private JLabel labelErrorEdad;
    private JLabel labelErrorAntecedentes;
    private JLabel labelErrorAntecedentesFamiliares;
    private JLabel labelErrorAlergias;
    private JLabel labelErrorTratamientosVigentes;

    private JTextField fieldPeso;
    private JTextField fieldEstatura;
    private JComboBox fieldTipoSanguineo;
    private JComboBox fieldGenero;
    private JComboBox fieldDia;
    private JComboBox fieldMes;
    private JComboBox fieldAño;
    private JTextField fieldEdad;
    private JTextArea fieldAntecedentes;
    private JTextArea fieldAntecedentesFamiliares;
    private JTextArea fieldAlergias;
    private JTextArea fieldTratamientosVigentes;

    private JButton buttonGuardar;

    private String[] optionsTipoSanguineo = {"A+", "O+", "B+", "AB+", "A-", "O-", "B-", "AB-"};
    private String[] optionsGenero = {"M", "F"};
    private String[] optionsAño = UtilidadesTiempo.getAniosDesdeHasta(1900, 2023);
    private String[] optionsMes = UtilidadesTiempo.getMesesEspaniol();
    private String[] optionsDia = UtilidadesTiempo.getMaximoNumDiasMes();

    private Persona persona;
    private HistorialMedico historialMedico;

    public PanelHistorialMedico(FrmMain frmMain, Rol rol, Integer idPaciente) {
        this.frmMain = frmMain;
        this.rol = rol;
        this.idPaciente = idPaciente;
        this.servicios = ManagerComponentes.servicios;
        setLayout(new BorderLayout());
        makePanelMain();
        add(panelMain, BorderLayout.CENTER);
        loadData();
    }

    private void makePanelMain() {
        panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.setBackground(Color.WHITE);
        //setBackground(new Color(38, 53, 72));
        panelMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        makePanelTitulo();
        makePanelNombreApellido();
        makePanelDatos();
        makePanelBotones();
        panelMain.add(panelTitulo);
        panelMain.add(panelNombreApellido);
        panelMain.add(panelDatos);
        panelMain.add(panelBotones);
    }

    private void makePanelTitulo() {
        panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout());
        panelTitulo.setBackground(new Color(0, 0, 0, 0));
        labelTitulo = CrearFormularios.crearTituloLabel("HISTORIAL MÉDICO");
        panelTitulo.add(labelTitulo);
    }

    private void makePanelNombreApellido() {
        panelNombreApellido = new JPanel();
        panelNombreApellido.setLayout(new BorderLayout());
        panelNombreApellido.setBackground(new Color(0, 0, 0, 0));
        makeLabelNombreApellido();
        panelNombreApellido.add(labelNombreApellido, BorderLayout.WEST);
    }

    private void makePanelDatos() {
        panelDatos = new JPanel();
        panelDatos.setLayout(new BorderLayout());
        panelDatos.setBackground(new Color(0, 0, 0, 0));
        makePanelDatosLeft();
        makePanelDatosRight();
        panelDatos.add(panelDatosLeft, BorderLayout.WEST);
        panelDatos.add(panelDatosRight, BorderLayout.EAST);
    }

    private void makePanelBotones() {
        panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setBackground(new Color(0, 0, 0, 0));
        buttonGuardar = CrearFormularios.crearButton("Guardar", 160, 40, new EditarHistorialMedico(frmMain, this));
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            panelBotones.add(buttonGuardar);
        }
    }

    private void makeLabelNombreApellido() {
        labelNombreApellido = CrearFormularios.crearSubtitulolabel("Nombre y Apellido");
    }

    private void makePanelDatosLeft() {
        panelDatosLeft = new JPanel();
        panelDatosLeft.setLayout(new FlowLayout());
        panelDatosLeft.setBackground(Color.WHITE);

        JPanel panelInterno = new JPanel();
        panelInterno.setLayout(new BoxLayout(panelInterno, BoxLayout.Y_AXIS));
        panelInterno.setBackground(Color.WHITE);

        labelPeso = CrearFormularios.crearEtiquetaDesdeCampo("Peso");
        fieldPeso = CrearFormularios.crearTextField(5, rol.equals(VariablesGlobales.ROL_MEDICO) ? true : false);
        labelErrorPeso = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelEstatura = CrearFormularios.crearEtiquetaDesdeCampo("Estatura");
        fieldEstatura = CrearFormularios.crearTextField(5, rol.equals(VariablesGlobales.ROL_MEDICO) ? true : false);
        labelErrorEstatura = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelTipoSanguineo = CrearFormularios.crearEtiquetaDesdeCampo("Tipo sanguíneo");
        fieldTipoSanguineo = CrearFormularios.crearComboBox(100, 30, optionsTipoSanguineo, false);
        labelErrorTipoSanguineo = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelGenero = CrearFormularios.crearEtiquetaDesdeCampo("Género");
        fieldGenero = CrearFormularios.crearComboBox(50, 30, optionsGenero, false);
        labelErrorGenero = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelDia = CrearFormularios.crearEtiquetaDesdeCampo("Dia");
        fieldDia = CrearFormularios.crearComboBox(60, 30, optionsDia, false);
        labelErrorDia = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelMes = CrearFormularios.crearEtiquetaDesdeCampo("Mes");
        fieldMes = CrearFormularios.crearComboBox(140, 30, optionsMes, false);
        labelErrorMes = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelAño = CrearFormularios.crearEtiquetaDesdeCampo("Año");
        fieldAño = CrearFormularios.crearComboBox(100, 30, optionsAño, false);
        labelErrorAño = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelEdad = CrearFormularios.crearEtiquetaDesdeCampo("Edad");
        fieldEdad = CrearFormularios.crearTextField(4, false);
        labelErrorEdad = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");

        labelAntecedentes = CrearFormularios.crearEtiquetaDesdeCampo("Antecedentes");
        fieldAntecedentes = CrearFormularios.crearTextArea(5, 30, false, rol.equals(VariablesGlobales.ROL_MEDICO) ? true : false);
        labelErrorAntecedentes = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");
        JScrollPane scrollFieldAntecedentes = CrearFormularios.crearScrollPane(fieldAntecedentes);

        labelAntecedentesFamiliares = CrearFormularios.crearEtiquetaDesdeCampo("Antecedentes familiares");
        fieldAntecedentesFamiliares = CrearFormularios.crearTextArea(5, 30, false, rol.equals(VariablesGlobales.ROL_MEDICO) ? true : false);
        labelErrorAntecedentesFamiliares = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");
        JScrollPane scrollFieldAntecedentesFamiliares = CrearFormularios.crearScrollPane(fieldAntecedentesFamiliares);

        JPanel panelPesoEstaturaTipoSanguineoGenero = CrearFormularios.crearPanelMultiCampo();
        panelPesoEstaturaTipoSanguineoGenero.add(CrearFormularios.crearPanelCampo(labelPeso, fieldPeso, labelErrorPeso));
        panelPesoEstaturaTipoSanguineoGenero.add(CrearFormularios.crearPanelCampo(labelEstatura, fieldEstatura, labelErrorEstatura));
        panelPesoEstaturaTipoSanguineoGenero.add(CrearFormularios.crearPanelCampo(labelTipoSanguineo, fieldTipoSanguineo, labelErrorTipoSanguineo));
        panelPesoEstaturaTipoSanguineoGenero.add(CrearFormularios.crearPanelCampo(labelGenero, fieldGenero, labelErrorGenero));

        JPanel panelFechaNacimientoEdad = CrearFormularios.crearPanelMultiCampo();
        panelFechaNacimientoEdad.add(CrearFormularios.crearPanelCampo(labelDia, fieldDia, labelErrorDia));
        panelFechaNacimientoEdad.add(CrearFormularios.crearPanelCampo(labelMes, fieldMes, labelErrorMes));
        panelFechaNacimientoEdad.add(CrearFormularios.crearPanelCampo(labelAño, fieldAño, labelErrorAño));
        panelFechaNacimientoEdad.add(CrearFormularios.crearPanelCampo(labelEdad, fieldEdad, labelErrorEdad));
        JPanel panelAntecedentes = CrearFormularios.crearPanelCampo(labelAntecedentes, scrollFieldAntecedentes, labelErrorAntecedentes);

        JPanel panelAntecedentesFamiliares = CrearFormularios.crearPanelCampo(labelAntecedentesFamiliares, scrollFieldAntecedentesFamiliares, labelErrorAntecedentesFamiliares);

        panelInterno.add(panelPesoEstaturaTipoSanguineoGenero);
        panelInterno.add(panelFechaNacimientoEdad);
        panelInterno.add(panelAntecedentes);
        panelInterno.add(panelAntecedentesFamiliares);

        panelDatosLeft.add(panelInterno);
    }

    private void makePanelDatosRight() {
        panelDatosRight = new JPanel();
        panelDatosRight.setLayout(new FlowLayout());
        panelDatosRight.setBackground(Color.WHITE);
        JPanel panelInterno = new JPanel();
        panelInterno.setLayout(new BoxLayout(panelInterno, BoxLayout.Y_AXIS));
        labelAlergias = CrearFormularios.crearEtiquetaDesdeCampo("Alergias");
        fieldAlergias = CrearFormularios.crearTextArea(5, 30, false, rol.equals(VariablesGlobales.ROL_MEDICO) ? true : false);
        labelErrorAlergias = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");
        JScrollPane scrollFieldAlergias = CrearFormularios.crearScrollPane(fieldAlergias);
        labelTratamientosVigentes = CrearFormularios.crearEtiquetaDesdeCampo("Tratamientos vigentes");
        fieldTratamientosVigentes = CrearFormularios.crearTextArea(5, 30, false, rol.equals(VariablesGlobales.ROL_MEDICO) ? true : false);
        labelErrorTratamientosVigentes = CrearFormularios.crearEtiquetaErrDesdeCampo(" ");
        JScrollPane scrollFieldTratamientosVigentes = CrearFormularios.crearScrollPane(fieldTratamientosVigentes);
        JPanel panelAlergias = CrearFormularios.crearPanelCampo(labelAlergias, scrollFieldAlergias, labelErrorAlergias);
        JPanel panelTratamientosVigentes = CrearFormularios.crearPanelCampo(labelTratamientosVigentes, scrollFieldTratamientosVigentes, labelErrorTratamientosVigentes);
        panelInterno.add(panelAlergias);
        panelInterno.add(panelTratamientosVigentes);

        panelDatosRight.add(panelInterno);
    }

    public HistorialMedico getHistorialMedico() throws ModeloException {
        HistorialMedico historialMedico = new HistorialMedico();

        try {
            historialMedico.setId(this.historialMedico.getId());
            historialMedico.setPeso(ExtractorDeDatosDeForms.getFloatFrom("Peso", fieldPeso));
            historialMedico.setEstatura(ExtractorDeDatosDeForms.getFloatFrom("Estatura", fieldEstatura));
            historialMedico.setAntecedentes(fieldAntecedentes.getText());
            historialMedico.setAntecedentesFamiliares(fieldAntecedentesFamiliares.getText());
            historialMedico.setAlergias(fieldAlergias.getText());
            historialMedico.setTratamientosVigentes(fieldTratamientosVigentes.getText());
            LocalDate fechaNacimiento = ExtractorDeDatosDeForms.getLocalDateFrom("Fecha de Nacimiento", fieldAño, fieldMes, fieldDia);
            historialMedico.setFechaNacimiento(fechaNacimiento.toString());
            String tipoSanguineoStr = fieldTipoSanguineo.getSelectedItem().toString();
            TipoSanguineo tipoSanguineo = EncontrarTipoSanguineo.encontrar(tipoSanguineoStr);
            historialMedico.setTipoSanguineo(tipoSanguineo);
            historialMedico.setPersona(new Persona());
            historialMedico.getPersona().setId(idPaciente);
        } catch (CampoNovalidoException e) {
            throw new ModeloException(
                    "El valor del campo de " + e.getFieldName() + " es inválido",
                    e,
                    ErrorType.ErrorValorNoValido
            );
        }
        return historialMedico;
    }

    public void setNombreApellido(String nombreApellido) {
    }

    public void setPeso(Float peso) {
        fieldPeso.setText(String.valueOf(peso));
    }

    public void setEstatura(Float estatura) {
        fieldEstatura.setText(String.valueOf(estatura));
    }

    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        fieldTipoSanguineo.setSelectedItem(tipoSanguineo.toString());
    }

    public void setGenero(String genero) {
        fieldGenero.setSelectedItem(genero);
    }

    public void setDia(Integer dia) {
        fieldDia.setSelectedItem(String.valueOf(dia));
    }

    public void setMes(String mes) {
        fieldMes.setSelectedItem(mes);
    }

    public void setAño(Integer año) {
        fieldAño.setSelectedItem(String.valueOf(año));
    }

    public void setEdad(Integer edad) {
        fieldEdad.setText(String.valueOf(edad));
    }

    public void setAntecedentes(String antecedentes) {
        fieldAntecedentes.setText(antecedentes);
    }

    public void setAntecedentesFamiliares(String antecedentesFamiliares) {
        fieldAntecedentesFamiliares.setText(antecedentesFamiliares);
    }

    public void setAlergias(String alergias) {
        fieldAlergias.setText(alergias);
    }

    public void setTratamientosVigentes(String tratamientosVigentes) {
        fieldTratamientosVigentes.setText(tratamientosVigentes);
    }

    public void showResultOfEditarHistorialMedico(boolean successful, String message) {
        if (successful) {
            JOptionPane.showMessageDialog(frmMain, message, "Resultado de editar el historial médico", JOptionPane.PLAIN_MESSAGE, new ImageIcon("assets/img/successful.png"));
        } else {
            JOptionPane.showMessageDialog(frmMain, message, "Resultado de editar el historial médico", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadData() {
        Integer idPersona = SesionUsuario.persona.getId();
        if (rol.equals(VariablesGlobales.ROL_MEDICO)) {
            idPersona = idPaciente;
        } else {
            idPersona = SesionUsuario.persona.getId();
        }

        try {
            persona = servicios.verInformacionPersona(idPersona);
            historialMedico = servicios.verHistorialMedico(idPersona);

            labelNombreApellido.setText(persona.getNombre() + " " + persona.getApellido());

            if (historialMedico.getPeso() != null) {
                setPeso(historialMedico.getPeso());
            }
            if (historialMedico.getEstatura() != null) {
                setEstatura(historialMedico.getEstatura());
            }
            if (historialMedico.getTipoSanguineo() != null) {
                setTipoSanguineo(historialMedico.getTipoSanguineo());
            }
            if (persona.getGenero() != null) {
                setGenero(persona.getGenero());
            }
            if (historialMedico.getFechaNacimiento() != null) {
                LocalDate fechaNacimiento = LocalDate.parse(historialMedico.getFechaNacimiento());
                setAño(fechaNacimiento.getYear());
                setMes(UtilidadesTiempo.getMesesEspaniol(fechaNacimiento.getMonthValue()));
                setDia(fechaNacimiento.getDayOfMonth());
            }
            if (persona.getEdad() != null) {
                setEdad(persona.getEdad());
            }
            if (historialMedico.getAntecedentes() != null) {
                setAntecedentes(historialMedico.getAntecedentes());
            }
            if (historialMedico.getAntecedentesFamiliares() != null) {
                setAntecedentesFamiliares(historialMedico.getAntecedentesFamiliares());
            }
            if (historialMedico.getAlergias() != null) {
                setAlergias(historialMedico.getAlergias());
            }
            if (historialMedico.getTratamientosVigentes() != null) {
                setTratamientosVigentes(historialMedico.getTratamientosVigentes());
            }
        } catch (ModeloException e) {
            System.out.println("Error cargando los datos");
        }
    }

    public Rol getRol() {
        return rol;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }
}
