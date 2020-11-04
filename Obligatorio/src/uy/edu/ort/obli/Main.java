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
		
		arbol.InsertarUsuario("a@gmail.com", "manuel", "123");
		arbol.InsertarUsuario("b@gmail.com", "manuel", "123");
		arbol.InsertarUsuario("c@gmail.com", "manuel", "123");
		arbol.InsertarUsuario("d@gmail.com", "manuel", "123");
		arbol.InsertarUsuario("e@gmail.com", "manuel", "123");
		arbol.InsertarUsuario("f@gmail.com", "manuel", "123");
		
		I.Log("puto");

	}

}
