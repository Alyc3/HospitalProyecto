/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import modelo.enums.EstadoReceta;

/**
 *
 * @author Iván González
 */
public class Receta {

    private EstadoReceta estado;
    private Diagnostico diagnostico;

    public EstadoReceta getEstado() {
        return estado;
    }

    public void setEstado(EstadoReceta estado) {
        this.estado = estado;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

}
