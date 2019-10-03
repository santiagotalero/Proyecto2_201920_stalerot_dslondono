package model.data_structures;

import model.logic.TravelTime;

public class MaxHeapCP<T extends Comparable<T>> implements IMaxPriorityQueue<T>
{
	//Tomado de algs4.princeton

	/**
	 * Arreglo de la cola de prioridad. 
	 */
	private T[] pq;

	/**
	 * Tama�o actual de la cola de prioridad.
	 */
	private int N;

	/**
	 * Tama�o m�ximo de la cola de prioridad.
	 */
	private int max;

	// Constructor

	/**
	 * Construye una cola de prioridad.
	 */
	public MaxHeapCP()
	{
		max = 2;
		pq = (T[]) new Comparable[max+1];
		N = 0;
	}

	// M�todos

	/**
	 * Agrega un elemento a la cola. Si el elemento ya existe y tiene una prioridad
	 * diferente, el elemento debe actualizarse en la cola de prioridad. 
	 * @param v Elemento a introducir. 
	 */
	public void insert(T v)
	{
		if(N == max)
		{
			max *= 2;
			T[] copia = pq;
			pq = (T[]) new Comparable[max+1];
			for(int i = 0; i <= N; i++)
				pq[i] = copia[i];
		}
		pq[++N] = v;
		swim(N);
	}
	
	public void insert2(TravelTime v)
	{
		if(N == max)
		{
			max *= 2;
			T[] copia = pq;
			pq = (T[]) new Comparable[max+1];
			for(int i = 0; i <= N; i++)
				pq[i] = copia[i];
		}
		pq[++N] = (T) v;
		swim(N);
	}

	/**
	 * Obtiene el elemento m�ximo sin sacarlo de la cola .
	 * @return El elemento m�ximo de la cola, null si la cola est� vac�a. 
	 */
	public T darMax()
	{
		return pq[1];
	}

	/**
	 * Saca el elemento m�ximo de la cola y lo retorna.
	 * @return Elemento m�ximo de la cola, null si la cola est� vac�a.
	 */
	public T delMax()
	{
		T max = null;
		if(!isEmpty())
		{
			max = pq[1];
			exchange(1, N--);
			pq[N+1] = null;
			sink(1);
		}
		return max;
	}

	/**
	 * Retorna si la cola est� vac�a.
	 * @return True si la cola est� vac�a, False de lo contrario. 
	 */
	public boolean isEmpty()
	{
		return N == 0;
	}

	/**
	 * Revisa si un primer elemento es menor a un segundo.
	 * @param i �ndice del primer elemento a comparar.
	 * @param j �ndice del segundo elemento a comparar.
	 * @return True si i < j, False de lo contrario.
	 */
	private boolean less(int i, int j)
	{
		return pq[i].compareTo(pq[j]) < 0;
	}

	/**
	 * Intercambia dos �ndices en la cola de prioridad.
	 * @param i �ndice del primer elemento a cambiar.
	 * @param j �ndice del segundo elemento a cambiar. 
	 */
	private void exchange(int i, int j)
	{
		T t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}

	/**
	 * Ejecuta la funci�n swim para un �ndice k en la cola de prioridad.
	 * @param k �ndice del valor a hacerle sink.
	 */
	private void swim(int k)
	{
		while(k > 1 && less(k/2, k))
		{
			exchange(k/2, k);
			k = k/2;
		}
	}

	/**
	 * Ejecuta la funci�n sink para un �ndice k en la cola de prioridad. 
	 * @param k �ndice del valor a hacerle sink. 
	 */
	private void sink(int k)
	{
		while(2*k <= N)
		{
			int j = 2*k;
			if(j < N && less(j, j+1))
				j++;
			if(!less(k, j))
				break;
			exchange(k, j);
			k =j;
		}
	}

	/**
	 * Retorna el tama�o de la cola de prioridad.
	 * @return El tama�o de la cola de prioridad. N >= 0.
	 */
	public int size()
	{
		return N;
	}

	@Override
	public int darNumElementos() {

		return N;
	}
}
