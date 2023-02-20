package hospital.vista.utilities;

import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer.UIResource;

public class ComboBoxCellRenderer extends UIResource {

    /**
     * Método que permite agregar relleno a todas las celdas de un comboBox
     * 
     * @param list
     * @param value
     * @param index
     * @param isSelected
     * @param cellHasFocus
     * @return 
     */
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JComponent component = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        component.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        return component;
    }
}
