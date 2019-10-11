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
	private HashTableLinearProbing tablaHash;
	private MaxHeapCP heap;
	private RedBlackBST arbol;
	

	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		tablaHash= new HashTableLinearProbing();
		heap= new MaxHeapCP();
		arbol= new RedBlackBST();
	}
	
	
	public void cargarArchivos() throws IOException
	{
		//ARCHIVOS DE HORA
		
		
		
		
		//ARCHIVOS DE MES
		
		

		//ARCHIVO TXT (CSV)
		
		
		
		//ARCHIVO JSON
		String path= "./data/bogota_cadastral.json";
		
		Gson gson = new Gson();
		
		JsonReader reader;
		
		FeatureCollection a=null;
		try{
			reader= new JsonReader(new FileReader(path));
			a= gson.fromJson(reader, FeatureCollection.class);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		
		int i=0;
		
		while(i<a.getFeatures().length)
		{
			Feature actual= a.getFeatures()[i];
			
			tablaHash.put(actual.getPropiedades().getMOVEMENT_ID(), actual);
			i++;
		}
		
		
	}
	
	
	public void req1A()
	{
		
	}
	
	public void req2A()
	{
		
	}
	
	public void req3A()
	{
		
	}
	
	public void req1B()
	{
		
	}
	
	public void req2B()
	{
		
	}
	
	public void req3B()
	{
		
	}
	
	public void req1C()
	{
		
	}
	
	public void req2C()
	{
		
	}
	
	public void req3C()
	{
		
	}
	
	public void req4C()
	{
		
	}
	
	
	
}
