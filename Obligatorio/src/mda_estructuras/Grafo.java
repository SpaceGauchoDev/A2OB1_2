package mda_estructuras;

import mda_utilidades.I;

public class Grafo {
	public int cantidadDeVertices;
	public int verticesInicializados;
	
	VerticeGrafo[] vertices;
	ListaAristas[] aristasPorVertice;
	
	
	
	
	/*=================================================*/
	/*=============== OPERACIONES VERTICES ============*/
	/*VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV*/
	
	public VerticeGrafo existeVerticeInicializado(Geoloc pPos) {
		if(pPos == null) {
			return null;
		}
		
		VerticeGrafo resultado = null;
		if(cantidadDeVertices > 0) {
			for (int i = 0; i < verticesInicializados; i++) {
				if(vertices[i].posicion.equals(pPos)){
					resultado = vertices[i];
				}
			}
		}
		return resultado;
	}
	
	public VerticeGrafo[] obtenerVerticesInicializados() {
		VerticeGrafo[] verticesActivos = new VerticeGrafo[verticesInicializados];
		
		for(int i = 0; i < verticesInicializados; i++) {
			verticesActivos[i] = vertices[i];
		}
		
		return verticesActivos;
	}
	
	private boolean sonVerticesAdjacentes(VerticeGrafo vA, VerticeGrafo vB ) {
		return existeArista(vA.posicion, vB.posicion); 
	}
	
	// devuelve AristaGrafo como medio conveniente de devolver pares de datos de vertices
	public AristaGrafo existenParDeVerticesInicializados(Geoloc pPosA, Geoloc pPosB) {
		if(pPosA == null || pPosB == null) {
			return null;
		}
		
		AristaGrafo resultado = null;
		
		VerticeGrafo vA = existeVerticeInicializado(pPosA);
		VerticeGrafo vB = existeVerticeInicializado(pPosB);

		if(vA != null & vB!= null ) {
			resultado = new AristaGrafo();
			resultado.a = vA;
			resultado.b = vB;
		}
		
		return resultado;
	}
	
	public boolean inicializarVerticeEsquina(Geoloc pPos) {
		if(pPos == null) {
			return false;
		}
		
		VerticeGrafo nuevoVertice = new VerticeGrafo();
		nuevoVertice.posicion = pPos;
		nuevoVertice.tipoDeVertice = VerticeGrafo.TipoDeVertice.Esquina;
		nuevoVertice.indice = verticesInicializados;
		
		vertices[verticesInicializados] = nuevoVertice;
		verticesInicializados++;
		
		return true;
	}
	
	public boolean inicializarVerticeMovil(Geoloc pPos, String pMatricula) {
		if(pPos == null || pMatricula == "") {
			return false;
		}
		
		VerticeGrafo nuevoVertice = new VerticeGrafo();
		nuevoVertice.posicion = pPos;
		nuevoVertice.matriculaMovil = pMatricula;
		nuevoVertice.tipoDeVertice = VerticeGrafo.TipoDeVertice.Movil;
		nuevoVertice.indice = verticesInicializados;
		nuevoVertice.activo = true;
		
		vertices[verticesInicializados] = nuevoVertice;
		verticesInicializados++;
		
		return true;
	}
	
	public boolean inicializarVerticeDelivery(Geoloc pPos, String pCedula) {
		if(pPos == null || pCedula == "") {
			return false;
		}
		
		VerticeGrafo nuevoVertice = new VerticeGrafo();
		nuevoVertice.posicion = pPos;
		nuevoVertice.cedulaDelivery = pCedula;
		nuevoVertice.tipoDeVertice = VerticeGrafo.TipoDeVertice.Delivery;
		nuevoVertice.indice = verticesInicializados;
		nuevoVertice.activo = true;
		
		vertices[verticesInicializados] = nuevoVertice;
		verticesInicializados++;

		return true;
	}
	
	
	/*=================================================*/
	/*=============== OPERACIONES ARISTAS =============*/
	/*VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV*/


