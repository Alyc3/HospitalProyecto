/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.tda.lista.ListaEnlazada;
import controlador.tda.lista.exception.PosicionException;
import modelo.Cuenta;

/**
 *
 * @author johnny
 */
public class InicioLogin {

    private ListaEnlazada<Cuenta> listCuenta;

    public InicioLogin() {
        listCuenta = new ListaEnlazada<Cuenta>();
    }

    public int Buscar(String listUsuario) {
        int n = -1;
        for (int i = 0; i < listCuenta.getSize(); i++) {
            if (listUsuario.get(i).getCuenta().equals(listUsuario)) {
                n = i;
                break;
            }
        }
        return n;
    }

    public boolean ingresar(Cuenta listUsuario) {
        if (Buscar(listUsuario.getUsuario()) == -1) {
            listCuenta.insertarCabecera(listUsuario);
            return true;
        } else {
            return false;
        }
    }

    public boolean modificar(Cuenta listUsuario) throws PosicionException {
        if (Buscar(listUsuario.getUsuario()) != -1) {
            Cuenta usuariList = obtener(listUsuario.getUsuario());
            usuariList.setUsuario(listUsuario.getUsuario());
            usuariList.setClave(listUsuario.getClave());
            usuariList.setPersona(listUsuario.getPersona());
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminar(String listUsuario) throws PosicionException {
        if (Buscar(listUsuario) != -1) {
            listCuenta.eliminarDato(Buscar(listUsuario));
            return true;
        } else {
            return false;
        }
    }

    public Cuenta obtener(String listUsuario) throws PosicionException {
        if (Buscar(listUsuario) != -1) {
            //return listCuenta.get(Buscar(listUsuario));
            return listCuenta.obtenerDato(Buscar(listUsuario));
        } else {
            return null;
        }
    }
}