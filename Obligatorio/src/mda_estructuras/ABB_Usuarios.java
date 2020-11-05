package mda_estructuras;
import mda_utilidades.Abecedario;
import mda_utilidades.I;

public class ABB_Usuarios {
	public NodoABB raiz;
	public int cantidadDeUsuarios;
	public static enum TipoDeRecorrido {InOrder, Preorder, Postorder};
	
	
	public String infoUsuarios(TipoDeRecorrido pTipoDeRecorrido) {
		// String s = "";
		ListaString l = new ListaString();
		//s = concatenarInfoUsuarios(raiz, "", TipoDeRecorrido.InOrder);

		switch(pTipoDeRecorrido) {
		case InOrder:
			l = concatenarInfoUsuariosInOrder(raiz,l);
			break;
		case Preorder:
			l = concatenarInfoUsuariosPreorder(raiz,l);
			break;
		case Postorder:
			l = concatenarInfoUsuariosPostorder(raiz,l);
			break;
		}
		
		return l.concatenarAscendente("|",true ,"" ,false);
	}
	
	private ListaString concatenarInfoUsuariosInOrder(NodoABB pNodo, ListaString pLista){
		if(pNodo != null) {
			I.Log("recorre");
			concatenarInfoUsuariosInOrder(pNodo.izq, pLista); 
			pLista.insertarAlFinal(pNodo.datoUsuario.email + ";" + pNodo.datoUsuario.nombre);
			concatenarInfoUsuariosInOrder(pNodo.der, pLista);
		}
		
		return pLista;
	}
	
	
	private ListaString concatenarInfoUsuariosPreorder(NodoABB pNodo, ListaString pLista){
		if(pNodo != null) {
			I.Log("recorre");
			pLista.insertarAlFinal(pNodo.datoUsuario.email + ";" + pNodo.datoUsuario.nombre);
			concatenarInfoUsuariosInOrder(pNodo.izq, pLista); 
			concatenarInfoUsuariosInOrder(pNodo.der, pLista);
		}
		
		return pLista;
	}
	
	private ListaString concatenarInfoUsuariosPostorder(NodoABB pNodo, ListaString pLista){
		if(pNodo != null) {
			I.Log("recorre");
			concatenarInfoUsuariosInOrder(pNodo.izq, pLista); 
			concatenarInfoUsuariosInOrder(pNodo.der, pLista);
			pLista.insertarAlFinal(pNodo.datoUsuario.email + ";" + pNodo.datoUsuario.nombre);
		}
		
		return pLista;
	}		
	
	
	public ResultadoBusquedaUsuario buscarUsuario(String pEmail) {
		ResultadoBusquedaUsuario r = new ResultadoBusquedaUsuario();
		
		if(pEmail == "" || raiz == null) {
			r.iteraciones = 0;
			r.resultado = false;
			r.usuario = null;
		}
		
		//raiz.iteraciones = 0;
		NodoABB nodo = buscarABB(raiz, pEmail, 0);
		
		r.usuario = nodo.datoUsuario;
		r.resultado = (r.usuario == null) ? false : true;
		r.iteraciones = nodo.iteraciones;
		
		return r;
	}
	
	
	private NodoABB buscarABB(NodoABB pNodo, String pEmail, int pIteraciones) {
        if (pNodo == null) {
        	// en caso de no encontrar el usuario, creamos un nodo para envíar la informacion de la cantidad de iteraciones realizadas
        	NodoABB busquedaFallida = new NodoABB();
        	busquedaFallida.iteraciones = pIteraciones;
        	//busquedaFallida.datoUsuario = null; // esto ya se setea como null con el constructor por defecto de NodoABB
        	return busquedaFallida;
        } else if (pNodo.datoUsuario.email == pEmail) {
        	pNodo.iteraciones = pIteraciones;
        	return pNodo;
        }
        
		// buscamos el orden de comparación
		Abecedario abc = new Abecedario();
		int orden = abc.compararStrings(pEmail, pNodo.datoUsuario.email, Abecedario.Dir.Descendente);
		
        if (orden < 0) {
        	I.Log("busca izquierda");
        	return buscarABB(pNodo.izq, pEmail, pIteraciones +1);
        }else {
        	I.Log("busca derecha");
        	return buscarABB(pNodo.der, pEmail, pIteraciones +1);
        }
	}
	
	
	public boolean insertarUsuario (String pEmail, String pNombre, String pPassword) {
		boolean resultado = false;
		
		raiz = insertarABB(raiz, pEmail, pNombre, pPassword);
		
		return resultado;
	}
	
