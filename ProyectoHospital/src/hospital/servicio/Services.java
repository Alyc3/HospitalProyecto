package hospital.servicio;

import hospital.modelo.Cuenta;
import hospital.modelo.excepciones.ModeloException;

public class Services {

    private LoginService loginService;

    public Services() {
    }

    public void SetLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
    
    /**
     * Método para validar el logueo de un usuario por medio de su cuenta
     * @param usuario
     * @param clave
     * @return
     * @throws ModeloException 
     */
    public Cuenta Login(String usuario, String clave) throws ModeloException {
        return loginService.execute(usuario, clave);
    }
}
