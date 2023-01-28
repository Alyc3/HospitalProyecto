package hospital;

import hospital.modelo.Rol;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.vista.FrmMain;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws ModeloException, FileNotFoundException {

        ManagerComponentes.init();
        FrmMain hw = new FrmMain();
        hw.setVisible(true);
    }
}
