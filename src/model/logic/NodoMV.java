package model.logic;

public class NodoMV 
{
	private int id;
	private double longitud;
	private double latitud;
	
	public NodoMV(int id, double longitud, double latitud) {
		super();
		this.id = id;
		this.longitud = longitud;
		this.latitud = latitud;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	
}
