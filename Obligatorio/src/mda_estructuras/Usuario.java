package mda_estructuras;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario {
	public String email;
	public String nombre;
	public String password;
	
	public Geoloc ubicacionActual;
	
	public String infoUsuario() {
		return email + ";" + nombre;
	}
	
	public static boolean validarEmail(String pEmail) {
		// https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
		// 5. Regex to restrict no. of characters in top level domain [Recommended]
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(pEmail);
		return matcher.matches();
	}
	
	// TODO: crear validacion de nombreYContraseña
	public static boolean validarNombreYPassword(String pNombre, String pPassword) {
		
		
		return true;
	}
	
	public Usuario() {
		email = "noInizializado";
		nombre = "noInizializado";
		password = "noInizializado";
		ubicacionActual = new Geoloc();
	}

}
