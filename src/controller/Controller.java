package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import model.data_structures.HashTableLinearProbing;
import model.data_structures.MaxHeapCP;
import model.data_structures.Queue;
import model.data_structures.RedBlackBST;
import model.logic.MVCModelo;
import model.logic.SortComparator;
import model.logic.TravelTime;
import model.logic.ClasesArchivoJSon.Feature;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;
	
	/* Instancia de la Vista*/
	private MVCView view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new MVCView();
		modelo = new MVCModelo();
	}
		
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";
		

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
				case 1:
					
					//CARGA ARCHIVOS
					
					try {
						modelo.cargarArchivos();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;

				case 2:
					System.out.println("Ingrese el número de letras frecuentas a consultar:");
					int n= lector.nextInt();
					
					HashTableLinearProbing tabla= modelo.req1A(n);
					
					Queue q=(Queue) tabla.keys();
					Iterator iter=q.iterator();
					
					Queue[] z= new Queue[n];
					char[] c= new char[n];
					
					int i=0;
					while(iter.hasNext())
					{
						char key= (char) iter.next();
						
						c[i]=key;

						Queue zonas= (Queue) tabla.get(key);
						
						z[i]=zonas;	
						
						i++;
					}

					
					int j=0;
					
					while(j<n)
					{
						int masZonas=0;
						int indice=0;
						Queue mas=null;
						char letra='@';
						i=0;
						
						while(i<c.length)
						{
							char l= c[i];
							Queue k= z[i];

							if(k!=null && k.size()>masZonas)
							{
								letra=l;
								masZonas=k.size();
								mas= k;
								indice=i;
							}
							i++;
						}
						Queue vacia= new Queue();
						z[indice]=vacia;
						
						System.out.println("Letra: " + letra);
						System.out.println("");
						
						Iterator iterZ= mas.iterator();
						
						while(iterZ.hasNext())
						{
							String actual= (String) iterZ.next();
							System.out.println("Nombre zona: "+ actual);
						}
						System.out.println("");
						j++;
					}
				

					break;
				case 3:
					
					System.out.println("Ingrese la latitud del nodo:");
					double latitud= lector.nextDouble();
					
					System.out.println("Ingrese la longitud del nodo:");
					double longitud= lector.nextDouble();
					
					RedBlackBST<String, Object[]> arbol= modelo.req2A(latitud, longitud);
					
					System.out.println("");
					System.out.println(arbol.size()+" nodos encontrados.");
					System.out.println("");
					
					Queue q3=(Queue) arbol.keys();
					Iterator iter3=q3.iterator();
					
					while(iter3.hasNext())
					{
						String key= (String) iter3.next();
						
						Object[] datos= arbol.get(key);
						
						String nombre=(String) datos[0];
						double lat=(double) datos[1];
						double lon=(double) datos[2];
						
						System.out.println(nombre + " : "+ lat+" , "+ lon);
					}
					
					
					break;
				case 4: 
					System.out.println("Ingrese un tiempo menor del rango: ");
					int horaMenor= lector.nextInt();
					
					System.out.println("Ingrese un tiempo mayor del rango:");
					int horaMayor= lector.nextInt();
					
					System.out.println("Ingrese la cantidad de viajes: ");
					n= lector.nextInt();
					
					MaxHeapCP<TravelTime> heap= modelo.req3A(horaMenor, horaMayor, n);
					
					while(!heap.isEmpty())
					{
						TravelTime actual= heap.delMax();

						System.out.println("Zona origen: "+ actual.getSourceID()+", zona destino: "+actual.getDstID()+", mes:"+actual.getIdentificador()+", tiempo promedio: "+actual.getMeanTravelTime());

					}
					
					
					
					break;
				case 5: 
					System.out.println("Ingrese la cantidad de zonas: ");
					int n5= lector.nextInt();
					
					HashTableLinearProbing<String,double[]> tabla5= modelo.req1B(n5);
					
					Queue q5=(Queue) tabla5.keys();
					Iterator iter5=q5.iterator();
					
					while(iter5.hasNext())
					{
						String nombreZona= (String) iter5.next();
						
						double[] coordenada= tabla5.get(nombreZona);
						
						double lat= coordenada[0];
						
						double lon =  coordenada[1];
						
						System.out.println("Zona: "+ nombreZona+", coordenada: "+ lat + ", "+ lon);
					}
					break;
				case 6: 
					System.out.println("Ingrese la latitud del nodo:");
					double latitud6= lector.nextDouble();
					
					System.out.println("Ingrese la longitud del nodo:");
					double longitud6= lector.nextDouble();
					
					RedBlackBST<Integer, double[]> arbol6= modelo.req2B(latitud6, longitud6);
					
					System.out.println("");
					System.out.println(arbol6.size()+" nodos encontrados.");
					System.out.println("");
					
					Queue<Integer> q6=(Queue<Integer>) arbol6.keys();
					Iterator<Integer> iter6=q6.iterator();
					
					while(iter6.hasNext())
					{
						int key= iter6.next();
						
						double[] datos= arbol6.get(key);
						
						double lat=datos[0];
						double lon=datos[1];
						
						System.out.println("Id: "+key + " : "+ lat+" , "+ lon);
					}
					
					break;
				case 7: 
					//REQ3B
					break;
				case 8: 
					System.out.println("Ingrese una zona de salida: ");
					int idZonaSalida= lector.nextInt();
					
					System.out.println("Ingrese una hora del dia: ");
					int hora= lector.nextInt();
					
					MaxHeapCP<TravelTime>heap8= modelo.req1C(idZonaSalida, hora);
					
					while(!heap8.isEmpty())
					{
						TravelTime actual= heap8.delMax();

						System.out.println("Zona origen: "+ actual.getSourceID()+", zona destino: "+actual.getDstID()+", hora:"+actual.getIdentificador()+", tiempo promedio: "+actual.getMeanTravelTime());

					}
					break;
				case 9: 
					System.out.println("Ingrese una zona de llegada: ");
					int idZonaLlegada= lector.nextInt();
					
					System.out.println("Ingrese la hora menor: ");
					int horaMenor9= lector.nextInt();
					
					System.out.println("Ingrese la hora mayor: ");
					int horaMayor9= lector.nextInt();
					
					MaxHeapCP<TravelTime> heap9= modelo.req2C(idZonaLlegada, horaMenor9, horaMayor9);
					
					while(!heap9.isEmpty())
					{
						TravelTime actual= heap9.delMax();

						System.out.println("Zona origen: "+ actual.getSourceID()+", zona destino: "+actual.getDstID()+", hora:"+actual.getIdentificador()+", tiempo promedio: "+actual.getMeanTravelTime());
					}
					
					break;
				case 10: 
					
					System.out.println("Ingrese la cantidad de zonas: ");
					int n10= lector.nextInt();
					
					HashTableLinearProbing<String,Integer> tabla10= modelo.req3C(n10);
					
					Queue q10=(Queue) tabla10.keys();
					Iterator iter10=q10.iterator();
					
					while(iter10.hasNext())
					{
						String nombreZona= (String) iter10.next();
						
						int numeroNodos= tabla10.get(nombreZona);
						
						System.out.println("Zona: "+ nombreZona+", numero de nodos: "+ numeroNodos);
					}
					
					break;
				case 11: 
					
					double[] porcentajes=modelo.req4C();
					
					System.out.println("Porcentaje de datos faltantes por zona");
					
					int i11=0;
					
					while(i11<porcentajes.length)
					{
						int zona=i11+1;
						String linea=zona+"|";
						double cantidadDeAsteriscos= porcentajes[i11]/2;
						while(cantidadDeAsteriscos>0)
						{
							linea= linea+"*";
							cantidadDeAsteriscos--;
						}
						System.out.println(linea);
						i11++;
					}
					
					
					
					break;

				case 12: 
					System.out.println("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;	

				default: 
					System.out.println("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
