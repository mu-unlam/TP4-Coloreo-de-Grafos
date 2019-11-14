package com.unlam.TP4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;

import com.unlam.TP4.nodos.Nodo;

public class ColoreadorGrafos {
	private MatrizSimetrica grafo;
	private int cantNodos;
	private int cantAristas;
	private int gradoMax;
	private int gradoMin;
	private int mejorColor = 0;
	private int colorMax = 1;
	private double porcentajeAdyacencia;
	private ArrayList<Nodo> nodos;
	private int[] solucion;
	private int[] mejoresColores;
	private int[] nodosColoreados;
	private int[] gradosNodos;

	/**
	 * El constructor toma un path para levantar el grafo y realizar el
	 * procesamiento necesario
	 * 
	 * @param path
	 * @throws FileNotFoundException
	 */
	public ColoreadorGrafos(String path) throws FileNotFoundException {
		File file = new File(path);
		Scanner scan = new Scanner(file);
		scan.useLocale(Locale.ENGLISH);
		// Arranco escaneando la primer fila del archivo. Esto tiene variada
		// informacion:
		this.cantNodos = scan.nextInt();
		this.cantAristas = scan.nextInt();
		this.porcentajeAdyacencia = scan.nextDouble();
		this.gradoMax = scan.nextInt();
		this.gradoMin = scan.nextInt();
		this.grafo = new MatrizSimetrica(this.cantNodos);

		// En el caso de tener un grafo vacio, se pinta con 1 color.
		// Se trata de un valor teorico.
		this.colorMax = 1;

		// Inicializo la variable nodos con el objecto correspondiente
		nodos = new ArrayList<Nodo>();

		// Lo mismo con los distintos arrays que uso para la solucion
		nodosColoreados = new int[this.cantNodos];
		gradosNodos = new int[this.cantNodos];
		solucion = new int[this.cantNodos];
		mejoresColores = new int[this.cantNodos];

		// Inicializo los vectores
		for (int i = 0; i < this.cantNodos; i++) {
			gradosNodos[i] = 0;
			mejoresColores[i] = 0;
		}

		// Voy levantando las aristas del archivo y creo los pares
		for (int i = 0; i < this.cantAristas; i++) {
			int fil = scan.nextInt();
			int col = scan.nextInt();
			gradosNodos[fil]++;
			gradosNodos[col]++;
			// Obtengo el indice que deberia tener basandome en la fila y columan del mismo
			int indice = this.grafo.getIndice(fil, col);
			this.grafo.ponerArista(indice);
		}

		// Por ultimo, creo los N nodos y les seteo el grado correspondiente
		for (int i = 0; i < this.cantNodos; i++) {
			Nodo nodo = new Nodo(i);
			nodo.setGrado(this.gradosNodos[i]);
			this.nodos.add(nodo);
		}

		// Para finalizar, cierro el stream del archivo
		scan.close();
	}

	/**
	 * Metodo encargado de obtener la cantidad minima de colores necesarios para
	 * colorear mi grafo
	 */
	private void colorear() {
		// Inicializo las variables
		int color = 1, nodo, indice;
		this.colorMax = 1;

		// Arranco mi array de nodos con color en 0
		// Despues voy a ir completandolo
		for (int i = 0; i < this.cantNodos; i++) {
			this.nodosColoreados[i] = 0;
		}

		// Siempre arranco coloreando el primer nodo que tengo
		nodosColoreados[this.nodos.get(0).getNroNodo()] = color;

		// Ahora comienzo a hacer todo el procesamiento
		// El primer nodo ya lo coloree, asi que lo salteo
		for (int i = 1; i < this.cantNodos; i++) {
			// Obtengo el valor del nodo (que en parte tambien es el indice)
			nodo = this.nodos.get(i).getNroNodo();
			this.nodosColoreados[nodo] = color;
			int j = 0;

			// Voy recorriendo todos los nodos
			while (j < this.cantNodos) {
				// Si no es mi propio nodo,
				if (nodo != j) {

					// Segun el sentido, obtendre el indice
					// Es decir, transformo del par filas y columnas al indice en el vector
					if (nodo < j) {
						indice = this.grafo.getIndice(nodo, j);
					} else {
						indice = this.grafo.getIndice(j, nodo);
					}

					// Una vez realizo lo anterior, ya puedo consultar si tengo una arista alli.
					if (this.grafo.hayArista(indice) && this.nodosColoreados[nodo] == this.nodosColoreados[j]) {
						color++;
						if (color > colorMax) {
							colorMax = color;
						}
						this.nodosColoreados[nodo] = color;
						// Arranco de nuevo
						j = -1;
					}
				}
				j++;
			}

			// Vuelvo a arrancar la vuelta de color
			color = 1;
		}
	}