	private NodoABB rebalancear(NodoABB pNodo) {
		if (pNodo.balance == -2) {
			// arbol desbalanciado a la izquierda	
			
			if (pNodo.izq.balance <= 0) {
				// Left-Left case.
				return izqIzq(pNodo);
			} else {
				// Left-Right case.
				return izqDer(pNodo);
			}

		} else if (pNodo.balance == +2) {
			// arbol desbalanciado a la izquierda
			
			if (pNodo.der.balance >= 0) {
				// Right-Right case.
				return derDer(pNodo);
			} else {
				// Right-Left case.
				return derIzq(pNodo);
			}
		}
	
		// el arbol está lo suficientemente balanceado 
		return pNodo;
	}
	
	
	private NodoABB izqIzq(NodoABB pNodo) {
		return rotarADer(pNodo);
	}
	
	private NodoABB izqDer(NodoABB pNodo) {
		pNodo.izq = rotarAIzq(pNodo.izq);
	    return izqIzq(pNodo);
	}
	
	private NodoABB derDer(NodoABB pNodo) {
		return rotarAIzq(pNodo);
	}
	
	private NodoABB derIzq(NodoABB pNodo) {
		pNodo.der = rotarADer(pNodo.izq);
	    return derDer(pNodo);
	}
	
	private NodoABB rotarAIzq(NodoABB pNodo) {
		NodoABB nuevoPredecesor = pNodo.der;
		
		pNodo.der = nuevoPredecesor.izq;
		nuevoPredecesor.izq = pNodo;
		
		actualizarNodo(pNodo);
		actualizarNodo(nuevoPredecesor);

		return nuevoPredecesor;
	}

	private NodoABB rotarADer(NodoABB pNodo) {
		NodoABB nuevoPredecesor = pNodo.izq;
		
		pNodo.izq = nuevoPredecesor.der;
		nuevoPredecesor.der = pNodo;
		
		actualizarNodo(pNodo);
		actualizarNodo(nuevoPredecesor);
		
		return nuevoPredecesor;
	}
	
	
	// TODO: como todos estos datos pertenecen al nodo, la actualizacion probablemente 
	// debería ser responsabilidad interna del nodo, crear una funcion publica en NodoABB
	// que pueda ser llamada para actualizar sus datos
	private void actualizarNodo(NodoABB pNodo) {
		int alturaDer = -1;
		int alturaIzq = -1;
		
		if(pNodo.der != null) {
			alturaDer = pNodo.der.altura;
		}
		
		if(pNodo.izq != null) {
			alturaIzq = pNodo.izq.altura;
		}
		
		pNodo.altura = 1 + Math.max(alturaDer, alturaIzq);
		
		pNodo.balance = alturaDer - alturaIzq;
	}
	
	
	private NodoABB insertarABB(NodoABB pNodo, String pEmail, String pNombre, String pPassword) {
		// nodo raiz o nodo hoja
		if (pNodo == null) {
			
			Usuario u = new Usuario();
			u.email = pEmail;
			u.nombre = pNombre;
			u.password = pPassword;
			
			NodoABB nodo = new NodoABB();
			nodo.datoUsuario = u;
				
			cantidadDeUsuarios++; 
			I.Log("inserta!");
			
			return nodo;
		}
		
		// buscamos el orden de comparación
		Abecedario abc = new Abecedario();
		int orden = abc.compararStrings(pEmail, pNodo.datoUsuario.email, Abecedario.Dir.Descendente);
		
		if(orden < 0) {
			// el nuevo valor es menor que el actual por lo que debería ir hacia la izquierda
			pNodo.izq = insertarABB(pNodo.izq, pEmail, pNombre, pPassword);
		} else if(orden > 0) {
			// el nuevo valor es mayor que el actual por lo que debería ir hacia la derecha
			pNodo.der = insertarABB(pNodo.der, pEmail, pNombre, pPassword);
		} else {
			// el nuevo valor es el mismo valor que el valor actual, por lo que no deberia insertarse! 
			I.Log("Tratando de insertar un nodo que ya existe!");
		}
		
		actualizarNodo(pNodo);
		
		return rebalancear(pNodo);
	}
	

	public ABB_Usuarios() {
		raiz = null;
		cantidadDeUsuarios = 0;
	}
}
