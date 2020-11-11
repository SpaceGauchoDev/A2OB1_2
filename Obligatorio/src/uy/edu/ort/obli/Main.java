package uy.edu.ort.obli;

import mda_estructuras.*;
import mda_utilidades.*;

public class Main {

	public static void main(String[] args) {
		I.Log("Start");
		
		Grafo mapa = new Grafo(10);
		
		Geoloc g1 = new Geoloc(1.0,1.0);
		mapa.inicializarVerticeEsquina(g1);
		
		Geoloc g2 = new Geoloc(2.0,2.0);
		mapa.inicializarVerticeEsquina(g2);

		Geoloc g3 = new Geoloc(3.0,3.0);
		mapa.inicializarVerticeEsquina(g3);
		
		Geoloc g4 = new Geoloc(4.0,4.0);
		mapa.inicializarVerticeEsquina(g4);
		
		mapa.crearArista(g1, g2, 10, 10);
		
		I.Log("ble");
		
		boolean busqueda1 = mapa.existeArista(g1, g2);
		//AristaGrafo busqueda2 = mapa.existeArista(g3, g4);
		//AristaGrafo busqueda3 = mapa.existeArista(g2, g3);
		
		if(busqueda1) {
			I.Log("busqueda 1 encontro!");
		}else {
			I.Log("busqueda 1 no encontro!");
		}
		
		/*
		if(busqueda2 != null) {
			I.Log("busqueda 2 encontro!");
		}else {
			I.Log("busqueda 2 no encontro!");
		}
		
		if(busqueda3 != null) {
			I.Log("busqueda 3 encontro!");
		}else {
			I.Log("busqueda 3 no encontro!");
		}
		*/


		I.Log("preEnd");
		I.Log("End");
	}

}
