package hospital.modelo;

import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.VariablesGlobales;
import java.time.LocalDate;
import java.time.LocalTime;

public class ConsultaMedica {

    // Atributos
    private Integer id;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;
    private Integer numConsultorio;
    private HistorialMedico historialMedico;

    /**
     * Métood para validar la Fecha y hora de una Consulta medica
     * El día debe ser como mínimo, el día siguiente
     * La hora no debe admitir segundos o inferiores
     * La hora de la consulta deben iniciar en el minuto 0 o 30
     * La hora debe estar dentro de los horarios de consulta:
     * de 8:00 a 12:00 o de 14:00 a 18:00
     * 
     * @param isNew
     * @throws ModeloException 
     */
    public void validarFechaHora(boolean isNew) throws ModeloException {
        if (fecha == null) {
            throw new ModeloException("La fecha es obligatoria", ErrorType.ErrorArchivoRequerido);
        }
        if (hora == null) {
            throw new ModeloException("La hora es obligatoria", ErrorType.ErrorArchivoRequerido);
        }

        if (isNew) {
            LocalDate today = LocalDate.now();
            if (!today.isBefore(fecha)) {
                throw new ModeloException("Debe solicitar consulta para uno de los siguientes dias", ErrorType.ErrorValorNoPermitido);
            }

            if (hora.getNano() != 0) {
                throw new ModeloException("No debe indicar los nanosegundos", ErrorType.ErrorValorNoValido);
            }
            if (hora.getSecond() != 0) {
                throw new ModeloException("No debe indicar los segundos", ErrorType.ErrorValorNoValido);
            }
            if (hora.getMinute() != 0 && hora.getMinute() != 30) {
                throw new ModeloException("Las horas de inicio de consulta tienen uno de los siguiente formatos: hh:00 o hh:30", ErrorType.ErrorValorNoValido);
            }

            LocalTime t1, t2, t3, t4;
            t1 = LocalTime.parse("08:00");
            t2 = LocalTime.parse("12:00");
            t3 = LocalTime.parse("14:00");
            t4 = LocalTime.parse("18:00");
            if (!((hora.compareTo(t1) >= 0 && hora.compareTo(t2) < 0) || (hora.compareTo(t3) >= 0 && hora.compareTo(t4) < 0))) {
                throw new ModeloException(
                        "Los horarios de consulta son de 08:00 a 12:00 y de 14:00 a 18:00 cada media hora",
                        ErrorType.ErrorValorNoPermitido
                );
            }
        }
    }

    /**
     * Método para validar el Estado de una consulta médica
     * Es obligatorio
     * Solo admite los valores de: PENDIENTE, CANCELADA, FINALIZADA
     * 
     * @throws ModeloException 
     */
    public void validarEstado() throws ModeloException {
        if (estado == null || estado.equals("")) {
            throw new ModeloException("El estado es obligatorio", ErrorType.ErrorArchivoRequerido);
        }
        if (!estado.equals("PENDIENTE") && !estado.equals("CANCELADA") && !estado.equals("FINALIZADA")) {
            throw new ModeloException(
                    "Los posibles estados de una consulta son PENDIENTE, CANCELADA y FINALIZADA",
                    ErrorType.ErrorValorNoValido
            );
        }
    }

    /**
     * Método para validar el numero de consultorio de una consulta medica
     * Es obligatorio
     * Debe estar entre 1 y 30
     * 
     * @throws ModeloException 
     */
    public void validarNumConsultorio() throws ModeloException {
        if (numConsultorio == null) {
            throw new ModeloException("El número de consultorio es obligatorio", ErrorType.ErrorArchivoRequerido);
        }
        if (!(1 <= numConsultorio && numConsultorio <= VariablesGlobales.NUM_CONSULTORIOS)) {
            throw new ModeloException(
                    "El número de consultorio debe estar entre 1 y " + VariablesGlobales.NUM_CONSULTORIOS,
                    ErrorType.ErrorValorNoPermitido
            );
        }
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getEstado() {
        return estado;
    }

    public Integer getNumConsultorio() {
        return numConsultorio;
    }

    public HistorialMedico getHistorialMedico() {
        return historialMedico;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNumConsultorio(Integer numConsultorio) {
        this.numConsultorio = numConsultorio;
    }

    public void setHistorialMedico(HistorialMedico historialMedico) {
        this.historialMedico = historialMedico;
    }
}
