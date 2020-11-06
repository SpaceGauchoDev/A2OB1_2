package uy.edu.ort.obli;
import mda_estructuras.*;
import uy.edu.ort.obli.Retorno.Resultado;

public class Sistema implements ISistema {
	ABB_Usuarios arbolDeUsuarios;
	
	@Override
	public Retorno inicializarSistema(int maxPuntos) {
		Retorno r = new Retorno(Resultado.OK);
		arbolDeUsuarios = new ABB_Usuarios();
		
		return r;
	}

	@Override
	public Retorno destruirSistema() {
		Retorno r = new Retorno(Resultado.OK);
		arbolDeUsuarios = null;
		
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
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int metros, int minutos) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno registrarDelivery(String cedula, double coordX, double coordY) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno registrarMovil(String matricula, double coordX, double coordY) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno movilMasCercano(double coordXi, double coordYi) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno deliveryMasCercano(double coordXi, double coordYi) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno caminoMinimoMovil(double coordXi, double coordYi, double coordXf, double coordYf) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno caminoMinimoDelivery(double coordXi, double coordYi, double coordXf, double coordYf) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno dibujarMapa() {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}
	
	public Sistema() {
		
	}
}
