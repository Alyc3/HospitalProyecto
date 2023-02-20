package hospital.vista.utilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * Contiene los métodos que permiten crear componentes con los estilos
 * específicos a ser usados
 *
 */
public class CrearFormularios {

    private static final Color colorPrincipal = new Color(0, 76, 153);
    private static final Color colorFondo = Color.WHITE;
    private static final Color colorTransparente = new Color(0, 0, 0, 0);
    private static final Color colorCampoNombre = new Color(89, 103, 128);
    private static final Color colorCampoError = Color.RED;
    private static final Color colorCampoBorde = new Color(102, 148, 210);
    private static final Color colorCampoNoEditable = new Color(238, 238, 238);

    /**
     * Método para obtener una etiqueta con el estilo de título
     *
     * @param title
     * @return
     */
    public static JLabel crearTituloLabel(String title) {
        JLabel label = new JLabel(title);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        label.setForeground(colorPrincipal);
        return label;
    }

    /**
     * Método para obtener una etiqueta con el estilo del subtitulo
     *
     * @param subtitle
     * @return
     */
    public static JLabel crearSubtitulolabel(String subtitle) {
        JLabel label = new JLabel(subtitle);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        label.setForeground(colorPrincipal);
        return label;
    }

    /**
     * Método para obtener un panel para almacenar varios campos
     *
     * @return
     */
    public static JPanel crearPanelMultiCampo() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(colorTransparente);
        return panel;
    }

    /**
     * Método para obtener un panel almacenando los componentes correspondientes
     *
     * @param labelNombre
     * @param field
     * @param labelError
     * @return
     */
    public static JPanel crearPanelCampo(JLabel labelNombre, JComponent field, JLabel labelError) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(colorFondo);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        panel.add(labelNombre, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        panel.add(labelError, BorderLayout.SOUTH);
        return panel;
    }

    /**
     * Método para obtener una etiqueta conteniendo el nombre de un campo
     *
     * @param text
     * @return
     */
    public static JLabel crearEtiquetaDesdeCampo(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 11));
        label.setForeground(colorCampoNombre);
        return label;
    }

    /**
     * Método para obtener una etiqueta conteniendo el mensaje de error sobre un
     * campo
     *
     * @param text
     * @return
     */
    public static JLabel crearEtiquetaErrDesdeCampo(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
        label.setForeground(colorCampoError);
        return label;
    }

    /**
     * Método para obtener un campo de texto
     *
     * @param cols
     * @return
     */
    public static JTextField crearTextField(int cols) {
        JTextField textField = new JTextField(cols);
        Border insideBorder = BorderFactory.createEmptyBorder(8, 10, 8, 10);
        Border outsideBorder = BorderFactory.createLineBorder(colorCampoBorde, 1);
        textField.setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        return textField;
    }

    /**
     * Método para obtener un campo de texto, y a la vez puede indicar si el
     * campo de texto será edtiable
     *
     * @param cols
     * @param editable
     * @return
     */
    public static JTextField crearTextField(int cols, boolean editable) {
        JTextField textField = CrearFormularios.crearTextField(cols);
        textField.setEditable(editable);
        if (!editable) {
            textField.setBackground(colorCampoNoEditable);
        }
        return textField;
    }

    /**
     * Método para obtener un campo de texto de contraseña
     *
     * @param cols
     * @return
     */
    public static JPasswordField crearPasswordFiled(int cols) {
        JPasswordField passwordField = new JPasswordField(cols);
        Border insideBorder = BorderFactory.createEmptyBorder(8, 10, 8, 10);
        Border outsideBorder = BorderFactory.createLineBorder(colorCampoBorde, 1);
        passwordField.setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        return passwordField;
    }

    /**
     * Método para obtener un área de texto
     *
     * @param rows
     * @param cols
     * @param paintBorder
     * @return
     */
    public static JTextArea crearTextArea(int rows, int cols, boolean paintBorder) {
        JTextArea textArea = new JTextArea(rows, cols);
        Border insideBorder = BorderFactory.createEmptyBorder(8, 10, 8, 10);
        Border outsideBorder = BorderFactory.createLineBorder(colorCampoBorde, 1);
        Border border = BorderFactory.createCompoundBorder(outsideBorder, insideBorder);
        if (paintBorder) {
            textArea.setBorder(border);
        } else {
            textArea.setBorder(insideBorder);
        }
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }

    /**
     * Método para obtener un área de texto
     *
     * @param rows
     * @param cols
     * @param paintBorder
     * @param editable
     * @return
     */
    public static JTextArea crearTextArea(int rows, int cols, boolean paintBorder, boolean editable) {
        JTextArea textArea = CrearFormularios.crearTextArea(rows, cols, paintBorder);
        textArea.setEditable(editable);
        if (!editable) {
            textArea.setBackground(colorCampoNoEditable);
        }
        return textArea;
    }

    /**
     * Método para obtener un combo box
     *
     * @param width
     * @param height
     * @param data
     * @return
     */
    public static JComboBox<String> crearComboBox(int width, int height, String[] data) {
        JComboBox<String> comboBox = new JComboBox<String>(data);
        comboBox.setPreferredSize(new Dimension(width, height));
        comboBox.setBackground(Color.WHITE);
        comboBox.setRenderer(new ComboBoxCellRenderer());
        return comboBox;
    }

    /**
     * Método para obtener un combo box, a la vez que indica si el combo box
     * será editable o no
     *
     * @param width
     * @param height
     * @param data
     * @param enabled
     * @return
     */
    public static JComboBox<String> crearComboBox(int width, int height, String[] data, boolean enabled) {
        JComboBox<String> comboBox = CrearFormularios.crearComboBox(width, height, data);
        comboBox.setEnabled(enabled);
        if (!enabled) {
            comboBox.setBackground(colorCampoNoEditable);
        }
        return comboBox;
    }

    /**
     * Método para obtener un botón
     *
     * @param text
     * @param width
     * @param height
     * @param listener
     * @return
     */
    public static JButton crearButton(String text, int width, int height, ActionListener listener) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(colorPrincipal);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        button.addActionListener(listener);
        return button;
    }

    /**
     * Método para obtener un botón que simula un link
     *
     * @param text
     * @param listener
     * @return
     */
    public static JButton crearLink(String text, ActionListener listener) {
        JButton link = new JButton(text);
        link.setContentAreaFilled(false);
        link.setForeground(colorPrincipal);
        link.setBorderPainted(false);
        link.setFocusPainted(false);
        link.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        link.addActionListener(listener);
        return link;
    }

    /**
     * Método para obtener un ScrollPane
     *
     * @param component
     * @return
     */
    public static JScrollPane crearScrollPane(Component component) {
        JScrollPane scrollPane = new JScrollPane(component);
        scrollPane.setBorder(BorderFactory.createLineBorder(colorCampoBorde, 1));
        return scrollPane;
    }
}
