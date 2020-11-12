package uy.edu.ort.obli;
import mda_estructuras.*;
import mda_utilidades.I;
import mda_utilidades.PaginaWeb;
import uy.edu.ort.obli.Retorno.Resultado;

public class Sistema implements ISistema {
	ABB_Usuarios arbolDeUsuarios;
	Grafo mapa;
	
	@Override
	public Retorno inicializarSistema(int maxPuntos) {
		Retorno r = new Retorno(Resultado.OK);
		
		arbolDeUsuarios = new ABB_Usuarios();
		mapa = new Grafo(maxPuntos);
		
		return r;
	}

	@Override
	public Retorno destruirSistema() {
		Retorno r = new Retorno(Resultado.OK);
		
		arbolDeUsuarios = null;
		mapa = null;
		
		return r;
	}

	@Override
	public Retorno registrarUsuario(String email, String nombre, String password) {
		Retorno r;
		
		if(!Usuario.validarEmail(email)) {
			r = new Retorno(Resultado.ERROR_1);
			return r;
		}
		
		if(arbolDeUsuarios.buscarUsuario(email).resultado) {
			r = new Retorno(Resultado.ERROR_2);
			return r;
		}
		
		// validacion no requerida por la letra
		if(!Usuario.validarNombreYPassword(nombre, password)) {
			r = new Retorno(Resultado.ERROR_3);
			r.valorString = "nombre de usuario y/o contraseña inválidos";
			return r;
		}
		
		arbolDeUsuarios.insertarUsuario(email, nombre, password);
		r = new Retorno(Resultado.OK);
		return r;
	}

	@Override
	public Retorno buscarUsuario(String email) {
		Retorno r;
			
		if(!Usuario.validarEmail(email)) {
			r = new Retorno(Resultado.ERROR_1);
			return r;
		}
		
		ResultadoBusquedaUsuario busqueda = arbolDeUsuarios.buscarUsuario(email);
		
		if(busqueda.resultado) {
			r = new Retorno(Resultado.OK);
			r.valorString = busqueda.usuario.email + ";" + busqueda.usuario.nombre; 
			r.valorEntero = busqueda.iteraciones;
			return r;			
		}else {
			r = new Retorno(Resultado.ERROR_2);
			return r;
		}
	}

	//TODO: preguntar si debería haber casos de error si el arbol no tiene datos
	//TODO: tecnicamente infoUsuarios() toma O 2*(n), 1(n) para recopilar todos los datos de usuarios en una lista y 1(n) para concatenar la lista en un string para devolver.
	@Override
	public Retorno listarUsuarios() {
		Retorno r = new Retorno(Resultado.OK);
		r.valorString = arbolDeUsuarios.infoUsuarios(ABB_Usuarios.TipoDeRecorrido.InOrder);
		
		return r;
	}

	@Override
	public Retorno direccionesDeUsuario(String email) {
		Retorno r;
		
		if(!Usuario.validarEmail(email)) {
			r = new Retorno(Resultado.ERROR_1);
			return r;
		}
		
		ResultadoBusquedaUsuario busqueda = arbolDeUsuarios.buscarUsuario(email);
		if(busqueda.resultado) {
			r = new Retorno(Resultado.OK);
			r.valorString = busqueda.usuario.destinosVisitados.concatenarAscendente("|", true, "", false);
			return r;			
		}else {
			r = new Retorno(Resultado.ERROR_2);
			return r;
		}
	}

	@Override
	public Retorno registrarEsquina(double coordX, double coordY) {
		Retorno r;
		if(mapa.verticesInicializados == mapa.cantidadDeVertices) {
			r = new Retorno(Resultado.ERROR_1);	
			return r;
		}
		
		Geoloc nuevaPosEsquina = new Geoloc();
		nuevaPosEsquina.lat = coordY;
		nuevaPosEsquina.lon = coordX;
		
		if(mapa.existeVerticeInicializado(nuevaPosEsquina) != null) {
			r = new Retorno(Resultado.ERROR_2);	
			return r;
		}
		
		mapa.inicializarVerticeEsquina(nuevaPosEsquina);
		r = new Retorno(Resultado.OK);
		return r;
	}

