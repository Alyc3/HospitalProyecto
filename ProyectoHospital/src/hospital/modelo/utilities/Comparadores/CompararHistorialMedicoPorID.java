package hospital.modelo.utilities.Comparadores;

import hospital.modelo.HistorialMedico;
import java.util.Comparator;

public class CompararHistorialMedicoPorID implements Comparator<HistorialMedico> {

    /**
     * Override de método compare para comparar dos Historiales Médicos por
     * medio de su ID
     *
     * @param a
     * @param b
     * @return
     */
    public int compare(HistorialMedico a, HistorialMedico b) {
        if (a.getId() < b.getId()) {
            return -1;
        }
        if (b.getId() < a.getId()) {
            return 1;
        }
        return 0;
    }
}
