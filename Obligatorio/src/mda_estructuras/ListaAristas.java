package mda_estructuras;
import mda_utilidades.I;

public class ListaAristas {
	NodoLista inicio;
	int largo;
	
	
	public AristaGrafo contieneArista(AristaGrafo pArista) {
		AristaGrafo resultado = null;
		if (largo == 0) {
			resultado = null;
		}else if (largo == 1){
			if(inicio.datoArista.equals(pArista)) {
				resultado = inicio.datoArista;
			}else {
				resultado = null;
			}
		}else{
			NodoLista nodoBuscador = inicio;
			int indice = 0;
			boolean encontro = false;
			
			while (!encontro && nodoBuscador != null) {
				if(nodoBuscador.datoArista.equals(pArista)) {
					encontro = true;
				}else {
					nodoBuscador = nodoBuscador.sig;
					indice++; 
				}
			}
			
			if(encontro) {
				resultado = nodoBuscador.datoArista;
			}else {
				resultado = null;
			}
		}
		
		return resultado;
	}
	
	
	public int buscarPrimerIndiceDeDato(NodoLista pDato) {
		int resultado;// = -1;
		if (largo == 0) {
			resultado = -1;
		}else if (largo == 1){
			if(inicio.datoArista.equals(pDato.datoArista)) {
				resultado = 0;
			}else {
				resultado = -1;
			}
		}else{
			NodoLista nodoBuscador = inicio;
			int indice = 0;
			boolean encontro = false;
			
			while (!encontro && nodoBuscador != null) {
				if(nodoBuscador.datoArista.equals(pDato.datoArista)) {
					encontro = true;
				}else {
					nodoBuscador = nodoBuscador.sig;
					indice++; 
				}
			}
			
			if(encontro) {
				resultado = indice;
			}else {
				resultado = -1;
			}
		}
		return resultado;
	}
	
	public NodoLista buscarDatoEnIndice(int pIndice) {
		NodoLista resultado = null;
		if(pIndice >= largo) {
			resultado = null;;
		}
		else if (largo == 0) {
			resultado = null;;
		}else if (largo == 1 && pIndice == 1){
			resultado = inicio;
		}else{
			NodoLista nodoBuscador = inicio;
			int indice = 0;
			boolean encontro = false;
			
			while (!encontro && nodoBuscador != null) {
				if(pIndice == indice) {
					encontro = true;
					resultado = nodoBuscador;
				}else {
					nodoBuscador = nodoBuscador.sig;
					indice++; 
				}
			}
			
			if(!encontro) {
				resultado = null;
			}
		}
		return resultado;	
	}	
	

	public void imprimirAscendente() {
		if(largo == 0) {
			I.Log("lista vacía.");
		}else {
			NodoLista nodoBuscador = inicio;
			StringBuilder s = new StringBuilder();
			s.append(nodoBuscador.datoArista.toString());
			s.append(", ");
			
			while (nodoBuscador.sig != null) {
				nodoBuscador = nodoBuscador.sig;
				s.append(nodoBuscador.datoArista.toString());
				
				s.append(", ");
			}
			//borramos el ultimo comaEspacio y agregamos un punto.
			s.delete(s.length() -2 , s.length());
			s.append(".");
			I.Log(s);
		}
	}
	
	public void imprimirAscendente(String pSeparador, boolean pUsarSeparador,  String pFinal, boolean pUsarFinal) {
		if(largo == 0) {
			I.Log("lista vacía.");
		}else {
			NodoLista nodoBuscador = inicio;
			StringBuilder s = new StringBuilder();
			s.append(nodoBuscador.datoArista.toString());
			if(pUsarSeparador) {
				s.append(pSeparador);				
			}
			while (nodoBuscador.sig != null) {
				nodoBuscador = nodoBuscador.sig;
				s.append(nodoBuscador.datoArista.toString());
				
				if(pUsarSeparador) {
					s.append(pSeparador);				
				}			}
			//borramos el ultimo separador y agregamos un punto.
			if(pUsarSeparador) {
				s.delete(s.length() -pSeparador.length() , s.length());				
			}
			if(pUsarFinal) {
				s.append(pFinal);				
			}
			I.Log(s);
		}
	}
	
	public String concatenarAscendente(String pSeparador, boolean pUsarSeparador,  String pFinal, boolean pUsarFinal) {
		if(largo == 0) {
			return ("lista vacía.");
		}else {
			NodoLista nodoBuscador = inicio;
			StringBuilder s = new StringBuilder();
			s.append(nodoBuscador.datoArista.toString());
			if(pUsarSeparador) {
				s.append(pSeparador);				
			}
			while (nodoBuscador.sig != null) {
				nodoBuscador = nodoBuscador.sig;
				s.append(nodoBuscador.datoArista.toString());
				
				if(pUsarSeparador) {
					s.append(pSeparador);				
				}			}
			//borramos el ultimo separador y agregamos un punto.
			if(pUsarSeparador) {
				s.delete(s.length() -pSeparador.length() , s.length());				
			}
			if(pUsarFinal) {
				s.append(pFinal);				
			}
			return s.toString();
		}
	}		

	public void imprimirDescendente() {
		if(largo == 0) {
			I.Log("lista vacía.");
		}else {
			NodoLista nodoBuscador = inicio;
			ListaString listaInvertida = new ListaString();
			listaInvertida.insertarAlPrincipio(inicio.datoArista.toString());
			
			while (nodoBuscador.sig != null) {
				nodoBuscador = nodoBuscador.sig;
				listaInvertida.insertarAlPrincipio(nodoBuscador.datoArista.toString());
			}
			listaInvertida.imprimirAscendente();
		}
	}

	public void insertarAlFinal(AristaGrafo pDato) {
		if(pDato == null) {
			I.Log("pDato null, insercion fallida");
			return;
		}
		
		NodoLista nuevoNodo = new NodoLista();
		nuevoNodo.datoArista = pDato; 
		
		if (largo == 0) {
			inicio = nuevoNodo;
		}else {
			NodoLista nodoBuscador = inicio;
			while (nodoBuscador.sig != null) {
				nodoBuscador = nodoBuscador.sig;
			}
			
			nodoBuscador.sig = nuevoNodo;
		}
		
		largo++; 
	}

	public void insertarAlPrincipio(AristaGrafo pDato) {
		if(pDato == null) {
			I.Log("pDato null, insercion fallida");
			return;
		}		
		
		if (largo == 0) {
			inicio = new NodoLista();
			inicio.datoArista = pDato;
		}else {
			NodoLista restoDeLista = inicio;
			inicio = new NodoLista();
			inicio.datoArista = pDato;
			inicio.sig = restoDeLista;
		}
		
		largo++; 
	}
	
	public ListaAristas() {
		largo = 0;
		inicio = null;
	}
}
