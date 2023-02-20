package hospital.vista;

import hospital.modelo.Rol;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;

public class FrmMain extends JFrame {

    private FrmHome homeView;
//    private FrmLogin loginView;
//    private FrmSignup signupView;
    //private PacienteMainView pacienteMainView;
    //privateMedicoMainView medicoMainView;

    // main window
    public FrmMain() {
        super("Administracion Hospital");

        homeView = new FrmHome(this);
//        loginView = new FrmLogin(this);
//        signupView = new FrmSignup(this);
        //pacienteMainView = new PacienteMainView(this);
        //medicoMainView = new MedicoMainView(this);

        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        homeView.show();

        /*
		Container cp = window.getContentPane();
		cp.removeAll();
		cp.repaint();
		System.out.println("medico");

		cp.setLayout(new FlowLayout());
         */
        //clear();
        setLocationRelativeTo(this);
    }

    public void clear() {
        Container cp = getContentPane();
        cp.removeAll();
        cp.repaint();
    }

    public void loadHomeView() {
        homeView.show();
    }

//    public void loadLoginView(Rol rol) {
//        loginView.show(rol);
//    }
//
//    public void loadSignupView(Rol rol) {
//        signupView.show(rol);
//    }

    public void loadPacienteMainView() {
        //pacienteMainView.show();
    }

    public void loadMedicoMainView() {
        //medicoMainView.show();		
    }
}
