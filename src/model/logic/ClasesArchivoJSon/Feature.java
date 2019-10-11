package model.logic.ClasesArchivoJSon;

public class Feature 
{
	private geometry geometrias;
	private properties  propiedades;
	public Feature(geometry geometrias, properties propiedades) {
		super();
		this.geometrias = geometrias;
		this.propiedades = propiedades;
	}
	public geometry getGeometrias() {
		return geometrias;
	}
	public void setGeometrias(geometry geometrias) {
		this.geometrias = geometrias;
	}
	public properties getPropiedades() {
		return propiedades;
	}
	public void setPropiedades(properties propiedades) {
		this.propiedades = propiedades;
	}
}
