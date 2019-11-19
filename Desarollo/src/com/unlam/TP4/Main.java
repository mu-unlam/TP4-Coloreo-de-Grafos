package com.unlam.TP4;

import java.io.IOException;
import java.util.Locale;

public class Main {
	public final static String PATH_SALIDA_GRAFOS_GENERADOS = "../Grafos Generados/";
	public final static String PATH_SALIDA_GRAFOS_COLOREADOS = "../Grafos coloreados/";
	private static final int CANTIDAD_CORRIDAS = 10000;
	public static final String PATH_SALIDA_RESULTADOS = "../Resultados/";

	public static void main(String[] args) throws IOException {
		/**
		 * El modo 0 de ejecucion es para la creacion de los grafos, mientras que el
		 * modo 1 es para el caso de que se quieran ejecutar los distintos algoritmos de
		 * coloreo
		 */

		int modoEjecucion = 1;
		int valorEvaluacion = 600;
		double[] adyacencia = { 0.4, 0.6, 0.9 };
		double[] adyacenciaRegular = { 0.5, 0.75 };
		int valorEvaluacionRegular = 1000;
		Locale.setDefault(Locale.ENGLISH);
		if (modoEjecucion == 0) {

			// Genero los aleatorios
			for (double i : adyacencia) {
				GeneradorGrafos.generarGrafoAleatorioConProbabilidad(valorEvaluacion, i);
				GeneradorGrafos.generarGrafoAleatorioConPorcentajeDeAdyacencia(valorEvaluacion, i);
			}

			// Genero los regulares
			for (double i : adyacenciaRegular) {
				GeneradorGrafos.regularConPorcentajeAdyacencia(valorEvaluacionRegular, i);
			}

		} else if (modoEjecucion == 1) {

			for (double i : adyacencia) {
				System.out.println("======================== INICIO VALOR ADYACENCIA " + String.valueOf(i)
						+ " ===========================");
				System.out.println("ALEATORIO CON PORCENTAJE DE ADYACENCIA");
				GrafoNDNP grafo2 = new GrafoNDNP(PATH_SALIDA_GRAFOS_GENERADOS + "GRAFO_ALEATORIO_PORCENTAJE_ADYACENCIA_"
						+ valorEvaluacion + "_" + String.valueOf(i) + ".txt");
				grafo2.coloreoSecuencialAleatorio(CANTIDAD_CORRIDAS);
				grafo2.coloreoWelshPowell(CANTIDAD_CORRIDAS);
				grafo2.coloreoMatula(CANTIDAD_CORRIDAS);
				grafo2.escribirResultados(
						"GRAFO_ALEATORIO_PORCENTAJE_ADYACENCIA_" + valorEvaluacion + "_" + String.valueOf(i));

				System.out.println("");

				System.out.println("======================== FIN VALOR ADYACENCIA" + String.valueOf(i)
						+ " ===========================");
			}
			// Ahora evaluo los regulares
			for (double i : adyacenciaRegular) {
				System.out.println("======================== INICIO VALOR ADYACENCIA " + String.valueOf(i)
						+ " ===========================");
				System.out.println("ALEATORIO CON PORCENTAJE DE ADYACENCIA");
				GrafoNDNP grafo2 = new GrafoNDNP(PATH_SALIDA_GRAFOS_GENERADOS + "REGULAR_" + valorEvaluacionRegular
						+ "_ADYACENCIA_" + String.format("%.2f", i) + ".txt");
				grafo2.coloreoSecuencialAleatorio(CANTIDAD_CORRIDAS);
				grafo2.coloreoWelshPowell(CANTIDAD_CORRIDAS);
				grafo2.coloreoMatula(CANTIDAD_CORRIDAS);
				grafo2.escribirResultados(
						"REGULAR_" + valorEvaluacionRegular + "_ADYACENCIA_" + String.format("%.2f", i));
				System.out.println("");

				System.out.println("======================== FIN VALOR ADYACENCIA" + String.valueOf(i)
						+ " ===========================");
			}

		}
	}

}
