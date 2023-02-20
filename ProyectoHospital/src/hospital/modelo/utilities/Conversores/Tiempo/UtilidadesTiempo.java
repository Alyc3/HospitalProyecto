package hospital.modelo.utilities.Tiempo;

public class UtilidadesTiempo {

    private static String[] months_sp_up = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};

    /**
     * Método que retorna un arreglo de cadenas de los años desde-hasta
     * @param from
     * @param to
     * @return 
     */
    public static String[] getAniosDesdeHasta(int from, int to) {
        String[] years = new String[to + 1 - from];
        for (int i = from; i <= to; i++) {
            years[i - from] = String.valueOf(i);
        }
        return years;
    }

    /**
     * Método que retorna los nombres de los meses en español y en mayus
     * @return 
     */
    public static String[] getMesesEspaniol() {
        String[] months = new String[12];
        for (int i = 0; i < months_sp_up.length; i++) {
            months[i] = months_sp_up[i];
        }
        return months;
    }

    /**
     * Método que retorna los números del 1 al 31, representando la cantidad 
     * maxima de dias de un mes
     * @return 
     */
    public static String[] getMaximoNumDiasMes() {
        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) {
            days[i - 1] = String.valueOf(i);
        }
        return days;
    }

    /**
     * Método que retorna la posición que ocupa el mes indicado
     * Si no corresponde con el nombre de algun mes, retorna 0
     * @param month
     * @return 
     */
    public static int posicionMes(String month) {
        for (int i = 0; i < months_sp_up.length; i++) {
            if (months_sp_up[i].equals(month)) {
                return i + 1;
            }
        }
        return 0;
    }
}
