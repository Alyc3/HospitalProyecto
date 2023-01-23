package hospital.controlador.lista;

// List es la implementación de una lista
// doblemente enlazada.
public class ListaEnlazada<T> {
	
	private int size;
	private Nodo<T> cabecera;

	public ListaEnlazada(){
		size = 0;
		cabecera = null;
	}

	/**
         * Método que retorna el número de elementos de la lista
         * @return 
         */
	public int size(){
		return size;
	}

	/**
         * Método para comprobar si la lista se encuentra vacía
         * @return 
         */
	public boolean vacia(){
		return cabecera == null;
	}

	/**
         * Método para insertar un valor al final de la lista
         * @param value 
         */
	public void insertarFinal(T value){
		Nodo<T> nodo = new Nodo<T>(value, null, null);
		if(cabecera == null){ // size == 0
			nodo.previo = nodo;
			nodo.siguiente = nodo;
			cabecera = nodo;
		}
		else if(cabecera.siguiente == cabecera){ // size == 1
			nodo.previo = cabecera;
			nodo.siguiente = cabecera;
			cabecera.previo = nodo;
			cabecera.siguiente = nodo;
		}
		else{ // size >= 2
			Nodo<T> last = cabecera.previo;
			nodo.previo = last;
			nodo.siguiente = cabecera;
			last.siguiente = nodo;
			cabecera.previo = nodo;
		}
		size++;
	}

	/**
         * Método para obtener el último elemento de la lista
         * Si la lista está vacía retorna null
         * @return 
         */
	public T ultimo(){
		if(cabecera == null){ // size == 0
			return null;
		}
		else{ // size >= 1
			Nodo<T> last = cabecera.previo;
			return last.valor;
		}
	}

	/**
         * Método para eliminar y retornar el último elemento de la lista
         * @return 
         */
	public T eliminarUltimo(){
		if(cabecera == null){ // size == 0
			return null;
		}
		else{ // size >= 1
			T value = cabecera.previo.valor;
			if(cabecera.siguiente == cabecera){ // size == 1
				cabecera = null;
			}
			else{ // size >= 2
				Nodo<T> last = cabecera.previo;
				last.previo.siguiente = last.siguiente;
				last.siguiente.previo = last.previo;
			}
			size--;
			return value;
		}
	}

        /**
         * Método para obtener el elemento en la posición pos
         * Si la posición es menor a 0 o mayor al tamanio de la lista retorna null
         * @param pos
         * @return 
         */
	public T obtener(int pos){
		if(0 <= pos && pos < size){
			Nodo<T> p = cabecera;
			for(int i=1; i<pos; i++){
				p = p.siguiente;
			}
			return p.valor;
		}
		else{
			return null;
		}
	}

        /**
         * Método para modificar el elemento en la posición pos
         * Si la posición es menor a 0 o mayor al tamanio de la lista no ocurre nada
         * @param pos
         * @param value 
         */
	public void insertar(int pos, T value){
		if(0 <= pos && pos < size){
			Nodo<T> p = cabecera;
			for(int i=1; i<pos; i++){
				p = p.siguiente;
			}
			p.valor = value;
		}
	}

	public IteradorLista<T> iteradorLista(){
		return new IteradorLista<T>(cabecera);
	}

        /**
         * Método para convertir la lista a un arreglo
         * @param arr 
         */
	public void toArray(T[] arr){
		/*
		if(arr.length < size){
	
		}
		*/

		Nodo<T> p = cabecera;
		for(int i=0; i<size; i++){
			arr[i] = p.valor;
			p = p.siguiente;

			/*try {

			}
			catch() {

			}*/
		}
	}
}