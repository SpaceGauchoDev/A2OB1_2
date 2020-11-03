package uy.edu.ort.obli;

public interface ISistema {
	
	// Restricción de eficiencia: no tiene
	Retorno inicializarSistema (int maxPuntos);
	 
	// Restricción de eficiencia: no tiene
	Retorno destruirSistema();
	 
	// Restricción de eficiencia: Esta operación deberá realizarse en orden (log n) promedio
	Retorno registrarUsuario(String email, String nombre, String password);
	
	// Restricción de eficiencia: Esta operación deberá realizarse en orden (log n) promedio
	Retorno buscarUsuario(String email);
	 
	// Restricción de eficiencia: Esta operación deberá realizarse en orden (n) promedio
	Retorno listarUsuarios();
	 
	// Restricción de eficiencia: no tiene	
	Retorno direccionesDeUsuario(String email);
	 
	// Restricción de eficiencia: no tiene
	Retorno registrarEsquina(double coordX, double coordY);
	 
	// Restricción de eficiencia: no tiene
	Retorno registrarDelivery(String cedula, double coordX, double coordY);
	
	// Restricción de eficiencia: no tiene
	Retorno registrarMovil(String matricula, double coordX, double coordY);
	
	// José Sebastián Grattarola Rizzo, Tuesday, 3 de November de 2020, 09:38 
	// Estimados, como están? Actualizamos la interfaz del sistema, ya que en el método registrarTramo faltaba el parámetro correspondiente al tiempo (costo en minutos)
	// Restricción de eficiencia: ????
	Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int metros, int minutos);
	 
	// Restricción de eficiencia: ????	
	Retorno movilMasCercano(double coordXi, double coordYi);
	
	// Restricción de eficiencia: ????
	Retorno deliveryMasCercano(double coordXi, double coordYi);
	 
	// Restricción de eficiencia: ????	
	Retorno caminoMinimoMovil(double coordXi, double coordYi, double coordXf, double coordYf);

	// Restricción de eficiencia: ????	
	Retorno caminoMinimoDelivery(double coordXi, double coordYi, double coordXf, double coordYf);

	// Restricción de eficiencia: ????
	Retorno dibujarMapa();
}
