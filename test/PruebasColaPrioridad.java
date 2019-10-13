

import java.util.Iterator;

import junit.framework.TestCase;
import model.data_structures.MaxHeapCP;
import model.logic.TravelTime;


public class PruebasColaPrioridad extends TestCase 
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------


	private MaxHeapCP<Integer> heap;
	
	//Trimestre, sourceId , dstID, hod, meanTravelTime, standardDeviationTravelTime
	
	private TravelTime elem1 = new TravelTime(  1,1,1,1,1,1);

	private TravelTime elem2 = new TravelTime( 2,2,2,2,2,2);

	private TravelTime elem3 = new TravelTime( 3,3,3,3,3,3);

	//-----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	/**
	 *  Escenario 0: Construye una nueva cola.
	 */
	public void setupEscenario0() 
	{
		heap = new MaxHeapCP();
	}


	/**
	 *  Escenario 1: Construye una cola, un heap y agrag un elemento a cada uno.
	 */
	public void setupEscenario1() 
	{
		heap = new MaxHeapCP();
		heap.insert2(elem1);
	}

	/**
	 *  Escenario 2: Construye una cola y un heap. Agregaga tres elementos ordenadamente
	 */
	public void setupEscenario2() 
	{;
		heap = new MaxHeapCP();

		heap.insert2(elem1);

		heap.insert2(elem2);

		heap.insert2(elem3);

	}

	/**
	 *  Escenario 3: Construye una cola y un heap. Agregaga tres elementos desordenadamente
	 */
	public void setupEscenario3() 
	{
		heap = new MaxHeapCP();

		heap.insert2(elem2);

		heap.insert2(elem3);

		heap.insert2(elem1);

	}

	/**
	 *  Escenario 4: Construye una cola y un heap. Agregaga dos elementos desordenadamente e intenta insert2 uno reptetido
	 */
	public void setupEscenario4() 
	{
		heap = new MaxHeapCP();

		heap.insert2(elem1);

		heap.insert2(elem2);

		heap.insert2(elem2);
	}



	/**
	 * Prueba 2: verfica que se puedan agregar elementos a la cola de prioridad basada en Heap y que el máximo queda en la posición adecuada <br>
	 * <b>Métodos a probar:</b> <br>
	 * MaxHeapCP.insert2<br>
	 * size<br>
	 * isEmpty
	 * <b> Casos de prueba: </b><br>
	 * 1. Se agrega un elemento a la lista.
	 * 2. Se agregan 3 elementos a lista ordenadamente
	 * 3. Se agregan 3 elementos a lista desordenadamente.
	 * 4. Se intentan agragar elementos repetidos
	 */
	public void testinsert22()
	{

		//Caso de prueba 1:
		setupEscenario1();
		assertTrue("El tamañaño de la lista deberia ser uno", heap.size()==1);
		assertFalse("La lista no deberia estar vacia", heap.isEmpty());

		//Caso de prueba 2:
		setupEscenario2();
		assertTrue("El tamañaño de la lista deberia ser tres", heap.size() ==3);
		assertEquals("El elemento con la mayor proridad no está en la posición adecuada",elem3.compareToo(heap.darMax()));


		//Caso de prueba 3:
		setupEscenario3();
		assertTrue("El tamaña de la lista deberia ser tres", heap.size()==3);
		assertEquals("El elemento está en la posición equivocada",elem3.compareToo(heap.darMax().intValue()));

		//Caso de prueba4:
		setupEscenario4();
		assertTrue("El tamañaño de la lista deberia ser dos", heap.size()==3);
		assertTrue("El elemento está en la posición equivocada",elem2.compareToo(heap.darMax())==0);

	}

	/**
	 * Prueba 2: verfica que se puedan agragr elementos a la lista y que queden ordenados <br>
	 * <b>Métodos a probar:</b> <br>
	 * MaxColaCP.delMax<br>
	 * MaxHeapCP.delMax<br>
	 * MaxColaCP.darNumElementos<br>
	 * MaxHeapCP.size
	 * isEmpty
	 * <b> Casos de prueba: </b><br>
	 * 1. La lista está vacía
	 * 2. Solo hay un elemento en ela lsita
	 * 3. Hay más de un elemento en el la lista. 
	 */
	public void testDelMax()
	{
		//Caso de prueba 1:
		setupEscenario0();

	
		
		assertNull("El elemento retonado debería ser null",heap.delMax());
		assertTrue("La estructura debería seguir vacía", heap.isEmpty() );
		assertTrue("La estructura debería seguir vacía", heap.size()==0);
		

		//Caso de prueba 2:
		setupEscenario1();


		assertNotNull("El elemento retonado debería ser null",heap.delMax());
		assertTrue("La estructura debería seguir vacía", heap.size()==0);
		assertTrue("La estructura debería seguir vacía", heap.isEmpty() );

		//Caso de prueba 3:
		setupEscenario2();
;


		Integer tem = heap.darMax();
		assertNotNull("El elemento retonado debería ser null",heap.delMax());
		assertTrue("La lista debería estar vacía", heap.size()==2);
		assertFalse("Ese elemento de no debería estar en la lista", tem.compareTo(heap.delMax()) == 0);

	}
}
