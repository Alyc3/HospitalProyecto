package hospital.modelo.utilities.Comparadores;

import hospital.modelo.ConsultaMedica;
import java.util.Comparator;

public class CompararConsultaPorFechaHora implements Comparator<ConsultaMedica> {

    /**
     * Override de método compare para comparar dos Consulta Médicas por medio 
     * de la Fecha y Hora
     * @param a
     * @param b
     * @return 
     */
    public int compare(ConsultaMedica a, ConsultaMedica b) {
        if (a.getFecha().isAfter(b.getFecha())) {
            return -1;
        }
        if (b.getFecha().isAfter(a.getFecha())) {
            return 1;
        }
        if (a.getHora().isAfter(b.getHora())) {
            return -1;
        }
        if (b.getHora().isAfter(a.getHora())) {
            return 1;
        }
        return 0;
    }
}
