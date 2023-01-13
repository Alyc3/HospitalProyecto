/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalproyecto;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Edison
 */
public class ConsultaMedica {
    private int id;
    private Time hora;
    private Date fecha;
    private EstadoTurno estado;
    private Integer numConsultorio;
    private HistorialMedico historialMedico;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }

    public Integer getNumConsultorio() {
        return numConsultorio;
    }

    public void setNumConsultorio(Integer numConsultorio) {
        this.numConsultorio = numConsultorio;
    }

    public HistorialMedico getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(HistorialMedico historialMedico) {
        this.historialMedico = historialMedico;
    }
    
    
}
