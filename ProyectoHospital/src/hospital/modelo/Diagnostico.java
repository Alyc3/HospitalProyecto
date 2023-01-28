package hospital.modelo;

public class Diagnostico {

    // Atributos
    private Integer id;
    private String recomendacion;
    private ConsultaMedica consultaMedica;

    // Getters
    public Integer getId() {
        return id;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public ConsultaMedica getConsultaMedica() {
        return consultaMedica;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public void setConsultaMedica(ConsultaMedica consultaMedica) {
        this.consultaMedica = consultaMedica;
    }
}
