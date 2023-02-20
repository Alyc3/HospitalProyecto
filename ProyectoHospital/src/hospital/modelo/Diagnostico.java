package hospital.modelo;

import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;

public class Diagnostico {

    // Atributos
    private Integer id;
    private String recomendacion;
    private ConsultaMedica consultaMedica;

    /**
     * Método para validar la recomendacion de un diagnostico
     * Tiene un límite de 1000 caracteres
     * 
     * @throws ModeloException 
     */
    public void validarRecomendacion() throws ModeloException {
        if (recomendacion != null && recomendacion.length() > 1000) {
            throw new ModeloException("La recomendación es máximo de 1000 caracteres", ErrorType.ErrorValorNoValido);
        }
    }

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
