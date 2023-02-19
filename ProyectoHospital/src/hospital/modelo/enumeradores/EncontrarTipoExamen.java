package hospital.modelo.enumeradores;

public class EncontrarTipoExamen {

    private static TipoExamen[] tiposExamenes = {
        TipoExamen.Electrolitos,
        TipoExamen.Heces,
        TipoExamen.Orina,
        TipoExamen.Hematologia
    };

    /**
     * Método para encontrar un TipoExamen cuyo toString retorna un str
     * Retornará null si ningún TipoExamen coincide
     * @param str
     * @return 
     */
    public static TipoExamen encontrar(String str) {
        for (int i = 0; i < tiposExamenes.length; i++) {
            if (tiposExamenes[i].toString().equals(str)) {
                return tiposExamenes[i];
            }
        }
        return null;
    }
}
