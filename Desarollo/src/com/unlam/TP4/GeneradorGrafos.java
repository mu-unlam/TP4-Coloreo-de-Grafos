package com.unlam.TP4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

import com.unlam.TP4.nodos.Nodo;
import com.unlam.TP4.nodos.ParNodos;
import com.unlam.TP4.nodos.RandomParNodos;

public class GeneradorGrafos {

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
		int cantMaximaAristas = ((cantNodos * (cantNodos - 1)) / 2), cantAristas = 0;

		// Complejidad O(N^2)
		for (int i = 0; i < cantNodos - 1; i++) {
			for (int j = i + 1; j < cantNodos; j++) {
				// Si mi random es menor a la probabilidad de ocurrencia, entonces tengo una
				// nueva relacion entre dos nodos
				if (random.nextFloat() < probabilidadOcurrencia) {

					if (i < j) {
						arrParesDeNodos.add(new ParNodos(new Nodo(i), new Nodo(j)));
					} else {
						arrParesDeNodos.add(new ParNodos(new Nodo(j), new Nodo(i)));
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
		String path = Main.PATH_SALIDA_GRAFOS_GENERADOS + "GRAFO_ALEATORIO_PROBABILISTICO_" + cantNodos + "_"
				+ String.valueOf(probabilidadOcurrencia) + ".txt";

		escribirGrafoEnArchivo(path, arrParesDeNodos, cantNodos, cantAristas, porcentajeAdyacencia,
				grados.getNodo1().getGrado(), grados.getNodo2().getGrado());
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
		int gradoMaximo = 0, gradoMinimo = Integer.MAX_VALUE, max = 0, min = 0;

		for (int i = 0; i < cantNodos; i++) {
			grados[i] = 0;
		}

		// Voy calculando el grado de cada nodo
		for (int i = 0; i < arrayAristas.size(); i++) {
			grados[arrayAristas.get(i).getNodo1().getNroNodo()] += 1;
			grados[arrayAristas.get(i).getNodo2().getNroNodo()] += 1;
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
		Nodo nodo1 = new Nodo(-1);
		nodo1.setGrado(gradoMaximo);

		Nodo nodo2 = new Nodo(-1);
		nodo2.setGrado(gradoMinimo);

		// Luego de recorrer todos los nodos, ya tengo mi gradoMaximo y mi GradoMinimo
		return new ParNodos(nodo1, nodo2);
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
			buffer.write(String.valueOf(array.get(i).getNodo1().getNroNodo()));
			buffer.write(" ");
			buffer.write(String.valueOf(array.get(i).getNodo2().getNroNodo()));
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
	public static void generarGrafoAleatorioConPorcentajeDeAdyacencia(int cantNodos, double porcentaje)
			throws IOException {
		ArrayList<ParNodos> arrParesDeNodos = new ArrayList<ParNodos>();
		ArrayList<RandomParNodos> arrParNodosRandom = new ArrayList<RandomParNodos>();
		Random random = new Random();
		// Calculo como hice anteriormente la cantidad maxima de aristas
		int cantMaximaAristas = ((cantNodos * (cantNodos - 1)) / 2), cantAristas = 0;

		// Voy creando pares random de aristas
		for (int i = 0; i < cantNodos - 1; i++) {
			for (int j = i + 1; j < cantNodos; j++) {
				if (i < j)
					arrParNodosRandom
							.add(new RandomParNodos(new ParNodos(new Nodo(i), new Nodo(j)), random.nextDouble()));
				else
					arrParNodosRandom
							.add(new RandomParNodos(new ParNodos(new Nodo(j), new Nodo(i)), random.nextDouble()));

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
		String path = Main.PATH_SALIDA_GRAFOS_GENERADOS + "GRAFO_ALEATORIO_PORCENTAJE_ADYACENCIA_" + cantNodos + "_"
				+ String.valueOf(porcentaje) + ".txt";
		escribirGrafoEnArchivo(path, arrParesDeNodos, cantNodos, cantAristas, porcentaje, grados.getNodo1().getGrado(),
				grados.getNodo2().getGrado());
	}

	/**
	 * Generador para grafos N-Partitos
	 * 
	 * @param cantNodos     Cantidad de nodos
	 * @param cantConjuntos Cantidad de conjuntos
	 * @throws IOException
	 */
	public static void generarGrafoNPartito(int cantNodos, int cantConjuntos) throws IOException {
		// Array que tiene las aristas (entre un nodo A y otro nodo B)
		ArrayList<ParNodos> arrParesDeNodos = new ArrayList<ParNodos>();

		// Voy a crear un array de conjuntos, con el fin de
		ArrayList<Integer> conjuntos = new ArrayList<Integer>();

		Random generadorRandoms = new Random();

		// Calculo como siempre el numero maximo de aristas posible
		int cantMaximaAristas = (cantNodos * (cantNodos - 1)) / 2, cantAristas = 0;

		// Nunca se puede tener mas conjuntos que nodos, por lo que hago un exit.
		if (cantConjuntos > cantNodos) {
			System.out.println("La cantidad de nodos debe ser mayor o igual a la cantidad de conjuntos");
			System.exit(1);
		}

		// Voy a agregar los distintos posibles indices de conjuntos a un arraylist
		for (int i = 0; i < cantConjuntos; i++) {
			conjuntos.add(i);
		}

		// Una vez hecho esto, necesito categorizar a los N (cantNodos-cantConjuntos)
		// nodos restantes
		// De esta forma, realizare los grupos
		// Por ejemplo, si tengo 6 nodos y 3 conjuntos
		// mi tope en el for sera 3, y antes ya en conjuntos tenia [0,1,2]
		// El parametro que se envia a la funcion random funciona como tope, entonces el
		// random es entre 0 y X
		// De esta manera, obtendre los distintos conjuntos
		for (int i = 0; i < (cantNodos - cantConjuntos); i++) {
			int random = generadorRandoms.nextInt(cantConjuntos);
			conjuntos.add(random);
		}

		// Ahora tengo que armar los pares de nodos
		for (int i = 0; i < cantNodos - 1; i++) {
			for (int j = i + 1; j < cantNodos; j++) {
				// Si los dos nodos que agarre pertenecen a conjuntos distintos, entonces creo
				// la arista
				if (conjuntos.get(i) != conjuntos.get(j)) {

					// Determino el sentido del nodo
					if (i < j) {
						arrParesDeNodos.add(new ParNodos(new Nodo(i), new Nodo(j)));
					} else {
						arrParesDeNodos.add(new ParNodos(new Nodo(j), new Nodo(i)));
					}

					// Incremento la cantidad de aristas
					cantAristas++;
				}
			}
		}

		ParNodos grados = calcularGrado(arrParesDeNodos, cantNodos);
		double porcentajeAdyacencia = (double) cantAristas / cantMaximaAristas;
		String path = Main.PATH_SALIDA_GRAFOS_GENERADOS + cantNodos + "_NODOS__GRAFO_" + cantConjuntos + "PARTITO.txt";

		escribirGrafoEnArchivo(path, arrParesDeNodos, cantNodos, cantAristas, porcentajeAdyacencia,
				grados.getNodo1().getGrado(), grados.getNodo2().getGrado());
	}

	public static void generarGrafoRegularConGrado(int cantNodos, int grado) throws IOException {

		// VALIDACIONES
		if (cantNodos < grado + 1 && cantNodos * grado % 2 != 0) {
			System.out.println(
					"No se puede generar el grafo, la condicion es que CANT_NODOS >= GRADO + 1 y que CANT_NODOS * GRADO SEA PAR");
			return;
		}

		if (cantNodos % 2 != 0 && grado % 2 != 0) {
			System.out.println("No se puede generar un grafo de cantidad de nodos y grado impar");
			return;
		}

		if (grado >= cantNodos) {
			System.out.println("El grado no puede ser igual, o mayor a la cantidad de nodos");
			return;
		}

		ArrayList<ParNodos> array = new ArrayList<ParNodos>();
		int cantAristas = 0;
		int gradoActual = 2;
		int cantMaximaAristas = (cantNodos * (cantNodos - 1)) / 2;
		int desplazamiento = 0;

		int nodo1;
		int nodo2;

		// Genero una cruz en el grafo
		// Genera componentes aristas que no estan vinculadas entre ellas
		// Si necesito un grafo de grado impar necesito que primero este con grado 1
		// antes de sumarle una cantidad par de grado a cada nodo
		if (grado % 2 != 0) {
			for (int i = 0; i < cantNodos / 2; i++) {
				nodo1 = i;
				nodo2 = i + cantNodos / 2;

				array.add(new ParNodos(new Nodo(nodo1), new Nodo(nodo2)));
				cantAristas++;
			}
		}

		if (grado > 1) {
			int j = 0;
			while (gradoActual <= grado) {
				desplazamiento = gradoActual / 2;
				j = 0;
				// Cada pasada del for aumenta en 2 el grado de cada nodo
				for (int i = 0; i < cantNodos; i++) {
					if (i + desplazamiento < cantNodos) {
						nodo1 = i;
						nodo2 = i + desplazamiento;
						if (nodo1 < nodo2)
							array.add(new ParNodos(new Nodo(nodo1), new Nodo(nodo2)));
					} else {
						// si entra aca es por que tiene que conectar un nodo final con de los primeros,
						// con cual dependera del desplazamiento
						if (i < j)
							array.add(new ParNodos(new Nodo(i), new Nodo(j)));
						else
							array.add(new ParNodos(new Nodo(j), new Nodo(i)));
						j++;
					}
					cantAristas++;
				}
				gradoActual += 2;
			}
		}

		ParNodos grados = calcularGrado(array, cantNodos);
		double porcentajeAdyacencia = (double) cantAristas / cantMaximaAristas;

		String path = Main.PATH_SALIDA_GRAFOS_GENERADOS + "REGULAR_" + cantNodos + "_ADYACENCIA_"
				+ String.format("%.2f", porcentajeAdyacencia) + ".txt";

		escribirGrafoEnArchivo(path, array, cantNodos, cantAristas, porcentajeAdyacencia, grados.getNodo1().getGrado(),
				grados.getNodo2().getGrado());
	}

	public static void regularConPorcentajeAdyacencia(int cantNodos, double porcentaje) throws IOException {
		int cantMaximaAristas = (cantNodos * (cantNodos - 1)) / 2;
		// Dado el porcentaje de adyacencia voy a calcular cuantas aristas va a tener mi
		// grafo, si tiende al 100% la cantidad de aristas sera n-1.
		int grado = (int) Math.ceil(((porcentaje * cantMaximaAristas) / (cantNodos / 2)));
		double minimo = (double) (cantNodos / 2) / cantMaximaAristas;

		if (porcentaje < minimo) {
			System.out.println(
					"El porcentaje de adyacencia ingresado es demasiado bajo para generar un grafo regular (el minimo es: "
							+ String.format("%1.3f", minimo) + ")");
			System.exit(1);
		}
		if (porcentaje > 1) {
			System.out.println("El porcentaje de adyacencia ingresado es superior al 100%");
			System.exit(1);
		}

		generarGrafoRegularConGrado(cantNodos, grado);
	}
}
