package mda_estructuras;

import mda_utilidades.I;

public class ListaInt {
	NodoLista inicio;
	int largo;	

	public void imprimirAscendente() {
		if(largo == 0) {
			I.Log("Lista vacía.");
		}else {
			NodoLista nodoBuscador = inicio;
			StringBuilder s = new StringBuilder();
			s.append(nodoBuscador.datoInt);
			s.append(", ");
			
			while (nodoBuscador.sig != null) {
				nodoBuscador = nodoBuscador.sig;
				s.append(nodoBuscador.datoInt);
				
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
			ListaInt listaInvertida = new ListaInt();
			listaInvertida.insertarAlPrincipio(inicio.datoInt);
			
			while (nodoBuscador.sig != null) {
				nodoBuscador = nodoBuscador.sig;
				listaInvertida.insertarAlPrincipio(nodoBuscador.datoInt);
			}
			listaInvertida.imprimirAscendente();
		}
	}

	public void insertarAlFinal(int pDato) {
		NodoLista nuevoNodo = new NodoLista();
		nuevoNodo.datoInt = pDato; 
		
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

	public void insertarAlPrincipio(int pDato) {
		if (largo == 0) {
			inicio = new NodoLista();
			inicio.datoInt = pDato;
		}else {
			NodoLista restoDeLista = inicio;
			inicio = new NodoLista();
			inicio.datoInt = pDato;
			inicio.sig = restoDeLista;
		}
		
		largo++; 
	}
	
	public ListaInt() {
		largo = 0;
		inicio = null;
	}
}
