package hospital.vista.servicio;

import hospital.controlador.lista.ListaEnlazada;
import hospital.modelo.ConsultaMedica;
import hospital.modelo.HistorialMedico;
import hospital.modelo.Persona;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.controlador.json.repositorio.RepositorioConsultaMedica;
import hospital.controlador.json.repositorio.RepositorioHistorialMedico;
import hospital.controlador.json.repositorio.RepositorioPersona;

public class ServicioListarPacientesPendientes {

    private RepositorioPersona repositorioPersona;
    private RepositorioHistorialMedico repositorioHistorialMedico;
    private RepositorioConsultaMedica repositorioConsultaMedica;

    public ServicioListarPacientesPendientes() {
        this.repositorioPersona = ManagerComponentes.repositorioPersona;
        this.repositorioHistorialMedico = ManagerComponentes.repositorioHistorialmedico;
        this.repositorioConsultaMedica = ManagerComponentes.repositorioConsultaMedica;
    }

    /**
     * Método que permite obtener la lista de todos los pacientes que tienen una
     * consulta médica pendiente
     *
     * @return
     * @throws ModeloException
     */
    public ListaEnlazada<Persona> execute() throws ModeloException {
        ListaEnlazada<ConsultaMedica> listConsultasMedicas = repositorioConsultaMedica.encontrarTodosConEstadoPendiente();
        ConsultaMedica[] consultasMedicas = new ConsultaMedica[listConsultasMedicas.size()];
        listConsultasMedicas.toArray(consultasMedicas);
        ListaEnlazada<Integer> listIdsHistorialesMedicos = new ListaEnlazada<Integer>();
        for (int i = 0; i < consultasMedicas.length; i++) {
            listIdsHistorialesMedicos.insertarFinal(consultasMedicas[i].getHistorialMedico().getId());
        }
        ListaEnlazada<HistorialMedico> listHistorialesMedicos = repositorioHistorialMedico.encontrarMuchosPorId(listIdsHistorialesMedicos);
        HistorialMedico[] historialesMedicos = new HistorialMedico[listIdsHistorialesMedicos.size()];
        listHistorialesMedicos.toArray(historialesMedicos);
        ListaEnlazada<Integer> listIdsPersonas = new ListaEnlazada<Integer>();
        for (int i = 0; i < historialesMedicos.length; i++) {
            listIdsPersonas.insertarFinal(historialesMedicos[i].getPersona().getId());
        }
        ListaEnlazada<Persona> listPersonas = repositorioPersona.encontrarMuchosPorId(listIdsPersonas);
        return listPersonas;
    }
}
