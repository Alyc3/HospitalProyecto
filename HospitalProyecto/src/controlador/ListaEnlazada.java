/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.Listas.excepciones.ListaNullException;
import controlador.Listas.excepciones.PosicionNoEncontradaExcepcion;


/**
 *
 * @author ivangonzalez
 */
public class ListaEnlazada<E> {

    private NodoLista<E> cabecera;
    private Integer size;
    private ListaEnlazada<E> lista;

    public ListaEnlazada() {
        cabecera = null;
        size = 0;
    }

    public Boolean estaVacia() {
        return cabecera == null;
    }

//    private Integer tamanio(){
//        Integer tamanio = 0;
//        NodoLista<E> aux = cabecera;
//        while(aux != null){
//            tamanio++;
//            aux = aux.getSiguiente();
//        }
//        return tamanio;
//    }
    public void insertar(E dato) {
        NodoLista<E> nodo = new NodoLista<>(dato, null);
        if (estaVacia()) {
            this.cabecera = nodo;
        } else {
            NodoLista<E> aux = cabecera;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nodo);
        }
        size++;
    }

    public void insertarCabecera(E dato) {

        if (estaVacia()) {
            insertar(dato);
        } else {
            NodoLista<E> nodo = new NodoLista<>(dato, null);
            nodo.setSiguiente(cabecera);
            cabecera = nodo;
            size++;
        }
    }

    public void insertarPosicion(E dato, Integer pos) throws PosicionNoEncontradaExcepcion {
        if (estaVacia()) {
            insertar(dato);
        } else if (pos >= 0 && pos < size) {
            if (pos == (size - 1)) {
                insertar(dato);
            } else if (pos == 0) {
                insertarCabecera(dato);
            } else {
                NodoLista<E> nodo = new NodoLista(dato, null);
                NodoLista<E> aux = cabecera;
                for (int i = 0; i < (pos - 1); i++) {
                    aux = aux.getSiguiente();
                }
                NodoLista<E> siguiente = aux.getSiguiente();
                aux.setSiguiente(nodo);
                nodo.setSiguiente(siguiente);
                size++;
            }

        } else {
            throw new PosicionNoEncontradaExcepcion();
        }
    }

    public void imprimir() {
        System.out.println("-------------LISTA ENLAZADA---------------");
        NodoLista<E> aux = cabecera;
        while (aux != null) {
            System.out.print(aux.getDato().toString() + "\t");
            aux = aux.getSiguiente();
        }
        System.out.println("\n------------------------------------------");

    }

    public E obtener(Integer pos) throws ListaNullException, PosicionNoEncontradaExcepcion {

        if (!estaVacia()) {
            E dato = null;
            if (pos >= 0 && pos < size) {
                if (pos == 0) {
                    dato = cabecera.getDato();
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSiguiente();
                    }
                    dato = aux.getDato();
                }
            } else {
                throw new PosicionNoEncontradaExcepcion();
            }

            return dato;
        } else {
            throw new ListaNullException();
        }

//        return dato;
    }

    public void modificarDato(E dato, Integer pos) throws PosicionNoEncontradaExcepcion {
        if (estaVacia()) {
            insertar(dato);
        } else if (pos >= 0 && pos < size) {
            if (pos == 0) {
                cabecera.setDato(dato);
            } else {
                NodoLista<E> aux = cabecera;
                for (int i = 0; i < pos; i++) {
                    aux = aux.getSiguiente();
                }
                aux.setDato(dato);
            }
        } else {
            throw new PosicionNoEncontradaExcepcion();
        }
    }


public E eliminar(Integer pos) throws ListaNullException, PosicionNoEncontradaExcepcion{
        System.out.println("pos " + pos);
        if(!estaVacia()){
            E dato = null;
            if(pos >= 0 && pos < size){
                if(pos == 0){
                    dato = cabecera.getDato();
                    cabecera = cabecera.getSiguiente();
                    size--;
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos; i++) {
                         aux = aux.getSiguiente();
                    }
                    dato = aux.getDato();
                    NodoLista<E> proximo = aux.getSiguiente();
                    aux.setSiguiente(proximo.getSiguiente());
                    size--;
                }
            } else 
                throw new PosicionNoEncontradaExcepcion();
            
            return dato;
        } else throw new ListaNullException();
    }

    public NodoLista<E> getCabecera() {
        return cabecera;
    }

    public void setCabecera(NodoLista<E> cabecera) {
        this.cabecera = cabecera;
    }

    public Integer getSize() {
//        this.size = tamanio();
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public ListaEnlazada<E> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<E> lista) {
        this.lista = lista;
    }
    
    
    
    
}
