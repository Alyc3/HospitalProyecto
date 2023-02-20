package hospital.vista.servicio;

import hospital.controlador.lista.ListaEnlazada;
import hospital.modelo.ConsultaMedica;
import hospital.modelo.Diagnostico;
import hospital.modelo.HistorialMedico;
import hospital.modelo.Persona;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.modelo.global.VariablesGlobales;
import hospital.controlador.json.repositorio.RepositorioConsultaMedica;
import hospital.controlador.json.repositorio.RepositorioDiagnostico;
import hospital.controlador.json.repositorio.RepositorioHistorialMedico;
import hospital.controlador.json.repositorio.RepositorioPersona;

public class ServicioAgendarConsultaMedica {

    private RepositorioPersona repositorioPersona;
    private RepositorioHistorialMedico repositorioHistorialMedico;
    private RepositorioConsultaMedica repositorioConsultaMedica;
    private RepositorioDiagnostico repositorioDiagnostico;

    public ServicioAgendarConsultaMedica() {
        this.repositorioPersona = ManagerComponentes.repositorioPersona;
        this.repositorioHistorialMedico = ManagerComponentes.repositorioHistorialmedico;
        this.repositorioConsultaMedica = ManagerComponentes.repositorioConsultaMedica;
        repositorioDiagnostico = ManagerComponentes.repositorioDiagnostico;
    }

    /**
     * Método para agendar consultas médicas, valida que la cedula y consultaMedica no sean nulos
     * Valida que la persona sea un paciente
     * 
     * @param cedula
     * @param consultaMedica
     * @return
     * @throws ModeloException 
     */
    public ConsultaMedica execute(String cedula, ConsultaMedica consultaMedica) throws ModeloException {
        if (cedula == null || cedula.equals("")) {
            throw new ModeloException("La cédula no puede ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        if (consultaMedica == null) {
            throw new ModeloException("La consulta médica no puede ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        Persona persona = repositorioPersona.encontrarPorCedula(cedula);
        if (persona == null) {
            throw new ModeloException("No se encontró una persona registrada con esa cédula", ErrorType.ErrorObjetoNoEncontrado);
        }
        if (!persona.getRol().equals(VariablesGlobales.ROL_PACIENTE)) {
            throw new ModeloException("La persona registrada con esa cédula no es un paciente", ErrorType.ErrorAccionNoPermitida);
        }
        HistorialMedico historialMedico = repositorioHistorialMedico.encontrarPorIdPersona(persona.getId());
        consultaMedica.validarFechaHora(true);
        boolean[] estadoConsultorios = new boolean[VariablesGlobales.NUM_CONSULTORIOS + 1];
        for (int i = 1; i < estadoConsultorios.length; i++) {
            estadoConsultorios[i] = true;
        }
        ListaEnlazada<ConsultaMedica> listConsultasMedicas = repositorioConsultaMedica.encontrarTodosConEstadoPendiente();
        ConsultaMedica[] consultasMedicas = new ConsultaMedica[listConsultasMedicas.size()];
        listConsultasMedicas.toArray(consultasMedicas);
        for (int i = 0; i < consultasMedicas.length; i++) {
            if (historialMedico.getId() == consultasMedicas[i].getHistorialMedico().getId()) {
                throw new ModeloException(
                        "La persona registrada con esa cédula ya tiene asociada una consulta médica pendiente",
                        ErrorType.ErrorAccionNoPermitida
                );
            }
        }
        for (int i = 0; i < consultasMedicas.length; i++) {
            if (consultaMedica.getFecha().equals(consultasMedicas[i].getFecha())) {
                if (consultaMedica.getHora().equals(consultasMedicas[i].getHora())) {
                    estadoConsultorios[consultasMedicas[i].getNumConsultorio()] = false;
                }
            }
        }
        int numConsultorio = -1;
        for (int i = 1; i < estadoConsultorios.length && numConsultorio == -1; i++) {
            if (estadoConsultorios[i]) {
                numConsultorio = i;
            }
        }
        if (numConsultorio == -1) {
            throw new ModeloException("No hay consultorio disponible en el horario seleccionado", ErrorType.ErrorRestriccionUnicidad);
        }
        consultaMedica.setHistorialMedico(historialMedico);
        consultaMedica.setEstado("PENDIENTE");
        consultaMedica.setNumConsultorio(numConsultorio);
        ConsultaMedica consultaMedicaNew = repositorioConsultaMedica.crear(consultaMedica);
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setRecomendacion("");
        diagnostico.setConsultaMedica(consultaMedicaNew);
        repositorioDiagnostico.crear(diagnostico);
        return consultaMedica;
    }
}
