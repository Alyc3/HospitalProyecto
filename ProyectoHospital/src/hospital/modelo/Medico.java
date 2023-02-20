package hospital.modelo;

import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;

public class Medico extends Persona {
    // Atributos.

    private String especialidad;

    /**
     * Método para validar la especialidad del medico
     * Es obligatoria
     * Tiene un máximo de 100 caracteres
     * 
     * @throws ModeloException 
     */
    public void validarEspecialidad() throws ModeloException {
        if (especialidad == null || especialidad.equals("")) {
            throw new ModeloException("La especialidad es obligatoria", ErrorType.ErrorArchivoRequerido);
        }
        if (especialidad != null && especialidad.length() > 100) {
            throw new ModeloException("La especialidad debe tener máximo 100 caracteres", ErrorType.ErrorValorNoValido);
        }
    }

    // Getters.
    public String getEspecialidad() {
        return especialidad;
    }

    // Setters.
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
