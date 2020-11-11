package mda_estructuras;

public class VerticeGrafo {
	public Geoloc posicion;
	
	public static enum TipoDeVertice {SinInicializar, Esquina, Movil, Delivery};
	public TipoDeVertice tipoDeVertice;
	public String cedulaDelivery;
	public String matriculaMovil;
	public int indice;
	
	
	public String toString() {
		return "pos: " + posicion.toString() + " |tipoDeVertice: " + tipoDeVertice; 
	}
	
	// lo unico que importa para que un vertice sea el mismo vertice es la geolocalizacion
	public boolean equals(VerticeGrafo pV) {
		return pV.posicion.equals(pV.posicion);  
	}	
	
	public VerticeGrafo() {
		posicion = new Geoloc();
		tipoDeVertice = TipoDeVertice.SinInicializar;
		cedulaDelivery = "";
		matriculaMovil = "";
		indice = 0;
	}
}
