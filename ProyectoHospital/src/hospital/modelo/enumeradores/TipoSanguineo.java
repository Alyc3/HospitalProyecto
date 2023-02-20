package hospital.modelo.enumeradores;

public enum TipoSanguineo {

    APOS("A", "+"),
    OPOS("O", "+"),
    BPOS("B", "+"),
    ABPOS("AB", "+"),
    ANEG("A", "-"),
    ONEG("O", "-"),
    BNEG("B", "-"),
    ABNEG("AB", "-");

    private final String grupo;
    private final String rh;

    private TipoSanguineo(String grupo, String rh) {
        this.grupo = grupo;
        this.rh = rh;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getRH() {
        return rh;
    }

    public String toString() {
        return grupo + rh;
    }
}
