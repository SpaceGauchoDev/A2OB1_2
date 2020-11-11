package mda_estructuras;

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
	
	// devuelve AristaGrafo como medio conveniente de devolver pares de datos de vertices
	private AristaGrafo existenParDeVerticesInicializados(Geoloc pPosA, Geoloc pPosB) {
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
		
		vertices[verticesInicializados] = nuevoVertice;
		verticesInicializados++;

		return true;
	}
	
	
	/*=================================================*/
	/*=============== OPERACIONES ARISTAS =============*/
	/*VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV*/


	public AristaGrafo existeArista(Geoloc pPosA, Geoloc pPosB) {
		AristaGrafo aristaIda = null;
		AristaGrafo aristaVuelta = null;
		
		AristaGrafo aristaTempIda = new AristaGrafo();
		aristaTempIda.a.posicion = pPosA;
		aristaTempIda.b.posicion = pPosB;
		
		AristaGrafo aristaTempVuelta = new AristaGrafo();
		aristaTempVuelta.a.posicion = pPosB;
		aristaTempVuelta.b.posicion = pPosA;

		
		for (int i = 0; i < verticesInicializados; i++) {
			ListaAristas lista = aristasPorVertice[i];
			if(lista != null) {
				aristaIda = lista.contieneArista(aristaTempIda);
				aristaVuelta = lista.contieneArista(aristaTempVuelta);
			}
			
			if(aristaIda != null && aristaVuelta != null) {
				break;
			}
			
		}
		
		return aristaIda;
	}
	
	
	public boolean crearArista(Geoloc pPosA, Geoloc pPosB, int pDistancia, int pTiempo) {
		//checkeamos parametros validos antes de continuar
		if(pPosA == null || pPosB == null || pDistancia == 0 || pTiempo == 0) {
			return false;
		}
		
		AristaGrafo parDeVertices = existenParDeVerticesInicializados(pPosA, pPosB);
		AristaGrafo existeArista = existeArista(pPosA, pPosB);
		
		// si los vertices no han sido inicializados o la arista ya existe, no continuamos
		if(parDeVertices == null || existeArista != null) {
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
