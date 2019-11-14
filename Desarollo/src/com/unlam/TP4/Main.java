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

		int modoEjecucion = 0;
		int valorEvaluacion = 600;
		double[] adyacencia = { 0.4, 0.6, 0.9 };
		if (modoEjecucion == 0) {
			for (double i : adyacencia) {
				GeneradorGrafos.generarGrafoAleatorioConProbabilidad(valorEvaluacion, i);
				GeneradorGrafos.generarGrafoAleatorioConPorcentajeDeAdyacencia(valorEvaluacion, i);
			}
		} else {
			if (modoEjecucion == 1) {

//				for (int i : valores) {
//					System.out.println("======================== INICIO VALOR" + String.valueOf(i)
//							+ " ===========================");
//					System.out.println("N-PARTITO");
//					GrafoNDNP grafo1 = new GrafoNDNP(
//							PATH_SALIDA_GRAFOS_GENERADOS + String.valueOf(i) + "_NODOS__GRAFO_5PARTITO.txt");
//					grafo1.coloreoSecuencialAleatorio(CANTIDAD_CORRIDAS);
//					grafo1.coloreoWelshPowell(CANTIDAD_CORRIDAS);
//					grafo1.coloreoMatula(CANTIDAD_CORRIDAS);
//					System.out.println("");
//					System.out.println("ALEATORIO CON PORCENTAJE DE ADYACENCIA");
//
//					GrafoNDNP grafo2 = new GrafoNDNP(PATH_SALIDA_GRAFOS_GENERADOS
//							+ "GRAFO_ALEATORIO_PORCENTAJE_ADYACENCIA_" + String.valueOf(i) + "_0.35.txt");
//					grafo2.coloreoSecuencialAleatorio(CANTIDAD_CORRIDAS);
//					grafo2.coloreoWelshPowell(CANTIDAD_CORRIDAS);
//					grafo2.coloreoMatula(CANTIDAD_CORRIDAS);
//					System.out.println("");
//					System.out.println("ALEATORIO PROBABILISTICO");
//					GrafoNDNP grafo3 = new GrafoNDNP(PATH_SALIDA_GRAFOS_GENERADOS + "GRAFO_ALEATORIO_PROBABILISTICO_"
//							+ String.valueOf(i) + "_0.6.txt");
//					grafo3.coloreoSecuencialAleatorio(CANTIDAD_CORRIDAS);
//					grafo3.coloreoWelshPowell(CANTIDAD_CORRIDAS);
//					grafo3.coloreoMatula(CANTIDAD_CORRIDAS);
//					System.out.println("");
//
//					System.out.println(
//							"======================== FIN VALOR" + String.valueOf(i) + " ===========================");
//				}

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
			} else if (modoEjecucion == 3) {
//ProbadorColoreo pc = new ProbadorColoreo(pathEntrada, pathSalida);
//Main.PATH_SALIDA_GRAFOS_COLOREADOS + "COLOREO" + "_" + algoritmoElegido + "_" + this.cantNodos
//+ "_0.35.out"
			}
		}
	}

}
