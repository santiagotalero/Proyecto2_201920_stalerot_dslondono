package model.logic.ClasesArchivoJSon;

public class FeatureCollection 
{
	private Feature[] features;

	public Feature[] getFeatures() {
		return features;
	}

	public void setFeatures(Feature[] features) {
		this.features = features;
	}

	public FeatureCollection(Feature[] features) {
		super();
		this.features = features;
	}
	
}
