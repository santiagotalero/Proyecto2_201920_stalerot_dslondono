package model.logic.ClasesArchivoJSon;
public class geometry 
{
	private String type;
	private long[] coordinates;
	public geometry(String type, long[] coordinates) {
		super();
		this.type = type;
		this.coordinates = coordinates;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long[] getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(long[] coordinates) {
		this.coordinates = coordinates;
	}
}