	public boolean existeArista(Geoloc pPosA, Geoloc pPosB) {
		
		boolean encontroIda = false;
		boolean encontroVuelta = false;

		for (int i = 0; i < verticesInicializados; i++) {
			ListaAristas lista = aristasPorVertice[i];
			if(lista != null) {
				if(lista.obtenerDatoArista(pPosA.lat, pPosA.lon, pPosB.lat, pPosB.lon) != null) {
					encontroIda = true;
				}
				
				if(lista.obtenerDatoArista(pPosB.lat, pPosB.lon, pPosA.lat, pPosA.lon) != null) {
					encontroVuelta = true;
				}
			}
			
			if(encontroIda && encontroVuelta) {
				break;
			}
		}
		
		return encontroIda && encontroVuelta;
	}
	
	
	public int pesoDeArista(VerticeGrafo vA, VerticeGrafo vB, AristaGrafo.TipoDePeso tipoDePeso ) {
		
		Geoloc pPosA = vA.posicion;
		Geoloc pPosB = vB.posicion;
		
		boolean encontroIda = false;
		boolean encontroVuelta = false;
		
		int pesoIda = -1;
		int pesoVuelta = -1;

		for (int i = 0; i < verticesInicializados; i++) {
			ListaAristas lista = aristasPorVertice[i];
			if(lista != null) {
				
				AristaGrafo ida = lista.obtenerDatoArista(pPosA.lat, pPosA.lon, pPosB.lat, pPosB.lon); 
				if(ida != null) {
					encontroIda = true;
					
					
					if(tipoDePeso == AristaGrafo.TipoDePeso.Distancia) {
						pesoIda = ida.distancia;
					}
					else if (tipoDePeso == AristaGrafo.TipoDePeso.Tiempo) {
						pesoIda = ida.tiempo;
					}
				}
				
				AristaGrafo vuelta = lista.obtenerDatoArista(pPosB.lat, pPosB.lon, pPosA.lat, pPosA.lon);
				if(vuelta != null) {
					encontroVuelta = true;
					
					if(tipoDePeso == AristaGrafo.TipoDePeso.Distancia) {
						pesoVuelta = vuelta.distancia;
					}
					else if (tipoDePeso == AristaGrafo.TipoDePeso.Tiempo) {
						pesoVuelta = vuelta.tiempo;
					}
				}
			}
			
			if(encontroIda && encontroVuelta) {
				break;
			}
		}
		
		// en este grafo se asume que los pesos son iguales en ambos sentidos, si hay una discordancia 
		// devolvemos -1 como error
		if(pesoIda == pesoVuelta) {
			return pesoIda;
		}else {
			return -1;
		}
	}
	
	public boolean crearArista(Geoloc pPosA, Geoloc pPosB, int pDistancia, int pTiempo) {
		//checkeamos parametros validos antes de continuar
		if(pPosA == null || pPosB == null || pDistancia == 0 || pTiempo == 0) {
			return false;
		}
		
		AristaGrafo parDeVertices = existenParDeVerticesInicializados(pPosA, pPosB);
		boolean existeArista = existeArista(pPosA, pPosB);
		
		// si los vertices no han sido inicializados o la arista ya existe, no continuamos
		if(parDeVertices == null || existeArista) {
			return false;
		}
		
		// obtener lista de aristas del primer vertice a travez su indice
		ListaAristas listaDeAristasA = aristasPorVertice[parDeVertices.a.indice];
		
		// si esta es la primer arista que se agrega a la lista de este vertice
		// la lista va a estar nula, la inicializamos y la agregamos al array
		if(listaDeAristasA == null) {
			listaDeAristasA = new ListaAristas();
			aristasPorVertice[parDeVertices.a.indice] = listaDeAristasA;
		}
		
		// crear y agregar nueva arista a la lista de aristas del primer vertice
		AristaGrafo A_B = new AristaGrafo();
		A_B.a = parDeVertices.a;
		A_B.b = parDeVertices.b;
		A_B.distancia = pDistancia;
		A_B.tiempo = pTiempo;
		listaDeAristasA.insertarAlFinal(A_B);
		

		// obtener lista de aristas del segundo vertice a travez su indice
		ListaAristas listaDeAristasB = aristasPorVertice[parDeVertices.b.indice];
		
		// si esta es la primer arista que se agrega a la lista de este vertice
		// la lista va a estar nula, la inicializamos y la agregamos al array
		if(listaDeAristasB == null) {
			listaDeAristasB = new ListaAristas();
			aristasPorVertice[parDeVertices.b.indice] = listaDeAristasB;
		}
		
		// crear y agregar nueva arista a la lista de aristas del segundo vertice
		AristaGrafo B_A = new AristaGrafo();
		B_A.a = parDeVertices.b;
		B_A.b = parDeVertices.a;
		B_A.distancia = pDistancia;
		B_A.tiempo = pTiempo;
		listaDeAristasB.insertarAlFinal(B_A);
		
		return true;
	}
	
