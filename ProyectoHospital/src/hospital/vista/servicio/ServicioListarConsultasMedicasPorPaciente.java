package hospital.vista.servicio;

import hospital.controlador.lista.ListaEnlazada;
import hospital.controlador.lista.Ordenamiento;
import hospital.modelo.ConsultaMedica;
import hospital.modelo.HistorialMedico;
import hospital.modelo.Persona;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.modelo.global.VariablesGlobales;
import hospital.modelo.utilities.Comparadores.CompararConsultaPorFechaHora;
import hospital.controlador.json.repositorio.RepositorioConsultaMedica;
import hospital.controlador.json.repositorio.RepositorioHistorialMedico;
import hospital.controlador.json.repositorio.RepositorioPersona;

public class ServicioListarConsultasMedicasPorPaciente {

    private RepositorioPersona repositorioPersona;
    private RepositorioHistorialMedico repositorioHistorialMedico;
    private RepositorioConsultaMedica repositorioConsultaMedica;

    public ServicioListarConsultasMedicasPorPaciente() {
        this.repositorioPersona = ManagerComponentes.repositorioPersona;
        this.repositorioHistorialMedico = ManagerComponentes.repositorioHistorialmedico;
        this.repositorioConsultaMedica = ManagerComponentes.repositorioConsultaMedica;
    }

    /**
     * Método que permite obtener una lista de las últimas 5 consultas médicas
     * creadas por un paciente
     *
     * @param idPaciente
     * @return
     * @throws ModeloException
     */
    public ListaEnlazada<ConsultaMedica> execute(Integer idPaciente) throws ModeloException {
        if (idPaciente == null) {
            throw new ModeloException("El id del paciente no debe ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        Persona persona = repositorioPersona.encontrarPorId(idPaciente);
        if (persona == null) {
            throw new ModeloException("No hay una persona registrada con ese id", ErrorType.ErrorObjetoNoEncontrado);
        }
        if (!persona.getRol().equals(VariablesGlobales.ROL_PACIENTE)) {
            throw new ModeloException("El id no corresponde al de un paciente", ErrorType.ErrorAccionNoPermitida);
        }
        HistorialMedico historialMedico = repositorioHistorialMedico.encontrarPorIdPersona(persona.getId());
        ListaEnlazada<ConsultaMedica> listConsultasMedicas = repositorioConsultaMedica.encontrarTodosPorIdHistorialMedico(historialMedico.getId());
        ConsultaMedica[] consultasMedicas = new ConsultaMedica[listConsultasMedicas.size()];
        listConsultasMedicas.toArray(consultasMedicas);
        Ordenamiento.mergeSort(consultasMedicas, new CompararConsultaPorFechaHora());
        ConsultaMedica consultaPendiente = null;
        for (int i = 0; i < consultasMedicas.length && consultaPendiente == null; i++) {
            if (consultasMedicas[i].getEstado().equals("PENDIENTE")) {
                consultaPendiente = consultasMedicas[i];
            }
        }
        ListaEnlazada<ConsultaMedica> ultimasConsultasMedicas = new ListaEnlazada<ConsultaMedica>();
        if (consultaPendiente != null) {
            ultimasConsultasMedicas.insertarFinal(consultaPendiente);
        }
        for (int i = 0; i < consultasMedicas.length && ultimasConsultasMedicas.size() < 5; i++) {
            if (consultaPendiente == null || consultasMedicas[i].getId() != consultaPendiente.getId()) {
                ultimasConsultasMedicas.insertarFinal(consultasMedicas[i]);
            }
        }
        return ultimasConsultasMedicas;
    }
}
