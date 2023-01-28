package hospital.modelo.enumeradores;

public class EncontrarTipoSanguineo {

    private static TipoSanguineo[] tiposSanguineos = {
        TipoSanguineo.APOS,
        TipoSanguineo.OPOS,
        TipoSanguineo.BPOS,
        TipoSanguineo.ABPOS,
        TipoSanguineo.ANEG,
        TipoSanguineo.ONEG,
        TipoSanguineo.BNEG,
        TipoSanguineo.ABNEG
    };

    /**
     * Método para encontrar un TipoSanguineo cuyo toString() retorne un str
     * Si no hay coincidencias retornará null.
     * @param str
     * @return 
     */
    public static TipoSanguineo encontrar(String str) {
        for (int i = 0; i < tiposSanguineos.length; i++) {
            if (tiposSanguineos[i].toString().equals(str)) {
                return tiposSanguineos[i];
            }
        }
        return null;
    }
}
