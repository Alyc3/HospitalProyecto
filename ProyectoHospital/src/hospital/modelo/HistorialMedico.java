package hospital.modelo;

import hospital.modelo.enumeradores.TipoSanguineo;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

    /**
     * Método para validar la Fecha de Nacimiento del Historial Medico
     * Es obligatoria
     * Debe ser una fecha posterior o igual a 1900-01-01
     * Debe ser una fecha inferior a la actual
     * Debe tener el formato aaaa-mm-dd
     * @throws ModeloException 
     */
    public void validarFechaNacimiento() throws ModeloException {
        if (fechaNacimiento == null || fechaNacimiento.equals("")) {
            throw new ModeloException("La fecha de nacimiento es obligatoria", ErrorType.ErrorArchivoRequerido);
        }

        try {
            LocalDate date = LocalDate.parse(fechaNacimiento);
            LocalDate d1 = LocalDate.parse("1900-01-01");
            LocalDate d2 = LocalDate.now();

            if (date.isBefore(d1)) {
                throw new ModeloException(
                        "La fecha de nacimiento debe estar entre " + d1.toString() + " y " + d2.toString(),
                        ErrorType.ErrorValorNoPermitido
                );
            }
            if (date.isAfter(d2)) {
                throw new ModeloException(
                        "La fecha de nacimiento debe estar entre " + d1.toString() + " y " + d2.toString(),
                        ErrorType.ErrorValorNoPermitido
                );
            }
        } catch (DateTimeParseException e) {
            throw new ModeloException(
                    "La fecha de nacimiento debe tener un formato aaaa-mm-dd y representar una fecha válida",
                    ErrorType.ErrorValorNoValido
            );
        }
    }

    /**
     * Método para validar el peso de un historial medico
     * Es obligatorio
     * No debe ser negativo
     * Debe ser menor a 1000
     * 
     * @throws ModeloException 
     */
    public void validarPeso() throws ModeloException {
        if (peso == null || peso.equals("")) {
            throw new ModeloException("El peso es obligatorio", ErrorType.ErrorArchivoRequerido);
        }
        if (peso != null && !(0 <= peso && peso < 1000.00)) {
            throw new ModeloException("El peso no debe ser negativo y debe ser menor a 1000 KG", ErrorType.ErrorValorNoValido);
        }
    }

    /**
     * Método para validar la estatura de un Historial medico
     * Es obligatoria
     * No debe ser negativa
     * Debe ser menor a 3
     * 
     * @throws ModeloException 
     */
    public void validarEstatura() throws ModeloException {
        if (estatura == null || estatura.equals("")) {
            throw new ModeloException("La estatura es obligatoria", ErrorType.ErrorArchivoRequerido);
        }
        if (estatura != null && !(0 <= estatura && estatura < 3.00)) {
            throw new ModeloException("La estatura no debe ser negativa y debe ser menor a 3.00 metros", ErrorType.ErrorValorNoValido);
        }
    }

    /**
     * Método para validar los Antecendentes de un historial medico
     * Tiene un límite de 100 caracteres
     * 
     * @throws ModeloException 
     */
    public void validarAntecedentes() throws ModeloException {
        if (antecedentes != null && antecedentes.length() > 1000) {
            throw new ModeloException("Los antecedentes deben tener máximo 1000 caracteres", ErrorType.ErrorValorNoValido);
        }
    }

    /**
     * Método para validar las alergias de un historial medico
     * Tiene un límite de 1000 caracteres
     * 
     * @throws ModeloException 
     */
    public void validarAlergias() throws ModeloException {
        if (alergias != null && alergias.length() > 1000) {
            throw new ModeloException("Las alergías deben tener máximo 1000 caracteres", ErrorType.ErrorValorNoValido);
        }
    }

    /**
     * Método para validar los Tratamientos vigentes de un historial medico
     * Tiene un límite de 100 caracteres
     * 
     * @throws ModeloException 
     */
    public void validarTratamientosVigentes() throws ModeloException {
        if (tratamientosVigentes != null && tratamientosVigentes.length() > 1000) {
            throw new ModeloException("Los tratamientos vigentes deben tener máximo 1000 caracteres", ErrorType.ErrorValorNoValido);
        }
    }

    /**
     * Método para validar los Antecendentes familiares de un historial medico
     * Tiene un límite de 1000 caracteres
     * 
     * @throws ModeloException 
     */
    public void validarAntecedentesFamiliares() throws ModeloException {
        if (antecedentesFamiliares != null && antecedentesFamiliares.length() > 1000) {
            throw new ModeloException("Los antecedentes familiares deben tener máximo 1000 caracteres", ErrorType.ErrorValorNoValido);
        }
    }

    /**
     * Método para validar el Tipo Sanguineo de un historial medico
     * Es obligatorio
     * 
     * @throws ModeloException 
     */
    public void validarTipoSanguineo() throws ModeloException {
        if (tipoSanguineo == null) {
            throw new ModeloException("El tipo sanguíneo es obligatorio", ErrorType.ErrorArchivoRequerido);
        }
    }

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
