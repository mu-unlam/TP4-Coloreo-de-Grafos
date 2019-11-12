package com.unlam.TP4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GeneradorGrafos {
	private final static String PATH_SALIDA = "../Grafos Generados/";

	/**
	 * Calcula el grado minimo y maximo del grafo
	 * 
	 * @param cantNodos              cantidad de nodos
	 * @param probabilidadOcurrencia
	 * @throws IOException
	 * @return void
	 */
	public static void generarGrafoAleatorioConProbabilidad(int cantNodos, double probabilidadOcurrencia)
			throws IOException {
		ArrayList<ParNodos> arrParesDeNodos = new ArrayList<ParNodos>();
		Random random = new Random();
		// Mi cantidad maxima de aristas
		// Si tengo 6 nodos, el numero maximo es (6*5)/2 = 15 aristas
		// Ya que no tengo en cuenta las duplicadas
		int cantMaximaAristas = ((cantNodos * (cantNodos - 1)) / 2);
		int cantAristas = 0;

		// Complejidad O(N^2)
		for (int i = 0; i < cantNodos - 1; i++) {
			for (int j = i + 1; j < cantNodos; j++) {
				// Si mi random es menor a la probabilidad de ocurrencia, entonces tengo una
				// nueva relacion entre dos nodos
				if (random.nextFloat() < probabilidadOcurrencia) {

					if (i < j) {
						arrParesDeNodos.add(new ParNodos(i, j));
					} else {
						arrParesDeNodos.add(new ParNodos(j, i));
					}

					// Tengo una arista más
					cantAristas++;
				}
			}
		}

		// Dada las aristas que fui generando, y la cantidad de nodos, voy a calcular su
		// grado minimo y su grado maximo
		// Esto lo almacenare en un parNodos, ya que es efectivamente el objeto que
		// necesito
		ParNodos grados = calcularGrado(arrParesDeNodos, cantNodos);
		double porcentajeAdyacencia = (double) cantAristas / cantMaximaAristas;
		String path = PATH_SALIDA + "GRAFO_ALEATORIO_PROBABILISTICO_" + cantNodos + "_" + probabilidadOcurrencia
				+ ".txt";

		escribirGrafoEnArchivo(path, arrParesDeNodos, cantNodos, cantAristas, porcentajeAdyacencia, grados.getNodo1(),
				grados.getNodo2());
	}

	/**
	 * Calcula el grado minimo y maximo del grafo
	 * 
	 * @param array     Contiene las relaciones entre nodos
	 * @param cantNodos cantidad de nodos
	 * @return ParNodos
	 */
	private static ParNodos calcularGrado(ArrayList<ParNodos> arrayAristas, int cantNodos) {
		int[] grados = new int[cantNodos];
		int gradoMaximo = 0;
		// Para hallar el minimo, pongo el maximo valor posible
		int gradoMinimo = Integer.MAX_VALUE;
		int max = 0;
		int min = 0;

		for (int i = 0; i < cantNodos; i++) {
			grados[i] = 0;
		}

		// Voy calculando el grado de cada nodo
		for (int i = 0; i < arrayAristas.size(); i++) {
			grados[arrayAristas.get(i).getNodo1()] += 1;
			grados[arrayAristas.get(i).getNodo2()] += 1;
		}

		// Ahora debo buscar el maximo y el minimo
		// Esto es como se hace desde elementos, no hay ninguna magia
		for (int i = 0; i < cantNodos; i++) {
			// Uso estas dos variables temporales
			max = grados[i];
			min = grados[i];
			if (max > gradoMaximo) {
				gradoMaximo = max;
			}
			if (min < gradoMinimo && min > 0) {
				gradoMinimo = min;
			}
		}
		// Luego de recorrer todos los nodos, ya tengo mi gradoMaximo y mi GradoMinimo
		return new ParNodos(gradoMaximo, gradoMinimo);
	}

	/**
	 * 
	 * Escribe un grafo en el archivo segun el formato de la consigna
	 * 
	 * @param path                 Ruta del archivo que se escribe
	 * @param array                Contiene las relaciones entre nodos
	 * @param cantNodos            cantidad de nodos
	 * @param cantAristas          contiene la cantidad de aristas
	 * @param porcentajeAdyacencia homonimo
	 * @param gradoMaximo
	 * @param gradoMinimo
	 * @throws IOException en caso de ocurrir un error con el archivo
	 */
	private static void escribirGrafoEnArchivo(String path, ArrayList<ParNodos> array, int cantNodos, int cantAristas,
			double porcentajeAdyacencia, int gradoMaximo, int gradoMinimo) throws IOException {
		FileWriter file = new FileWriter(path);
		BufferedWriter buffer = new BufferedWriter(file);
		System.out.println();
		buffer.write(String.valueOf(cantNodos));
		buffer.write(" ");
		buffer.write(String.valueOf(cantAristas));
		buffer.write(" ");
		buffer.write(String.format("%1.2f", porcentajeAdyacencia));

		buffer.write(" ");
		buffer.write(String.valueOf(gradoMaximo));
		buffer.write(" ");
		buffer.write(String.valueOf(gradoMinimo));
		buffer.newLine();

		for (int i = 0; i < array.size(); i++) {
			buffer.write(String.valueOf(array.get(i).getNodo1()));
			buffer.write(" ");
			buffer.write(String.valueOf(array.get(i).getNodo2()));
			buffer.newLine();
		}

		buffer.close();

		if (gradoMinimo == 0) {
			System.out.println("Error, el grafo tiene grado minimo 0");
			System.exit(0);
		}

	}

	/**
	 * Generador de grafo aleatorio con porcentaje de adyacencia
	 * 
	 * @param cantNodos  cantidad de nodos
	 * @param porcentaje
	 * @throws IOException
	 * @return void
	 */
	public static void generarGrafoAleatorioConPorcentajeDeAdyacencia(int cantNodos, double porcentaje) throws IOException {
		ArrayList<ParNodos> arrParesDeNodos = new ArrayList<ParNodos>();
		ArrayList<RandomParNodos> arrParNodosRandom = new ArrayList<RandomParNodos>();
		Random random = new Random();
		// Calculo como hice anteriormente la cantidad maxima de aristas
		int cantMaximaAristas = ((cantNodos * (cantNodos - 1)) / 2);
		int cantAristas = 0;

		// Voy creando pares random de aristas
		for (int i = 0; i < cantNodos - 1; i++) {
			for (int j = i + 1; j < cantNodos; j++) {
				if (i < j)
					arrParNodosRandom.add(new RandomParNodos(new ParNodos(i, j), random.nextDouble()));
				else
					arrParNodosRandom.add(new RandomParNodos(new ParNodos(j, i), random.nextDouble()));

			}
		}

		Collections.sort(arrParNodosRandom);

		for (int i = 0; i < cantMaximaAristas * porcentaje; i++) {
			arrParesDeNodos.add(arrParNodosRandom.get(i).getNodos());
			cantAristas++;
		}

		// Dada las aristas que fui generando, y la cantidad de nodos, voy a calcular su
		// grado minimo y su grado maximo
		// Esto lo almacenare en un parNodos, ya que es efectivamente el objeto que
		// necesito
		ParNodos grados = calcularGrado(arrParesDeNodos, cantNodos);
		String path = PATH_SALIDA + "GRAFO_ALEATORIO_PORCENTAJE_ADYACENCIA_" + cantNodos + "_"
				+ String.format("%.2f", porcentaje) + ".txt";
		escribirGrafoEnArchivo(path, arrParesDeNodos, cantNodos, cantAristas, porcentaje, grados.getNodo1(),
				grados.getNodo2());
	}

}
