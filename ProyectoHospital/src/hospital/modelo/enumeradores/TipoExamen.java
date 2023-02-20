package hospital.modelo.enumeradores;

public enum TipoExamen {

    Electrolitos("Electrolitos"),
    Heces("Heces"),
    Orina("Orina"),
    Hematologia("Hematología");

    private final String name;

    private TipoExamen(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