	@Override
	public Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int metros, int minutos) {
		Retorno r;
		
		if(metros <= 0) {
			r = new Retorno(Resultado.ERROR_1);	
			return r;
		}
		
		if(minutos <= 0) {
			r = new Retorno(Resultado.ERROR_2);	
			return r;
		}
		
		Geoloc geoI = new Geoloc();
		geoI.lat = coordYi;
		geoI.lon = coordXi;
		
		Geoloc geoF = new Geoloc();
		geoF.lat = coordYf;
		geoF.lon = coordXf;
		
		if(mapa.existenParDeVerticesInicializados(geoI, geoF) == null) {
			r = new Retorno(Resultado.ERROR_3);	
			return r;
		}
		
		if(mapa.existeArista(geoI, geoF)) {
			r = new Retorno(Resultado.ERROR_4);
			return r;
		}
		
		r = new Retorno(Resultado.OK);
		mapa.crearArista(geoI, geoF, metros, minutos);
		return r;
	}

	@Override
	public Retorno registrarDelivery(String cedula, double coordX, double coordY) {
		Retorno r;
		if(mapa.verticesInicializados == mapa.cantidadDeVertices) {
			r = new Retorno(Resultado.ERROR_1);	
			return r;
		}
		
		Geoloc nuevaPosDelivery = new Geoloc();
		nuevaPosDelivery.lat = coordY;
		nuevaPosDelivery.lon = coordX;
		
		if(mapa.existeVerticeInicializado(nuevaPosDelivery) != null) {
			r = new Retorno(Resultado.ERROR_2);	
			return r;
		}
		
		mapa.inicializarVerticeDelivery(nuevaPosDelivery, cedula);
		r = new Retorno(Resultado.OK);
		return r;
	}

	@Override
	public Retorno registrarMovil(String matricula, double coordX, double coordY) {
		Retorno r;
		if(mapa.verticesInicializados == mapa.cantidadDeVertices) {
			r = new Retorno(Resultado.ERROR_1);	
			return r;
		}
		
		Geoloc nuevaPosMovil = new Geoloc();
		nuevaPosMovil.lat = coordY;
		nuevaPosMovil.lon = coordX;
		
		if(mapa.existeVerticeInicializado(nuevaPosMovil) != null) {
			r = new Retorno(Resultado.ERROR_2);	
			return r;
		}
		
		mapa.inicializarVerticeMovil(nuevaPosMovil, matricula);
		r = new Retorno(Resultado.OK);
		return r;
	}

	@Override
	public Retorno movilMasCercano(double coordXi, double coordYi) {
		Retorno r;
			
		Geoloc puntoDeInicio = new Geoloc();
		puntoDeInicio.lat = coordXi;
		puntoDeInicio.lon = coordYi;
		
		VerticeGrafo verticeDeInicio = mapa.existeVerticeInicializado(puntoDeInicio);
		
		if(verticeDeInicio == null ) {
			r = new Retorno(Resultado.ERROR_1);
			return r;
		}
		
		if(!mapa.existeAlgunMovilDisponible()) {
			r = new Retorno(Resultado.ERROR_2);
			return r;
		}
		
		r = new Retorno(Resultado.OK);
		
		VerticeGrafo movil = mapa.movilActivoMasCercanoEnMetros(verticeDeInicio); 
		r.valorEntero = movil.distanciaEnMetros;
		movil.activo = false;
		
		return r;
	}

	@Override
	public Retorno deliveryMasCercano(double coordXi, double coordYi) {
		Retorno r;
		
		Geoloc puntoDeInicio = new Geoloc();
		puntoDeInicio.lat = coordXi;
		puntoDeInicio.lon = coordYi;
		
		VerticeGrafo verticeDeInicio = mapa.existeVerticeInicializado(puntoDeInicio);
		
		if(verticeDeInicio == null ) {
			r = new Retorno(Resultado.ERROR_1);
			return r;
		}
		
		if(!mapa.existeAlgunDeliveryDisponible()) {
			r = new Retorno(Resultado.ERROR_2);
			return r;
		}
		
		VerticeGrafo delivery = mapa.deliveryActivoMasCercanoEnAristas(verticeDeInicio);
		r = new Retorno(Resultado.OK);
		r.valorEntero = delivery.distanciaEnAristas;
		r.valorString = delivery.posicion.toString();
		delivery.activo = false;
		
		return r;
	}

	@Override
	public Retorno caminoMinimoMovil(Double coordXi, Double coordYi, Double coordXf, Double coordYf, String email) {
		Retorno r;
		
		Geoloc puntoDeInicio = new Geoloc();
		puntoDeInicio.lat = coordXi;
		puntoDeInicio.lon = coordYi;
		
		Geoloc puntoDeFin = new Geoloc();
		puntoDeFin.lat = coordXf;
		puntoDeFin.lon = coordYf;

		VerticeGrafo verticeDeInicio = mapa.existeVerticeInicializado(puntoDeInicio);
		VerticeGrafo verticeDeFin = mapa.existeVerticeInicializado(puntoDeFin);
		
		if(verticeDeInicio == null || verticeDeFin == null) {
			r = new Retorno(Resultado.ERROR_1);
			return r;
		}
		
		
		r = new Retorno(Resultado.OK);
		ResultadoBusquedaUsuario busqueda = arbolDeUsuarios.buscarUsuario(email);
		busqueda.usuario.destinosVisitados.insertarOIncrementar(puntoDeFin);
		
		VerticeGrafo caminoMinimo = mapa.caminoMasCortoParaMovilEnMetros(verticeDeInicio, verticeDeFin); 
		r.valorEntero = caminoMinimo.distanciaEnMetros;
		r.valorString = caminoMinimo.caminoRecorrido;
		
		return r;
	}

	@Override
	public Retorno caminoMinimoDelivery(double coordXi, double coordYi, double coordXf, double coordYf) {
		Retorno r;
		
		Geoloc puntoDeInicio = new Geoloc();
		puntoDeInicio.lat = coordXi;
		puntoDeInicio.lon = coordYi;
		
		Geoloc puntoDeFin = new Geoloc();
		puntoDeFin.lat = coordXf;
		puntoDeFin.lon = coordYf;

		VerticeGrafo verticeDeInicio = mapa.existeVerticeInicializado(puntoDeInicio);
		VerticeGrafo verticeDeFin = mapa.existeVerticeInicializado(puntoDeFin);
		
		if(verticeDeInicio == null || verticeDeFin == null) {
			r = new Retorno(Resultado.ERROR_1);
			return r;
		}
		
		if(!mapa.existeAlgunDeliveryDisponible()) {
			r = new Retorno(Resultado.ERROR_2);
			return r;
		}
		
		r = new Retorno(Resultado.OK);
		
		VerticeGrafo caminoMinimo = mapa.caminoMasCortoParaDeliveryEnMinutos(verticeDeInicio, verticeDeFin); 
		r.valorEntero = caminoMinimo.distanciaEnMinutos;
		r.valorString = caminoMinimo.caminoRecorrido;
		
		return r;
	}

	@Override
	public Retorno dibujarMapa() {
		Retorno r = new Retorno(Resultado.OK);
		
		String apiKey = "AIzaSyC2kHGtzaC3OOyc7Wi1LMBcEwM9btRZLqw";
		PaginaWeb web = new PaginaWeb(apiKey, mapa.obtenerVerticesInicializados());
		web.EscribirArchivo();
		web.EjecutarArchivo();
		
		return r;
	}
	
	public Sistema() {
		
	}
}
