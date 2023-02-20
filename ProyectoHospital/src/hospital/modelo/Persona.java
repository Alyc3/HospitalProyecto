package hospital.modelo;

import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;

public class Persona {
    // Atributos.

    private Integer id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private Integer edad;
    private String genero;
    private String correo;
    private String telefono;
    private Rol rol;

    /**
     * Método para validar la cedula de la persona
     * Es obligatoria
     * Debe tener una longitud de 10 digitos
     * Solo debe contener dígitos
     * 
     * @throws ModeloException 
     */
    public void validarCedula() throws ModeloException {
        if (cedula == null || cedula.equals("")) {
            throw new ModeloException("La cédula es obligatoria", ErrorType.ErrorArchivoRequerido);
        }
        if (cedula.length() != 10) {
            throw new ModeloException("La cédula debe tener 10 dígitos", ErrorType.ErrorValorNoValido);
        }
        for (int i = 0; i < cedula.length(); i++) {
            if (!('0' <= cedula.charAt(i) && cedula.charAt(i) <= '9')) {
                throw new ModeloException("La cédula solo debe contener dígitos", ErrorType.ErrorValorNoValido);
            }
        }
    }

    /**
     * Método para validar el nombre de la persona
     * Es obligatorio
     * Tiene un máximo de 50 caracteres
     * 
     * @throws ModeloException 
     */
    public void validarNombre() throws ModeloException {
        if (nombre == null || nombre.equals("")) {
            throw new ModeloException("El nombre es obligatorio", ErrorType.ErrorArchivoRequerido);
        }
        if (nombre.length() > 50) {
            throw new ModeloException("El nombre debe tener máximo 50 caracteres", ErrorType.ErrorValorNoValido);
        }
    }

    /**
     * Método para validar el apellido de la persona
     * Es obligatorio
     * Tiene un máximo de 50 caracteres
     * @throws ModeloException 
     */
    public void validarApellido() throws ModeloException {
        if (apellido == null || apellido.equals("")) {
            throw new ModeloException("El apellido es obligatorio", ErrorType.ErrorArchivoRequerido);
        }
        if (apellido.length() > 50) {
            throw new ModeloException("El apellido debe tener máximo 50 caracteres", ErrorType.ErrorValorNoValido);
        }
    }

    /**
     * Método para validar la dirección de la persona
     * Es obligatoria
     * Tiene un máximo de 100 caracteres
     * 
     * @throws ModeloException 
     */
    public void validarDireccion() throws ModeloException {
        if (direccion == null || direccion.equals("")) {
            throw new ModeloException("La dirección es obligatoria", ErrorType.ErrorArchivoRequerido);
        }
        if (direccion != null && direccion.length() > 100) {
            throw new ModeloException("La dirección debe tener máximo 100 caracteres", ErrorType.ErrorValorNoValido);
        }
    }

    /**
     * Método para validar la edad de la persona
     * Es obligatoria
     * 
     * @throws ModeloException 
     */
    public void validarEdad() throws ModeloException {
        if (edad == null) {
            throw new ModeloException("La edad es obligatoria", ErrorType.ErrorArchivoRequerido);
        }
    }

    /**
     * Método para validar el genero de la persona
     * Es obligatorio
     * Solo puede ser M(Masculino) o F (Femenino)
     * 
     * @throws ModeloException 
     */
    public void validarGenero() throws ModeloException {
        if (genero == null || genero.equals("")) {
            throw new ModeloException("El género es obligatorio", ErrorType.ErrorArchivoRequerido);
        }
        if (!(genero.equals("M") || genero.equals("F"))) {
            throw new ModeloException("El género debe ser M o F", ErrorType.ErrorValorNoValido);
        }
    }

    /**
     * Método para validar el correo de la persona
     * Es obligatorio
     * Tiene un máximo de 100 caracteres
     * 
     * @throws ModeloException 
     */
    public void validarCorreo() throws ModeloException {
        if (correo == null || correo.equals("")) {
            throw new ModeloException("El correo es obligatorio", ErrorType.ErrorArchivoRequerido);
        }
        if (correo.length() > 100) {
            throw new ModeloException("El correo debe tener máximo 100 caracteres", ErrorType.ErrorValorNoValido);
        }
    }

    /**
     * Método para validar el teléfono de la persona
     * Es obligatorio
     * Debe tener una longitud de 10 digitos
     * El primer caracter debe ser un cero
     * Debe estar conformado solo por digitos
     * 
     * @throws ModeloException 
     */
    public void validarTelefono() throws ModeloException {
        if (telefono == null || telefono.equals("")) {
            throw new ModeloException("El teléfono es obligatorio", ErrorType.ErrorArchivoRequerido);
        }
        if (telefono.length() != 10) {
            throw new ModeloException("El teléfono debe tener 10 caracteres", ErrorType.ErrorValorNoValido);
        }
        if (telefono.charAt(0) != '0') {
            throw new ModeloException("El primer carácter del teléfono debe ser un cero(0)", ErrorType.ErrorValorNoValido);
        }
        for (int i = 0; i < telefono.length(); i++) {
            if (!('0' <= telefono.charAt(i) && telefono.charAt(i) <= '9')) {
                throw new ModeloException("El teléfono debe estar conformado solo por dígitos", ErrorType.ErrorValorNoValido);
            }
        }
    }

    /**
     * Valida si el rol de la persona no fue definido
     * 
     * @throws ModeloException 
     */
    public void validarRol() throws ModeloException {
        if (rol == null) {
            throw new ModeloException("El rol no esta definido", ErrorType.ErrorArchivoRequerido);
        }
    }

    // Getters.
    public Integer getId() {
        return id;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public Rol getRol() {
        return rol;
    }

    // Setters.
    public void setId(Integer id) {
        this.id = id;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
