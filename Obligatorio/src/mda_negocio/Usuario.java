package mda_negocio;

public class Usuario {
	public String email;
	public String nombre;
	public String pasword;
	
	public Geoloc ubicacionActual;
	
	public Usuario() {
		email = "noInizializado";
		nombre = "noInizializado";
		nombre = "noInizializado";
		ubicacionActual = new Geoloc();
	}

}
