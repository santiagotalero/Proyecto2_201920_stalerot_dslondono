

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.HashTableLinearProbing;

/**
 * Clase que prueba la implementación de una tabla de hash con linear probing.
 * @author Daniel del Castillo A.
 */
public class HashTableLinearProbingTest 
{	
	// Atributos
	
	/**
	 * Atributo que representa una tabla con linear probing.
	 */
	private HashTableLinearProbing<Integer, String> table1;
	
	// Setup
	
	@Before
	/**
	 * Inicializa la tabla.
	 */
	public void setup1()
	{
		table1 = new HashTableLinearProbing<Integer, String>();
	}
	
	// Tests
	
	@Test
	/**
	 * Prueba si la tabla se inicializó correctamente. 
	 */
	public void testIncializacion()
	{
		assertTrue("La tabla no debería ser null.", table1 != null);
		assertTrue("La tabla debería estar vacía.", table1.isEmpty());
		assertTrue("El tamaño de la tabla debería ser 0.", table1.size() == 0);
		assertTrue("No debería encontrar ninguna llave.", table1.get(123) == null);
		Iterable<Integer> llaves = table1.keys();
		Iterator<Integer> it = llaves.iterator();
		Integer i = 0;
		while(it.hasNext())
		{
			i++;
			Integer act = it.next();
		}
		assertTrue("No deberían haber llaves.", i == 0);
	}
	
	@Test
	/**
	 * Prueba si la tabla agrega correctamente un elemento.
	 */
	public void testAgregar()
	{
		table1.put(123, "Hola");
		assertTrue("La tabla no debería estar vacía.", !table1.isEmpty());
		assertTrue("La tabla debería tener un tamaño de 1.", table1.size() == 1);
		assertTrue("Debería retornar la String 'Hola'.", table1.get(123).equals("Hola"));
		assertTrue("No debería encontrar ninguna llave.", table1.get(1234) == null);
		Iterable<Integer> llaves = table1.keys();
		Iterator<Integer> it = llaves.iterator();
		boolean encontro = false;
		Integer i = 0;
		while(it.hasNext())
		{
			Integer act = it.next();
			if(act == 123)
				encontro = true;
			i++;
		}
		assertTrue("Sólo debería haber una llave.", i == 1);
		assertTrue("Debería encontrar la llave 123.", encontro);
		assertTrue("Debería encontrar la llave 123.", table1.contains(123));
	}
	
	@Test
	/**
	 * Prueba si la tabla agrega correctamente un elemento repetido (misma llave).
	 */
	public void testAgregar2()
	{
		table1.put(123, "Hola");
		table1.put(123, "Hello");
		assertTrue("La tabla no debería estar vacía.", !table1.isEmpty());
		assertTrue("La tabla debería tener un tamaño de 1.", table1.size() == 1);
		assertTrue("Debería retornar la String 'Hello'.", table1.get(123).equals("Hello"));
		assertTrue("No debería encontrar ninguna llave.", table1.get(1234) == null);
		Iterable<Integer> llaves = table1.keys();
		Iterator<Integer> it = llaves.iterator();
		boolean encontro = false;
		Integer i = 0;
		while(it.hasNext())
		{
			Integer act = it.next();
			if(act == 123)
				encontro = true;
			i++;
		}
		assertTrue("Sólo debería haber una llave.", i == 1);
		assertTrue("Debería encontrar la llave 123.", encontro);
		assertTrue("Debería encontrar la llave 123.", table1.contains(123));
	}
	
	@Test
	/**
	 * Prueba si la tabla agrega correctamente dos elementos (distintas llaves).
	 */
	public void testAgregar3()
	{
		table1.put(123, "Hola");
		table1.put(234, "Hello");
		assertTrue("La tabla no debería estar vacía.", !table1.isEmpty());
		assertTrue("La tabla debería tener un tamaño de 2.", table1.size() == 2);
		assertTrue("Debería retornar la String 'Hola'.", table1.get(123).equals("Hola"));
		assertTrue("Debería retornar la String 'Hello'.", table1.get(234).equals("Hello"));
		assertTrue("No debería encontrar ninguna llave.", table1.get(1234) == null);
		Iterable<Integer> llaves = table1.keys();
		Iterator<Integer> it = llaves.iterator();
		boolean encontro1 = false;
		boolean encontro2 = false;
		Integer i = 0;
		while(it.hasNext())
		{
			Integer act = it.next();
			if(act == 123)
				encontro1 = true;
			else if(act == 234)
				encontro2 = true;
			i++;
		}
		assertTrue("Sólo debería haber dos llaves.", i == 2);
		assertTrue("Debería encontrar la llave 123.", encontro1);
		assertTrue("Debería encontrar la llave 234.", encontro2);
		assertTrue("Debería encontrar la llave 123.", table1.contains(123));
		assertTrue("Debería encontrar la llave 234.", table1.contains(234));
	}
	
