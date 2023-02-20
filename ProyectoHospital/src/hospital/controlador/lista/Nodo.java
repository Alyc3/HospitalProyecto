package hospital.controlador.lista;

public class Nodo<T> {

    protected T valor;
    protected Nodo<T> previo;
    protected Nodo<T> siguiente;

    public Nodo(T valor, Nodo<T> previo, Nodo<T> siguiente) {
        this.valor = valor;
        this.previo = previo;
        this.siguiente = siguiente;
    }
}
