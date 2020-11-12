package uy.edu.ort.obli;

import mda_estructuras.*;
import mda_utilidades.*;

public class Main {

	public static void main(String[] args) {
		I.Log("Start");
		
		Grafo mapa = new Grafo(6);
		
		Geoloc gA = new Geoloc(-34.9041699,-56.1946491);
		Geoloc gB = new Geoloc(-34.9065014,-56.1895629);
		Geoloc gC = new Geoloc(-34.9058486,-56.1913223);
		Geoloc gD = new Geoloc(-34.9039313,-56.1915286);
		Geoloc gE = new Geoloc(-34.9038551,-56.1904163);
		Geoloc gF = new Geoloc(-34.9048658,-56.1914352);
		
		mapa.inicializarVerticeEsquina(gA);
		mapa.inicializarVerticeMovil(gB, "123");
		mapa.inicializarVerticeDelivery(gC, "AAA");
		mapa.inicializarVerticeEsquina(gD);
		mapa.inicializarVerticeDelivery(gE, "BBB");
		mapa.inicializarVerticeMovil(gF, "456");
		
		String apiKey = "AIzaSyC2kHGtzaC3OOyc7Wi1LMBcEwM9btRZLqw";
		PaginaWeb web = new PaginaWeb(apiKey, mapa.obtenerVerticesInicializados());
		web.EscribirArchivo();
		web.EjecutarArchivo();
		
		/*
		mapa.crearArista(gA, gB, 2, 10);
		mapa.crearArista(gB, gD, 8, 10);
		mapa.crearArista(gF, gB, 3, 10);
		mapa.crearArista(gF, gC, 2, 10);
		mapa.crearArista(gC, gD, 1, 10);
		mapa.crearArista(gC, gE, 4, 10);
		mapa.crearArista(gE, gA, 1, 10);
		

		VerticeGrafo vA = mapa.existeVerticeInicializado(gA);
		VerticeGrafo vB = mapa.existeVerticeInicializado(gB);
		VerticeGrafo vC = mapa.existeVerticeInicializado(gC);
		VerticeGrafo vD = mapa.existeVerticeInicializado(gD);
		VerticeGrafo vE = mapa.existeVerticeInicializado(gE);
		VerticeGrafo vF = mapa.existeVerticeInicializado(gF);
		*/
		
		
		/*
		if(mapa.existeAlgunMovilDisponible()) {
			I.Log("existe movil disponible");
		}
		*/
		
		//mapa.distanciaAMovilActivoMasCercano(vA);
		
		/*
		mapa.deliveryActivoMasCercano(vC, vE);
		
		mapa.deliveryActivoMasCercano(vA, vE);
		
		mapa.deliveryActivoMasCercano(vF, vE);
		
		mapa.deliveryActivoMasCercano(vA, vF);
		*/

		I.Log("preEnd");
		I.Log("End");
	}

}
