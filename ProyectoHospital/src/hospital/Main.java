package hospital;

import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.vista.FrmMain;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws ModeloException, FileNotFoundException {

        try {
            ManagerComponentes.init();
            FrmMain frmMain = new FrmMain();
            frmMain.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
