package mda_estructuras;
import mda_utilidades.I;

public class ListaString{
	NodoLista inicio;
	int largo;
	
	
	public int buscarPrimerIndiceDeDato(String pDato) {
		int resultado;// = -1;
		if (largo == 0) {
			resultado = -1;
		}else if (largo == 1){
			if(inicio.datoString == pDato) {
				resultado = 0;
			}else {
				resultado = -1;
			}
		}else{
			NodoLista nodoBuscador = inicio;
			int indice = 0;
			boolean encontro = false;
			
			while (!encontro && nodoBuscador != null) {
				if(pDato == nodoBuscador.datoString) {
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
			s.append(nodoBuscador.datoString);
			s.append(", ");
			
			while (nodoBuscador.sig != null) {
				nodoBuscador = nodoBuscador.sig;
				s.append(nodoBuscador.datoString);
				
				s.append(", ");
			}
			//borramos el ultimo comaEspacio y agregamos un punto.
			s.delete(s.length() -2 , s.length());
			s.append(".");
			I.Log(s);
		}
	}

	public void imprimirDescendente() {
		if(largo == 0) {
			I.Log("lista vacía.");
		}else {
			NodoLista nodoBuscador = inicio;
			ListaString listaInvertida = new ListaString();
			listaInvertida.insertarAlPrincipio(inicio.datoString);
			
			while (nodoBuscador.sig != null) {
				nodoBuscador = nodoBuscador.sig;
				listaInvertida.insertarAlPrincipio(nodoBuscador.datoString);
			}
			listaInvertida.imprimirAscendente();
		}
	}

	public void insertarAlFinal(String pDato) {
		NodoLista nuevoNodo = new NodoLista();
		nuevoNodo.datoString = pDato; 
		
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

	public void insertarAlPrincipio(String pDato) {
		if (largo == 0) {
			inicio = new NodoLista();
			inicio.datoString = pDato;
		}else {
			NodoLista restoDeLista = inicio;
			inicio = new NodoLista();
			inicio.datoString = pDato;
			inicio.sig = restoDeLista;
		}
		
		largo++; 
	}
	
	public ListaString() {
		largo = 0;
		inicio = null;
	}
}
