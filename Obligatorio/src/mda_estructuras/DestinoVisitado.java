package mda_estructuras;

public class DestinoVisitado {
	public int cantidadDeVecesVisitado;
	public double lat;
	public double lon;
	
	public boolean esMismaGeoloc(double pLat, double pLon) {
		return (pLat == lat && pLon == lon);
	}
	
	public boolean esMismaGeoloc(Geoloc pGeoloc) {
		return (lat == pGeoloc.lat && lon == pGeoloc.lon);
	}
	
	public String toString() {
		return " lat: " + lat + " ; "  + " lon: " + lon + " ; " + " cant:  " + cantidadDeVecesVisitado;
	}
	
	public DestinoVisitado() {
		cantidadDeVecesVisitado = 0;
		lat = 0;
		lon = 0;
	}
}
