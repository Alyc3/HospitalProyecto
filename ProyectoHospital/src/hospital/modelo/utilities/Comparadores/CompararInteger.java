package hospital.modelo.utilities.Comparadores;

import java.util.Comparator;

public class CompararInteger implements Comparator<Integer> {

    /**
     * Override de método para comparar 2 Integers
     * @param a
     * @param b
     * @return 
     */
    @Override
    public int compare(Integer a, Integer b) {
        if (a < b) {
            return -1;
        }
        if (b < a) {
            return 1;
        }
        return 0;
    }
}
