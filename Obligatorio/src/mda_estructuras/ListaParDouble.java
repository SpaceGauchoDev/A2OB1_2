package mda_estructuras;

import mda_utilidades.I;

public class ListaParDouble {
	NodoLista inicio;
	int largo;	

	public void imprimirAscendente() {
		if(largo == 0) {
			I.Log("Lista vacía.");
		}else {
			NodoLista nodoBuscador = inicio;
			StringBuilder s = new StringBuilder();
			s.append(nodoBuscador.datoParDouble.toString());
			s.append(", ");
			
			while (nodoBuscador.sig != null) {
				nodoBuscador = nodoBuscador.sig;
				s.append(nodoBuscador.datoParDouble.toString());
				
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
			I.Log("Lista vacía.");
		}else {
			NodoLista nodoBuscador = inicio;
			ListaParDouble listaInvertida = new ListaParDouble();
			listaInvertida.insertarAlPrincipio(inicio.datoParDouble);
			
			while (nodoBuscador.sig != null) {
				nodoBuscador = nodoBuscador.sig;
				listaInvertida.insertarAlPrincipio(nodoBuscador.datoParDouble);
			}
			listaInvertida.imprimirAscendente();
		}
	}

	public void insertarAlFinal(ParDouble pDato) {
		NodoLista nuevoNodo = new NodoLista();
		nuevoNodo.datoParDouble = pDato; 
		
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

	public void insertarAlPrincipio(ParDouble pDato) {
		if (largo == 0) {
			inicio = new NodoLista();
			inicio.datoParDouble = pDato;
		}else {
			NodoLista restoDeLista = inicio;
			inicio = new NodoLista();
			inicio.datoParDouble = pDato;
			inicio.sig = restoDeLista;
		}
		
		largo++; 
	}
	
	public ListaParDouble() {
		largo = 0;
		inicio = null;
	}
}
