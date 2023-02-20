package hospital.modelo.utilities.Comparadores;

import hospital.modelo.Persona;
import java.util.Comparator;

public class CompararPersonaPorId implements Comparator<Persona> {

    /**
     * Override de método compare para comparar dos personas por medio de su id
     * @param a
     * @param b
     * @return 
     */
    @Override
    public int compare(Persona a, Persona b) {
        if (a.getId() < b.getId()) {
            return -1;
        }
        if (b.getId() < a.getId()) {
            return 1;
        }
        return 0;
    }
}
