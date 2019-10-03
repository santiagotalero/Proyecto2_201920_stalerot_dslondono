package model.data_structures;

public interface IMaxPriorityQueue <T extends Comparable<T>>
{
	// M�todos

	/**
	 * Agrega un elemento a la cola. Si el elemento ya existe y tiene una prioridad
	 * diferente, el elemento debe actualizarse en la cola de prioridad. 
	 * @param v Elemento a introducir. 
	 */
	public void insert(T v);

	/**
	 * Obtiene el elemento m�ximo sin sacarlo de la cola .
	 * @return El elemento m�ximo de la cola, null si la cola est� vac�a. 
	 */
	public T darMax();

	/**
	 * Saca el elemento m�ximo de la cola y lo retorna.
	 * @return Elemento m�ximo de la cola, null si la cola est� vac�a.
	 */
	public T delMax();

	/**
	 * Retorna si la cola est� vac�a.
	 * @return True si la cola est� vac�a, False de lo contrario. 
	 */
	public boolean isEmpty();
	
	/**
	 * Retorna el tama�o de la cola de prioridad.
	 * @return El tama�o de la cola de prioridad. N >= 0.
	 */
	public int darNumElementos();
}
