package hospital.modelo;

public class Medicina {

    // Atributos
    private String nombre;
    private Integer unidad;
    private String pauta;
    private ConsultaMedica consultaMedica;

    // Getters
    public String getNombre() {
        return nombre;
    }

    public Integer getUnidad() {
        return unidad;
    }

    public String getPauta() {
        return pauta;
    }

    public ConsultaMedica getConsultaMedica() {
        return consultaMedica;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUnidad(Integer unidad) {
        this.unidad = unidad;
    }

    public void setPauta(String pauta) {
        this.pauta = pauta;
    }

    public void setConsultaMedica(ConsultaMedica consultaMedica) {
        this.consultaMedica = consultaMedica;
    }
}
