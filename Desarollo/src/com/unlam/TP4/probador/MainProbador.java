package com.unlam.TP4.probador;

import java.io.FileNotFoundException;

public class MainProbador {

	private static final String PATH_ENTRADA = "";
	private static final String PATH_SALIDA = "";

	public static void main(String[] args) throws FileNotFoundException {

		ProbadorColoreo pc = new ProbadorColoreo(PATH_ENTRADA, PATH_SALIDA);

		System.out.println("Entrada: ");
		pc.mostrarEntrada();
		System.out.println("Salida: ");
		pc.mostrarSalida();
		pc.probar();

		if (pc.probar()) {
			System.out.println("OK");
		} else {
			System.out.println("FALLO");
		}
	}
}
