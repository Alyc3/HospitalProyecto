package hospital.vista.utilities;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Sirve para crear la configuración que se utilizará para las tablas
 */
public class VistaTabla extends JPanel {

    private JPanel panelMain;

    private int rows, cols;

    private JPanel[][] panelsCells;

    public VistaTabla(int rows, int cols) {

        this.rows = rows;
        this.cols = cols;

        setLayout(new BorderLayout());

        makePanelMain();

        add(panelMain, BorderLayout.CENTER);
    }

    private void makePanelMain() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridBagLayout());

        panelsCells = new JPanel[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                panelsCells[row][col] = new JPanel();
                Border insideBorder = BorderFactory.createEmptyBorder(5, 20, 5, 20);
                //Border outsideBorder = BorderFactory.createLineBorder(new Color(102, 148, 210), 1);
                Border outsideBorder = BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK);
                Border border = BorderFactory.createCompoundBorder(outsideBorder, insideBorder);
                panelsCells[row][col].setBorder(border);
                panelsCells[row][col].setLayout(new GridBagLayout());

                GridBagConstraints constraints = new GridBagConstraints();
                constraints.gridx = col;
                constraints.gridy = row;
                constraints.fill = GridBagConstraints.BOTH;

                panelMain.add(panelsCells[row][col], constraints);
            }
        }

        setRowColor(0, new Color(0, 76, 153));
        for (int row = 1; row < rows; row++) {
            setRowColor(row, Color.WHITE);
        }
    }

    public void setRowColor(int row, Color color) {
        for (int col = 0; col < cols; col++) {
            setCellColor(row, col, color);
        }
    }

    public void setColColor(int col, Color color) {
        for (int row = 0; row < rows; row++) {
            setCellColor(row, col, color);
        }
    }

    public void setCellColor(int row, int col, Color color) {
        panelsCells[row][col].setBackground(color);
    }

    public void setCellComponent(int row, int col, Component component) {
        panelsCells[row][col].removeAll();
        panelsCells[row][col].add(component);
    }
}
