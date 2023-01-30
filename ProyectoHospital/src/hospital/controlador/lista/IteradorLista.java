package hospital.controlador.lista;

public class IteradorLista<T> {

    private ListaEnlazada<T> lista;
    private Nodo<T> nodo;

    protected IteradorLista(ListaEnlazada<T> lista, Nodo<T> nodo) {
        this.lista = lista;
        this.nodo = nodo;
    }

    public boolean isInvalid() {
        if (nodo == null) {
            return true;
        }
        if (nodo.previo == null || nodo.siguiente == null) {
            return true;
        }
        return false;
    }

    public void prev() throws IllegalStateException {
        if (isInvalid()) {
            throw new IllegalStateException("this is an invalid iterator");
        }
        nodo = nodo.previo;
    }

    public void next() throws IllegalStateException {
        if (isInvalid()) {
            throw new IllegalStateException("this is an invalid iterator");
        }
        nodo = nodo.siguiente;
    }

    public T get() throws IllegalStateException {
        if (isInvalid()) {
            throw new IllegalStateException("this is an invalid iterator");
        }
        return nodo.valor;
    }

    public void set(T valor) throws IllegalStateException {
        if (isInvalid()) {
            throw new IllegalStateException("this is an invalid iterator");
        }
        nodo.valor = valor;
    }

    public void add(T value) {
        if (isInvalid()) {
            throw new IllegalStateException("this is an invalid iterator");
        }
        Nodo<T> nodo2 = new Nodo<T>(value, null, null);
        nodo2.previo = nodo.previo;
        nodo2.siguiente = nodo;
        nodo.previo.siguiente = nodo2;
        nodo.previo = nodo2;
        if (nodo == lista.cabecera) {
            nodo = nodo2;
            lista.cabecera = nodo;
        } else {
            nodo = nodo2;
        }
        lista.size++;
    }

    public void remove() {
        if (isInvalid()) {
            throw new IllegalStateException("this is an invalid iterator");
        }
        if (nodo.siguiente == nodo) { // size == 1
            nodo.previo = null;
            nodo.siguiente = null;
            nodo = null;
            lista.cabecera = null;
        } else {
            Nodo<T> p1 = nodo.previo;
            Nodo<T> p2 = nodo.siguiente;
            p1.siguiente = p2;
            p2.previo = p1;
            nodo.previo = null;
            nodo.siguiente = null;
            if (nodo == lista.cabecera) {
                nodo = p2;
                lista.cabecera = nodo;
            } else {
                nodo = p2;
            }
        }
        lista.size--;
    }

    /**
     * Método para comprobar si un nodo de la lista posee un nodo previo a sí
     * mismo
     *
     * @return
     */
    public boolean tienePrevio() {
        return nodo.previo != null;
    }

    /**
     * Método para comprobar si un nodo de la lista posee un nodo siguiente
     *
     * @return
     */
    public boolean tieneSiguiente() {
        return nodo.siguiente != null;
    }

    /**
     * Método para obtener el nodo siguiente
     */
    public void siguiente() {
        nodo = nodo.siguiente;
    }

    /**
     * Método para obetner el nodo previo
     */
    public void previo() {
        nodo = nodo.previo;
    }

    /**
     * Método para obtener el valor del nodo
     *
     * @return
     */
    public T getValue() {
        return nodo.valor;
    }
}
