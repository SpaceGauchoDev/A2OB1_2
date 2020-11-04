package mda_estructuras;

public class NodoABB {
	public Usuario datoUsuario;
	public NodoABB izq;
	public NodoABB der;
	public int iteraciones;
	public int altura;
	public int balance;
	
	/*
	// balance a partir de este nodo
	public int balance() {
		int resultado = 0;
		
		if(izq != null) {
			resultado+= izq.altura;
		} 
		if(der != null) {
			resultado-= der.altura; 
		}
		
		return resultado;
	}
	
	public void actualizar() {
		altura = alturaMaxima() + 1;
		balance = der.altura - izq.altura;
	}
	
	// altura maxima a partir de este nodo
	public int alturaMaxima() {
		int alturaDer = -1;
		int alturaIzq = -1;
		if(izq != null) {
			alturaIzq= izq.altura;
		} 
		if(der != null) {
			alturaDer= der.altura;
		}
		
		if (alturaDer >= alturaIzq) {
			return alturaDer;
		}else {
			return alturaIzq;
		}
	} 
	*/
	
	public NodoABB() {
		datoUsuario = null;
		izq = null;
		der = null;
		iteraciones = 0;
		altura = 0;
		balance = 0;
	}
}
