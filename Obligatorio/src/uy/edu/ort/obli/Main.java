package uy.edu.ort.obli;
import mda_estructuras.*;
import mda_utilidades.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Everything in working order");
		/*
		//Lista l = new Lista(Lista.TipoDeDato.INT);
		ListaString l = new ListaString();
		l.insertarAlFinal("como");
		l.insertarAlFinal("coma");
		l.insertarAlFinal("come");
		l.insertarAlFinal("comi");
		
		l.insertarAlFinal("comu");
		l.imprimirAscendente();
		l.imprimirDescendente();
		
		I.Log(l.buscarPrimerIndiceDeDato("comu"));
		*/
		
		
		ABB_Usuarios arbol = new ABB_Usuarios();
		
		arbol.insertarUsuario("a@gmail.com", "manuel", "123");
		arbol.insertarUsuario("b@gmail.com", "manuel", "123");
		arbol.insertarUsuario("c@gmail.com", "manuel", "123");
		arbol.insertarUsuario("d@gmail.com", "manuel", "123");
		arbol.insertarUsuario("e@gmail.com", "manuel", "123");
		arbol.insertarUsuario("f@gmail.com", "manuel", "123");
		
		ResultadoBusquedaUsuario b = arbol.buscarUsuario("b@gmail.com");
		
		if(b.resultado) {
			Geoloc g1 = new Geoloc(15, 16);
			Geoloc g2 = new Geoloc(17, 3);
			Geoloc g3 = new Geoloc(-50, 18);
			b.usuario.destinosVisitados.insertarOIncrementar(g1);
			b.usuario.destinosVisitados.insertarOIncrementar(g1);
			b.usuario.destinosVisitados.insertarOIncrementar(g1);
			b.usuario.destinosVisitados.insertarOIncrementar(g2);
			b.usuario.destinosVisitados.insertarOIncrementar(g3);
			b.usuario.destinosVisitados.insertarOIncrementar(g3);
		}
		
		ResultadoBusquedaUsuario b2 = arbol.buscarUsuario("b@gmail.com");
		if(b2.resultado) {
			b2.usuario.destinosVisitados.imprimirAscendenteNewLine();
		}
		
		/*
		ListaDestinosVisitados l = new ListaDestinosVisitados(ListaDestinosVisitados.Dir.Descendente);
		
		Geoloc g1 = new Geoloc(15, 16);
		Geoloc g2 = new Geoloc(17, 3);
		Geoloc g3 = new Geoloc(-50, 18);
		//Geoloc g4 = new Geoloc(15, 16);
		//Geoloc g5 = new Geoloc(15, 16);

		l.insertarOIncrementar(g1);
		l.insertarOIncrementar(g1);
		l.insertarOIncrementar(g2);
		l.insertarOIncrementar(g2);
		l.insertarOIncrementar(g2);
		
		l.imprimirAscendenteNewLine();
		*/
		
		I.Log("puto");
	}

}