	/**
	 * El orden en que se colorean los vértices se decide antes de que se empiece a
	 * colorearlos. Dada una ordenación de los vértices del grafo, los algoritmos
	 * secuenciales asignan el mínimo color posible al siguiente vértice. Es decir,
	 * si queremos colorear V, teniendo ordenados numéricamente los colores,
	 * asignamos a V el color más pequeño que no aparece entre los asignados a los
	 * vecinos de V ya coloreados.
	 * 
	 * @param cantEjecuciones
	 * @throws IOException
	 */
	public void coloreoSecuencialAleatorio(int cantEjecuciones) throws IOException {
		int nroCorrida = 0;
		long tiempoInicial = 0, tiempoFinal = 0;

		// Si no tengo nodos, debo salir. No hay nada que evaluar.
		if (this.nodos.size() == 0) {
			this.escribirSolucion("ALGORITMO_SECUENCIAL");
			return;
		}

		for (int i = 0; i < cantEjecuciones; i++) {
			// Permuto los nodos
			Collections.shuffle(this.nodos);
			
			tiempoInicial = System.nanoTime();
			// Llamo al nodo que asignara los colores
			this.colorear();
			tiempoFinal = System.nanoTime();

			if (this.colorMax < this.mejorColor || this.mejorColor == 0) {
				// Seteo la cantidad de colores (siempre estoy buscando la menor posible)
				this.mejorColor = this.colorMax;
				// Me guardo en que ejecucion estoy, ESTO SERA UTIL PARA EL ANALISIS ESTADISTICO
				nroCorrida = i + 1;
				// Me guardo la solucion, para mostrar como quedaria
				this.solucion = this.nodosColoreados.clone();
			}

			// En este vector, voy seteando en cada indice lo que mas se repite
			// Sirve para el analisis estadistico
			this.mejoresColores[this.colorMax - 1]++;
		}

		// Paso a la ultima etapa, donde escribo el archivo
		this.escribirSolucion("ALGORITMO_SECUENCIAL");
		System.out.print("SECUENCIAL\t");
		System.out.println("Menor cantidad de colores: " + this.mejorColor + " en la iteración: " + nroCorrida + " ("
				+ String.valueOf(tiempoFinal - tiempoInicial) + " ns.)");

		// Antes de continuar con otro metodo, resulta menester limpiar las distintas
		// variables
		// Las mismas deberian ser locales para no tener que hacer esto
		// TODO si queda tiempo
		this.mejorColor = 0;
		for (int i = 0; i < this.cantNodos; i++) {
			this.mejoresColores[i] = 0;
		}
	}

	/**
	 * Es una variante del algoritmo de coloración secuencial básico, también
	 * conocida como "Primero el de mayor grado". Es debida a Welsh y Powell, y en
	 * este algoritmo, los vértices se ordenan inicialmente de acuerdo a sus grados.
	 * Es decir, ordenamos de forma que d(V1) >= d(V2) >= ... >= d(Vn).
	 * 
	 * @param cantEjecuciones
	 * @throws IOException
	 */
	public void coloreoWelshPowell(int cantEjecuciones) throws IOException {
		int nroCorrida = 0;
		long tiempoInicial = 0, tiempoFinal = 0;
		// Si no tengo nodos, debo salir. No hay nada que evaluar.
		if (this.nodos.size() == 0) {
			this.escribirSolucion("ALGORITMO_WELSH_POWELL");
			return;
		}

		for (int i = 0; i < cantEjecuciones; i++) {
			// Ordeno los nodos de menor a mayor
			Collections.sort(this.nodos, new Comparator<Nodo>() {
				@Override
				public int compare(Nodo nodo1, Nodo nodo2) {
					return nodo2.getGrado() - nodo1.getGrado();
				}
			});

			tiempoInicial = System.nanoTime();
			// Llamo al nodo que asignara los colores
			this.colorear();
			tiempoFinal = System.nanoTime();

			if (this.colorMax < this.mejorColor || this.mejorColor == 0) {
				// Seteo la cantidad de colores (siempre estoy buscando la menor posible)
				this.mejorColor = this.colorMax;
				// Me guardo en que ejecucion estoy, ESTO SERA UTIL PARA EL ANALISIS ESTADISTICO
				nroCorrida = i + 1;
				// Me guardo la solucion, para mostrar como quedaria
				this.solucion = this.nodosColoreados.clone();
			}

			// En este vector, voy seteando en cada indice lo que mas se repite
			// Sirve para el analisis estadistico
			this.mejoresColores[this.colorMax - 1]++;
		}

		// Paso a la ultima etapa, donde escribo el archivo
		this.escribirSolucion("ALGORITMO_WELSH_POWELL");
		System.out.print("WELSH_POWELL\t");
		System.out.println("Menor cantidad de colores: " + this.mejorColor + " en la iteración: " + nroCorrida + " ("
				+ String.valueOf(tiempoFinal - tiempoInicial) + " ns.)");

		// Antes de continuar con otro metodo, resulta menester limpiar las distintas
		// variables
		// Las mismas deberian ser locales para no tener que hacer esto
		// TODO si queda tiempo
		this.mejorColor = 0;
		for (int i = 0; i < this.cantNodos; i++) {
			this.mejoresColores[i] = 0;
		}
	}

