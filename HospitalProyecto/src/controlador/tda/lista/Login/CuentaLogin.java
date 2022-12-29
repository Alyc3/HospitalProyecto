/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.tda.lista.Login;

import controlador.tda.lista.exception.PosicionException;
import modelo.Cuenta;
import vista.InicioLogin;

/**
 *
 * @author johnny
 */
public class CuentaLogin {

    private static InicioLogin logUsuario = new InicioLogin();

    public static boolean validar(String usuario, String clave) throws PosicionException {
        if (obtener(usuario) != null) {
            Cuenta UCuenta = obtener(usuario);
            if (UCuenta.getUsuario().equals(usuario) && UCuenta.getClave().equals(clave)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean insertar(Cuenta cuenta) {
        return logUsuario.ingresar(cuenta);
    }

    public static boolean modificar(Cuenta cuenta) throws PosicionException {
        return logUsuario.modificar(cuenta);
    }

    public static boolean eliminar(String cuentas) throws PosicionException {
        return logUsuario.eliminar(cuentas);
    }

    public static Cuenta obtener(String cuentas) throws PosicionException {
        return logUsuario.obtener(cuentas);
    }

}
