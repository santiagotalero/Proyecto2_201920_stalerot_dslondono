package model.data_structures;

import java.util.Iterator;

/**
 * Clase que representa el API de las tablas de símbolos.
 * @param <Key> Llave con la que se identificarán y buscarán las tuplas de la tabla.
 * @param <Value> Valor a almacenar con la correspondiente llave en la tabla. 
 */
public interface ISymbolTable<Key, Value>
{
	// Métodos
	
	/**
	 * Pone la tupla llave-valor en la tabla (remueve la llave de la tabla si el valor es null).
	 * @param key La llave de la tupla a introducir. 
	 * @param val El valor de la tupla a introducir. Si es null, elimina la llave.
	 */
	public void put(Key key, Value val);
	
	/**
	 * Retorna el valor asignado a la llave por parámetro.
	 * @param key Llave del valor a buscar.
	 * @return El valor de la llave correspondiente.
	 */
	public Value get(Key key);
	
	/**
	 * Remueve la llave y su correspondiente valor de la tabla.
	 * @param key
	 */
	public void delete(Key key);
	
	/**
	 * @param key Llave a revisar.
	 * @return True si hay un valor correspondiente a la llave, false de lo contrario.
	 */
	public boolean contains(Key key);
	
	/**
	 * @return True si la tabla está vacía, false de lo contrario.
	 */
	public boolean isEmpty();
	
	/**
	 * @return Número de pares llave-valor en la tabla. 
	 */
	public int size();
	
	/**
	 * @return Todas las llaves en la tabla. 
	 */
	public Iterable<Key> keys();
}
