package mda_negocio;

public class Movil {
	public String matricula;
	public Geoloc ubicacionActual;
	public boolean activo;
	
	public Movil () { 
		matricula = "noInizializado";
		ubicacionActual = new Geoloc();
		activo = false; 
	}
}
