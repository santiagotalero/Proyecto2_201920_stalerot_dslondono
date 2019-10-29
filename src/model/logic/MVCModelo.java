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
	private TravelTime[] arregloHeapHoras;
	

	
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
	
	private Queue zonasEmpiezanPorLetra(char c)
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
	
	private Object[] letraMasAparece (String texto)
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
		MaxHeapCP<TravelTime> heap= new MaxHeapCP();
		MaxHeapCP<TravelTime> copia= new MaxHeapCP<TravelTime>();
		
		while(!heapMes.isEmpty()&& (n)>0)
		{
			TravelTime actual=(TravelTime) heapMes.delMax();
			copia.insert(actual);
			double tiempo= actual.getMeanTravelTime();
			
			if(tiempo>tiempoMenor && tiempo<tiempoMayor)
			{
				heap.insert(actual);
				n--;
			}
		}
		heapMes=copia;
		return heap;
		
	}
	
		/**
	 * Mostrar las N zonas que estan más al norte
	 * @param N
	 * @return una HTLP con las N zonas mas al norte
	 */
	public HashTableLinearProbing<String,double[]> req1B( int n )
	{
		HashTableLinearProbing<String, double[]> retorno = new HashTableLinearProbing<>();
		HashTableLinearProbing<String,Feature> copia= new HashTableLinearProbing<String,Feature>();

		while(n>0)
		{
			Queue que = (Queue) tablaHashZonas.keys(); //Una copia de la queue de Keys
			Iterator iter = que.iterator(); //Iterador de dicha copia

			double[] arrTempCoord = new double[2];

			//Variables temporales
			double latTemp = 0.0; 
			String nomTemp = ""; 
			double longTemp = 0.0;
			String llaveTemp = "";
			Feature zonaTemp=null;


			while(iter.hasNext()) //Iterar sobre la copia de Keys
			{
				String keyActual= (String) iter.next(); //Key actual

				Feature zona = tablaHashZonas.get(keyActual);  //Valor de la Key actual, es decir la zona. 

				double [][][][] coordenadas= zona.getGeometrias().getCoordinates(); //Arreglo de coordenadas de la zona

				double latZona = 0.0; 
				String nomZona = ""; 
				double longZona = 0.0;
				String llaveZona = "";
				Feature zonaZona=null;
				
				
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
								double[] coordenada = coordenadas[i][j][z]; //Coordenadas en la posicion i, j & z 

								double lat = coordenada[1];
								double lon = coordenada[0];

								if(lat>latTemp)
								{
									latZona=lat;
									nomZona=zona.getPropiedades().getScanombre();
									longZona=lon;
									llaveZona=keyActual;
									zonaZona=zona;
								}

								w++;
							}
							z++;
						}
						j++;
					}
					i++;
				}
				
				if(latZona>latTemp)
				{
					latTemp=latZona;
					nomTemp=nomZona;
					longTemp=longZona;
					llaveTemp=llaveZona;
					zonaTemp=zonaZona;
				}
				
			}



			arrTempCoord[0] = latTemp; 
			arrTempCoord[1] = longTemp; 

			retorno.put(nomTemp, arrTempCoord );

			tablaHashZonas.delete(llaveTemp);
			copia.put(llaveTemp, zonaTemp );


			n--;
		}


		Queue q=(Queue) copia.keys();
		Iterator iter=q.iterator();
		
		while(iter.hasNext())
		{
			String key= (String) iter.next();
			
			Feature zona= copia.get(key);
			
			tablaHashZonas.put(key, zona);
		}




		return retorno;
	}






	
	public RedBlackBST<Integer,double[]> req2B(double latitud, double longitud)
	{

		//Se deben mostrar todos los nodos que tengan esas mismas latitud y longitud truncando a 2 cifras decimales

		RedBlackBST<Integer,double[]> retorno= new RedBlackBST<Integer,double[]>();

		Queue<Integer> que = (Queue<Integer>) arbolNodosMV.keys(); //Se convierte en un queue las llaves
		Iterator<Integer> iter = que.iterator(); //Iterator para iterar sobre el queue de llaves

		while(iter.hasNext())
		{
			int key= iter.next(); //Key actual

			NodoMV nodo = arbolNodosMV.get(key); //Nodo actual 

			double lat=nodo.getLatitud();
			double lon=nodo.getLongitud();

			if(Math.floor(lat*100) == Math.floor(latitud*100) && Math.floor(lon*100) == Math.floor(longitud*100))
			{
				double[] n= new double[2];


				n[0]= lat;
				n[1]= lon;


				retorno.put(nodo.getId(), n);
			}


		}

		return retorno;
	}
	
	public MaxHeapCP req3B()
	{
		//Retornaremos un heap con los N viajes, los cuales su desviación estándar se encuentre entre el rango de tiempos dados
		return null;
	}
	
	public MaxHeapCP req1C(int idZonaSalida, int hora )
	{
		//Retornaremos un heap con las viajes que cumplen las características dadas por parámetro

		MaxHeapCP<TravelTime> heap= new MaxHeapCP();
		MaxHeapCP<TravelTime> copia= new MaxHeapCP<TravelTime>();
		
		while(!heapHoras.isEmpty())
		{
			TravelTime actual=(TravelTime) heapHoras.delMax();
			copia.insert(actual);
			int salida=actual.getSourceID();
			int h=actual.getIdentificador();
			
			if(salida==idZonaSalida && hora==h)
			{
				heap.insert(actual);
			}
		}
		
		heapHoras=copia;
		
		return heap;
	}
	
	public MaxHeapCP req2C(int idZonaLlegada, int horaMenor, int horaMayor)
	{
		//Retornaremos una heap con los viajes que cumplan esa característica y se encuentren entre ese rango de zonas

		MaxHeapCP<TravelTime> heap= new MaxHeapCP();
		MaxHeapCP<TravelTime> copia= new MaxHeapCP<TravelTime>();
		
		while(!heapHoras.isEmpty())
		{
			TravelTime actual=(TravelTime) heapHoras.delMax();
			copia.insert(actual);
			int llegada=actual.getDstID();
			int h=actual.getIdentificador();
			
			if(llegada==idZonaLlegada && h>=horaMenor && h<=horaMayor)
			{
				heap.insert(actual);
			}
		}
		heapHoras=copia;
		return heap;
	}
	
	public HashTableLinearProbing req3C(int n)
	{
		//Retornaremos una tabla de hash con las n zonas con mayor cantidad de nodos que definen su frontera
		HashTableLinearProbing<String,Integer> datos= new HashTableLinearProbing<String,Integer>();
		HashTableLinearProbing<String,Feature> copia= new HashTableLinearProbing<String,Feature>();
		
		while(n>0)
		{
			Queue q=(Queue) tablaHashZonas.keys();
			Iterator iter=q.iterator();
			
			String nombre="No encontro";
			int nodosMax=0;
			Feature zonaMax=null;
			String keyMax="";
			
			
			while(iter.hasNext())
			{
				String key= (String) iter.next();
				
				Feature zona= tablaHashZonas.get(key);
		
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
					zonaMax=zona;
				}
			}
			
			tablaHashZonas.delete(keyMax);
			copia.put(keyMax, zonaMax);
			
			datos.put(nombre, nodosMax);
			
			n--;
		}
		
		Queue q=(Queue) copia.keys();
		Iterator iter=q.iterator();
		
		while(iter.hasNext())
		{
			String key= (String) iter.next();
			
			Feature zona= copia.get(key);
			
			tablaHashZonas.put(key, zona);
		}
		
		
		return datos;
	}
	
	public double[] req4C()
	{
		//Retornarenmos un arreglo del tamaño de zonas existentes con el porcentajeDeDatosFaltantes de cada zona en orden ascendente
		arregloHeapHoras= new TravelTime[heapMes.size()];
		arregloHeapHoras=heapToArray();
		
		double[] porcentajeDeDatosFaltantes= new double [1047];
		int datosPorZona=50256;
		
		int i=1;
		
		while(i<=20)
		{
			int datosReales= cantidadDeViajesDeUnaZonaOrigen(i);
			
			int datosFaltantes= datosPorZona-datosReales;
			double porcentaje= (100*datosFaltantes)/datosPorZona;
			porcentajeDeDatosFaltantes[i-1]=porcentaje;
			
			i++;
		}

		return porcentajeDeDatosFaltantes;
	}
	
	private int cantidadDeViajesDeUnaZonaOrigen(int idZona)
	{
		int contador=0;
		
		int i=0;
		while(i<arregloHeapHoras.length)
		{
			TravelTime actual=arregloHeapHoras[i];
			
			if(actual.getSourceID()==idZona)
			{
				contador++;
			}
			i++;
		}
		
		return contador;
	}
	private int cantidadDeZonasOrigen()
	{

		Queue<Integer> zonas= new Queue<Integer>();
		MaxHeapCP<TravelTime> copia= new MaxHeapCP<TravelTime>();

		while(!heapHoras.isEmpty())
		{
			TravelTime actual=(TravelTime) heapHoras.delMax();
			copia.insert(actual);
			int salida=actual.getSourceID();


			if(numeroNoEnQueue(zonas,salida))
			{
				zonas.enqueue(salida);
			}
		}

		heapHoras=copia;

		return zonas.size();
	}
	
	private boolean numeroNoEnQueue(Queue<Integer> q, int n)
	{
		Iterator<Integer> iter= q.iterator();
		
		while(iter.hasNext())
		{
			int actual= iter.next();
			
			if(actual==n)
			{
				return false;
			}
		}
		return true;
	}
	
	private TravelTime[] heapToArray()
	{
		TravelTime[] array= new TravelTime[heapHoras.size()];
		
		MaxHeapCP<TravelTime> copia= new MaxHeapCP<TravelTime>();

		int i=0;
		while(!heapHoras.isEmpty())
		{
			TravelTime actual=(TravelTime) heapHoras.delMax();
			copia.insert(actual);

			array[i]=actual;
			i++;
		}

		heapHoras=copia;

		return array;
	}
	
	
}
