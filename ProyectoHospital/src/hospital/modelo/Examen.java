package hospital.modelo;

import hospital.modelo.enumeradores.TipoExamen;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;

public class Examen {

    // Atributos
    private TipoExamen tipoExamen;
    private String tiempoEntrega;
    private ConsultaMedica consultaMedica;

    /**
     * Método para validar el Tipo de Examen de un examen medico
     * Es obligatorio
     * 
     * @throws ModeloException 
     */
    public void validarTipoExamen() throws ModeloException {
        if (tipoExamen == null) {
            throw new ModeloException("El tipo de examen es obligatorio", ErrorType.ErrorArchivoRequerido);
        }
    }

    /**
     * Método para validar el tiempo de entrega de un Examen
     * Es obligatorio
     * Tiene una longitud máxima de 30 caracteres
     * 
     * @throws ModeloException 
     */
    public void validarTiempoEntrega() throws ModeloException {
        if (tiempoEntrega == null || tiempoEntrega.equals("")) {
            throw new ModeloException("El tiempo de entrega es obligatorio", ErrorType.ErrorArchivoRequerido);
        }
        if (tiempoEntrega.length() > 30) {
            throw new ModeloException("El tiempo de entrega debe ser definido usando máximo 30 caracteres", ErrorType.ErrorValorNoValido);
        }
    }

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
