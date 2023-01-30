package hospital.servicio;

import hospital.modelo.Cuenta;
import hospital.modelo.HistorialMedico;
import hospital.modelo.Persona;
import hospital.modelo.excepciones.ModeloException;

public class Servicios {

    private ServicioLogin loginService;
    private ServicioRegistrarPersona registrarPersonaService;

    public Servicios() {
    }

    //Setters
    public void setLoginService(ServicioLogin loginService) {
        this.loginService = loginService;
    }

    public void setRegistrarPersonaService(ServicioRegistrarPersona registrarPersonaService) {
        this.registrarPersonaService = registrarPersonaService;
    }

    /**
     * Método para validar el logueo de un usuario por medio de su cuenta
     *
     * @param usuario
     * @param clave
     * @return
     * @throws ModeloException
     */
    public Cuenta login(String usuario, String clave) throws ModeloException {
        return loginService.execute(usuario, clave);
    }

    /**
     * Método para registrar a una nueva Persona
     *
     * @param cuenta
     * @param persona
     * @param historialMedico
     * @return
     * @throws ModeloException
     */
    public Cuenta registrarPersona(Cuenta cuenta, Persona persona, HistorialMedico historialMedico) throws ModeloException {
        return registrarPersonaService.execute(cuenta, persona, historialMedico);
    }
}
