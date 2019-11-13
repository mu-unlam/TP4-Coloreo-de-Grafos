package com.unlam.TP4;

import java.io.IOException;

public class Main {
	private final static String PATH_SALIDA = "../Grafos Generados/";

	public static void main(String[] args) throws IOException {
		/**
		 * El modo 0 de ejecucion es para la creacion de los grafos, mientras que el
		 * modo 1 es para el caso de que se quieran ejecutar los distintos algoritmos de
		 * coloreo
		 */
		int modoEjecucion = 1;

		if (modoEjecucion == 0) {
			GeneradorGrafos.generarGrafoAleatorioConProbabilidad(5, 0.5);
			GeneradorGrafos.generarGrafoAleatorioConPorcentajeDeAdyacencia(5, 0.9);
			GeneradorGrafos.generarGrafoNPartito(0, 0);
		} else {
			ColoreadorGrafos grafo = new ColoreadorGrafos(PATH_SALIDA + "GRAFO_ALEATORIO_PROBABILISTICO_5_0.5.txt");
			grafo.coloreoSecuencialAleatorio(10);
			grafo = new ColoreadorGrafos(PATH_SALIDA + "GRAFO_ALEATORIO_PORCENTAJE_ADYACENCIA_5_0.90.txt");
			grafo.coloreoSecuencialAleatorio(10);
			grafo = new ColoreadorGrafos(PATH_SALIDA + "GRAFO_2PARTITO.txt");
			grafo.coloreoSecuencialAleatorio(10);
			grafo = new ColoreadorGrafos(PATH_SALIDA + "GRAFO_5PARTITO.txt");
			grafo.coloreoSecuencialAleatorio(10);
		}
	}

}
