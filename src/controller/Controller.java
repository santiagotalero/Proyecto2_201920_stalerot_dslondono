package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import model.data_structures.Queue;
import model.logic.MVCModelo;
import model.logic.TravelTime;
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
					
					//REQ1A

					break;
				case 3:
					//REQ2A
					break;
				case 4: 
					//REQ3A
					break;
				case 5: 
					//REQ1B
					break;
				case 6: 
					//REQ2B
					break;
				case 7: 
					//REQ3B
					break;
				case 8: 
					//REQ1C
					break;
				case 9: 
					//REQ2C
					break;
				case 10: 
					//REQ3C
					break;
				case 11: 
					//REQ4C
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
