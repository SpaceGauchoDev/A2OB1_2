package mda_estructuras;

public class VerticeGrafo {
	public Geoloc posicion;
	
	public static enum TipoDeVertice {SinInicializar, Esquina, Movil, Delivery};
	public TipoDeVertice tipoDeVertice;
	public String cedulaDelivery;
	public String matriculaMovil;
	public boolean activo;
	public int indice;
	
	public int distanciaEnAristas;
	public int distanciaEnMetros;
	public int distanciaEnMinutos;
	public String caminoRecorrido;
	
	/*
	public void activar() {
		activo = true;
	}
	
	public void desactivar() {
		activo = false;
	}
	*/
	
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
		activo = false;
		
		distanciaEnAristas = 0;
		distanciaEnMetros = 0;
	}
}
