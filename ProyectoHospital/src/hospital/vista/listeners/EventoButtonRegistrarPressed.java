package hospital.vista.listeners;

import hospital.modelo.Cuenta;
import hospital.modelo.HistorialMedico;
import hospital.modelo.Medico;
import hospital.modelo.Persona;
import hospital.modelo.excepciones.ModeloException;
import hospital.modelo.global.ManagerComponentes;
import hospital.vista.FrmSignup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoButtonRegistrarPressed implements ActionListener {

    private FrmSignup view;

    public EventoButtonRegistrarPressed(FrmSignup view) {
        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
//        System.out.println("Registrarse");
//
//        Cuenta cuenta = view.getCuenta();
//        System.out.println(cuenta.getUsuario() + " " + cuenta.getClave());
//
//        Persona persona = view.getPersona();
//        System.out.println("Cedula: " + persona.getCedula());
//        System.out.println("Nombre: " + persona.getNombre());
//        System.out.println("Apellido: " + persona.getApellido());
//        System.out.println("Correo: " + persona.getCorreo());
//        System.out.println("Telefono: " + persona.getTelefono());
//        System.out.println("Direccion: " + persona.getDireccion());
//        System.out.println("Genero: " + persona.getGenero());
//        System.out.println("Rol: " + persona.getRol().getNombre());
//
//        if (Medico.class.isInstance(persona)) {
//            System.out.println("Especialidad: " + ((Medico) persona).getEspecialidad());
//        }
//
//        HistorialMedico hs = view.getHistorialMedico();
//        System.out.println(hs.getFechaNacimiento());
//
//        try {
//            Cuenta cuenta2 = ManagerComponentes.services.registrarPersona(cuenta, persona, hs);
//            view.showResultadoRegistro(true, "El registro ");
//        } catch (ModeloException exc) {
//            view.showResultadoRegistro(false, exc.getMessage());
//            //	System.out.println(exc.getErrorType());
//        }
        /*catch(Exception e){
			System.out.println(e);
		}*/

        //	Rol rol = new Rol(1, "Rol-Medico");
        //	view.loadLoginView(rol);
    }
}
