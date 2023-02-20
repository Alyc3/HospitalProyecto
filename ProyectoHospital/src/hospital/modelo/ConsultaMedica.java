package hospital.modelo;

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
