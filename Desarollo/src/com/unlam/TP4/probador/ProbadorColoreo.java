package com.unlam.TP4.probador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.unlam.TP4.nodos.Nodo;
import com.unlam.TP4.nodos.ParNodos;

public class ProbadorColoreo {

	private ArrayList<ParNodos> entrada;
	private ArrayList<NodoPintado> salida;

	private int cantNodos;

	public ProbadorColoreo(String pathEntrada, String pathSalida) throws FileNotFoundException {
		File fArchivo = new File(pathEntrada);
		Scanner scan = new Scanner(fArchivo);

		this.entrada = new ArrayList<ParNodos>();
		this.salida = new ArrayList<NodoPintado>();

		int cantAristas;
		int fila;
		int columna;

		this.cantNodos = scan.nextInt();
		cantAristas = scan.nextInt();

		// Valores que no me son utiles

		scan.nextDouble();
		scan.nextInt();
		scan.nextDouble();

		for (int i = 0; i < cantAristas; i++) {
			fila = scan.nextInt();
			columna = scan.nextInt();
			this.entrada.add(new ParNodos(new Nodo(fila), new Nodo(columna)));
		}

		scan.close();

		fArchivo = new File(pathSalida);
		scan = new Scanner(fArchivo);

		scan.nextInt();
		scan.nextInt();
		scan.nextInt();
		scan.nextDouble();
		scan.nextInt();
		scan.nextInt();

		// Salida de un grafo coloreado
		for (int i = 0; i < this.cantNodos; i++) {
			fila = scan.nextInt();
			columna = scan.nextInt();
			salida.add(new NodoPintado(fila, columna));
		}
	}

	public boolean probar() {

		int nodoColoreado;
		int nodo1;
		int nodo2;
		int color1 = -1;
		int color2 = -2;

		boolean color1Pintado = false;
		boolean color2Pintado = false;

		for (int i = 0; i < this.entrada.size(); i++) {
			nodo1 = this.entrada.get(i).getNodo1().getNroNodo();
			nodo2 = this.entrada.get(i).getNodo2().getNroNodo();

			for (int j = 0; j < this.salida.size(); j++) {
				nodoColoreado = this.salida.get(j).getNodo();

				// Si es el mismo nodo y no esta pintado
				if (nodoColoreado == nodo1 && !color1Pintado) {
					color1 = nodoColoreado;
					color1Pintado = true;
				} else {
					if (nodoColoreado == nodo2 && !color2Pintado) {
						color2 = nodoColoreado;
						color2Pintado = true;
					}
				}

				if (color2Pintado && color1Pintado) {
					if (color1 == color2 || (nodoColoreado == nodo1 && nodoColoreado == nodo2))// Si son adyacentes es
						return false;
				}

			}
			// alguno no pintado
			if (!color2Pintado || !color2Pintado) {
				return false;
			}

			color1Pintado = false;
			color2Pintado = false;
		}

		return true;
	}
}