	/*=================================================*/
	/*=============== OPERACIONES GRAFO ===============*/
	/*VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV*/
	
	
	public boolean existeAlgunMovilDisponible() {
		boolean resultado = false;
		
		for(int i = 0; i < verticesInicializados; i++) {
			if(vertices[i].tipoDeVertice == VerticeGrafo.TipoDeVertice.Movil &&  vertices[i].activo) {
				resultado = true;
				break;
			} 
		}
		
		return resultado;
	}
	
	
	public boolean existeAlgunDeliveryDisponible() {
		boolean resultado = false;
		
		for(int i = 0; i < verticesInicializados; i++) {
			if(vertices[i].tipoDeVertice == VerticeGrafo.TipoDeVertice.Delivery &&  vertices[i].activo) {
				resultado = true;
				break;
			} 
		}
		
		return resultado;
	}
	
	public VerticeGrafo movilActivoMasCercanoEnMetros(VerticeGrafo pInicio/*, VerticeGrafo pFin*/) {
		
		int[] costos = new int[verticesInicializados];
		boolean[] visitados = new boolean[verticesInicializados];
		VerticeGrafo[] predecesores = new VerticeGrafo[verticesInicializados];
		
		// Inicializacion
		// ==============
		
		// Marco el nodo inicial como visitado
		visitados[pInicio.indice] = true;
 
		// Recorro todos los nodos
		// TODO entender porque empezamos en i = 1 y no en i = 0 
		for(int i = 0; i < verticesInicializados ; i++) { 
						
			//si no son el inicial
			if(i != pInicio.indice) { 
				
				 // si son adjacentes guardo sus pesos
				if(sonVerticesAdjacentes(vertices[pInicio.indice], vertices[i])) {
					
					costos[i] = pesoDeArista(vertices[pInicio.indice], vertices[i], AristaGrafo.TipoDePeso.Distancia);
					predecesores[i] = pInicio;
					
				}else { 
					// si no son adjacentes sus pesos son infinito temporalmente
					costos[i] = Integer.MAX_VALUE;
				}
			}
		}
		
		// LOOP PRINCIPAL
		// ==============
		
		// TODO entender porque empezamos en i = 1 y no en i = 0
		for(int i = 0; i <verticesInicializados; i++) {
		
			// buscamos el indice del vertice no visitado con menor costo
			int w = indiceNoVisitadoMenosCosto(costos, visitados);
			if(w == -1) {
				// -1 = no hay mas nodos no visitados podemos dejar de iterar 
				break;
			}
			visitados[w] = true;
			
			// TODO entender porque empezamos en j = 1 y no en j = 0
			for (int j = 0; j <verticesInicializados; j++) {
				
				// si son adjacentes de menor peso y no han sido visitados 
				if(sonVerticesAdjacentes(vertices[w], vertices[j]) && !visitados[j]) {
					
					int costoActual = costos[j];
					int nuevoCosto = costos[w] +  pesoDeArista(vertices[w], vertices[j], AristaGrafo.TipoDePeso.Distancia);
					
					if(nuevoCosto < costoActual) {
						// hay una mejor opcion, actualizamos costos y predecesores
						costos[j] = nuevoCosto;
						predecesores[j] = vertices[w];
					}
				}
			}
		}
		 
		
		// TENEMOS LOS COSTOS DESDE EL ORIGEN A TODOS LOS VERTICES
		// BUSCAMOS AHORA EL DE MENOR COSTO QUE SEA UN MOVIL ACTIVO 
		int menor = Integer.MAX_VALUE;
		int menorMovilActivo = -1;
		
		for (int i = 0; i < costos.length; i++) {
			if(	costos[i] <= menor &&
				vertices[i].tipoDeVertice == VerticeGrafo.TipoDeVertice.Movil && 
				vertices[i].activo) {
				
				menor = costos[i];
				menorMovilActivo = i;
				
			}
		}
		
		I.Log("menorMovilActivo: " + vertices[menorMovilActivo].matriculaMovil);
		
		vertices[menorMovilActivo].distanciaEnMetros = costos[menorMovilActivo];
		return vertices[menorMovilActivo];
	}
	