	/**
	 * Es una variante del algoritmo de coloración secuencial básico, también
	 * conocida como "El de menor grado el último". Se debe a Marble, Matula e
	 * Isaacson, y en este algoritmo, los vértices se ordenan en orden inverso.
	 * Primero se elige vn como el vértice de menor grado, luego se elige Vn-1 como
	 * el vértice de menor grado en G-{Vn}, y así se continúa recursivamente,
	 * examinando los vértices de menor grado y eliminándolos del grafo
	 * 
	 * @param cantEjecuciones
	 * @throws IOException
	 */
	public void coloreoMatula(int cantEjecuciones) throws IOException {
		int nroCorrida = 0;
		long tiempoInicial = 0, tiempoFinal = 0;

		// Si no tengo nodos, debo salir. No hay nada que evaluar.
		if (this.nodos.size() == 0) {
			this.escribirSolucion("ALGORITMO_MATULA_MARBLE_ISAACSON");
			return;
		}

		for (int i = 0; i < cantEjecuciones; i++) {
			// Ordeno los nodos de mayor a menor
			Collections.sort(this.nodos, new Comparator<Nodo>() {
				@Override
				public int compare(Nodo nodo1, Nodo nodo2) {
					return nodo1.getGrado() - nodo2.getGrado();
				}
			});
			
			tiempoInicial = System.nanoTime();
			// Llamo al nodo que asignara los colores
			this.colorear();
			tiempoFinal = System.nanoTime();

			if (this.colorMax < this.mejorColor || this.mejorColor == 0) {
				// Seteo la cantidad de colores (siempre estoy buscando la menor posible)
				this.mejorColor = this.colorMax;
				// Me guardo en que ejecucion estoy, ESTO SERA UTIL PARA EL ANALISIS ESTADISTICO
				nroCorrida = i + 1;
				// Me guardo la solucion, para mostrar como quedaria
				this.solucion = this.nodosColoreados.clone();
			}

			// En este vector, voy seteando en cada indice lo que mas se repite
			// Sirve para el analisis estadistico
			this.mejoresColores[this.colorMax - 1]++;
		}

		// Paso a la ultima etapa, donde escribo el archivo
		this.escribirSolucion("ALGORITMO_MATULA_MARBLE_ISAACSON");
		System.out.print("MATULA_MARBLE_ISAACSON\t");
		System.out.println("Menor cantidad de colores: " + this.mejorColor + " en la iteración: " + nroCorrida + " ("
				+ String.valueOf(tiempoFinal - tiempoInicial) + " ns.)");

		// Antes de continuar con otro metodo, resulta menester limpiar las distintas
		// variables
		// Las mismas deberian ser locales para no tener que hacer esto
		// TODO si queda tiempo
		this.mejorColor = 0;
		for (int i = 0; i < this.cantNodos; i++) {
			this.mejoresColores[i] = 0;
		}
	}

	/**
	 * 
	 * @param algoritmoElegido
	 * @throws IOException
	 */
	private void escribirSolucion(String algoritmoElegido) throws IOException {
		FileWriter file = new FileWriter(Main.PATH_SALIDA_GRAFOS_COLOREADOS + "COLOREO" + "_" + algoritmoElegido + "_" + this.cantNodos
				+ "_" + String.format("%.2f", this.porcentajeAdyacencia) + ".out");
		BufferedWriter buffer = new BufferedWriter(file);

		buffer.write(String.valueOf(this.cantNodos));
		buffer.write(" ");
		buffer.write(String.valueOf(this.mejorColor));
		buffer.write(" ");
		buffer.write(String.valueOf(this.cantAristas));
		buffer.write(" ");
		buffer.write(String.valueOf(NumberFormat.getInstance().format(this.porcentajeAdyacencia)));
		buffer.write(" ");
		buffer.write(String.valueOf(this.gradoMax));
		buffer.write(" ");
		buffer.write(String.valueOf(this.gradoMin));
		buffer.newLine();

		for (int i = 0; i < this.solucion.length; i++) {
			buffer.write(String.valueOf(i));
			buffer.write(" ");
			buffer.write(String.valueOf(this.solucion[i]));
			buffer.newLine();
		}

		buffer.close();
	}

}
