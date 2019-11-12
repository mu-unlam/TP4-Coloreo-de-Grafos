package com.unlam.TP4;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		GeneradorGrafos.generarGrafoAleatorioConProbabilidad(5, 0.5);
		GeneradorGrafos.generarGrafoAleatorioConPorcentajeDeAdyacencia(5, 0.9);
	}

}
