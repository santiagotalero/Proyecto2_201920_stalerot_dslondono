package view;

import model.logic.MVCModelo;

public class MVCView 
{
	    /**
	     * Metodo constructor
	     */
	    public MVCView()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("--------- \n PROYECTO 2 ESTRUCTURAS DE DATOS \n---------");
			System.out.println("Pulse 1 para cargar archivos. (SOLO SE PUEDE HACER UNA VEZ)");
			System.out.println("Pulse 2 para requerimiento 1A. ");
			System.out.println("Pulse 3 para requerimiento 2A. ");
			System.out.println("Pulsa 4 para requerimiento 3A");
			System.out.println("Pulsa 5 para requerimiento 1B");
			System.out.println("Pulsa 6 para requerimiento 2B");
			System.out.println("Pulsa 7 para requerimiento 3B");
			System.out.println("Pulsa 8 para requerimiento 1C");
			System.out.println("Pulsa 9 para requerimiento 2C");
			System.out.println("Pulsa 10 para requerimiento 3C");
			System.out.println("Pulsa 11 para requerimiento 4C");
			System.out.println("Pulsa 12 para salir");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		

}
