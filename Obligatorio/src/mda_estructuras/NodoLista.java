package mda_estructuras;


public class NodoLista {
	public String datoString;
	public ParDouble datoParDouble;
	public int datoInt;
	
	public NodoLista sig;

	
	public NodoLista() {
		datoParDouble = new ParDouble();
		datoString = "";
		datoInt = 0;
		sig = null;
	}
}
