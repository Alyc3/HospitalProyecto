package hospital.modelo;

public class Cuenta {
    // Atributos.

    private Integer id;
    private String usuario;
    private String clave;
    private Persona persona;

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