	public VerticeGrafo caminoMasCortoParaMovilEnMetros(VerticeGrafo pInicio, VerticeGrafo pFin) {
		
		int[] costos = new int[verticesInicializados];
		boolean[] visitados = new boolean[verticesInicializados];
		VerticeGrafo[] predecesores = new VerticeGrafo[verticesInicializados];
		
		// Inicializacion
		// ==============
		
		// Marco el nodo inicial como visitado
		visitados[pInicio.indice] = true;
 
		// Recorro todos los nodos
		// TODO entender porque empezamos en i = 1 y no en i = 0 
		for(int i = 0; i < verticesInicializados ; i++) { 
						
			//si no son el inicial
			if(i != pInicio.indice) { 
				
				 // si son adjacentes guardo sus pesos
				if(sonVerticesAdjacentes(vertices[pInicio.indice], vertices[i])) {
					
					costos[i] = pesoDeArista(vertices[pInicio.indice], vertices[i], AristaGrafo.TipoDePeso.Distancia);
					predecesores[i] = pInicio;
					
				}else { 
					// si no son adjacentes sus pesos son infinito temporalmente
					costos[i] = Integer.MAX_VALUE;
				}
			}
		}
		
		// LOOP PRINCIPAL
		// ==============
		
		// TODO entender porque empezamos en i = 1 y no en i = 0
		for(int i = 0; i <verticesInicializados; i++) {
		
			// buscamos el indice del vertice no visitado con menor costo
			int w = indiceNoVisitadoMenosCosto(costos, visitados);
			if(w == -1) {
				// -1 = no hay mas nodos no visitados podemos dejar de iterar 
				break;
			}
			visitados[w] = true;
			
			// TODO entender porque empezamos en j = 1 y no en j = 0
			for (int j = 0; j <verticesInicializados; j++) {
				
				// si son adjacentes de menor peso y no han sido visitados 
				if(sonVerticesAdjacentes(vertices[w], vertices[j]) && !visitados[j]) {
					
					int costoActual = costos[j];
					int nuevoCosto = costos[w] +  pesoDeArista(vertices[w], vertices[j], AristaGrafo.TipoDePeso.Distancia);
					
					if(nuevoCosto < costoActual) {
						// hay una mejor opcion, actualizamos costos y predecesores
						costos[j] = nuevoCosto;
						predecesores[j] = vertices[w];
					}
				}
			}
		}
		 
		
		I.Log("distanciaEnMetros: " + costos[pFin.indice]);
		pInicio.distanciaEnMetros = costos[pFin.indice];
		
		ListaString ls = new ListaString();
		ls = concatenarCamino(pInicio, pFin, predecesores, ls);
		String camino = ls.concatenarAscendente("|", true, "", false);
		
		pInicio.caminoRecorrido = camino;
		
		return pInicio;
	}
	
	
	public VerticeGrafo caminoMasCortoParaDeliveryEnMinutos(VerticeGrafo pInicio, VerticeGrafo pFin) {
		
		int[] costos = new int[verticesInicializados];
		boolean[] visitados = new boolean[verticesInicializados];
		VerticeGrafo[] predecesores = new VerticeGrafo[verticesInicializados];
		
		// Inicializacion
		// ==============
		
		// Marco el nodo inicial como visitado
		visitados[pInicio.indice] = true;
 
		// Recorro todos los nodos
		// TODO entender porque empezamos en i = 1 y no en i = 0 
		for(int i = 0; i < verticesInicializados ; i++) { 
						
			//si no son el inicial
			if(i != pInicio.indice) { 
				
				 // si son adjacentes guardo sus pesos
				if(sonVerticesAdjacentes(vertices[pInicio.indice], vertices[i])) {
					
					costos[i] = pesoDeArista(vertices[pInicio.indice], vertices[i], AristaGrafo.TipoDePeso.Tiempo);
					predecesores[i] = pInicio;
					
				}else { 
					// si no son adjacentes sus pesos son infinito temporalmente
					costos[i] = Integer.MAX_VALUE;
				}
			}
		}
		
		// LOOP PRINCIPAL
		// ==============
		
		// TODO entender porque empezamos en i = 1 y no en i = 0
		for(int i = 0; i <verticesInicializados; i++) {
		
			// buscamos el indice del vertice no visitado con menor costo
			int w = indiceNoVisitadoMenosCosto(costos, visitados);
			if(w == -1) {
				// -1 = no hay mas nodos no visitados podemos dejar de iterar 
				break;
			}
			visitados[w] = true;
			
			// TODO entender porque empezamos en j = 1 y no en j = 0
			for (int j = 0; j <verticesInicializados; j++) {
				
				// si son adjacentes de menor peso y no han sido visitados 
				if(sonVerticesAdjacentes(vertices[w], vertices[j]) && !visitados[j]) {
					
					int costoActual = costos[j];
					int nuevoCosto = costos[w] +  pesoDeArista(vertices[w], vertices[j], AristaGrafo.TipoDePeso.Tiempo);
					
					if(nuevoCosto < costoActual) {
						// hay una mejor opcion, actualizamos costos y predecesores
						costos[j] = nuevoCosto;
						predecesores[j] = vertices[w];
					}
				}
			}
		}
		 
		
		I.Log("distanciaEnMinutos: " + costos[pFin.indice]);
		pInicio.distanciaEnMinutos = costos[pFin.indice];
		
		ListaString ls = new ListaString();
		ls = concatenarCamino(pInicio, pFin, predecesores, ls);
		String camino = ls.concatenarAscendente("|", true, "", false);
		
		pInicio.caminoRecorrido = camino;
		
		return pInicio;
	}
	
	
	private ListaString concatenarCamino(VerticeGrafo o, VerticeGrafo d, VerticeGrafo[] predecesores, ListaString l){
		
		if(d.indice !=0 ){
			concatenarCamino(o, predecesores[d.indice], predecesores, l);
		}

		l.insertarAlFinal(d.posicion.toString());
		return l;
	}
	
	
	
