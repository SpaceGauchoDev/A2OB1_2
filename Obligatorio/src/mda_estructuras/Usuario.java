package mda_estructuras;

public class Usuario {
	public String email;
	public String nombre;
	public String password;
	
	public Geoloc ubicacionActual;
	
	public Usuario() {
		email = "noInizializado";
		nombre = "noInizializado";
		password = "noInizializado";
		ubicacionActual = new Geoloc();
	}

}
