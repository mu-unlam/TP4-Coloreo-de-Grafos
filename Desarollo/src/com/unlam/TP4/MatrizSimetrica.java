package com.unlam.TP4;

public class MatrizSimetrica {

	// Almacena las aristas
	// Y si hay o no camino en cada uno

	private boolean[] matrizSimetrica;
	private int ordenMatriz;
	private int dimensionVector;

	/**
	 * Constructor de la matriz simetrica
	 * 
	 * @param n Orden de la matriz
	 */
	public MatrizSimetrica(int n) {
		this.ordenMatriz = n;
		// La dimension del vector
		// Por ejemplo, si tengo 6 nodos, N=6
		// y la dimension será 15
		// Esto se puede verificar con un grafico
		this.dimensionVector = (n * n - n) / 2;

		// Creo mi objecto vector de boolean con la dimension calculada
		matrizSimetrica = new boolean[this.dimensionVector];

		// Inicializo todo en falso, excepto la ultima
		for (int i = 0; i < n - 1; i++)
			matrizSimetrica[i] = false;
	}

	// Agrego la arista y le pongo el valor true.
	public void ponerArista(int pos) {
		matrizSimetrica[pos] = true;
	}

	public boolean hayArista(int pos) {
		return matrizSimetrica[pos];
	}

	/**
	 * Dada una fila y columna, obtengo el indice. Esto luego lo uso para saber si
	 * hay arista o no
	 * 
	 * @param fil
	 * @param col
	 * @return
	 */
	public int getIndice(int fil, int col) {
		return (int) (fil * this.ordenMatriz + col - (Math.pow(fil, 2) + 3 * fil + 2) / 2);
	}

	/**
	 * Setea el indice
	 * 
	 * @param fil
	 * @param col
	 */
	public void setIndice(int fil, int col) {
		matrizSimetrica[fil * this.ordenMatriz + col - (fil * fil + 3 * fil + 2) / 2] = true;
	}

	public void mostrar() {
		for (int i = 0; i < this.dimensionVector; i++) {
			if (this.matrizSimetrica[i])
				System.out.print(1 + " ");
			else
				System.out.print(0 + " ");
		}

	}

	public int getOrdenMatriz() {
		return this.ordenMatriz;
	}

	public int getDimension() {
		return this.dimensionVector;
	}
}