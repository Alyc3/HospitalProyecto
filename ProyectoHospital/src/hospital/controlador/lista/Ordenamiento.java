package hospital.controlador.lista;

import java.util.Comparator;

public class Ordenamiento {

    /**
     * Método para mezclar dos sublistas adyacentes (previamente ordenadas), de
     * tal forma que la sublista resultante esté ordenada
     *
     * @param <T>
     * @param arr
     * @param l1
     * @param r1
     * @param l2
     * @param r2
     * @param aux
     * @param comparador
     * @throws IllegalArgumentException
     * @throws ArrayIndexOutOfBoundsException
     */
    private static <T> void merge(T[] arr, int l1, int r1, int l2, int r2, T[] aux, Comparator<T> comparador)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (l1 >= r1) {
            throw new IllegalArgumentException("l1(" + l1 + ") >= r1(" + r1 + ")");
        }
        if (l2 >= r2) {
            throw new IllegalArgumentException("l2(" + l2 + ") >= r2(" + r2 + ")");
        }
        if (r1 != l2) {
            throw new IllegalArgumentException("r1(" + r1 + ") != l2(" + l2 + ")");
        }
        if (l1 < 0) {
            throw new ArrayIndexOutOfBoundsException("l1(" + l1 + ") < 0");
        }
        if (r2 > arr.length) {
            throw new ArrayIndexOutOfBoundsException("r2(" + r2 + ") > arr.length(" + arr.length + ")");
        }

        for (int i = l1, i1 = l1, i2 = l2; i < r2; i++) {
            if (i1 < r1 && i2 < r2) {
                if (comparador.compare(arr[i1], arr[i2]) <= 0) {
                    aux[i] = arr[i1++];
                } else {
                    aux[i] = arr[i2++];
                }
            } else if (i1 < r1) {
                aux[i] = arr[i1++];
            } else {
                aux[i] = arr[i2++];
            }
        }
        for (int i = l1; i < r2; i++) {
            arr[i] = aux[i];
        }
    }

    /**
     * Método genérico para el ordenamiento de listas
     *
     * @param <T>
     * @param arr
     * @param l
     * @param r
     * @param comparador
     * @throws IllegalArgumentException
     * @throws ArrayIndexOutOfBoundsException
     */
    public static <T> void mergeSort(T[] arr, int l, int r, Comparator<T> comparador) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (l >= r) {
            throw new IllegalArgumentException("l(" + l + ") >= r(" + r + ")");
        }
        if (l < 0) {
            throw new ArrayIndexOutOfBoundsException("l(" + l + ") < 0");
        }
        if (r > arr.length) {
            throw new ArrayIndexOutOfBoundsException("r(" + r + ") > arr.length(" + arr.length + ")");
        }

        if (r - l >= 2) {
            T[] aux = arr.clone();
            mergeSort(arr, l, (l + r + 1) / 2, comparador);
            mergeSort(arr, (l + r + 1) / 2, r, comparador);
            merge(arr, l, (l + r + 1) / 2, (l + r + 1) / 2, r, aux, comparador);
        }
    }

    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr.length > 0) {
            mergeSort(arr, 0, arr.length, comparator);
        }
    }
}
