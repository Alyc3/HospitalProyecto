package hospital.controlador.lista;

public class IteradorLista<T> {
	
	private Nodo<T> nodo;

	protected IteradorLista(Nodo<T> nodo){
		this.nodo = nodo;
	}

        /**
         * Método para comprobar si un nodo de la lista posee un nodo previo a sí mismo
         * @return 
         */
	public boolean tienePrevio(){
		return nodo.previo != null;
	}

        /**
         * Método para comprobar si un nodo de la lista posee un nodo siguiente
         * @return 
         */
	public boolean tieneSiguiente(){
		return nodo.siguiente != null;
	}

        /**
         * Método para obtener el nodo siguiente 
         */
	public void siguiente(){
		nodo = nodo.siguiente;
	}

        /**
         * Método para obetner el nodo previo
         */
	public void previo(){
		nodo = nodo.previo;
	}

        /**
         * Método para obtener el valor del nodo 
         * @return 
         */
	public T getValue(){
		return nodo.valor;
	}
}