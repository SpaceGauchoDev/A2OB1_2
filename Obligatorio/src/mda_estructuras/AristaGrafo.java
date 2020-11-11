package mda_estructuras;

public class AristaGrafo {
	public VerticeGrafo a;
	public VerticeGrafo b;
	public int distancia;
	public int tiempo;
	
	public String toString() {
		return "a: " + a.toString() + " |b: " + b.toString() + " |d: "  + distancia + "|t: " + tiempo; 
	}
	
	// lo unico que importa para que una arista sea la misma arista es la geolocalizacion de los vertices que lo componen
	public boolean equals(AristaGrafo pA) {
		return a.equals(pA.a) && b.equals(pA.b);  
	}
	
	public AristaGrafo() {
		a = new VerticeGrafo();
		b = new VerticeGrafo();
		distancia = -1;
		tiempo = -1;
	}
}
