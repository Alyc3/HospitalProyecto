package hospital.vista.servicio;

import hospital.modelo.Persona;
import hospital.modelo.excepciones.ErrorType;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.controlador.json.repositorio.RepositorioPersona;

public class ServicioVerInformacionPersona {

    private RepositorioPersona repositorioPersona;

    public ServicioVerInformacionPersona() {
        repositorioPersona = ManagerComponentes.repositorioPersona;
    }

    /**
     * Método para obtener la información de la persona asociada a un
     * determinado id
     *
     * @param idPersona
     * @return
     * @throws ModeloException
     */
    public Persona execute(Integer idPersona) throws ModeloException {
        if (idPersona == null) {
            throw new ModeloException("El id de la persona no debe ser null", ErrorType.ErrorValorNuloNoPermitido);
        }
        Persona persona = repositorioPersona.encontrarPorId(idPersona);
        if (persona == null) {
            throw new ModeloException("No se encontró una persona con ese id", ErrorType.ErrorObjetoNoEncontrado);
        }
        return persona;
    }
}
