package com.unlam.TP4.probador;

import java.io.FileNotFoundException;

public class MainProbador {

	private static final String PATH_ENTRADA = "../Grafos generados/GRAFO_ALEATORIO_PORCENTAJE_ADYACENCIA_600_0.4.txt";
	private static final String PATH_SALIDA_WELSH_POWELL = "../Grafos coloreados/COLOREO_ALGORITMO_WELSH_POWELL_600_0.40.out";
	private static final String PATH_SALIDA_SECUENCIAL = "../Grafos coloreados/COLOREO_ALGORITMO_SECUENCIAL_600_0.40.out";

	public static void main(String[] args) throws FileNotFoundException {

		ProbadorColoreo probadorSecuencial = new ProbadorColoreo(PATH_ENTRADA, PATH_SALIDA_SECUENCIAL);
		ProbadorColoreo probadorWP = new ProbadorColoreo(PATH_ENTRADA, PATH_SALIDA_WELSH_POWELL);

		System.out.println("Probando coloreo secuencial, aguarde...");

		if (probadorSecuencial.probar()) {
			System.out.println("El coloreo es correcto.");
		} else {
			System.out.println("ERROR! El coloreo es incorrecto.");
		}

		System.out.println("Probando coloreo Wellsh Powel, aguarde...");
		if (probadorWP.probar()) {
			System.out.println("El coloreo es correcto.");
		} else {
			System.out.println("ERROR! El coloreo es incorrecto.");
		}
	}
}
