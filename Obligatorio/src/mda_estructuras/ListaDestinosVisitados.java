package mda_estructuras;
import mda_utilidades.I;

public class ListaDestinosVisitados {
	public static enum Dir {Ascendente, Descendente};
	
	NodoLista inicio;
	int largo;
	Dir orden;
	
	
	
	public int buscarPrimerIndiceDeDato(Geoloc pDato) {
		int resultado;// = -1;
		if (largo == 0) {
			resultado = -1;
		}else if (largo == 1){
			if(inicio.datoDestinoVisitado.esMismaGeoloc(pDato.lat, pDato.lon)) {
				resultado = 0;
			}else {
				resultado = -1;
			}
		}else{
			NodoLista nodoBuscador = inicio;
			int indice = 0;
			boolean encontro = false;
			
			while (!encontro && nodoBuscador != null) {
				if(nodoBuscador.datoDestinoVisitado.esMismaGeoloc(pDato.lat, pDato.lon)) {
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
	
	
	public NodoLista buscarNodoEnIndice(int pIndice) {
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
			s.append(nodoBuscador.datoDestinoVisitado.toString());
			s.append(", ");
			
			while (nodoBuscador.sig != null) {
				nodoBuscador = nodoBuscador.sig;
				s.append(nodoBuscador.datoDestinoVisitado.toString());
				s.append(", ");
			}
			//borramos el ultimo comaEspacio y agregamos un punto.
			s.delete(s.length() -2 , s.length());
			s.append(".");
			I.Log(s);
		}
	}
	
	public void imprimirAscendenteNewLine() {
		if(largo == 0) {
			I.Log("lista vacía.");
		}else {
			NodoLista nodoBuscador = inicio;
			I.Log(nodoBuscador.datoDestinoVisitado.toString());
		
			while (nodoBuscador.sig != null) {
				nodoBuscador = nodoBuscador.sig;
				I.Log(nodoBuscador.datoDestinoVisitado.toString());
			}
		}
	}
	
	
	public void imprimirAscendente(String pSeparador, boolean pUsarSeparador,  String pFinal, boolean pUsarFinal) {
		if(largo == 0) {
			I.Log("lista vacía.");
		}else {
			NodoLista nodoBuscador = inicio;
			StringBuilder s = new StringBuilder();
			s.append(nodoBuscador.datoDestinoVisitado.toString());
			if(pUsarSeparador) {
				s.append(pSeparador);				
			}
			while (nodoBuscador.sig != null) {
				nodoBuscador = nodoBuscador.sig;
				s.append(nodoBuscador.datoDestinoVisitado.toString());
				
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
			s.append(nodoBuscador.datoDestinoVisitado.toString());
			if(pUsarSeparador) {
				s.append(pSeparador);				
			}
			while (nodoBuscador.sig != null) {
				nodoBuscador = nodoBuscador.sig;
				s.append(nodoBuscador.datoDestinoVisitado.toString());
				
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
			listaInvertida.insertarAlPrincipio(inicio.datoDestinoVisitado.toString());
			
			while (nodoBuscador.sig != null) {
				nodoBuscador = nodoBuscador.sig;
				listaInvertida.insertarAlPrincipio(nodoBuscador.datoDestinoVisitado.toString());
			}
			listaInvertida.imprimirAscendente();
		}
	}

	public void insertarOIncrementar(Geoloc pDato) {
		if (largo == 0) {
			I.Log("inserta al inicio");			
			NodoLista nuevoNodo = new NodoLista();
			nuevoNodo.datoDestinoVisitado.lat = pDato.lat;
			nuevoNodo.datoDestinoVisitado.lon = pDato.lon;
			nuevoNodo.datoDestinoVisitado.cantidadDeVecesVisitado = 1;
			
			inicio = nuevoNodo;
			largo++;
		}else {
			NodoLista nodoBuscador = inicio;
			
			boolean encontro = false;
			
			if (nodoBuscador.datoDestinoVisitado.esMismaGeoloc(pDato)) {
				I.Log("Encontro repetido");
				nodoBuscador.datoDestinoVisitado.cantidadDeVecesVisitado++;
				encontro = true;
			}
			
			while (!encontro && nodoBuscador.sig != null) {
				nodoBuscador = nodoBuscador.sig;
				if (nodoBuscador.datoDestinoVisitado.esMismaGeoloc(pDato)) {
					I.Log("Encontro repetido");
					nodoBuscador.datoDestinoVisitado.cantidadDeVecesVisitado++;
					encontro = true;
				}
			}
			
			if (!encontro) {
				NodoLista nuevoNodo = new NodoLista();
				I.Log("recorrio toda la lista y no encontro repetido");
				nuevoNodo.datoDestinoVisitado.lat = pDato.lat;
				nuevoNodo.datoDestinoVisitado.lon = pDato.lon;
				nuevoNodo.datoDestinoVisitado.cantidadDeVecesVisitado = 1;
				
				nodoBuscador.sig = nuevoNodo;
				largo++;
				
			}
		}
		ordenarLista();
	}
	
	
	public void ordenarLista() {
        // no ordenamos listas chicas
        if(largo == 0 || largo == 1){
            return;
        }
        
        for(int i = 0; i < largo - 1; i++){
            for(int j = 0; j < largo - 1; j++){
            	
            	
            	if(orden == Dir.Descendente) {
                	// esta comparación ordena la lista descendentemente 
                    if(buscarNodoEnIndice(j).datoDestinoVisitado.cantidadDeVecesVisitado < buscarNodoEnIndice(j+1).datoDestinoVisitado.cantidadDeVecesVisitado){
                    	intercambiarNodosEnIndice(j, j+1);
                    }
            	}else {
                	// esta comparación ordena la lista ascendente 
                    if(buscarNodoEnIndice(j).datoDestinoVisitado.cantidadDeVecesVisitado > buscarNodoEnIndice(j+1).datoDestinoVisitado.cantidadDeVecesVisitado){
                    	intercambiarNodosEnIndice(j, j+1);
                    }
            	}
            }
        }
	}
	
	
	public void intercambiarNodosEnIndice(int p1, int p2) {
		if(p1 < 0 || p1 > largo || p2 < 0 || p2 > largo){
			I.Log("Al menos una posición está de fuera de la lista.");
		}else {
			if(largo == 0) {
				I.Log("No se puede intercambiar nodos en una lista vacía.");
			}else {
				if( p1 == p2) {
					I.Log("No se puede intercambiar nodos en la misma posición.");
				}else {
					
					// intercambiar las posiciones para que 
					// p1 sea menor que p2
					if (p1 > p2) {
						int temp = p1;
						p1 = p2;
						p2 = temp;
					}
					
					NodoLista  n1		= null;	// nodo 2
					NodoLista  n1S		= null;	// nodo siguiente al nodo 1
					NodoLista  n2A		= null;	// nodo anterior al nodo 1
					NodoLista  n2		= null;	// nodo 2
					NodoLista  n2S		= null;	// nodo siguiente al nodo 2
					NodoLista  n1Old	= null;	// nodo temporal para intercambio de datos
					
					NodoLista nodoBuscador = inicio; 
					int posicion = 0;
					
					while (posicion <= p2) {
						if(posicion == p1) {
							n1 = nodoBuscador;
							n1S = nodoBuscador.sig;
							
						}
						
						if(posicion == p2 - 1) {
							n2A = nodoBuscador;
						}
						
						if(posicion == p2) {
							n2 = nodoBuscador;
							n2S = nodoBuscador.sig;
						}
						
						nodoBuscador = nodoBuscador.sig;
						posicion++;
					}
					
					n1Old = new NodoLista();
					n1Old.datoDestinoVisitado = n1.datoDestinoVisitado;
					
					n1.datoDestinoVisitado = n2.datoDestinoVisitado;
					n1.sig = n1S;
					
					n2A.sig = new NodoLista();
					n2A.sig.datoDestinoVisitado = n1Old.datoDestinoVisitado;
					
					n2A.sig.sig = n2S;
				}
			}
		}
	}	
	
	
	
	public ListaDestinosVisitados(Dir pOrden) {
		largo = 0;
		inicio = null;
		orden = pOrden;
	}
}
