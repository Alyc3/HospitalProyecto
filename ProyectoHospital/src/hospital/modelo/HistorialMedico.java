package hospital.modelo;

import hospital.modelo.enumeradores.TipoSanguineo;

public class HistorialMedico {

    // Atributos
    private Integer id;
    private String fechaNacimiento;
    private Float peso;
    private Float estatura;
    private String antecedentes;
    private String alergias;
    private String tratamientosVigentes;
    private String antecedentesFamiliares;
    private TipoSanguineo tipoSanguineo;
    private Persona persona;

    // Getters
    public Integer getId() {
        return id;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Float getPeso() {
        return peso;
    }

    public Float getEstatura() {
        return estatura;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public String getAlergias() {
        return alergias;
    }

    public String getTratamientosVigentes() {
        return tratamientosVigentes;
    }

    public String getAntecedentesFamiliares() {
        return antecedentesFamiliares;
    }

    public TipoSanguineo getTipoSanguineo() {
        return tipoSanguineo;
    }

    public Persona getPersona() {
        return persona;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public void setEstatura(Float estatura) {
        this.estatura = estatura;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public void setTratamientosVigentes(String tratamientosVigentes) {
        this.tratamientosVigentes = tratamientosVigentes;
    }

    public void setAntecedentesFamiliares(String antecedentesFamiliares) {
        this.antecedentesFamiliares = antecedentesFamiliares;
    }

    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
