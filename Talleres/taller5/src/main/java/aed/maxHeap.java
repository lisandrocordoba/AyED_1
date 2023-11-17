package aed;

class maxHeap<T extends Comparable<T>> {

	private T[] heap;
	private int capacidad;
	private int tamaño;
	
	/*
	InvRep : tamaño <= capacidad y capacidad = |heap| yLuego
	para todo i: int, si 0 <= i < |tamaño| entonces luego ((2*i+1 <= tamaño entonces luego heap[i] >= heap[2*i + 1]) y (2*i + 2 <= tamaño entonces luego heap[i] >= heap[2*i + 2]))
	y debe estar perfectamente balanceado, es decir, que ninguna posicion del array menor a tamaño sea nula.
	*/

	// O(1)
	private void swap(T[] array, int a, int b) {
		T elem = array[a];
		array[a] = array[b];
		array[b] = elem;
	}
	
	private int padre(int i) {
		return (i - 1) / 2;
	}

	private int izq(int i) {
		return 2 * i + 1;
	}
	
	private int der(int i) {
		return 2 * i + 2;
	}
	
	//O(n)
	public maxHeap(int n) {
		capacidad = n;
		heap = (T[]) new Comparable[capacidad];
		tamaño = 0;
	}

	// O(n)
	public maxHeap(T[] array, int n) {

		capacidad = n + 1;
		tamaño = capacidad - 1;
		heap = (T[]) new Comparable[capacidad];
		heap = array; // O(n)
		for (int i = (tamaño-1)/2; i>=0 ; i--) {
			bajar(i); // Floyd O(n)
		}
	}

	// O(log n) mientras tenga lugar, en mi SistemaCNE ya conozco cuanto espacio necesito.
	public void apilar(T elem) {
		// Cuando me quedo sin capacidad duplico el tamaño O(n).
		if (tamaño == capacidad) {
            T[] nuevoHeap = (T[]) new Comparable[tamaño*2];
            for (int i = 0; i < tamaño; i++) {
                nuevoHeap[i] = heap[i];
            }
            heap = nuevoHeap;
		}

		int i = tamaño;
		heap[i] = elem;
		tamaño++;
	
		subir(i); // Restablezco el invariante.
	}

	// O(log n), pues recorre en el peor caso la altura.
	private void subir(int i) {
		while (i != 0 && heap[i].compareTo(heap[padre(i)]) > 0) {
			swap(heap, i, padre(i));
			i = padre(i);
		}
	}
	

	public T max() {
		return heap[0];
	}
	

    // Devuelvo el maximo y lo elimino.
	public T desapilar() {

		if (tamaño == 1) {
			tamaño--;
			return heap[0];
		}

		T max = heap[0];
		heap[0] = heap[tamaño - 1];
		tamaño--;
		bajar(0); // Restablezco el invariante.

		return max;
	}

	private void bajar(int i) {
		int largest = i;
		boolean prioridad = true;
		
		// En el peor caso bajo desde la raiz a una hoja, O(log n)
		while (esHoja(largest) && prioridad) {

			i = largest;
			int izq = izq(largest);
			int der = der(largest);

			if (izq < tamaño && heap[izq].compareTo(heap[largest]) >= 0) {
				largest = izq;
			}

			if (der < tamaño && heap[der].compareTo(heap[largest]) >= 0) {
				largest = der;
			}

			else if (der >= tamaño || heap[izq].compareTo(heap[largest]) < 0){
				prioridad = false; // Si no hay un hijo mas grande el ciclo termina.
			}

			if (i != largest) {
				swap(heap, i, largest);
			}
		}
	}

	private boolean esHoja(int i) {
		boolean res = (izq(i) < tamaño);
		return res;
	}

	public void modificarMaximo(T elem){
		heap[0] = elem;
		bajar(0);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (int i = 0; i < this.tamaño-1; i++) {
			sb.append(this.heap[i] + ",");
		}
		sb.append(this.heap[tamaño-1] + "}");
		return sb.toString();
	}
}
