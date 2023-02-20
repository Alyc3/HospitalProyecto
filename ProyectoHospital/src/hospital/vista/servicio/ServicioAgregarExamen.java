package hospital.vista.servicio;

import hospital.controlador.lista.ListaEnlazada;
import hospital.modelo.ConsultaMedica;
import hospital.modelo.Examen;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.controlador.json.repositorio.RepositorioConsultaMedica;
import hospital.controlador.json.repositorio.RepositorioExamen;

public class ServicioAgregarExamen {

    private RepositorioConsultaMedica repositorioConsultaMedica;
    private RepositorioExamen repositorioExamen;

    public ServicioAgregarExamen() {
        this.repositorioConsultaMedica = ManagerComponentes.repositorioConsultaMedica;
        this.repositorioExamen = ManagerComponentes.repositorioExamen;
    }

    /**
     * Método que permite agregar un nuevo Examen
     * Valida que la id de la consulta a la que se la agrega no sea nulo
     * Valida que el Examen no sea nulo
     * 
     * @param idConsultaMedica
     * @param examen
     * @return
     * @throws ModeloException 
     */
    public Examen execute(Integer idConsultaMedica, Examen examen) throws ModeloException {
        if (idConsultaMedica == null) {
            throw new ModeloException("El id de la consulta médica no puede ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        if (examen == null) {
            throw new ModeloException("El examen no puede ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        ConsultaMedica consultaMedica = repositorioConsultaMedica.encontrarPorId(idConsultaMedica);
        if (consultaMedica == null) {
            throw new ModeloException("No se encontró una consulta médica con ese id", ErrorType.ErrorObjetoNoEncontrado);
        }
        examen.validarTipoExamen();
        examen.validarTiempoEntrega();
        ListaEnlazada<Examen> listExamenes = repositorioExamen.encontrarTodoPorIdConsultaMedica(idConsultaMedica);
        Examen[] examenes = new Examen[listExamenes.size()];
        listExamenes.toArray(examenes);
        for (int i = 0; i < examenes.length; i++) {
            if (examenes[i].getConsultaMedica() != null && idConsultaMedica == examenes[i].getConsultaMedica().getId()) {
                if (examen.getTipoExamen().equals(examenes[i].getTipoExamen())) {
                    throw new ModeloException(
                            "Ya existe un examen del mismo tipo asociado a la misma consulta médica",
                            ErrorType.ErrorAccionNoPermitida
                    );
                }
            }
        }
        examen.setConsultaMedica(consultaMedica);
        return repositorioExamen.crear(examen);
    }
}