	@Test
	/**
	 * Prueba si la tabla elimina corectamente un elemento.
	 *<b>Pre:<\b> la tabla agrega elementos de forma correcta.<br>
	 */
	public void testDelete()
	{
		table1.put(123, "Hola");
		table1.delete(123);
		assertTrue("La tabla debería estar vacía.", table1.isEmpty());
		assertTrue("La tabla debería tener un tamaño de 0.", table1.size() == 0);
		assertTrue("No debería encontrar ninguna llave 123.", table1.get(123) == null);
		Iterable<Integer> llaves = table1.keys();
		Iterator<Integer> it = llaves.iterator();
		boolean noEsta = true;
		Integer i = 0;
		while(it.hasNext())
		{
			Integer act = it.next();
			if(act == 123)
				noEsta = false;
			i++;
		}
		assertTrue("No debería haber ninguna llave.", i == 0);
		assertTrue("No debería encontrar la llave 123.", noEsta);
		assertTrue("No debería encontrar la llave 123.", !table1.contains(123));
	}
	
	@Test
	/**
	 * Prueba si la tabla no comete errores al borrar el mismo elemento dos veces.
	 *<b>Pre:<\b> la tabla agrega elementos de forma correcta.<br>
	 */
	public void testDelete2()
	{
		table1.put(123, "Hola");
		table1.delete(123);
		table1.delete(123);
		assertTrue("La tabla debería estar vacía.", table1.isEmpty());
		assertTrue("La tabla debería tener un tamaño de 0.", table1.size() == 0);
		assertTrue("No debería encontrar ninguna llave 123.", table1.get(123) == null);
		Iterable<Integer> llaves = table1.keys();
		Iterator<Integer> it = llaves.iterator();
		boolean noEsta = true;
		Integer i = 0;
		while(it.hasNext())
		{
			Integer act = it.next();
			if(act == 123)
				noEsta = false;
			i++;
		}
		assertTrue("No debería haber ninguna llave.", i == 0);
		assertTrue("No debería encontrar la llave 123.", noEsta);
		assertTrue("No debería encontrar la llave 123.", !table1.contains(123));
	}
	
	@Test
	/**
	 * Prueba si la tabla elimina un elemento (cuando hay varios) correctamente.
	 *<b>Pre:<\b> la tabla agrega elementos de forma correcta.<br>
	 */
	public void testDelete3()
	{
		table1.put(123, "Hola");
		table1.put(234, "Hello");
		table1.put(345, "Chao");
		table1.put(456, "Bye");
		table1.delete(123);
		assertTrue("La tabla no debería estar vacía.", !table1.isEmpty());
		assertTrue("La tabla debería tener un tamaño de 3.", table1.size() == 3);
		assertTrue("No debería encontrar ninguna llave 123.", table1.get(123) == null);
		Iterable<Integer> llaves = table1.keys();
		Iterator<Integer> it = llaves.iterator();
		boolean noEsta = true;
		boolean error = false;
		Integer i = 0;
		while(it.hasNext())
		{
			Integer act = it.next();
			if(act == 123)
				noEsta = false;
			else if(act != 234 && act != 345 && act != 456)
				error = true;
			i++;
		}
		assertTrue("Deberían haber 3 llaves.", i == 3);
		assertTrue("No debería encontrar la llave 123.", noEsta);
		assertTrue("No debería borrar una llave distinta a 123.", !error);
		assertTrue("No debería encontrar la llave 123.", !table1.contains(123));
		assertTrue("Debería encontrar la llave 234.", table1.contains(234));
		assertTrue("Debería encontrar la llave 345.", table1.contains(345));
		assertTrue("Debería encontrar la llave 456.", table1.contains(456));
		assertTrue("El valor de la llave 234 no corresponde a su valor.", table1.get(234).equals("Hello"));
		assertTrue("El valor de la llave 345 no corresponde a su valor.", table1.get(345).equals("Chao"));
		assertTrue("El valor de la llave 456 no corresponde a su valor.", table1.get(456).equals("Bye"));
	}
}
