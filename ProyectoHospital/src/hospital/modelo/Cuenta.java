package hospital.modelo;

import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;

public class Cuenta {
    // Atributos.

    private Integer id;
    private String usuario;
    private String clave;
    private Persona persona;

    /**
     * Método para validar el usuario de una Cuenta
     * Es obligatorio
     * Tiene un límite de 50 caracteres
     * 
     * @throws ModeloException 
     */
    public void validarUsuario() throws ModeloException {
        if (usuario == null || usuario.equals("")) {
            throw new ModeloException("El usuario es obligatorio", ErrorType.ErrorArchivoRequerido);
        }
        if (usuario.length() > 50) {
            throw new ModeloException("El usuario debe tener máximo 50 caracteres", ErrorType.ErrorValorNoValido);
        }
    }

    /**
     * Método para validar la clave de una Cuenta
     * Es obligatoria
     * Tiene un límite de 50 caracteres
     * Debe contener al menos una letra minúscula
     * Debe contener al menos una letra mayúscula
     * Debe contener al menos un dígito
     * 
     * @throws ModeloException 
     */
    public void validarClave() throws ModeloException {
        if (clave == null || clave.equals("")) {
            throw new ModeloException("La clave es obligatoria", ErrorType.ErrorArchivoRequerido);
        }
        if (clave.length() < 8) {
            throw new ModeloException("La clave debe tener mínimo 8 caracteres", ErrorType.ErrorValorNoValido);
        }
        if (clave.length() > 50) {
            throw new ModeloException("La clave debe tener máximo 50 caracteres", ErrorType.ErrorValorNoValido);
        }

        boolean lowercase = false, uppercase = false, digit = false;
        for (int i = 0; i < clave.length(); i++) {
            if ('a' <= clave.charAt(i) && clave.charAt(i) <= 'z') {
                lowercase = true;
            } else if ('A' <= clave.charAt(i) && clave.charAt(i) <= 'Z') {
                uppercase = true;
            } else if ('0' <= clave.charAt(i) && clave.charAt(i) <= '9') {
                digit = true;
            }
        }

        if (!lowercase) {
            throw new ModeloException("La clave debe contener al menos una minúscula", ErrorType.ErrorValorNoValido);
        }
        if (!uppercase) {
            throw new ModeloException("La clave debe contener al menos una mayúscula", ErrorType.ErrorValorNoValido);
        }
        if (!digit) {
            throw new ModeloException("La clave debe contener al menos un dígito", ErrorType.ErrorValorNoValido);
        }
    }

    // Getters.
    public Integer getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    public Persona getPersona() {
        return persona;
    }

    // Setters.
    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
