package hospital.modelo;

import hospital.modelo.enumeradores.TipoExamen;

public class Examen {

    // Atributos
    private TipoExamen tipoExamen;
    private String tiempoEntrega;
    private ConsultaMedica consultaMedica;

    // Getters
    public TipoExamen getTipoExamen() {
        return tipoExamen;
    }

    public String getTiempoEntrega() {
        return tiempoEntrega;
    }

    public ConsultaMedica getConsultaMedica() {
        return consultaMedica;
    }

    // Setters
    public void setTipoExamen(TipoExamen tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public void setTiempoEntrega(String tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public void setConsultaMedica(ConsultaMedica consultaMedica) {
        this.consultaMedica = consultaMedica;
    }
}
