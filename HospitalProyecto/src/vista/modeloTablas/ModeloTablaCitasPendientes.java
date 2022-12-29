/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTablas;

import controlador.ListaEnlazada;
import javax.swing.table.AbstractTableModel;
import modelo.ConsultaMedica;
import modelo.Persona;

/**
 *
 * @author Iván González
 */
public class ModeloTablaCitasPendientes extends AbstractTableModel{
    
    private ListaEnlazada<ConsultaMedica> lista;

    public ListaEnlazada<ConsultaMedica> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<ConsultaMedica> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.getSize();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "Cedula";
            case 1: return "Nombre";
            case 2: return "Edad";
            case 3: return "Genero";
            case 4: return "Fecha";
            case 5: return "Hora";
            case 6: return "Estado";
            default: return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ConsultaMedica cm = null;
        try {
            cm = lista.obtener(rowIndex);
        } catch (Exception e) {
            System.out.println(e);
        }
        switch(columnIndex){
            case 0: return (cm != null) ? cm.getHistorialMedico().getPersona().getCedula(): "NO DEFINIDO";
            case 1: return (cm != null) ? cm.getHistorialMedico().getPersona().getNombre(): "NO DEFINIDO";
            case 2: return (cm != null) ? cm.getHistorialMedico().getPersona().getEdad(): "NO DEFINIDO";
            case 3: return (cm != null) ? cm.getHistorialMedico().getPersona().getGenero(): "NO DEFINIDO";
            case 4: return (cm != null) ? cm.getFecha(): "NO DEFINIDO";
            case 5: return (cm != null) ? cm.getHora(): "NO DEFINIDO";
            case 6: return (cm != null) ? cm.getEstadoTurno(): "NO DEFINIDO";
            default: return null;
        }
    }
    
    
    
    
    
    
    
    
    
}
