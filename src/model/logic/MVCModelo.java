package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.opencsv.CSVReader;


import model.data_structures.HashTableLinearProbing;
import model.data_structures.MaxHeapCP;
import model.data_structures.Queue;
import model.data_structures.RedBlackBST;
import model.logic.ClasesArchivoJSon.Feature;
import model.logic.ClasesArchivoJSon.FeatureCollection;


/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private HashTableLinearProbing<String,Feature> tablaHashZonas;
	private MaxHeapCP<TravelTime> heapHoras;
	private MaxHeapCP<TravelTime> heapMes;
	private RedBlackBST<Integer,NodoMV> arbolNodosMV;
	

	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		tablaHashZonas= new HashTableLinearProbing<String,Feature>();
		heapHoras= new MaxHeapCP<TravelTime>();
		heapMes= new MaxHeapCP<TravelTime>();
		arbolNodosMV= new RedBlackBST<Integer,NodoMV>();
	}
	
	
	public void cargarArchivos() throws IOException
	{
		//ARCHIVOS DE HORA
		
		CSVReader readerCSV = null;
		try 
		{
			readerCSV = new CSVReader(new FileReader("./data/bogota-cadastral-2018-1-All-HourlyAggregate.csv"));

			
			readerCSV.readNext();

			for(String[] nextLine : readerCSV)
			{
				TravelTime actual= new TravelTime(1,Integer.parseInt(nextLine[0]),Integer.parseInt(nextLine[1]),Integer.parseInt(nextLine[2]),Double.parseDouble(nextLine[3]),Double.parseDouble(nextLine[4]));
				heapHoras.insert(actual);
			}

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		finally{
			if (readerCSV != null) {
				try {
					readerCSV.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		System.out.println("Número de viajes cargados para el archivo por horas del 1er trimestre: " + heapHoras.size());
		
		int tamanoParcial= heapHoras.size();
		try 
		{
			readerCSV = new CSVReader(new FileReader("./data/bogota-cadastral-2018-2-All-HourlyAggregate.csv"));

			
			readerCSV.readNext();

			
			for(String[] nextLine : readerCSV)
			{
				TravelTime actual= new TravelTime(2,Integer.parseInt(nextLine[0]),Integer.parseInt(nextLine[1]),Integer.parseInt(nextLine[2]),Double.parseDouble(nextLine[3]),Double.parseDouble(nextLine[4]));
				heapHoras.insert(actual);
			}

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		finally{
			if (readerCSV != null) {
				try {
					readerCSV.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		
		
		System.out.println("Número de viajes cargados para el archivo por horas del 2ndo trimestre: " + (heapHoras.size()-tamanoParcial));
		
		//ARCHIVOS DE MES
		
		CSVReader readerCSV2 = null;
		try 
		{
			readerCSV2 = new CSVReader(new FileReader("./data/bogota-cadastral-2018-1-All-MonthlyAggregate.csv"));

			
			readerCSV2.readNext();

			
			for(String[] nextLine : readerCSV2)
			{
				TravelTime actual= new TravelTime(1,Integer.parseInt(nextLine[0]),Integer.parseInt(nextLine[1]),Integer.parseInt(nextLine[2]),Double.parseDouble(nextLine[3]),Double.parseDouble(nextLine[4]));
				heapMes.insert(actual);
			}

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		finally{
			if (readerCSV2 != null) {
				try {
					readerCSV2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		System.out.println("Número de viajes cargados para el archivo por mes del 1er trimestre: " + heapMes.size());
		tamanoParcial= heapMes.size();
		try 
		{
			readerCSV2 = new CSVReader(new FileReader("./data/bogota-cadastral-2018-2-All-MonthlyAggregate.csv"));

			
			readerCSV2.readNext();

			
			for(String[] nextLine : readerCSV2)
			{
				TravelTime actual= new TravelTime(2,Integer.parseInt(nextLine[0]),Integer.parseInt(nextLine[1]),Integer.parseInt(nextLine[2]),Double.parseDouble(nextLine[3]),Double.parseDouble(nextLine[4]));
				heapMes.insert(actual);
			}

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		finally{
			if (readerCSV2 != null) {
				try {
					readerCSV2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		
		System.out.println("Número de viajes cargados para el archivo por mes del 2ndo trimestre: " + (heapMes.size()-tamanoParcial));
		//ARCHIVO TXT (CSV)
		CSVReader reader = null;
		
		reader = new CSVReader(new FileReader("./data/Nodes_of_red_vial-wgs84_shp.txt"));
		
		for(String[] nextLine : reader)
		{
			NodoMV actual= new NodoMV(Integer.parseInt(nextLine[0]),Double.parseDouble(nextLine[1]),Double.parseDouble(nextLine[2]));
			arbolNodosMV.put(actual.getId(), actual);
		}
		System.out.println("Número de nodos de la malla vial: " + arbolNodosMV.size());
		//ARCHIVO JSON
		String path= "./data/bogota_cadastral.json";
		
		Gson gson = new Gson();
		
		JsonReader readerJSon;
		
		FeatureCollection a=null;
		try{
			readerJSon= new JsonReader(new FileReader(path));
			a= gson.fromJson(readerJSon, FeatureCollection.class);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		
		int i=0;
		
		while(i<a.getFeatures().length)
		{
			Feature actual= a.getFeatures()[i];
			
			tablaHashZonas.put(actual.getPropiedades().getMOVEMENT_ID(), actual);
			i++;
		}
		System.out.println("Número de zonas cargadas: " + tablaHashZonas.size());
		
		
		System.out.println(tablaHashZonas.get("1").getGeometrias().getCoordinates()[0][0].length);
	}
	
	
	public HashTableLinearProbing req1A(int n)
	{
		//Retornaremos una tabla de hash con las zonas que empiezan por las N letras más frecuentes
		
		String letras="";
		
		Queue q=(Queue) tablaHashZonas.keys();
		Iterator iter=q.iterator();
		
		while(iter.hasNext())
		{
			String key= (String) iter.next();
			
			Feature zona= tablaHashZonas.get(key);
			
			String nombre= zona.getPropiedades().getScanombre();
			
			letras= letras+ nombre.substring(0, 1);
		}

		
		
		HashTableLinearProbing<Character, Queue<String>> tabla= new HashTableLinearProbing<Character, Queue<String>>();
		
		while(n>0)
		{
			Object[] masFrecuente= letraMasAparece(letras);
			
			char letra= (char) masFrecuente[0];
			int veces= (int)masFrecuente[1];

			
			Queue zonas= zonasEmpiezanPorLetra(letra);
			
			
			tabla.put(letra, zonas);
			
			n--;
			String reemplazo= ""+letra;
			letras=letras.replace(reemplazo, "");
		}
		
		
		return tabla;
	}
	
	public Queue zonasEmpiezanPorLetra(char c)
	{
		Queue zonas= new Queue();
		
		Queue q=(Queue) tablaHashZonas.keys();
		Iterator iter=q.iterator();
		
		while(iter.hasNext())
		{
			String key= (String) iter.next();
			
			Feature zona= tablaHashZonas.get(key);
			
			String nombre= zona.getPropiedades().getScanombre();
			
			char primeraLetra= nombre.charAt(0);

			if(primeraLetra==c)
			{
				zonas.enqueue(nombre);
			}
		}
		return zonas;
	}
	
	public Object[] letraMasAparece (String texto)
	{
		Object [] arreglo= new Object[2];
		
		char[] textoArray = texto.toCharArray();
		
		char letraMas='@';
		int veces=1;
		
		
		int i=0;
		while(i<textoArray.length)
		{	
			int vecesTemp=1;
			char actual= textoArray[i];
			int j=i+1;
			while(j< textoArray.length)
			{
				char comparar=textoArray[j];
				
				if(actual==comparar)
				{
					vecesTemp++;
				}
				j++;
			}
			if(vecesTemp>veces)
			{
				veces=vecesTemp;
				letraMas=actual;
			}
			i++;
		}
		arreglo[0]= letraMas;
		arreglo[1]= veces;
		
		return arreglo;
	}
	
	public RedBlackBST req2A(double latitud, double longitud)
	{
		//Retornaremos un arbol balanceado con los nodos que se encuentran entre las coordenadas dadas por parámetro
		RedBlackBST<String,Object[]> arbol= new RedBlackBST();
		
		Queue q=(Queue) tablaHashZonas.keys();
		Iterator iter=q.iterator();
		
		while(iter.hasNext())
		{
			String key= (String) iter.next();
			
			Feature zona= tablaHashZonas.get(key);
	
			double [][][][] coordenadas= zona.getGeometrias().getCoordinates();
			
			int i=0;
			while(i<coordenadas.length)
			{
				int j=0;
				while(j<coordenadas[i].length)
				{
					int z=0;
					while(z<coordenadas[i][j].length)
					{
						int w=0;
						while(w<coordenadas[i][j][z].length)
						{
							double[] coordenada= coordenadas[i][j][z];
							
							double lat= coordenada[1];
							double lon= coordenada[0];
							
							if(Math.floor(lat*1000)==Math.floor(latitud*1000)&Math.floor(lon*1000)==Math.floor(longitud*1000))
							{
								Object[] nodo= new Object[3];
								
								nodo[0]=zona.getPropiedades().getScanombre();
								nodo[1]= lat;
								nodo[2]=lon;
								String llave= zona.getPropiedades().getMOVEMENT_ID();

								arbol.put(llave, nodo);
							}
							w++;
						}
						z++;
					}
					j++;
				}
				i++;
			}	
		}
		
		return arbol;
	
	}
	
	public MaxHeapCP req3A(double tiempoMenor, double tiempoMayor , int n)
	{
		//Retornaremos un heap con los N viajes, los cuales su meanTravelTime se encuentre entre el rango de tiempos dados
		MaxHeapCP<TravelTime> copia= heapMes;
		MaxHeapCP<TravelTime> heap= new MaxHeapCP();
		
		while(!copia.isEmpty()&& (n)>0)
		{
			TravelTime actual=(TravelTime) copia.delMax();
			double tiempo= actual.getMeanTravelTime();
			
			if(tiempo>tiempoMenor && tiempo<tiempoMayor)
			{
				heap.insert(actual);
				n--;
			}
		}
		return heap;
		
	}
	
	/**
	 * Mostrar las N zonas que estan más al norte
	 * @param N
	 * @return una HTLP con las N zonas mas al norte
	 */
	public HashTableLinearProbing req1B( int N )
	{
		HashTableLinearProbing retorno = new HashTableLinearProbing<>();

		//Que una zona esté más al norte implica que su latitud es mayor
		// Arreglo de coordenadas

		Queue<Feature> zonas = new Queue<>(); //Queue de zonas

		Queue que = (Queue) tablaHashZonas.keys();
		Queue auxiliar = new Queue<>(); 

		Iterator iter = que.iterator();
		while ( iter.hasNext()) 
		{
			String actual = (String) iter.next(); //Key actual
			Feature zona = (Feature)tablaHashZonas.get(actual); //Valor del Key, es decir la zona


			double [][][][] coordenadas= zona.getGeometrias().getCoordinates();

			int i=0;
			while(i<coordenadas.length)
			{
				int j=0;
				while(j<coordenadas[i].length)
				{
					int z=0;
					while(z<coordenadas[i][j].length)
					{
						int w=0;
						while(w<coordenadas[i][j][z].length)
						{
							double[] coordenada= coordenadas[i][j][z];

							double lat= coordenada[1];
							double lon= coordenada[0];

							Object[] nodo= new Object[3];
							nodo[1]= lat;
							nodo[2]=lon;

							auxiliar.enqueue(lat); //Agregar todas las latitudes al queue auxiliar
						}
						w++;
					}
					z++;
				}
				j++;
			}
			i++;
			
			double[] vectorLatitudes; 
			int m = 0; 
			Iterator iter2 = auxiliar.iterator();  //Iterar sobre la queue de double para despues agregarlos a un vector y hacer bubble sort
			while( iter2.hasNext()) 
			{
				double actual3 = (double) iter2.next(); 
				vectorLatitudes[i] = actual3;
				i++; 
			}


			bubbleSort(vectorLatitudes); //Organizar las latitudes descendentemente; 
			
			
			//for(int i = 0; i < N; i++)
			//{
			//	Seleccionar las N latitudes y despues imprimir el nombre y las coordenadas de la zona mas al norte 
			//}
		}	

		//Hasta aquí voy

		


		return retorno; 
	}












	public void bubbleSort(double[] arr) 
	{	 
		int n = arr.length; 
		for (int i = 0; i < n-1; i++) 
			for (int j = 0; j < n-i-1; j++) 
				if (arr[j] > arr[j+1]) 
				{ 
					// swap arr[j+1] and arr[i] 
					double temp = arr[j]; 
					arr[j] = arr[j+1]; 
					arr[j+1] = temp; 
				} 		
	}






	
	public RedBlackBST req2B(double latitud, double longitud)
	{
		//Retornaremos un arbol balanceado con los nodos que se encuentran entre las coordenadas dadas por parámetro
		return null;
	}
	
	public MaxHeapCP req3B()
	{
		//Retornaremos un heap con los N viajes, los cuales su desviación estándar se encuentre entre el rango de tiempos dados
		return null;
	}
	
	public MaxHeapCP req1C(int idZonaSalida, int hora )
	{
		//Retornaremos un heap con las viajes que cumplen las características dadas por parámetro
		MaxHeapCP<TravelTime> copia= heapHoras;
		MaxHeapCP<TravelTime> heap= new MaxHeapCP();
		
		while(!copia.isEmpty())
		{
			TravelTime actual=(TravelTime) copia.delMax();
			int salida=actual.getSourceID();
			int h=actual.getIdentificador();
			
			if(salida==idZonaSalida && hora==h)
			{
				heap.insert(actual);
			}
		}
		return heap;
	}
	
	public MaxHeapCP req2C(int idZonaLlegada, int horaMenor, int horaMayor)
	{
		//Retornaremos una heap con los viajes que cumplan esa característica y se encuentren entre ese rango de zonas
		MaxHeapCP<TravelTime> copia= heapHoras;
		MaxHeapCP<TravelTime> heap= new MaxHeapCP();
		
		while(!copia.isEmpty())
		{
			TravelTime actual=(TravelTime) copia.delMax();
			int llegada=actual.getDstID();
			int h=actual.getIdentificador();
			
			if(llegada==idZonaLlegada && h>horaMenor&&h<horaMayor)
			{
				heap.insert(actual);
			}
		}
		return heap;
	}
	
	public HashTableLinearProbing req3C(int n)
	{
		//Retornaremos una tabla de hash con las n zonas con mayor cantidad de nodos que definen su frontera
		HashTableLinearProbing<String,Integer> datos= new HashTableLinearProbing<String,Integer>();
		HashTableLinearProbing<String,Feature> copia= tablaHashZonas;
		
		while(n>0)
		{
			Queue q=(Queue) copia.keys();
			Iterator iter=q.iterator();
			
			String nombre="No encontro";
			int nodosMax=0;
			String keyMax="";
			
			
			while(iter.hasNext())
			{
				String key= (String) iter.next();
				
				Feature zona= copia.get(key);
		
				double [][][][] coordenadas= zona.getGeometrias().getCoordinates();
				
				int numeroNodos=0;
				
				int i=0;
				while(i<coordenadas.length)
				{
					int j=0;
					while(j<coordenadas[i].length)
					{
						int z=0;
						while(z<coordenadas[i][j].length)
						{
							numeroNodos ++;
							z++;
						}
						j++;
					}
					i++;
				}
				
				if(numeroNodos>nodosMax)
				{
					nodosMax=numeroNodos;
					nombre= zona.getPropiedades().getScanombre();
					keyMax=key;
				}
			}
			
			copia.delete(keyMax);
			
			datos.put(nombre, nodosMax);
			
			n--;
		}
		
		
		return datos;
	}
	
	public int[] req4C()
	{
		//Retornarenmos un arreglo del tamaño de zonas existentes con la cantidadDeDatosFaltantes de cada zona en orden ascendente
		int[] cantidadDeDatosFaltantes= new int [tablaHashZonas.size()];
		
		return cantidadDeDatosFaltantes;
	}
	
	
	
}
