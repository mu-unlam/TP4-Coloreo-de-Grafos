package com.unlam.TP4;

public class ColoreadorGrafos {
	/**
	 * El orden en que se colorean los vértices se decide antes de que se empiece a
	 * colorearlos. Dada una ordenación de los vértices del grafo, los algoritmos
	 * secuenciales asignan el mínimo color posible al siguiente vértice. Es decir,
	 * si queremos colorear V, teniendo ordenados numéricamente los colores,
	 * asignamos a V el color más pequeño que no aparece entre los asignados a los
	 * vecinos de V ya coloreados.
	 * 
	 * @param cantEjecuciones
	 */
	public void coloreoSecuencialAleatorio(int cantEjecuciones) {
	}

	/**
	 * Es una variante del algoritmo de coloración secuencial básico, también
	 * conocida como "Primero el de mayor grado". Es debida a Welsh y Powell, y en
	 * este algoritmo, los vértices se ordenan inicialmente de acuerdo a sus grados.
	 * Es decir, ordenamos de forma que d(V1) >= d(V2) >= ... >= d(Vn).
	 * 
	 * @param cantEjecuciones
	 */
	public void coloreoWelshPowell(int cantEjecuciones) {
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
	 */
	public void coloreoMatula(int cantEjecuciones) {
	}

}
