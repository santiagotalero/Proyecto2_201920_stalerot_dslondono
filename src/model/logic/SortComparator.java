package model.logic;

import java.util.Comparator;
import java.util.Random;

/**
 * Clase que representa m�ltiples m�todos de ordenamiento por Comparable. Usa Comparator.
 * @author Daniel del Castillo A.
 */
public class SortComparator 
{
	// Atributos
	
	/**
	 * Atributo auxiliar para el Mergesort.
	 */
	private static Comparable[] aux;

	// M�todos
	
	/**
	 * Ordenar datos aplicando el algoritmo Shellsort.
	 * @param datos Arreglo de datos a ordenar.
	 * @param c Comparador por el cual se comparar�n los datos.
	 */
	public static void shellSort(Comparable[] datos, Comparator c) 
	{
		int h = 1;
		while(h < datos.length/3)
			h = 3*h + 1;
		while(h >= 1)
		{
			for(int i = h; i < datos.length; i++)
			{
				for(int j = i; j >= h && less(datos[j], datos[j-h], c); j -= h)
					exchange(datos, j, j-h);
			}
			h = h/3;
		}
	}

	/**
	 * Ordenar datos aplicando el algoritmo Mergesort.
	 * @param datos Arreglo de datos a ordenar.
	 * @param c Comparador por el cual se comparar�n los datos.
	 */
	public static void mergeSort(Comparable[] datos, Comparator c) 
	{
		aux = new Comparable[datos.length];
		sort(datos, 0, datos.length-1, c);
	}
	
	/**
	 * M�todo encargado de ordenar recursivamente por medio de la l�gica Mergesort.
	 * @param datos Arreglo de datos a ordenar.
	 * @param lo La posici�n del elemento inferior en el arreglo.
	 * @param hi La posici�n del elemento superior en el arreglo. 
	 * @param c Comparador por el cual se comparar�n los datos.
	 */
	private static void sort(Comparable[] datos, int lo, int hi, Comparator c)
	{
		if(hi <= lo) 
			return;
		int mid = lo + (hi - lo)/2;
		sort(datos, lo, mid, c);
		sort(datos, mid+1, hi, c);
		merge(datos, lo, mid, hi, c);
	}
	
	/**
	 * M�todo encargado de juntar un arreglo que ha sido ordenado por Mergesort.
	 * @param datos Arreglo de datos a juntar.
	 * @param lo La posici�n del elemento inferior en el arreglo.
	 * @param mid La posici�n del elemento intermedio en el arreglo.
	 * @param hi La posicci�n del elemento superior en el arreglo.
	 * @param c Comparador por el cual se comparar�n los datos.
	 */
	private static void merge(Comparable[] datos, int lo, int mid, int hi, Comparator c)
	{
		int i = lo, j = mid+1;
		for(int k = lo; k <= hi; k++)
			aux[k] = datos[k];
		for(int k = lo; k <= hi; k++)
		{
			if(i > mid)
				datos[k] = aux[j++];
			else if(j > hi)
				datos[k] = aux[i++];
			else if (less(aux[j], aux[i], c))
				datos[k] = aux[j++];
			else
				datos[k] = aux[i++];
		}
	}
	
	/**
	 * Ordenar datos aplicando el algoritmo Quicksort.
	 * @param datos Arreglo de datos a ordenar.
	 * @param c Comparador por el cual se comparar�n los datos.
	 */
	public static void quickSort(Comparable[] datos, int low, int high, Comparator c) 
	{
		if(high >= low) 
		{
			Comparable v = datos[low];
			int pivot = low;
			int j = high;
			int	i = pivot;
			int contador = 0;
			while (i <= j) 
			{
				int comparacion = c.compare(datos[i], v);
				contador++;
				if(comparacion < 0)
				{
					exchange(datos, i, pivot);
					pivot++;
					i++;
				}
				else if (comparacion > 0)
				{
					exchange(datos, i, j);
					j--;
				}
				else
					i++;
			}
			quickSort(datos, low, pivot-1, c);
			quickSort(datos, j+1, high, c);
		}
	}

	/**
	 * Ordenar datos aplicando el algoritmo Quicksort.
	 * @param datos Arreglo de datos a ordenar.
	 * @param c Comparador por el cual se comparar�n los datos.
	 */
	public static void quickSort3(Comparable[] datos, Comparator c) 
	{
		shuffle(datos);
		sort3(datos, 0, datos.length-1, c);
	}
	
	/**
	 * M�todo encargado de ordenar recursivamente por medio de la l�gica Quicksort3.
	 * @param datos Arreglo de datos a ordenar.
	 * @param lo La posici�n del elemento inferior en el arreglo.
	 * @param hi La posici�n del elemento superior en el arreglo. 
	 * @param c Comparador por el cual se comparar�n los datos.
	 */
	private static void sort3(Comparable[] datos, int lo, int hi, Comparator c)
	{
		if(hi <= lo) 
			return;
		int lt = lo, gt = hi;
		Comparable v = datos[lo];
		int i = lo;
		while(i <= gt)
		{
			int cmp = c.compare(datos[i], v);
			if(cmp < 0)
				exchange(datos, lt++, i++);
			else if(cmp > 0)
				exchange(datos, i, gt--);
			else
				i++;
		}
		sort3(datos, lo, lt-1, c);
		sort3(datos, gt+1, hi, c);
	}

	/**
	 * Compara dos objetos usando la comparaci�n natural de su clase.
	 * @param v primer objeto de comparaci�n.
	 * @param w segundo objeto de comparaci�n.
	 * @return true si v es menor que w usando el m�todo compareTo(), false en caso contrario.
	 * @param c Comparador por el cual se comparar�n los datos.
	 */
	private static boolean less(Comparable v, Comparable w, Comparator c)
	{
		if(c.compare(v, w) < 0)
		{
			return true;
		}
		return false;
	}

	/**
	 * Intercambia los datos de las posiciones i y j.
	 * @param datos Arreglo de datos.
	 * @param i posici�n del primer elemento a intercambiar.
	 * @param j posici�n del segundo elemento a intercambiar.
	 */
	private static void exchange(Comparable[] datos, int i, int j)
	{
		Comparable tem = datos[i];
		datos[i]= datos[j];
		datos[j]=tem;
	}

	/**
	 * M�todo encargado de mezclar aleatoriamente el arreglo. 
	 * @param datos Arreglo de datos a mezclar. 
	 */
	private static void shuffle(Comparable[] datos)
	{
		int n = datos.length;
		Random random = new Random();
		for(int i = 0; i < datos.length; i++)
		{
			int randomValue = i + random.nextInt(n-i);
			Comparable randomElement = datos[randomValue];
			datos[randomValue] = datos[i];
			datos[i] = randomElement;
		}
	}
}
