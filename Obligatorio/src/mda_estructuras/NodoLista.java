package mda_estructuras;


public class NodoLista {
	public String datoString;
	public ParDouble datoParDouble;
	public int datoInt;
	public DestinoVisitado datoDestinoVisitado;
	public AristaGrafo datoArista;
	
	public NodoLista sig;

	public NodoLista() {
		datoParDouble = new ParDouble();
		datoDestinoVisitado = new DestinoVisitado();
		datoArista = new AristaGrafo();
		datoString = "";
		datoInt = 0;
		sig = null;
	}
}
