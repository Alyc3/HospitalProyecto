/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import javax.swing.JComboBox;
import modelo.enums.TipoExamen;

/**
 *
 * @author Iván González
 */
public class Utilidades {
    
    public static void cargarTipoExamen(JComboBox cbx){
        cbx.removeAllItems();
        for(TipoExamen tipo: TipoExamen.values()){
            cbx.addItem(tipo);
        }
    }
    
    public static TipoExamen obtenerTipoExamen(JComboBox cbx){
        return (TipoExamen)cbx.getSelectedItem();
    }
}