	public VerticeGrafo deliveryActivoMasCercanoEnAristas(VerticeGrafo pInicio/*, VerticeGrafo pFin*/) {
		
		int[] costos = new int[verticesInicializados];
		boolean[] visitados = new boolean[verticesInicializados];
		VerticeGrafo[] predecesores = new VerticeGrafo[verticesInicializados];
		
		// Inicializacion
		// ==============
		
		// Marco el nodo inicial como visitado
		visitados[pInicio.indice] = true;
 
		// Recorro todos los nodos
		// TODO entender porque empezamos en i = 1 y no en i = 0 
		for(int i = 0; i < verticesInicializados ; i++) { 
						
			//si no son el inicial
			if(i != pInicio.indice) { 
				
				 // si son adjacentes guardo sus pesos
				if(sonVerticesAdjacentes(vertices[pInicio.indice], vertices[i])) {
					
					//costos[i] = pesoDeArista(vertices[pInicio.indice], vertices[i], AristaGrafo.TipoDePeso.Distancia);
					costos[i] = 1;
					predecesores[i] = pInicio;
					
				}else { 
					// si no son adjacentes sus pesos son infinito temporalmente
					costos[i] = Integer.MAX_VALUE;
				}
			}
		}
		
		// LOOP PRINCIPAL
		// ==============
		
		// TODO entender porque empezamos en i = 1 y no en i = 0
		for(int i = 0; i <verticesInicializados; i++) {
		
			// buscamos el indice del vertice no visitado con menor costo
			int w = indiceNoVisitadoMenosCosto(costos, visitados);
			if(w == -1) {
				// -1 = no hay mas nodos no visitados podemos dejar de iterar 
				break;
			}
			visitados[w] = true;
			
			// TODO entender porque empezamos en j = 1 y no en j = 0
			for (int j = 0; j <verticesInicializados; j++) {
				
				// si son adjacentes de menor peso y no han sido visitados 
				if(sonVerticesAdjacentes(vertices[w], vertices[j]) && !visitados[j]) {
					
					int costoActual = costos[j];
					//int nuevoCosto = costos[w] +  pesoDeArista(vertices[w], vertices[j], AristaGrafo.TipoDePeso.Distancia);
					int nuevoCosto = costos[w] + 1;
					
					if(nuevoCosto < costoActual) {
						// hay una mejor opcion, actualizamos costos y predecesores
						costos[j] = nuevoCosto;
						predecesores[j] = vertices[w];
					}
				}
			}
		}
		 
		
		// TENEMOS LOS COSTOS DESDE EL ORIGEN A TODOS LOS VERTICES
		// BUSCAMOS AHORA EL DE MENOR COSTO QUE SEA UN DELIVERY ACTIVO 
		int menor = Integer.MAX_VALUE;
		int menorDeliveryActivo = -1;
		
		for (int i = 0; i < costos.length; i++) {
			if(	costos[i] <= menor &&
				vertices[i].tipoDeVertice == VerticeGrafo.TipoDeVertice.Delivery && 
				vertices[i].activo) {
				
				menor = costos[i];
				menorDeliveryActivo = i;
				
			}
		}
		
		I.Log("menorDeliveryActivo: " + vertices[menorDeliveryActivo].cedulaDelivery);
		
		vertices[menorDeliveryActivo].distanciaEnAristas = costos[menorDeliveryActivo];
		return vertices[menorDeliveryActivo];
	}	
	
	
	private int indiceNoVisitadoMenosCosto(int[] pCostos, boolean[] pVisitados) {
		int menor = Integer.MAX_VALUE;
		int resultado = -1;
		
		for (int i = 0; i < pCostos.length; i++) {
			if(!pVisitados[i] && pCostos[i] <= menor) {
				menor = pCostos[i];
				resultado = i;
			}
		}
		
		return resultado;
	}
	
	
	public void borrarGrafo() {
		
		cantidadDeVertices = -1;
		verticesInicializados = -1;
		vertices = null;
		aristasPorVertice = null;
	}
	
	public Grafo (int pCantidadDeVertices) {
		vertices = new VerticeGrafo[pCantidadDeVertices];
		aristasPorVertice = new ListaAristas[pCantidadDeVertices];
		cantidadDeVertices = pCantidadDeVertices;
		verticesInicializados = 0;
	}
		
}
