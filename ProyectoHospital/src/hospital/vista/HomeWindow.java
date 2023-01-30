package hospital.vista;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.*;

public class HomeWindow extends JFrame {
	
	public HomeWindow(){
		super("Administracion Hospital");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
	
		JLabel etiqueta = new JLabel("Usuario: ");
		JTextField field = new JTextField(20);
		JLabel etiqueta2 = new JLabel("Clave: ");
		JTextField field2 = new JTextField(20);
		cp.add(etiqueta);
		cp.add(field);
		cp.add(etiqueta2);
		cp.add(field2);
	}
}