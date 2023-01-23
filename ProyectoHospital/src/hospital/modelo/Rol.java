package hospital.modelo;

public class Rol {
    // Atributos.

    private Integer id;
    private String nombre;

    public Rol(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters.
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    // Setters.
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Rol clone() {
        return new Rol(id, nombre);
    }
}
