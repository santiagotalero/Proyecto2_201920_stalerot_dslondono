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
	private HashTableLinearProbing tablaHashZonas;
	private MaxHeapCP heapHoras;
	private MaxHeapCP heapMes;
	private RedBlackBST arbolNodosMV;
	

	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		tablaHashZonas= new HashTableLinearProbing();
		heapHoras= new MaxHeapCP();
		heapMes= new MaxHeapCP();
		arbolNodosMV= new RedBlackBST();
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
		
	}
	
	
	public HashTableLinearProbing req1A(int n)
	{
		//Retornaremos una tabla de hash con las zonas que empiezan por las N letras más frecuentes
		return null;
	}
	
	public RedBlackBST req2A(double latitud, double longitud)
	{
		//Retornaremos un arbol balanceado con los nodos que se encuentran entre las coordenadas dadas por parámetro
		return null;
	}
	
	public MaxHeapCP req3A(double tiempoMenor, double tiempoMayor , int n)
	{
		//Retornaremos un heap con los N viajes, los cuales su meanTravelTime se encuentre entre el rango de tiempos dados
		return null;
	}
	
	public HashTableLinearProbing req1B(int N)
	{
		//Retornaremos una tabla de hash con las N zonas más al norte
		return null;
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
		return null;
	}
	
	public MaxHeapCP req2C(int idZonaLlegada, int horaMenor, int horaMayor)
	{
		//Retornaremos una heap con los viajes que cumplan esa característica y se encuentren entre ese rango de zonas
		return null;
	}
	
	public HashTableLinearProbing req3C(int n)
	{
		//Retornaremos una tabla de hash con las n zonas con mayor cantidad de nodos que definen su frontera
		return null;
	}
	
	public int[] req4C()
	{
		//Retornarenmos un arreglo del tamaño de zonas existentes con la cantidadDeDatosFaltantes de cada zona en orden ascendente
		int[] cantidadDeDatosFaltantes= new int [tablaHashZonas.size()];
		
		return cantidadDeDatosFaltantes;
	}
	
	
	
}
