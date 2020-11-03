package uy.edu.ort.obli;

public interface ISistema {
	
	// Restricci�n de eficiencia: no tiene
	Retorno inicializarSistema (int maxPuntos);
	 
	// Restricci�n de eficiencia: no tiene
	Retorno destruirSistema();
	 
	// Restricci�n de eficiencia: Esta operaci�n deber� realizarse en orden (log n) promedio
	Retorno registrarUsuario(String email, String nombre, String password);
	
	// Restricci�n de eficiencia: Esta operaci�n deber� realizarse en orden (log n) promedio
	Retorno buscarUsuario(String email);
	 
	// Restricci�n de eficiencia: Esta operaci�n deber� realizarse en orden (n) promedio
	Retorno listarUsuarios();
	 
	// Restricci�n de eficiencia: no tiene	
	Retorno direccionesDeUsuario(String email);
	 
	// Restricci�n de eficiencia: no tiene
	Retorno registrarEsquina(double coordX, double coordY);
	 
	// Restricci�n de eficiencia: no tiene
	Retorno registrarDelivery(String cedula, double coordX, double coordY);
	
	// Restricci�n de eficiencia: no tiene
	Retorno registrarMovil(String matricula, double coordX, double coordY);
	
	// Jos� Sebasti�n Grattarola Rizzo, Tuesday, 3 de November de 2020, 09:38 
	// Estimados, como est�n? Actualizamos la interfaz del sistema, ya que en el m�todo registrarTramo faltaba el par�metro correspondiente al tiempo (costo en minutos)
	// Restricci�n de eficiencia: ????
	Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int metros, int minutos);
	 
	// Restricci�n de eficiencia: ????	
	Retorno movilMasCercano(double coordXi, double coordYi);
	
	// Restricci�n de eficiencia: ????
	Retorno deliveryMasCercano(double coordXi, double coordYi);
	 
	// Restricci�n de eficiencia: ????	
	Retorno caminoMinimoMovil(double coordXi, double coordYi, double coordXf, double coordYf);

	// Restricci�n de eficiencia: ????	
	Retorno caminoMinimoDelivery(double coordXi, double coordYi, double coordXf, double coordYf);

	// Restricci�n de eficiencia: ????
	Retorno dibujarMapa();
}
