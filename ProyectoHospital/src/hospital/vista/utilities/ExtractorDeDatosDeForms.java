package hospital.vista.utilities;

import hospital.modelo.excepciones.CampoNovalidoException;
import hospital.modelo.utilities.Tiempo.UtilidadesTiempo;
import java.time.DateTimeException;
import java.time.LocalDate;
import javax.swing.JComboBox;
import javax.swing.JTextField;


public class ExtractorDeDatosDeForms {

    /**
     * Método que retorna el contenido de un Text Field como String
     * @param fieldName
     * @param field
     * @return 
     */
    public String getStringFrom(String fieldName, JTextField field) {
        return field.getText();
    }
    
    /**
     * Método que retorna el contenido de un Text Field como Float
     * @param fieldName
     * @param field
     * @return
     * @throws CampoNovalidoException 
     */
    public static Float getFloatFrom(String fieldName, JTextField field) throws CampoNovalidoException {
        try {
            return Float.valueOf((field.getText()));
        } catch (NumberFormatException e) {
            throw new CampoNovalidoException(e, fieldName, field.getText());
        }
    }

    /**
     * Método que retorna el contenido de un Text Field como LocalDate
     * @param fieldName
     * @param fieldYear
     * @param fieldMonth
     * @param fieldDay
     * @return
     * @throws CampoNovalidoException 
     */
    public static LocalDate getLocalDateFrom(String fieldName, JComboBox fieldYear, JComboBox fieldMonth, JComboBox fieldDay) throws CampoNovalidoException {
        String yearStr = "", monthStr = "", dayStr = "";
        try {
            yearStr = fieldYear.getSelectedItem().toString();
            monthStr = fieldMonth.getSelectedItem().toString();
            dayStr = fieldDay.getSelectedItem().toString();
            int year = Integer.valueOf(yearStr);
            int month = UtilidadesTiempo.posicionMes(monthStr);
            int day = Integer.valueOf(dayStr);
            return LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new CampoNovalidoException(e, fieldName, yearStr + "-" + monthStr + "-" + dayStr);
        }
    }
}
