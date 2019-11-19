package com.unlam.TP4.probador;

import java.io.FileNotFoundException;

public class MainProbador {

	private static final String PATH_ENTRADA = "../Grafos generados/GRAFO_ALEATORIO_PORCENTAJE_ADYACENCIA_600_0.4.txt";
	private static final String PATH_SALIDA_SECUENCIAL = "../Grafos coloreados/COLOREO_ALGORITMO_WELSH_POWELL_600_0,40.out";
	private static final String PATH_SALIDA_WELSH_POWELL = "../Grafos coloreados/COLOREO_ALGORITMO_SECUENCIAL_600_0,40.out";

	public static void main(String[] args) throws FileNotFoundException {

		ProbadorColoreo pcSecuencial = new ProbadorColoreo(PATH_ENTRADA, PATH_SALIDA_SECUENCIAL);
		ProbadorColoreo pcWP = new ProbadorColoreo(PATH_ENTRADA, PATH_SALIDA_SECUENCIAL);
//		System.out.println("Entrada: ");
//		pc.mostrarEntrada();
//		System.out.println("Salida: ");
//		pc.mostrarSalida();
//		pc.probar();

		System.out.println("PROBADOR SECUENCIAL");
		if (pcSecuencial.probar()) {
			System.out.println("OK");
		} else {
			System.out.println("FALLO");
		}
		System.out.println("------------------------------");
		System.out.println("PROBADOR WP");
		if (pcWP.probar()) {
			System.out.println("OK");
		} else {
			System.out.println("FALLO");
		}
	}
}
