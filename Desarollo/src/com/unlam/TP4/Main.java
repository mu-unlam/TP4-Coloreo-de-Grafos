package com.unlam.TP4;

import java.io.IOException;

import com.unlam.TP4.probador.ProbadorColoreo;

public class Main {
	public final static String PATH_SALIDA_GRAFOS_GENERADOS = "../Grafos Generados/";
	public final static String PATH_SALIDA_GRAFOS_COLOREADOS = "../Grafos coloreados/";
	private static final int CANTIDAD_CORRIDAS = 10000;

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
				System.out.println("");
				System.out.println("ALEATORIO PROBABILISTICO");
				GrafoNDNP grafo3 = new GrafoNDNP(PATH_SALIDA_GRAFOS_GENERADOS + "GRAFO_ALEATORIO_PROBABILISTICO_"
						+ valorEvaluacion + "_" + String.valueOf(i) + ".txt");
				grafo3.coloreoSecuencialAleatorio(CANTIDAD_CORRIDAS);
				grafo3.coloreoWelshPowell(CANTIDAD_CORRIDAS);
				grafo3.coloreoMatula(CANTIDAD_CORRIDAS);
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
						+ "_ADYENCIA_" + String.format("%.2f", adyacenciaRegular) + ".txt");
				grafo2.coloreoSecuencialAleatorio(CANTIDAD_CORRIDAS);
				grafo2.coloreoWelshPowell(CANTIDAD_CORRIDAS);
				grafo2.coloreoMatula(CANTIDAD_CORRIDAS);
				System.out.println("");
				System.out.println("ALEATORIO PROBABILISTICO");
				GrafoNDNP grafo3 = new GrafoNDNP(PATH_SALIDA_GRAFOS_GENERADOS + "REGULAR_" + valorEvaluacionRegular
						+ "_ADYENCIA_" + String.format("%.2f", adyacenciaRegular) + ".txt");
				grafo3.coloreoSecuencialAleatorio(CANTIDAD_CORRIDAS);
				grafo3.coloreoWelshPowell(CANTIDAD_CORRIDAS);
				grafo3.coloreoMatula(CANTIDAD_CORRIDAS);
				System.out.println("");

				System.out.println("======================== FIN VALOR ADYACENCIA" + String.valueOf(i)
						+ " ===========================");
			}

		} else if (modoEjecucion == 2) {

			double[] porcentajes = { 0.50, 0.75 };

			for (double porcentaje : porcentajes) {

				long inicio = 0;
				long tFinal = 0;
				System.out.println("======================== INICIO" + " ===========================");
				System.out.println("GRAFO REGULAR");
				inicio = System.nanoTime();
				GeneradorGrafos.regularConPorcentajeAdyacencia(1000, porcentaje);
				tFinal = System.nanoTime() - inicio;

				System.out.println("TIEMPO DE GRAFO REGULAR DE 1000, PORCENTAJE ADYACENCIA "
						+ String.valueOf(porcentaje * 100) + "%" + ": " + tFinal + " ns.");
			}

		}
	}

}
