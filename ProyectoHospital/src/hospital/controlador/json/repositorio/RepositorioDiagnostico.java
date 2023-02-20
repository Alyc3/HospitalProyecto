package hospital.controlador.json.repositorio;

import hospital.modelo.Diagnostico;
import hospital.modelo.excepciones.ModeloException;

public interface RepositorioDiagnostico {

    /**
     * Método para crear un Diagnostico Medico
     * @param diagnostico
     * @return
     * @throws ModeloException 
     */
    public Diagnostico crear(Diagnostico diagnostico) throws ModeloException;

    /**
     * Método para actualizar un Diagnóstico Médico
     * @param diagnostico
     * @return
     * @throws ModeloException 
     */
    public Diagnostico actualizar(Diagnostico diagnostico) throws ModeloException;

    /**
     * Método para encontrar un Diagnostico médico por medio del id de una consulta
     * @param idConsultaMedica
     * @return
     * @throws ModeloException 
     */
    public Diagnostico encontrarPorIdConsultaMedica(Integer idConsultaMedica) throws ModeloException;
}
